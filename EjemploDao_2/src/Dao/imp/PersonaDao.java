package Dao.imp;

import Dao.PersonaDaoInterface;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Persona;
import java.util.Optional;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDao implements PersonaDaoInterface {

    private List<Persona> personas;

    public PersonaDao() {
        // Intenta consultar la lista de personas desde el archivo
        personas = leer();

        // Si la lista es nula (primera ejecución o archivo no existe), crea una nueva lista
        if (personas == null) {
            personas = new ArrayList<>();
        }
    }

    private static final Logger logger = Logger.getLogger(PersonaDao.class.getName());
   


    public boolean existePersona(int id) {
        return personas.stream().anyMatch(persona -> persona.getId() == id);
    }

    // Métodos del CRUD
    //Crear
    @Override
    public void crear(Persona persona) {
        if (!existePersona(persona.getId())) {
            personas.add(persona);
            escribir();
        } else {
            logger.log(Level.INFO, "Ya existe una persona con el mismo ID.");
        }
    }

    // Leer en la lista de personas por ID
    @Override
    public Persona consultar(int id) {
        Optional<Persona> personaConsulta = personas.stream()
                .filter(persona -> persona.getId() == id)
                .findFirst();

            if (personaConsulta.isPresent()) {
        Persona personaEncontrada = personaConsulta.get();
        
        return personaEncontrada; // Devuelve la persona encontrada
    } else {
        logger.log(Level.WARNING, "No se encontró la persona (ID: {0})", id);
        return null; // Devuelve null si no se encontró la persona
    }
}
     

    // Método para actualizar una persona
  
  @Override
public void actualizar(int id, Persona nuevaPersona) {
        if (existePersona(id)) {
            if (!existePersona(nuevaPersona.getId())) {
                for (int i = 0; i < personas.size(); i++) {
                    if (personas.get(i).getId() == id) {
                        personas.set(i, nuevaPersona);
                        escribir(); // Llama al método escribir para guardar los cambios en el archivo
                        logger.log(Level.INFO, "Persona con ID {0} actualizada con éxito.", id);
                        return;
                    }
                }
            } else {
                logger.log(Level.INFO, "Ya existe una persona con el mismo ID.");
            }
        } else {
            logger.log(Level.INFO, "La persona con ID {0} no existe.", id);
        }
    }




    // Método para eliminar una persona por ID
    @Override
   public void eliminar(int id) {
    Persona personaAEliminar = consultar(id);

    if (personaAEliminar != null) {
        personas.remove(personaAEliminar);
        logger.log(Level.INFO, "Persona eliminada con éxito (ID: {0})", id);
    } else {
        logger.log(Level.WARNING, "No se encontró la persona para eliminar (ID: {0})", id);
    }
}


    // Otros métodos
    // Método para buscar por criterio
    @Override
    public List<Persona> buscar(String criterio) {
        List<Persona> resultados = new ArrayList<>();
        boolean coincidenciaEncontrada = false;

        if (personas == null || personas.isEmpty()) {
            logger.log(Level.INFO, "La lista de personas está vacía.");
        } else {
            try {
                for (Persona persona : personas) {
                    if (persona.getNombre().toLowerCase().contains(criterio.toLowerCase())) {
                        resultados.add(persona);
                        coincidenciaEncontrada = true;
                    }
                }
            } catch (NullPointerException e) {
                System.out.println();
                logger.log(Level.INFO, "El archivo Personas.dat no existe o está vacío.", e.getMessage());
            }

            if (!coincidenciaEncontrada) {
                System.out.println();
                logger.log(Level.INFO, "El criterio no coincide con ningún nombre en la lista.");
            }
        }

        return resultados;
    }

    // Método para imprimir la lista
    @Override
    public void imprimirPersona(Persona persona) {
        if (persona != null) {
            System.out.println("ID: " + persona.getId());
            System.out.println("Nombre: " + persona.getNombre());
            System.out.println("Edad: " + persona.getEdad());
            System.out.println("------");
        } else {
            logger.log(Level.SEVERE, "La persona es nula.");
        }
    }

    @Override
    public void imprimirTodo() {
        for (Persona persona : personas) {
            imprimirPersona(persona); // Llama al método imprimirPersona para imprimir cada persona
        }
    }


    // Métodos para la serialización y deserialización del archivo
    public void escribir() {
        try {
            // Define el archivo donde se guardarán los datos del objeto
            FileOutputStream archivo = new FileOutputStream("Personas.txt");

            try ( // Crea el objeto de flujo de salida para la escritura del objeto
                     ObjectOutputStream salida = new ObjectOutputStream(archivo)) {
                // Escribe el objeto en el flujo de salida
                salida.writeObject(personas);
                // Cierra el flujo de salida al finalizar el bloque
            }
        } catch (IOException e) {
             // Captura y muestra información detallada sobre el error
            logger.log(Level.SEVERE, "Error al escribir el archivo: {0}", e.getMessage());
        }
    }

    public final List<Persona> leer() {
        File archivo = new File("Personas.txt");

        if (archivo.exists()) {
            try {
                // Define el archivo a leer
                FileInputStream archivoEntrada = new FileInputStream(archivo);
                // Crea el objeto de flujo de entrada para la lectura del objeto
                ObjectInputStream entrada = new ObjectInputStream(archivoEntrada);
                // Lee el objeto en el flujo de entrada
                List<Persona> listaPersonas = (List<Persona>) entrada.readObject();
                return listaPersonas;
                // Cierra el flujo de entrada de datos en atomatico al cerra el bloque 

            } catch (IOException | ClassNotFoundException e) {
              // Captura y muestra información detallada sobre el error
                logger.log(Level.SEVERE, "Error al leer el archivo: {0}", e.getMessage());
            }
        }

        return null;
    }
}
