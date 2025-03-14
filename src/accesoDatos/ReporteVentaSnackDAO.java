package accesoDatos;

import entidades.ReporteVentaSnack;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteVentaSnackDAO {

    public void insertar(ReporteVentaSnack reporte) throws Exception {
        String sql = "INSERT INTO reporte_venta_snack (Fecha, ID_Venta, Badge_Empleado_Snacks, Total) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setDate(1, reporte.getFecha());
            stmt.setInt(2, reporte.getIdVenta());
            stmt.setInt(3, reporte.getBadgeEmpleadoSnacks());
            stmt.setDouble(4, reporte.getTotal());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        reporte.setIdReporteSnack(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public void actualizar(ReporteVentaSnack reporte) throws Exception {
        String sql = "UPDATE reporte_venta_snack SET Fecha=?, ID_Venta=?, Badge_Empleado_Snacks=?, Total=? WHERE ID_Reporte_Snack=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, reporte.getFecha());
            stmt.setInt(2, reporte.getIdVenta());
            stmt.setInt(3, reporte.getBadgeEmpleadoSnacks());
            stmt.setDouble(4, reporte.getTotal());
            stmt.setInt(5, reporte.getIdReporteSnack());

            stmt.executeUpdate();
        }
    }

    public void eliminar(int idReporteSnack) throws Exception {
        String sql = "DELETE FROM reporte_venta_snack WHERE ID_Reporte_Snack=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReporteSnack);
            stmt.executeUpdate();
        }
    }

    public ReporteVentaSnack buscarPorID(int idReporteSnack) throws Exception {
        String sql = "SELECT * FROM reporte_venta_snack WHERE ID_Reporte_Snack=?";
        ReporteVentaSnack reporte = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReporteSnack);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                reporte = new ReporteVentaSnack(
                    rs.getInt("ID_Reporte_Snack"),
                    rs.getDate("Fecha"),
                    rs.getInt("ID_Venta"),
                    rs.getInt("Badge_Empleado_Snacks"),
                    rs.getDouble("Total")
                );
            }
        }
        return reporte;
    }

    public List<ReporteVentaSnack> listarTodos() throws Exception {
        List<ReporteVentaSnack> lista = new ArrayList<>();
        String sql = "SELECT * FROM reporte_venta_snack";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ReporteVentaSnack reporte = new ReporteVentaSnack(
                    rs.getInt("ID_Reporte_Snack"),
                    rs.getDate("Fecha"),
                    rs.getInt("ID_Venta"),
                    rs.getInt("Badge_Empleado_Snacks"),
                    rs.getDouble("Total")
                );
                lista.add(reporte);
            }
        }
        return lista;
    }
}
