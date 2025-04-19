// Paquete donde se encuentra la clase
package accesoDatos;

// Importación de la entidad Cliente
import entidades.Cliente;

// Importaciones necesarias para la conexión y manejo de SQL Server
import java.sql.Connection;        // Representa la conexión con la base de datos
import java.sql.PreparedStatement; // Permite ejecutar consultas SQL parametrizadas
import java.sql.ResultSet;         // Maneja el resultado de consultas SELECT
import java.sql.SQLException;      // Maneja excepciones de SQL
import java.util.ArrayList;        // Permite almacenar clientes en una lista
import java.util.List;             // Interfaz utilizada para manejar la lista de clientes

// Clase que maneja las operaciones de la base de datos relacionadas con la entidad Cliente
public class ClienteDAO {

    /**
     * Método para obtener todos los clientes de la base de datos.
     * @return Una lista de objetos Cliente con los datos obtenidos de la base de datos.
     */
    public List<Cliente> obtenerClientes() {
        // Lista donde se almacenarán los clientes obtenidos de la base de datos
        List<Cliente> clientes = new ArrayList<>();

        // Consulta SQL para obtener todos los clientes con sus respectivos campos
        String query = "SELECT ID_Cliente, Nombre, Apellido, Correo, Telefono, ID_Membresia FROM cliente";

        // Bloque try-with-resources para gestionar automáticamente la conexión y los recursos SQL
        try (Connection conn = ConexionSQL.conectar();  // Se establece la conexión con la base de datos
             PreparedStatement stmt = conn.prepareStatement(query); // Se prepara la consulta SQL
             ResultSet rs = stmt.executeQuery()) { // Se ejecuta la consulta y se obtiene el resultado

            // Itera sobre cada fila del resultado de la consulta
            while (rs.next()) {
                // Se crea un objeto Cliente con los valores obtenidos de la base de datos
                Cliente cliente = new Cliente(
                    rs.getInt("ID_Cliente"),            // Obtiene el ID del cliente
                    rs.getString("Nombre"),             // Obtiene el nombre del cliente
                    rs.getString("Apellido"),           // Obtiene el apellido del cliente
                    rs.getString("Correo"),             // Obtiene el correo del cliente
                    rs.getString("Telefono"),           // Obtiene el teléfono del cliente
                    rs.getObject("ID_Membresia") != null ? rs.getInt("ID_Membresia") : null // condición ? valor_si_verdadero : valor_si_falso;
                        /* 
                        se usa getObject ya que le da mejor tratamiento a los null
                        es decir getObject va a devolver null en lugar de 0, si se usara getInt se obtendria un 0 lo cual se podria mal interpretar
                        */
                    // condición ? valor_si_verdadero : valor_si_falso

                );
                // Se agrega el cliente a la lista
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            // Captura y muestra cualquier error de SQL
            System.out.println("Error al obtener clientes: " + e.getMessage());
        }
        // Retorna la lista de clientes obtenidos
        return clientes;
    }

    /**
     * Método para insertar un nuevo cliente en la base de datos.
     * @param nombre Nombre del cliente.
     * @param apellido Apellido del cliente.
     * @param correo Correo electrónico del cliente.
     * @param telefono Número de teléfono del cliente.
     * @param idMembresia ID de la membresía asociada (puede ser null si el cliente no tiene membresía).
     */
    public void insertarCliente(String nombre, String apellido, String correo, String telefono, Integer idMembresia) {
        // Consulta SQL para insertar un nuevo cliente en la base de datos
        String query = "INSERT INTO cliente (Nombre, Apellido, Correo, Telefono, ID_Membresia) VALUES (?, ?, ?, ?, ?)";

        // Bloque try-with-resources para manejar la conexión y la consulta
        try (Connection conn = ConexionSQL.conectar();  // Se establece la conexión con la base de datos
             PreparedStatement stmt = conn.prepareStatement(query)) { // Se prepara la consulta SQL

            // Se establecen los valores de los parámetros en la consulta SQL
            stmt.setString(1, nombre);  // Asigna el nombre del cliente
            stmt.setString(2, apellido); // Asigna el apellido del cliente
            stmt.setString(3, correo);  // Asigna el correo del cliente
            stmt.setString(4, telefono); // Asigna el teléfono del cliente
            
            // Si el cliente tiene una membresía, se asigna su ID; de lo contrario, se inserta un valor NULL
            if (idMembresia != null) {
                stmt.setInt(5, idMembresia);
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }

            // Se ejecuta la consulta SQL para insertar el cliente en la base de datos
            stmt.executeUpdate();
            System.out.println("Cliente insertado correctamente.");
            
        } catch (SQLException e) {
            // Captura y muestra cualquier error de SQL
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }
}
