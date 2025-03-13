package accesoDatos;

import entidades.Funcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionDAO {

    public void insertar(Funcion funcion) throws Exception {
        String sql = "INSERT INTO funcion (Fecha, Hora_Inicio, Hora_Fin, ID_Pelicula, Numero_Sala) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setDate(1, funcion.getFecha());
            stmt.setTime(2, funcion.getHoraInicio());
            stmt.setTime(3, funcion.getHoraFin());
            stmt.setInt(4, funcion.getIdPelicula());
            stmt.setInt(5, funcion.getNumeroSala());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        funcion.setIdFuncion(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public void actualizar(Funcion funcion) throws Exception {
        String sql = "UPDATE funcion SET Fecha=?, Hora_Inicio=?, Hora_Fin=?, ID_Pelicula=?, Numero_Sala=? WHERE ID_Funcion=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, funcion.getFecha());
            stmt.setTime(2, funcion.getHoraInicio());
            stmt.setTime(3, funcion.getHoraFin());
            stmt.setInt(4, funcion.getIdPelicula());
            stmt.setInt(5, funcion.getNumeroSala());
            stmt.setInt(6, funcion.getIdFuncion());

            stmt.executeUpdate();
        }
    }

    public void eliminar(int idFuncion) throws Exception {
        String sql = "DELETE FROM funcion WHERE ID_Funcion=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncion);
            stmt.executeUpdate();
        }
    }

    public Funcion buscarPorID(int idFuncion) throws Exception {
        String sql = "SELECT * FROM funcion WHERE ID_Funcion=?";
        Funcion funcion = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncion);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                funcion = new Funcion(
                    rs.getInt("ID_Funcion"),
                    rs.getDate("Fecha"),
                    rs.getTime("Hora_Inicio"),
                    rs.getTime("Hora_Fin"),
                    rs.getInt("ID_Pelicula"),
                    rs.getInt("Numero_Sala")
                );
            }
        }
        return funcion;
    }

    public List<Funcion> listarTodos() throws Exception {
        List<Funcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcion";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcion funcion = new Funcion(
                    rs.getInt("ID_Funcion"),
                    rs.getDate("Fecha"),
                    rs.getTime("Hora_Inicio"),
                    rs.getTime("Hora_Fin"),
                    rs.getInt("ID_Pelicula"),
                    rs.getInt("Numero_Sala")
                );
                lista.add(funcion);
            }
        }
        return lista;
    }
}
