package presentacion;

import accesoDatos.EmpleadoSnacksDAO;
import entidades.EmpleadoSnacks;
import java.util.List;

public class Test_EmpleadoSnacks {
    public static void main(String[] args) {
        EmpleadoSnacksDAO empleadoDAO = new EmpleadoSnacksDAO();

        try {
            EmpleadoSnacks nuevoEmpleado = new EmpleadoSnacks("Luis", "Fernández");
            empleadoDAO.insertar(nuevoEmpleado);
            System.out.println("Empleado insertado correctamente: " + nuevoEmpleado);

            System.out.println("\nListado de todos los empleados:");
            List<EmpleadoSnacks> lista = empleadoDAO.listarTodos();
            for (EmpleadoSnacks e : lista) {
                System.out.println(e);
            }

            int idBuscar = nuevoEmpleado.getBadgeEmpleadoSnacks();
            EmpleadoSnacks encontrado = empleadoDAO.buscarPorID(idBuscar);
            if (encontrado != null) {
                System.out.println("\nEmpleado encontrado: " + encontrado);
            } else {
                System.out.println("\nNo se encontró el empleado con ID " + idBuscar);
            }

            if (encontrado != null) {
                encontrado.setApellido("Gómez");
                empleadoDAO.actualizar(encontrado);
                System.out.println("Empleado actualizado: " + empleadoDAO.buscarPorID(idBuscar));
            }
            /*
            int idEliminar = nuevoEmpleado.getBadgeEmpleadoSnacks();
            empleadoDAO.eliminar(idEliminar);
            System.out.println("Empleado con ID " + idEliminar + " eliminado.");
            */
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
