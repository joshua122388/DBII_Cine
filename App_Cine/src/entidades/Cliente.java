// Definimos el paquete en el que se encuentra la clase
package entidades;

// Definimos la clase Cliente que representa a un cliente en la base de datos
public class Cliente {
    // Atributos privados que representan las columnas de la tabla cliente
    private int id;              // Identificador único del cliente
    private String nombre;       // Nombre del cliente
    private String apellido;     // Apellido del cliente
    private String correo;       // Correo electrónico del cliente
    private String telefono;     // Número de teléfono del cliente
    private Integer idMembresia; // ID de la membresía asociada

    // Constructor con todos los campos
    public Cliente(int id, String nombre, String apellido, String correo, String telefono, Integer idMembresia) {
        this.id = id;                    // Asigna el ID del cliente
        this.nombre = nombre;            // Asigna el nombre del cliente
        this.apellido = apellido;        // Asigna el apellido del cliente
        this.correo = correo;            // Asigna el correo electrónico del cliente
        this.telefono = telefono;        // Asigna el número de teléfono del cliente
        this.idMembresia = idMembresia;  // Asigna el ID de la membresía (puede ser NULL)
    }

    //Métodos Getters y Setters para acceder y modificar los atributos

    // Obtiene el ID del cliente
    public int getId() { return id; }
    // Modifica el ID del cliente
    public void setId(int id) { this.id = id; }
    
    // Obtiene el nombre del cliente
    public String getNombre() { return nombre; }
    // Modifica el nombre del cliente
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    // Obtiene el apellido del cliente
    public String getApellido() { return apellido; }
    // Modifica el apellido del cliente
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    // Obtiene el correo del cliente
    public String getCorreo() { return correo; }
    // Modifica el correo del cliente
    public void setCorreo(String correo) { this.correo = correo; }

    // Obtiene el número de teléfono del cliente
    public String getTelefono() { return telefono; }
    // Modifica el número de teléfono del cliente
    public void setTelefono(String telefono) { this.telefono = telefono; }

    // Obtiene el ID de la membresía (puede ser NULL)
    public Integer getIdMembresia() { return idMembresia; }
    // Modifica el ID de la membresía
    public void setIdMembresia(Integer idMembresia) { this.idMembresia = idMembresia; }

    //Método para mostrar la información del cliente en la consola
    public void mostrarInfo() {
        System.out.println("Cliente: " + nombre + " " + apellido + " - " + correo + 
                           " - Teléfono: " + (telefono != null ? telefono : "N/A") +   // Si el teléfono es NULL, muestra "N/A"
                           " - Membresía: " + (idMembresia != null ? idMembresia : "Sin membresía")); // Si ID_Membresía es NULL, muestra "Sin membresía"
    }
}
