package entidades;

public class Membresia {
    private int idMembresia;
    private String tipo;
    private double descuento;

    // Constructor vacío
    public Membresia() {}

    // Constructor con parámetros
    public Membresia(int idMembresia, String tipo, double descuento) {
        this.idMembresia = idMembresia;
        this.tipo = tipo;
        this.descuento = descuento;
    }

    // Getters y Setters
    public int getIdMembresia() { return idMembresia; }
    public void setIdMembresia(int idMembresia) { this.idMembresia = idMembresia; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }

    @Override
    public String toString() {
        return "Membresia{" + "id=" + idMembresia + ", tipo='" + tipo + "', descuento=" + descuento + "%}";
    }
}
