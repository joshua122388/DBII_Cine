package presentacion;

import accesoDatos.VentaSnackDAO;
import entidades.VentaSnack;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Test_VentaSnack {
    public static void main(String[] args) {
        VentaSnackDAO ventaDAO = new VentaSnackDAO();

        try {
            //Insertar una nueva venta de snack
            VentaSnack nuevaVenta = new VentaSnack(Date.valueOf("2025-03-20"), Time.valueOf("14:30:00"), 15.00, 2, 1, "SNK001");
            ventaDAO.insertar(nuevaVenta);
            System.out.println("Venta insertada correctamente: " + nuevaVenta);

            //Listar todas las ventas de snacks
            System.out.println("\nListado de todas las ventas:");
            List<VentaSnack> lista = ventaDAO.listarTodos();
            for (VentaSnack v : lista) {
                System.out.println(v);
            }

            //Buscar una venta por ID
            int idBuscar = nuevaVenta.getIdVenta();
            VentaSnack encontrada = ventaDAO.buscarPorID(idBuscar);
            if (encontrada != null) {
                System.out.println("\nVenta encontrada: " + encontrada);

                //Actualizar la venta de snack
                encontrada.setTotal(20.00); // Modificar el total
                encontrada.setCantidad(3); // Modificar la cantidad
                ventaDAO.actualizar(encontrada);
                System.out.println("Venta actualizada correctamente: " + ventaDAO.buscarPorID(idBuscar));
            } else {
                System.out.println("\nNo se encontró la venta con ID " + idBuscar);
            }
            /*
            //Eliminar la venta de snack
            int idEliminar = nuevaVenta.getIdVenta();
            ventaDAO.eliminar(idEliminar);
            System.out.println("Venta con ID " + idEliminar + " eliminada.");
            */
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
