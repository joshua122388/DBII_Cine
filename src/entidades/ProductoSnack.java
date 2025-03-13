package entidades;

public class ProductoSnack {
    private String codigoProducto; // Clave primaria
    private String nombre;
    private double precio;

    // Constructor vacío (para select y otros procesos)
    public ProductoSnack() {}

    // Constructor con todos los campos (para insert y update)
    public ProductoSnack(String codigoProducto, String nombre, double precio) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters
    public String getCodigoProducto() { return codigoProducto; }
    public void setCodigoProducto(String codigoProducto) { this.codigoProducto = codigoProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    // Método toString() para imprimir la información del producto
    @Override
    public String toString() {
        return "ProductoSnack{" +
               "codigoProducto='" + codigoProducto + '\'' +
               ", nombre='" + nombre + '\'' +
               ", precio=" + precio +
               '}';
    }
}
