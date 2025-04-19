package accesoDatos;

import entidades.Pelicula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO {

    public void insertar(Pelicula pelicula) throws Exception {
        String sql = "INSERT INTO pelicula (Titulo, Genero, Clasificacion, Sinopsis) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getGenero());
            stmt.setString(3, pelicula.getClasificacion());
            stmt.setString(4, pelicula.getSinopsis());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        pelicula.setIdPelicula(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public void actualizar(Pelicula pelicula) throws Exception {
        String sql = "UPDATE pelicula SET Titulo=?, Genero=?, Clasificacion=?, Sinopsis=? WHERE ID_Pelicula=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getGenero());
            stmt.setString(3, pelicula.getClasificacion());
            stmt.setString(4, pelicula.getSinopsis());
            stmt.setInt(5, pelicula.getIdPelicula());

            stmt.executeUpdate();
        }
    }

    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM pelicula WHERE ID_Pelicula=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Pelicula buscarPorID(int id) throws Exception {
        String sql = "SELECT * FROM pelicula WHERE ID_Pelicula=?";
        Pelicula pelicula = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pelicula = new Pelicula(
                    rs.getInt("ID_Pelicula"),
                    rs.getString("Titulo"),
                    rs.getString("Genero"),
                    rs.getString("Clasificacion"),
                    rs.getString("Sinopsis")
                );
            }
        }
        return pelicula;
    }

    public List<Pelicula> listarTodos() throws Exception {
        List<Pelicula> lista = new ArrayList<>();
        String sql = "SELECT * FROM pelicula";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pelicula pelicula = new Pelicula(
                    rs.getInt("ID_Pelicula"),
                    rs.getString("Titulo"),
                    rs.getString("Genero"),
                    rs.getString("Clasificacion"),
                    rs.getString("Sinopsis")
                );
                lista.add(pelicula);
            }
        }
        return lista;
    }
}
