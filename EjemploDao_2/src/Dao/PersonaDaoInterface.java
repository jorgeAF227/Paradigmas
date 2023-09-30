package Dao;

import Modelo.Persona;
import java.util.List;

public interface PersonaDaoInterface {

    void crear(Persona persona);

    Persona consultar(int id);

    void actualizar(int id, Persona nuevaPersona);

    void eliminar(int id);

    List<Persona> buscar(String criterio);

    public void imprimirTodo();

    public void imprimirPersona(Persona persona);

}
