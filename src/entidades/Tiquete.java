package entidades;

public class Tiquete {
    private int idBoleto;
    private String asiento;
    private double precio;
    private int cantidad;
    private int idCliente;
    private int badgeVendedor;
    private int idFuncion;

    public Tiquete() {}

    public Tiquete(String asiento, double precio, int cantidad, int idCliente, int badgeVendedor, int idFuncion) {
        this.asiento = asiento;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idCliente = idCliente;
        this.badgeVendedor = badgeVendedor;
        this.idFuncion = idFuncion;
    }

    public Tiquete(int idBoleto, String asiento, double precio, int cantidad, int idCliente, int badgeVendedor, int idFuncion) {
        this.idBoleto = idBoleto;
        this.asiento = asiento;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idCliente = idCliente;
        this.badgeVendedor = badgeVendedor;
        this.idFuncion = idFuncion;
    }

    public int getIdBoleto() { return idBoleto; }
    public void setIdBoleto(int idBoleto) { this.idBoleto = idBoleto; }

    public String getAsiento() { return asiento; }
    public void setAsiento(String asiento) { this.asiento = asiento; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getBadgeVendedor() { return badgeVendedor; }
    public void setBadgeVendedor(int badgeVendedor) { this.badgeVendedor = badgeVendedor; }

    public int getIdFuncion() { return idFuncion; }
    public void setIdFuncion(int idFuncion) { this.idFuncion = idFuncion; }

    @Override
    public String toString() {
        return "Tiquete{" +
               "idBoleto=" + idBoleto +
               ", asiento='" + asiento + '\'' +
               ", precio=" + precio +
               ", cantidad=" + cantidad +
               ", idCliente=" + idCliente +
               ", badgeVendedor=" + badgeVendedor +
               ", idFuncion=" + idFuncion +
               '}';
    }
}
