package presentacion;

import accesoDatos.VendedorTiquetesDAO;
import entidades.VendedorTiquetes;
import java.util.List;

public class Test_VendedorTiquetes {
    public static void main(String[] args) {
        VendedorTiquetesDAO vendedorDAO = new VendedorTiquetesDAO();

        try {
            //Insertar un nuevo vendedor
            VendedorTiquetes nuevoVendedor = new VendedorTiquetes("Carlos", "Ramírez");
            vendedorDAO.insertar(nuevoVendedor);
            System.out.println("Vendedor insertado correctamente: " + nuevoVendedor);

            //Listar todos los vendedores
            System.out.println("\nListado de todos los vendedores:");
            List<VendedorTiquetes> lista = vendedorDAO.listarTodos();
            for (VendedorTiquetes v : lista) {
                System.out.println(v);
            }

            //Buscar un vendedor por ID
            int idBuscar = nuevoVendedor.getBadgeVendedor();
            VendedorTiquetes encontrado = vendedorDAO.buscarPorID(idBuscar);
            if (encontrado != null) {
                System.out.println("\nVendedor encontrado: " + encontrado);
            } else {
                System.out.println("\nNo se encontró el vendedor con ID " + idBuscar);
            }

            // 📌 Actualizar un vendedor
            if (encontrado != null) {
                encontrado.setApellido("Gómez"); // Nuevo apellido
                vendedorDAO.actualizar(encontrado);
                System.out.println("Vendedor actualizado: " + vendedorDAO.buscarPorID(idBuscar));
            }

            //Eliminar un vendedor
            /*
            int idEliminar = nuevoVendedor.getBadgeVendedor();
            vendedorDAO.eliminar(idEliminar);
            System.out.println("Vendedor con ID " + idEliminar + " eliminado.");
            */

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
