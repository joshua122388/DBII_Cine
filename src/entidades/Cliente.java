package entidades;

// Definimos la clase Cliente que representa a un cliente en la base de datos
public class Cliente {
    // Atributos privados que representan las columnas de la tabla cliente
    private int idCliente;        // Identificador único del cliente (Debe coincidir con la BD)
    private String nombre;        // Nombre del cliente
    private String apellido;      // Apellido del cliente
    private String correo;        // Correo electrónico del cliente
    private String telefono;      // Número de teléfono del cliente
    private Integer idMembresia;  // ID de la membresía asociada (Puede ser NULL)

    // Constructor vacío (Importante para ciertas operaciones como SELECT)
    public Cliente() {}

    // Constructor con parámetros
    public Cliente(int idCliente, String nombre, String apellido, String correo, String telefono, Integer idMembresia) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.idMembresia = idMembresia;
    }

    // Getters y Setters

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Integer getIdMembresia() { return idMembresia; }
    public void setIdMembresia(Integer idMembresia) { this.idMembresia = idMembresia; }

    // Método toString() para imprimir información del cliente
    @Override
    public String toString() {
        return "Cliente{" +
               "idCliente=" + idCliente +
               ", nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               ", correo='" + correo + '\'' +
               ", telefono='" + (telefono != null ? telefono : "N/A") + '\'' +
               ", idMembresia=" + (idMembresia != null ? idMembresia : "Sin membresía") +
               '}';
    }
}
