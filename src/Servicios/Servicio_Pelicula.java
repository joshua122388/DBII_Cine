package Servicios;

import entidades.Pelicula;
import java.util.List;

public interface Servicio_Pelicula {
    void insertar(Pelicula pelicula) throws Exception;
    void actualizar(Pelicula pelicula) throws Exception;
    void eliminar(int id) throws Exception;
    Pelicula buscarPorID(int id) throws Exception;
    List<Pelicula> listarTodos() throws Exception;
}
