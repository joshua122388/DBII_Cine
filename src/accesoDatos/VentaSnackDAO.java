package accesoDatos;

import entidades.VentaSnack;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaSnackDAO {

    public void insertar(VentaSnack venta) throws Exception {
        String sql = "INSERT INTO venta_snack (Fecha, Hora, Total, Cantidad, Badge_Empleado_Snacks, Codigo_Productos) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setDate(1, venta.getFecha());
            stmt.setTime(2, venta.getHora());
            stmt.setDouble(3, venta.getTotal());
            stmt.setInt(4, venta.getCantidad());
            stmt.setInt(5, venta.getBadgeEmpleadoSnacks());
            stmt.setString(6, venta.getCodigoProductos());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        venta.setIdVenta(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public void actualizar(VentaSnack venta) throws Exception {
        String sql = "UPDATE venta_snack SET Fecha=?, Hora=?, Total=?, Cantidad=?, Badge_Empleado_Snacks=?, Codigo_Productos=? WHERE ID_Venta=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, venta.getFecha());
            stmt.setTime(2, venta.getHora());
            stmt.setDouble(3, venta.getTotal());
            stmt.setInt(4, venta.getCantidad());
            stmt.setInt(5, venta.getBadgeEmpleadoSnacks());
            stmt.setString(6, venta.getCodigoProductos());
            stmt.setInt(7, venta.getIdVenta());

            stmt.executeUpdate();
        }
    }

    public void eliminar(int idVenta) throws Exception {
        String sql = "DELETE FROM venta_snack WHERE ID_Venta=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVenta);
            stmt.executeUpdate();
        }
    }

    public VentaSnack buscarPorID(int idVenta) throws Exception {
        String sql = "SELECT * FROM venta_snack WHERE ID_Venta=?";
        VentaSnack venta = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVenta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                venta = new VentaSnack(
                    rs.getInt("ID_Venta"),
                    rs.getDate("Fecha"),
                    rs.getTime("Hora"),
                    rs.getDouble("Total"),
                    rs.getInt("Cantidad"),
                    rs.getInt("Badge_Empleado_Snacks"),
                    rs.getString("Codigo_Productos")
                );
            }
        }
        return venta;
    }

    public List<VentaSnack> listarTodos() throws Exception {
        List<VentaSnack> lista = new ArrayList<>();
        String sql = "SELECT * FROM venta_snack";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VentaSnack venta = new VentaSnack(
                    rs.getInt("ID_Venta"),
                    rs.getDate("Fecha"),
                    rs.getTime("Hora"),
                    rs.getDouble("Total"),
                    rs.getInt("Cantidad"),
                    rs.getInt("Badge_Empleado_Snacks"),
                    rs.getString("Codigo_Productos")
                );
                lista.add(venta);
            }
        }
        return lista;
    }
}
