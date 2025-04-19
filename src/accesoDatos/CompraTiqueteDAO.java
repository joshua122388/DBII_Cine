package accesoDatos;

import entidades.CompraTiquete;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraTiqueteDAO {

    public void insertar(CompraTiquete compra) throws Exception {
        String sql = "INSERT INTO compra_tiquete (ID_Cliente, Fecha_Compra, Cantidad) VALUES (?, ?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, compra.getIdCliente());
            stmt.setDate(2, compra.getFechaCompra());
            stmt.setInt(3, compra.getCantidad());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        compra.setIdCompra(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public void actualizar(CompraTiquete compra) throws Exception {
        String sql = "UPDATE compra_tiquete SET ID_Cliente=?, Fecha_Compra=?, Cantidad=? WHERE ID_Compra=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, compra.getIdCliente());
            stmt.setDate(2, compra.getFechaCompra());
            stmt.setInt(3, compra.getCantidad());
            stmt.setInt(4, compra.getIdCompra());

            stmt.executeUpdate();
        }
    }

    public void eliminar(int idCompra) throws Exception {
        String sql = "DELETE FROM compra_tiquete WHERE ID_Compra=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCompra);
            stmt.executeUpdate();
        }
    }

    public CompraTiquete buscarPorID(int idCompra) throws Exception {
        String sql = "SELECT * FROM compra_tiquete WHERE ID_Compra=?";
        CompraTiquete compra = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCompra);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                compra = new CompraTiquete(
                    rs.getInt("ID_Compra"),
                    rs.getInt("ID_Cliente"),
                    rs.getDate("Fecha_Compra"),
                    rs.getInt("Cantidad")
                );
            }
        }
        return compra;
    }

    public List<CompraTiquete> listarTodos() throws Exception {
        List<CompraTiquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM compra_tiquete";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CompraTiquete compra = new CompraTiquete(
                    rs.getInt("ID_Compra"),
                    rs.getInt("ID_Cliente"),
                    rs.getDate("Fecha_Compra"),
                    rs.getInt("Cantidad")
                );
                lista.add(compra);
            }
        }
        return lista;
    }
}
