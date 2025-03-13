package accesoDatos;

import entidades.VendedorTiquetes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendedorTiquetesDAO {

    // INSERT
    public void insertar(VendedorTiquetes vendedor) throws Exception {
        String sql = "INSERT INTO vendedor_tiquetes (Nombre, Apellido) VALUES (?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, vendedor.getNombre());
            stmt.setString(2, vendedor.getApellido());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        vendedor.setBadgeVendedor(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    // UPDATE
    public void actualizar(VendedorTiquetes vendedor) throws Exception {
        String sql = "UPDATE vendedor_tiquetes SET Nombre=?, Apellido=? WHERE Badge_Vendedor=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vendedor.getNombre());
            stmt.setString(2, vendedor.getApellido());
            stmt.setInt(3, vendedor.getBadgeVendedor());

            stmt.executeUpdate();
        }
    }

    // DELETE
    public void eliminar(int badgeVendedor) throws Exception {
        String sql = "DELETE FROM vendedor_tiquetes WHERE Badge_Vendedor=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, badgeVendedor);
            stmt.executeUpdate();
        }
    }

    // SELECT by ID
    public VendedorTiquetes buscarPorID(int badgeVendedor) throws Exception {
        String sql = "SELECT * FROM vendedor_tiquetes WHERE Badge_Vendedor=?";
        VendedorTiquetes vendedor = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, badgeVendedor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vendedor = new VendedorTiquetes(
                    rs.getInt("Badge_Vendedor"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido")
                );
            }
        }
        return vendedor;
    }

    // SELECT ALL
    public List<VendedorTiquetes> listarTodos() throws Exception {
        List<VendedorTiquetes> lista = new ArrayList<>();
        String sql = "SELECT * FROM vendedor_tiquetes";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VendedorTiquetes vendedor = new VendedorTiquetes(
                    rs.getInt("Badge_Vendedor"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido")
                );
                lista.add(vendedor);
            }
        }
        return lista;
    }
}
