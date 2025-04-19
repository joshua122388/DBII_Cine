

package accesoDatos;
import entidades.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // INSERT
    public void insertar(Cliente cliente) throws Exception {
        String sql = "INSERT INTO cliente (Nombre, Apellido, Correo, Telefono, ID_Membresia) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getCorreo());
            stmt.setString(4, cliente.getTelefono());

            if (cliente.getIdMembresia() != null) {
                stmt.setInt(5, cliente.getIdMembresia());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setIdCliente(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    // UPDATE
    public void actualizar(Cliente cliente) throws Exception {
        String sql = "UPDATE cliente SET Nombre=?, Apellido=?, Correo=?, Telefono=?, ID_Membresia=? WHERE ID_Cliente=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getCorreo());
            stmt.setString(4, cliente.getTelefono());

            if (cliente.getIdMembresia() != null) {
                stmt.setInt(5, cliente.getIdMembresia());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            stmt.setInt(6, cliente.getIdCliente());

            stmt.executeUpdate();
        }
    }

    // DELETE
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM cliente WHERE ID_Cliente=?";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // SELECT by ID
    public Cliente buscarPorID(int id) throws Exception {
        String sql = "SELECT * FROM cliente WHERE ID_Cliente=?";
        Cliente cliente = null;

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                    rs.getInt("ID_Cliente"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getString("Correo"),
                    rs.getString("Telefono"),
                    rs.getObject("ID_Membresia") != null ? rs.getInt("ID_Membresia") : null
                );
            }
        }
        return cliente;
    }

    // SELECT ALL
    public List<Cliente> listarTodos() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("ID_Cliente"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getString("Correo"),
                    rs.getString("Telefono"),
                    rs.getObject("ID_Membresia") != null ? rs.getInt("ID_Membresia") : null
                );
                lista.add(cliente);
            }
        }
        return lista;
    }
}
