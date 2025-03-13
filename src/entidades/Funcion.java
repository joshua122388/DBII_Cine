package entidades;

import java.sql.Date;
import java.sql.Time;

public class Funcion {
    private int idFuncion;
    private Date fecha;
    private Time horaInicio;
    private Time horaFin;
    private int idPelicula;
    private int numeroSala;

    public Funcion() {}

    public Funcion(Date fecha, Time horaInicio, Time horaFin, int idPelicula, int numeroSala) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idPelicula = idPelicula;
        this.numeroSala = numeroSala;
    }

    public Funcion(int idFuncion, Date fecha, Time horaInicio, Time horaFin, int idPelicula, int numeroSala) {
        this.idFuncion = idFuncion;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idPelicula = idPelicula;
        this.numeroSala = numeroSala;
    }

    public int getIdFuncion() { return idFuncion; }
    public void setIdFuncion(int idFuncion) { this.idFuncion = idFuncion; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Time getHoraInicio() { return horaInicio; }
    public void setHoraInicio(Time horaInicio) { this.horaInicio = horaInicio; }

    public Time getHoraFin() { return horaFin; }
    public void setHoraFin(Time horaFin) { this.horaFin = horaFin; }

    public int getIdPelicula() { return idPelicula; }
    public void setIdPelicula(int idPelicula) { this.idPelicula = idPelicula; }

    public int getNumeroSala() { return numeroSala; }
    public void setNumeroSala(int numeroSala) { this.numeroSala = numeroSala; }

    @Override
    public String toString() {
        return "Funcion{" +
               "idFuncion=" + idFuncion +
               ", fecha=" + fecha +
               ", horaInicio=" + horaInicio +
               ", horaFin=" + horaFin +
               ", idPelicula=" + idPelicula +
               ", numeroSala=" + numeroSala +
               '}';
    }
}
