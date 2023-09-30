package ejemplodao;

import Dao.imp.PersonaDao;
import Modelo.Persona;
import java.io.File;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 * Integrantes: Angeles Piña Haziel 
 * Arroyo Hernández Irving Antonio 
 * Cruz San Nicolás Brayan 
 * Castro Ramírez Itzel Jenifer
 * Jiménez Granillo Tania Dyane
 * Rodríguez Bustamante Jorge Alberto Filias
 */

public class EjemploDao {

    public static void main(String[] args) {
        PersonaDao personaDao = new PersonaDao();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        do {
            mostrarMenu();

            int opc = sc.nextInt();
            sc.nextLine(); // Limpiar salto de línea

            try {
                switch (opc) {
                    case 1 -> personaDao.imprimirTodo();
                    //muestra la ruta completa del archivo si este existe
                    // System.out.println("ubicacion del archivo: "+ new File("Personas.dat").getAbsolutePath());
                    case 2 -> registrarPersona(personaDao, sc);
                    case 3 -> eliminarPersona(personaDao, sc);
                    case 4 -> actualizarPersona(personaDao, sc);
                    case 5 -> consultarPersona(personaDao, sc);
                    case 6 -> salir = true;
                    default -> System.out.println("Opción no válida. Por favor, elija una opción válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un valor válido.");
                sc.nextLine(); // Limpiar el búfer de entrada
            }
        } while (!salir);

        System.out.println("FIN");
        sc.close(); //
    }
    //menus y otros metodos para trabajar con la consola por teclado

    private static void mostrarMenu() {
        System.out.println("----------------------------------------");
        System.out.println("            Menu                        ");
        System.out.println("----------------------------------------");
        System.out.println("Que movimiento desea hacer");
        System.out.println("1.- Consultar todo");
        System.out.println("2.- Registrar persona");
        System.out.println("3.- Eliminar persona");
        System.out.println("4.- Actualizar persona");
        System.out.println("5.- Consultar persona");
        System.out.println("6.- salir");
        System.out.println("Elija una opcion:");
    }

 private static void registrarPersona(PersonaDao personaDao, Scanner sc) {
    boolean agregarOtra = true;
    do {
        try {
            System.out.println("Ingrese el ID de la persona:");
            int id = sc.nextInt();
            sc.nextLine(); // Limpiar salto de línea
            System.out.println("Ingrese el nuevo nombre de la persona:");
            String nombre = sc.nextLine();

            System.out.println("Ingrese la nueva edad de la persona:");
            int edad = sc.nextInt();
            sc.nextLine(); // Limpiar el salto de línea pendiente

            Persona persona = new Persona(id, nombre, edad);

            // Llama al método crear de PersonaDao
            personaDao.crear(persona);

            System.out.println("¿Desea registrar otra persona? (S/N):");
            String respuesta = sc.nextLine().trim().toUpperCase();
            if (!respuesta.equals("S")) {
                agregarOtra = false;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un valor válido.");
            sc.nextLine(); // Limpiar el búfer de entrada
        }
    } while (agregarOtra);
}

   private static void eliminarPersona(PersonaDao personaDao, Scanner sc) {
    boolean eliminarOtra = true;
    do {
        try {
            System.out.println("Ingrese el ID de la persona que desea eliminar:");
            int id = sc.nextInt();
            sc.nextLine(); // Limpiar el salto de línea
            
            // Llama al método eliminar de PersonaDao
            personaDao.eliminar(id);

            System.out.println("¿Desea eliminar otra persona? (S/N):");
            String respuesta = sc.nextLine().trim().toUpperCase();
            if (!respuesta.equals("S")) {
                eliminarOtra = false;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un valor válido.");
            sc.nextLine(); // Limpiar el búfer de entrada
        }
    } while (eliminarOtra);
}

private static void actualizarPersona(PersonaDao personaDao, Scanner sc) {
    try {
        System.out.println("Ingrese el ID de la persona que desea actualizar:");
        int idActualizar = sc.nextInt();
        sc.nextLine(); // Limpiar el salto de línea pendiente

        Persona personaExistente = personaDao.consultar(idActualizar);

        if (personaExistente != null) {
            System.out.println("Persona a actualizar:");
            personaDao.imprimirPersona(personaExistente);

            System.out.println("Ingrese el nuevo ID de la persona:");
            int nuevoId = sc.nextInt();
            sc.nextLine(); // Limpiar el salto de línea

            System.out.println("Ingrese el nuevo nombre de la persona:");
            String nuevoNombre = sc.nextLine();

            System.out.println("Ingrese la nueva edad de la persona:");
            int nuevaEdad = sc.nextInt();
            sc.nextLine(); // Limpiar el salto de línea 

            // Crear una nueva persona con los nuevos valores y nuevo ID
            Persona nuevaPersona = new Persona(nuevoId, nuevoNombre, nuevaEdad);

            // Llama al método actualizar de PersonaDao
            personaDao.actualizar(idActualizar, nuevaPersona);
        } else {
            System.out.println("No se encontró ninguna persona con el ID: " + idActualizar);
        }
    } catch (InputMismatchException e) {
        System.out.println("Error: Ingrese un valor válido.");
        sc.nextLine(); // Limpiar el búfer de entrada
    }
}


    

    private static void consultarPersona(PersonaDao personaDao, Scanner sc) {
        boolean buscarOtra = true;
        do {
            mostrarMenuConsultarPersona();
            int op = sc.nextInt();
            sc.nextLine(); // Limpiar salto de línea

            switch (op) {
                case 1:
                    consultarPorId(personaDao, sc);
                   
                    break;
                case 2:
                    consultarPorCriterio(personaDao, sc);
                    break;
                case 3:
                    buscarOtra = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
            }
        } while (buscarOtra);
    }

    private static void mostrarMenuConsultarPersona() {
        System.out.println("----------------------------------------");
        System.out.println("         Menú Consultar Persona         ");
        System.out.println("----------------------------------------");
        System.out.println("1.- Consultar por ID");
        System.out.println("2.- Consultar por criterio");
        System.out.println("3.- Regresar al menú principal");
        System.out.println("Elija una opción:");
    }

    private static void consultarPorId(PersonaDao personaDao, Scanner sc) {
    try {
        System.out.println("Ingrese el id de la persona a consultar:");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar salto de línea

        Persona personaConsultada = personaDao.consultar(id);

        if (personaConsultada != null) {
            System.out.println("-------------------------------------------");
            System.out.println("Persona consultada por ID:");
            System.out.println("-------------------------------------------");
            personaDao.imprimirPersona(personaConsultada);
        } else {
            System.out.println("No se encontró ninguna persona con el ID: " + id);
        }
    } catch (InputMismatchException e) {
        System.out.println("Error: Ingrese un valor válido.");
        sc.nextLine(); // Limpiar el búfer de entrada
    }
}


    private static void consultarPorCriterio(PersonaDao personaDao, Scanner sc) {
        System.out.println("Ingrese el criterio a consultar:");
        String criterioBusqueda = sc.nextLine();
        //llama al metodo consultar
        List<Persona> resultados = personaDao.buscar(criterioBusqueda);
        System.out.println("-------------------------------------------");
        System.out.println("Personas que contienen '" + criterioBusqueda + "' en el nombre:");
        System.out.println("-------------------------------------------");
        for (Persona persona : resultados) {
            personaDao.imprimirPersona(persona);
        }
    }

   
}
