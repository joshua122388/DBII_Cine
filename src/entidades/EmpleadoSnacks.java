package entidades;

public class EmpleadoSnacks {
    private int badgeEmpleadoSnacks;
    private String nombre;
    private String apellido;

    public EmpleadoSnacks() {}

    public EmpleadoSnacks(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public EmpleadoSnacks(int badgeEmpleadoSnacks, String nombre, String apellido) {
        this.badgeEmpleadoSnacks = badgeEmpleadoSnacks;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getBadgeEmpleadoSnacks() { return badgeEmpleadoSnacks; }
    public void setBadgeEmpleadoSnacks(int badgeEmpleadoSnacks) { this.badgeEmpleadoSnacks = badgeEmpleadoSnacks; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    @Override
    public String toString() {
        return "EmpleadoSnacks{" +
               "badgeEmpleadoSnacks=" + badgeEmpleadoSnacks +
               ", nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               '}';
    }
}
