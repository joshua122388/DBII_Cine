package accesoDatos;

import entidades.Sala;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {

    // INSERT
    public void insertar(Sala sala) throws Exception {
        String sql = "INSERT INTO sala (Capacidad, Tipo) VALUES (?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, sala.getCapacidad());
            stmt.setString(2, sala.getTipo());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        sala.setNumeroSala(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    // UPDATE
    public void actualizar(Sala sala) throws Exception {
        String sql = "UPDATE sala SET Capacidad=?, Tipo=? WHERE Numero_Sala=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sala.getCapacidad());
            stmt.setString(2, sala.getTipo());
            stmt.setInt(3, sala.getNumeroSala());

            stmt.executeUpdate();
        }
    }

    // DELETE
    public void eliminar(int numeroSala) throws Exception {
        String sql = "DELETE FROM sala WHERE Numero_Sala=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroSala);
            stmt.executeUpdate();
        }
    }

    // SELECT by ID
    public Sala buscarPorID(int numeroSala) throws Exception {
        String sql = "SELECT * FROM sala WHERE Numero_Sala=?";
        Sala sala = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroSala);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sala = new Sala(
                    rs.getInt("Numero_Sala"),
                    rs.getInt("Capacidad"),
                    rs.getString("Tipo")
                );
            }
        }
        return sala;
    }

    // SELECT ALL
    public List<Sala> listarTodos() throws Exception {
        List<Sala> lista = new ArrayList<>();
        String sql = "SELECT * FROM sala";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Sala sala = new Sala(
                    rs.getInt("Numero_Sala"),
                    rs.getInt("Capacidad"),
                    rs.getString("Tipo")
                );
                lista.add(sala);
            }
        }
        return lista;
    }
}
