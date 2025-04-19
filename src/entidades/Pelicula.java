package entidades;

public class Pelicula {
    private int idPelicula;
    private String titulo;
    private String genero;
    private String clasificacion;
    private String sinopsis;

    // Constructor vacío
    public Pelicula() {}

    // Constructor con parámetros
    public Pelicula(int idPelicula, String titulo, String genero, String clasificacion, String sinopsis) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.sinopsis = sinopsis;
    }

    // Getters y Setters
    public int getIdPelicula() { return idPelicula; }
    public void setIdPelicula(int idPelicula) { this.idPelicula = idPelicula; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getClasificacion() { return clasificacion; }
    public void setClasificacion(String clasificacion) { this.clasificacion = clasificacion; }

    public String getSinopsis() { return sinopsis; }
    public void setSinopsis(String sinopsis) { this.sinopsis = sinopsis; }

    @Override
    public String toString() {
        return "Pelicula{" +
                "idPelicula=" + idPelicula +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", clasificacion='" + clasificacion + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                '}';
    }
}
