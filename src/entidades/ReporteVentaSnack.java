package entidades;

import java.sql.Date;

public class ReporteVentaSnack {
    private int idReporteSnack;
    private Date fecha;
    private int idVenta;
    private int badgeEmpleadoSnacks;
    private double total;

    public ReporteVentaSnack() {}

    public ReporteVentaSnack(Date fecha, int idVenta, int badgeEmpleadoSnacks, double total) {
        this.fecha = fecha;
        this.idVenta = idVenta;
        this.badgeEmpleadoSnacks = badgeEmpleadoSnacks;
        this.total = total;
    }

    public ReporteVentaSnack(int idReporteSnack, Date fecha, int idVenta, int badgeEmpleadoSnacks, double total) {
        this.idReporteSnack = idReporteSnack;
        this.fecha = fecha;
        this.idVenta = idVenta;
        this.badgeEmpleadoSnacks = badgeEmpleadoSnacks;
        this.total = total;
    }

    public int getIdReporteSnack() { return idReporteSnack; }
    public void setIdReporteSnack(int idReporteSnack) { this.idReporteSnack = idReporteSnack; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public int getBadgeEmpleadoSnacks() { return badgeEmpleadoSnacks; }
    public void setBadgeEmpleadoSnacks(int badgeEmpleadoSnacks) { this.badgeEmpleadoSnacks = badgeEmpleadoSnacks; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "ReporteVentaSnack{" +
               "idReporteSnack=" + idReporteSnack +
               ", fecha=" + fecha +
               ", idVenta=" + idVenta +
               ", badgeEmpleadoSnacks=" + badgeEmpleadoSnacks +
               ", total=" + total +
               '}';
    }
}
