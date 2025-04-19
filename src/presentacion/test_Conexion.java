package presentacion;

// prueba

import accesoDatos.ConexionSQL; // se importa la capa de acceso a datos que realiza la conexion a la base de datos

public class test_Conexion {
    public static void main(String[] args) { // El código debe ir dentro del método main
        ConexionSQL.conectar(); // Llamada correcta al método estático
    }
}
