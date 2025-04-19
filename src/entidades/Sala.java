package entidades;

public class Sala {
    private int numeroSala; // Clave primaria generada automáticamente
    private int capacidad;
    private String tipo;

    // Constructor vacío (para select y otros procesos)
    public Sala() {}

    // Constructor sin ID (para insert)
    public Sala(int capacidad, String tipo) {
        this.capacidad = capacidad;
        this.tipo = tipo;
    }

    // Constructor con ID (para select y delete)
    public Sala(int numeroSala, int capacidad, String tipo) {
        this.numeroSala = numeroSala;
        this.capacidad = capacidad;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getNumeroSala() { return numeroSala; }
    public void setNumeroSala(int numeroSala) { this.numeroSala = numeroSala; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    // Método toString() para imprimir la información de la sala
    @Override
    public String toString() {
        return "Sala{" +
               "numeroSala=" + numeroSala +
               ", capacidad=" + capacidad +
               ", tipo='" + tipo + '\'' +
               '}';
    }
}
