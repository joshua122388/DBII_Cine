package entidades;

import java.sql.Date;

public class ReporteVentaTiquete {
    private int idReporteTiquete;
    private Date fecha;
    private int idCliente;
    private int idFuncion;
    private int cantidad;
    private double total;

    public ReporteVentaTiquete() {}

    public ReporteVentaTiquete(Date fecha, int idCliente, int idFuncion, int cantidad, double total) {
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idFuncion = idFuncion;
        this.cantidad = cantidad;
        this.total = total;
    }

    public ReporteVentaTiquete(int idReporteTiquete, Date fecha, int idCliente, int idFuncion, int cantidad, double total) {
        this.idReporteTiquete = idReporteTiquete;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idFuncion = idFuncion;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getIdReporteTiquete() { return idReporteTiquete; }
    public void setIdReporteTiquete(int idReporteTiquete) { this.idReporteTiquete = idReporteTiquete; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdFuncion() { return idFuncion; }
    public void setIdFuncion(int idFuncion) { this.idFuncion = idFuncion; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "ReporteVentaTiquete{" +
               "idReporteTiquete=" + idReporteTiquete +
               ", fecha=" + fecha +
               ", idCliente=" + idCliente +
               ", idFuncion=" + idFuncion +
               ", cantidad=" + cantidad +
               ", total=" + total +
               '}';
    }
}
