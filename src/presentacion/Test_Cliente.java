package presentacion;

import accesoDatos.ClienteDAO;
import entidades.Cliente;
import java.util.List;
import accesoDatos.ConexionSQL;

public class Test_Cliente {
    public static void main(String[] args) {
        // Crear una instancia de ClienteDAO
        ClienteDAO dao = new ClienteDAO();

        try {
            
            //verificar conexion a SQL Server
            ConexionSQL con = new ConexionSQL();
            con.conectar();
            
            // Crear e insertar un cliente con éxito (sin ID, ya que se genera automáticamente)
            Cliente cliente = new Cliente(0, "Josh", "Palacios", "josh@email.com", "555-1234", 2);
            dao.insertar(cliente);
            System.out.println("Cliente insertado correctamente.");

            // Obtener y mostrar todos los clientes registrados
            System.out.println("\nLista de clientes actualizada:");
            List<Cliente> clientes = dao.listarTodos();

            for (Cliente c : clientes) {
                System.out.println(c.toString());
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
