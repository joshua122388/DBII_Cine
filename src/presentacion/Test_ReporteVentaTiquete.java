package presentacion;

import accesoDatos.ReporteVentaTiqueteDAO;
import entidades.ReporteVentaTiquete;
import java.sql.Date;
import java.util.List;

public class Test_ReporteVentaTiquete {
    public static void main(String[] args) {
        ReporteVentaTiqueteDAO reporteDAO = new ReporteVentaTiqueteDAO();

        try {
            //Insertar un nuevo reporte de venta de tiquete
            ReporteVentaTiquete nuevoReporte = new ReporteVentaTiquete(Date.valueOf("2025-04-10"), 4, 1, 4, 40.00);
            reporteDAO.insertar(nuevoReporte);
            System.out.println("Reporte insertado correctamente: " + nuevoReporte);

            //Listar todos los reportes de ventas de tiquetes
            System.out.println("\nListado de todos los reportes:");
            List<ReporteVentaTiquete> lista = reporteDAO.listarTodos();
            for (ReporteVentaTiquete r : lista) {
                System.out.println(r);
            }

            //Buscar un reporte por ID
            int idBuscar = nuevoReporte.getIdReporteTiquete();
            ReporteVentaTiquete encontrado = reporteDAO.buscarPorID(idBuscar);
            if (encontrado != null) {
                System.out.println("\nReporte encontrado: " + encontrado);

                //Actualizar el reporte de venta de tiquete
                encontrado.setCantidad(6); // Modificar la cantidad de tiquetes vendidos
                encontrado.setTotal(60.00); // Modificar el total
                reporteDAO.actualizar(encontrado);
                System.out.println("Reporte actualizado correctamente: " + reporteDAO.buscarPorID(idBuscar));
            } else {
                System.out.println("\nNo se encontró el reporte con ID " + idBuscar);
            }

            //Eliminar el reporte de venta de tiquete
            int idEliminar = nuevoReporte.getIdReporteTiquete();
            reporteDAO.eliminar(idEliminar);
            System.out.println("Reporte con ID " + idEliminar + " eliminado.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
