package accesoDatos;

import entidades.Tiquete;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TiqueteDAO {

    public void insertar(Tiquete tiquete) throws Exception {
        String sql = "INSERT INTO tiquete (Asiento, Precio, Cantidad, ID_Cliente, Badge_Vendedor, ID_Funcion) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, tiquete.getAsiento());
            stmt.setDouble(2, tiquete.getPrecio());
            stmt.setInt(3, tiquete.getCantidad());
            stmt.setInt(4, tiquete.getIdCliente());
            stmt.setInt(5, tiquete.getBadgeVendedor());
            stmt.setInt(6, tiquete.getIdFuncion());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        tiquete.setIdBoleto(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public void actualizar(Tiquete tiquete) throws Exception {
        String sql = "UPDATE tiquete SET Asiento=?, Precio=?, Cantidad=?, ID_Cliente=?, Badge_Vendedor=?, ID_Funcion=? WHERE ID_Boleto=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tiquete.getAsiento());
            stmt.setDouble(2, tiquete.getPrecio());
            stmt.setInt(3, tiquete.getCantidad());
            stmt.setInt(4, tiquete.getIdCliente());
            stmt.setInt(5, tiquete.getBadgeVendedor());
            stmt.setInt(6, tiquete.getIdFuncion());
            stmt.setInt(7, tiquete.getIdBoleto());

            stmt.executeUpdate();
        }
    }

    public void eliminar(int idBoleto) throws Exception {
        String sql = "DELETE FROM tiquete WHERE ID_Boleto=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idBoleto);
            stmt.executeUpdate();
        }
    }

    public Tiquete buscarPorID(int idBoleto) throws Exception {
        String sql = "SELECT * FROM tiquete WHERE ID_Boleto=?";
        Tiquete tiquete = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idBoleto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tiquete = new Tiquete(
                    rs.getInt("ID_Boleto"),
                    rs.getString("Asiento"),
                    rs.getDouble("Precio"),
                    rs.getInt("Cantidad"),
                    rs.getInt("ID_Cliente"),
                    rs.getInt("Badge_Vendedor"),
                    rs.getInt("ID_Funcion")
                );
            }
        }
        return tiquete;
    }

    public List<Tiquete> listarTodos() throws Exception {
        List<Tiquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM tiquete";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tiquete tiquete = new Tiquete(
                    rs.getInt("ID_Boleto"),
                    rs.getString("Asiento"),
                    rs.getDouble("Precio"),
                    rs.getInt("Cantidad"),
                    rs.getInt("ID_Cliente"),
                    rs.getInt("Badge_Vendedor"),
                    rs.getInt("ID_Funcion")
                );
                lista.add(tiquete);
            }
        }
        return lista;
    }
}
