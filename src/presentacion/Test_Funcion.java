package presentacion;

import accesoDatos.FuncionDAO;
import entidades.Funcion;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Test_Funcion {
    public static void main(String[] args) {
        FuncionDAO funcionDAO = new FuncionDAO();

        try {
            // Insertar una funcion
            Funcion nuevaFuncion = new Funcion(Date.valueOf("2025-03-20"), Time.valueOf("18:30:00"), Time.valueOf("20:30:00"), 1, 2);
            funcionDAO.insertar(nuevaFuncion);
            System.out.println("Funcion insertada correctamente: " + nuevaFuncion);

            // Listar todas las funciones
            System.out.println("\nListado de todas las funciones:");
            List<Funcion> lista = funcionDAO.listarTodos();
            for (Funcion f : lista) {
                System.out.println(f);
            }

            // Buscar una funcion
            int idBuscar = nuevaFuncion.getIdFuncion();
            Funcion encontrada = funcionDAO.buscarPorID(idBuscar);
            if (encontrada != null) {
                System.out.println("\nFuncion encontrada: " + encontrada);

                // Update the function details
                encontrada.setFecha(Date.valueOf("2025-03-25"));
                encontrada.setHoraInicio(Time.valueOf("19:00:00"));
                encontrada.setHoraFin(Time.valueOf("21:00:00"));
                
                funcionDAO.actualizar(encontrada);
                System.out.println("Funcion actualizada correctamente: " + funcionDAO.buscarPorID(idBuscar));
            } else {
                System.out.println("\nNo se encontró la función con ID " + idBuscar);
            }

            /*
            // Uncomment to delete the function
            int idEliminar = nuevaFuncion.getIdFuncion();
            funcionDAO.eliminar(idEliminar);
            System.out.println("Funcion con ID " + idEliminar + " eliminada.");
            */
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
