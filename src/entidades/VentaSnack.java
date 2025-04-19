package entidades;

import java.sql.Date;
import java.sql.Time;

public class VentaSnack {
    private int idVenta;
    private Date fecha;
    private Time hora;
    private double total;
    private int cantidad;
    private int badgeEmpleadoSnacks;
    private String codigoProductos;

    public VentaSnack() {}

    public VentaSnack(Date fecha, Time hora, double total, int cantidad, int badgeEmpleadoSnacks, String codigoProductos) {
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
        this.cantidad = cantidad;
        this.badgeEmpleadoSnacks = badgeEmpleadoSnacks;
        this.codigoProductos = codigoProductos;
    }

    public VentaSnack(int idVenta, Date fecha, Time hora, double total, int cantidad, int badgeEmpleadoSnacks, String codigoProductos) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
        this.cantidad = cantidad;
        this.badgeEmpleadoSnacks = badgeEmpleadoSnacks;
        this.codigoProductos = codigoProductos;
    }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Time getHora() { return hora; }
    public void setHora(Time hora) { this.hora = hora; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getBadgeEmpleadoSnacks() { return badgeEmpleadoSnacks; }
    public void setBadgeEmpleadoSnacks(int badgeEmpleadoSnacks) { this.badgeEmpleadoSnacks = badgeEmpleadoSnacks; }

    public String getCodigoProductos() { return codigoProductos; }
    public void setCodigoProductos(String codigoProductos) { this.codigoProductos = codigoProductos; }

    @Override
    public String toString() {
        return "VentaSnack{" +
               "idVenta=" + idVenta +
               ", fecha=" + fecha +
               ", hora=" + hora +
               ", total=" + total +
               ", cantidad=" + cantidad +
               ", badgeEmpleadoSnacks=" + badgeEmpleadoSnacks +
               ", codigoProductos='" + codigoProductos + '\'' +
               '}';
    }
}
