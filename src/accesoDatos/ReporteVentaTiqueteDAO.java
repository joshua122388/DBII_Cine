package accesoDatos;

import entidades.ReporteVentaTiquete;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteVentaTiqueteDAO {

    public void insertar(ReporteVentaTiquete reporte) throws Exception {
        String sql = "INSERT INTO reporte_venta_tiquete (Fecha, ID_Cliente, ID_Funcion, Cantidad, Total) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setDate(1, reporte.getFecha());
            stmt.setInt(2, reporte.getIdCliente());
            stmt.setInt(3, reporte.getIdFuncion());
            stmt.setInt(4, reporte.getCantidad());
            stmt.setDouble(5, reporte.getTotal());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        reporte.setIdReporteTiquete(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public void actualizar(ReporteVentaTiquete reporte) throws Exception {
        String sql = "UPDATE reporte_venta_tiquete SET Fecha=?, ID_Cliente=?, ID_Funcion=?, Cantidad=?, Total=? WHERE ID_Reporte_Tiquete=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, reporte.getFecha());
            stmt.setInt(2, reporte.getIdCliente());
            stmt.setInt(3, reporte.getIdFuncion());
            stmt.setInt(4, reporte.getCantidad());
            stmt.setDouble(5, reporte.getTotal());
            stmt.setInt(6, reporte.getIdReporteTiquete());

            stmt.executeUpdate();
        }
    }

    public void eliminar(int idReporteTiquete) throws Exception {
        String sql = "DELETE FROM reporte_venta_tiquete WHERE ID_Reporte_Tiquete=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReporteTiquete);
            stmt.executeUpdate();
        }
    }

    public ReporteVentaTiquete buscarPorID(int idReporteTiquete) throws Exception {
        String sql = "SELECT * FROM reporte_venta_tiquete WHERE ID_Reporte_Tiquete=?";
        ReporteVentaTiquete reporte = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReporteTiquete);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                reporte = new ReporteVentaTiquete(
                    rs.getInt("ID_Reporte_Tiquete"),
                    rs.getDate("Fecha"),
                    rs.getInt("ID_Cliente"),
                    rs.getInt("ID_Funcion"),
                    rs.getInt("Cantidad"),
                    rs.getDouble("Total")
                );
            }
        }
        return reporte;
    }

    public List<ReporteVentaTiquete> listarTodos() throws Exception {
        List<ReporteVentaTiquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM reporte_venta_tiquete";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ReporteVentaTiquete reporte = new ReporteVentaTiquete(
                    rs.getInt("ID_Reporte_Tiquete"),
                    rs.getDate("Fecha"),
                    rs.getInt("ID_Cliente"),
                    rs.getInt("ID_Funcion"),
                    rs.getInt("Cantidad"),
                    rs.getDouble("Total")
                );
                lista.add(reporte);
            }
        }
        return lista;
    }
}
