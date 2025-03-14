package presentacion;

import accesoDatos.ReporteVentaSnackDAO;
import entidades.ReporteVentaSnack;
import java.sql.Date;
import java.util.List;

public class Test_ReporteVentaSnack {
    public static void main(String[] args) {
        ReporteVentaSnackDAO reporteDAO = new ReporteVentaSnackDAO();

        try {
            //Insertar un nuevo reporte de venta de snack
            ReporteVentaSnack nuevoReporte = new ReporteVentaSnack(Date.valueOf("2025-04-10"), 1, 1, 25.00);
            reporteDAO.insertar(nuevoReporte);
            System.out.println("Reporte insertado correctamente: " + nuevoReporte);

            //Listar todos los reportes de ventas de snacks
            System.out.println("\nListado de todos los reportes:");
            List<ReporteVentaSnack> lista = reporteDAO.listarTodos();
            for (ReporteVentaSnack r : lista) {
                System.out.println(r);
            }

            //Buscar un reporte por ID
            int idBuscar = nuevoReporte.getIdReporteSnack();
            ReporteVentaSnack encontrado = reporteDAO.buscarPorID(idBuscar);
            if (encontrado != null) {
                System.out.println("\nReporte encontrado: " + encontrado);
                encontrado.setTotal(30.00);
                reporteDAO.actualizar(encontrado);
                System.out.println("Reporte actualizado correctamente: " + reporteDAO.buscarPorID(idBuscar));
            }

            //Eliminar el reporte de venta de snack
            reporteDAO.eliminar(idBuscar);
            System.out.println("Reporte eliminado con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
