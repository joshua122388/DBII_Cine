package entidades;

import java.sql.Date;

public class CompraTiquete {
    private int idCompra;
    private int idCliente;
    private Date fechaCompra;
    private int cantidad;

    public CompraTiquete() {}

    public CompraTiquete(int idCliente, Date fechaCompra, int cantidad) {
        this.idCliente = idCliente;
        this.fechaCompra = fechaCompra;
        this.cantidad = cantidad;
    }

    public CompraTiquete(int idCompra, int idCliente, Date fechaCompra, int cantidad) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.fechaCompra = fechaCompra;
        this.cantidad = cantidad;
    }

    public int getIdCompra() { return idCompra; }
    public void setIdCompra(int idCompra) { this.idCompra = idCompra; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public Date getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(Date fechaCompra) { this.fechaCompra = fechaCompra; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return "CompraTiquete{" +
               "idCompra=" + idCompra +
               ", idCliente=" + idCliente +
               ", fechaCompra=" + fechaCompra +
               ", cantidad=" + cantidad +
               '}';
    }
}
