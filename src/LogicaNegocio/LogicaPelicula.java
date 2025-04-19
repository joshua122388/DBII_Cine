package LogicaNegocio;

import Servicios.Servicio_Pelicula;
import accesoDatos.PeliculaDAO;
import entidades.Pelicula;
import java.util.List;

public class LogicaPelicula implements Servicio_Pelicula {
    private PeliculaDAO peliculaDAO = new PeliculaDAO();

    @Override
    public void insertar(Pelicula pelicula) throws Exception {
        if (pelicula.getTitulo() == null || pelicula.getTitulo().trim().isEmpty()) {
            throw new Exception("El título de la película no puede estar vacío.");
        }
        if (pelicula.getGenero() == null || pelicula.getGenero().trim().isEmpty()) {
            throw new Exception("El género de la película no puede estar vacío.");
        }
        if (pelicula.getClasificacion() == null || pelicula.getClasificacion().trim().isEmpty()) {
            throw new Exception("La clasificación de la película no puede estar vacía.");
        }

        peliculaDAO.insertar(pelicula);
    }

    @Override
    public void actualizar(Pelicula pelicula) throws Exception {
        if (pelicula.getIdPelicula() <= 0) {
            throw new Exception("ID de película inválido.");
        }
        if (pelicula.getTitulo() == null || pelicula.getTitulo().trim().isEmpty()) {
            throw new Exception("El título de la película no puede estar vacío.");
        }

        peliculaDAO.actualizar(pelicula);
    }

    @Override
    public void eliminar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID de película inválido.");
        }
        peliculaDAO.eliminar(id);
    }

    @Override
    public Pelicula buscarPorID(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID de película inválido.");
        }
        return peliculaDAO.buscarPorID(id);
    }

    @Override
    public List<Pelicula> listarTodos() throws Exception {
        return peliculaDAO.listarTodos();
    }
}
