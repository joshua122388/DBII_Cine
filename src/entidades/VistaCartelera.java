package entidades;

public class VistaCartelera {
    private String titulo;
    private String genero;
    private String clasificacion;
    private String horarios;

    public VistaCartelera(String titulo, String genero, String clasificacion, String horarios) {
        this.titulo = titulo;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.horarios = horarios;
    }

    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public String getClasificacion() { return clasificacion; }
    public String getHorarios() { return horarios; }
}
