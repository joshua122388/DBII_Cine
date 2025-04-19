package accesoDatos;

import entidades.Membresia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import accesoDatos.ConexionSQL;

public class MembresiaDAO {
    
    // Método para insertar una nueva membresía
    public void insertar(Membresia membresia) throws Exception {
        String sql = "INSERT INTO membresia (Tipo, Descuento) VALUES (?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, membresia.getTipo());
            stmt.setDouble(2, membresia.getDescuento());

            int filasAfectadas = stmt.executeUpdate();

            // Obtener el ID generado
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        membresia.setIdMembresia(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    // Método para actualizar una membresía existente
    public void actualizar(Membresia membresia) throws Exception {
        String sql = "UPDATE membresia SET Tipo=?, Descuento=? WHERE ID_Membresia=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, membresia.getTipo());
            stmt.setDouble(2, membresia.getDescuento());
            stmt.setInt(3, membresia.getIdMembresia());

            stmt.executeUpdate();
        }
    }

    // Método para eliminar una membresía por ID
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM membresia WHERE ID_Membresia=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para buscar una membresía por ID
    public Membresia buscarPorID(int id) throws Exception {
        String sql = "SELECT * FROM membresia WHERE ID_Membresia=?";
        Membresia membresia = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                membresia = new Membresia(
                    rs.getInt("ID_Membresia"),
                    rs.getString("Tipo"),
                    rs.getDouble("Descuento")
                );
            }
        }
        return membresia;
    }

    // Método para listar todas las membresías
    public List<Membresia> listarTodos() throws Exception {
        List<Membresia> lista = new ArrayList<>();
        String sql = "SELECT * FROM membresia";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Membresia membresia = new Membresia(
                    rs.getInt("ID_Membresia"),
                    rs.getString("Tipo"),
                    rs.getDouble("Descuento")
                );
                lista.add(membresia);
            }
        }
        return lista;
    }
}
