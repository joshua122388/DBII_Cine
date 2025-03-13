// Paquete donde se encuentra la clase
package presentacion;

// Importaciones necesarias
import accesoDatos.ClienteDAO;  // Importa la clase ClienteDAO que maneja la base de datos
import entidades.Cliente;       // Importa la entidad cliente
import java.util.List;          // Importa la interfaz List para manejar colecciones de clientes

// Clase principal que actúa como punto de entrada para la ejecución del programa
public class MainApp {
    public static void main(String[] args) {
        // Crea una instancia de ClienteDAO para interactuar con la base de datos
        ClienteDAO dao = new ClienteDAO();

        // Insertar con resultado exitoso
        //dao.insertarCliente("Josh", "Palacios", "jPal.23@gmail.com", "555-1234", 2);
     dao.insertarCliente("Alex", "Perez", "Alex.Perez@gmail.com", "555-1234", 2); // datos de prueba clase

        // Insertar con resultado fallido (valores nulos en la insercion)
        // dao.insertarCliente("Patricia", "Rodriguez", "patricia.rod@gmail.com", null, null); // el telefono no puede ser nulo
        dao.insertarCliente("Josh", "Palacios", "jPal.23@gmail.com", null, null);
        
        // Obtener y mostrar todos los clientes registrados en la base de datos
        System.out.println("\nLista de clientes actualizada:");
        List<Cliente> clientes = dao.obtenerClientes(); // Llama al método para obtener los clientes

        // Itera sobre la lista de clientes y muestra su información en consola
        for (Cliente c : clientes) {
            c.mostrarInfo();
        }
    }
}
