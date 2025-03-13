package presentacion;

import accesoDatos.CompraTiqueteDAO;
import entidades.CompraTiquete;
import java.sql.Date;
import java.util.List;

public class Test_CompraTiquete {
    public static void main(String[] args) {
        CompraTiqueteDAO compraDAO = new CompraTiqueteDAO();

        try {
            //Insertar una nueva compra de tiquete
            CompraTiquete nuevaCompra = new CompraTiquete(4, Date.valueOf("2025-03-25"), 3);
            compraDAO.insertar(nuevaCompra);
            System.out.println("Compra insertada correctamente: " + nuevaCompra);

            //Listar todas las compras de tiquetes
            System.out.println("\nListado de todas las compras:");
            List<CompraTiquete> lista = compraDAO.listarTodos();
            for (CompraTiquete c : lista) {
                System.out.println(c);
            }

            //Buscar una compra por ID
            int idBuscar = nuevaCompra.getIdCompra();
            CompraTiquete encontrada = compraDAO.buscarPorID(idBuscar);
            if (encontrada != null) {
                System.out.println("\nCompra encontrada: " + encontrada);

                //Actualizar la compra de tiquete
                encontrada.setCantidad(5); // Modificar la cantidad
                compraDAO.actualizar(encontrada);
                System.out.println("Compra actualizada correctamente: " + compraDAO.buscarPorID(idBuscar));
            } else {
                System.out.println("\nNo se encontró la compra con ID " + idBuscar);
            }
            
            /*
            //Eliminar la compra de tiquete
            int idEliminar = nuevaCompra.getIdCompra();
            compraDAO.eliminar(idEliminar);
            System.out.println("Compra con ID " + idEliminar + " eliminada.");
            */
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
