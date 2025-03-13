package entidades;

public class VendedorTiquetes {
    private int badgeVendedor; // Clave primaria generada automáticamente
    private String nombre;
    private String apellido;

    // Constructor vacío (para select y otros procesos)
    public VendedorTiquetes() {}

    // Constructor sin ID (para insert)
    public VendedorTiquetes(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Constructor con ID (para select y delete)
    public VendedorTiquetes(int badgeVendedor, String nombre, String apellido) {
        this.badgeVendedor = badgeVendedor;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Getters y Setters
    public int getBadgeVendedor() { return badgeVendedor; }
    public void setBadgeVendedor(int badgeVendedor) { this.badgeVendedor = badgeVendedor; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    // Método toString() para imprimir la información del vendedor
    @Override
    public String toString() {
        return "VendedorTiquetes{" +
               "badgeVendedor=" + badgeVendedor +
               ", nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               '}';
    }
}
