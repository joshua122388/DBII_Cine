package accesoDatos;

import entidades.EmpleadoSnacks;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoSnacksDAO {

    public void insertar(EmpleadoSnacks empleado) throws Exception {
        String sql = "INSERT INTO empleado_snacks (Nombre, Apellido) VALUES (?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        empleado.setBadgeEmpleadoSnacks(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public void actualizar(EmpleadoSnacks empleado) throws Exception {
        String sql = "UPDATE empleado_snacks SET Nombre=?, Apellido=? WHERE Badge_Empleado_Snacks=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setInt(3, empleado.getBadgeEmpleadoSnacks());

            stmt.executeUpdate();
        }
    }

    public void eliminar(int badgeEmpleadoSnacks) throws Exception {
        String sql = "DELETE FROM empleado_snacks WHERE Badge_Empleado_Snacks=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, badgeEmpleadoSnacks);
            stmt.executeUpdate();
        }
    }

    public EmpleadoSnacks buscarPorID(int badgeEmpleadoSnacks) throws Exception {
        String sql = "SELECT * FROM empleado_snacks WHERE Badge_Empleado_Snacks=?";
        EmpleadoSnacks empleado = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, badgeEmpleadoSnacks);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                empleado = new EmpleadoSnacks(
                    rs.getInt("Badge_Empleado_Snacks"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido")
                );
            }
        }
        return empleado;
    }

    public List<EmpleadoSnacks> listarTodos() throws Exception {
        List<EmpleadoSnacks> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado_snacks";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmpleadoSnacks empleado = new EmpleadoSnacks(
                    rs.getInt("Badge_Empleado_Snacks"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido")
                );
                lista.add(empleado);
            }
        }
        return lista;
    }
}
