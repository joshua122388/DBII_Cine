// Paquete donde se encuentra la clase
package accesoDatos;

// Importaciones necesarias para manejar la conexión con SQL Server
import java.sql.Connection;      // Representa la conexión con la base de datos
import java.sql.DriverManager;  // Permite obtener la conexión con SQL Server
import java.sql.SQLException;   // Maneja errores de SQL

// Clase para manejar la conexión con SQL Server
public class ConexionSQL {
    
    // URL de conexión a SQL Server
    // Contiene los datos necesarios para conectarse a la base de datos
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=cine;user=sa;password=P@ssword#2026;encrypt=false;trustServerCertificate=true";

    /**
     * Método estático para establecer la conexión con la base de datos.
     * @return Devuelve un objeto `Connection` si la conexión es exitosa, de lo contrario devuelve `null`.
     */
    public static Connection conectar() {
        try {
            // Se establece la conexión usando la URL definida anteriormente
            Connection conn = DriverManager.getConnection(URL);
            
            // Mensaje de éxito si la conexión se establece correctamente
            System.out.println("Conexión a SQL Server establecida.");
            
            // Se retorna la conexión activa
            return conn;
        } catch (SQLException e) {
            // Si hay un error, se captura y se muestra un mensaje en consola
            System.out.println("Error de conexión: " + e.getMessage());
            
            // Se retorna `null` para indicar que no se pudo conectar
            return null;
        }
    }
}
