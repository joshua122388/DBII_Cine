package presentacion;

import accesoDatos.ProductoSnackDAO;
import entidades.ProductoSnack;
import java.util.List;

public class Test_ProductoSnack {
    public static void main(String[] args) {
        ProductoSnackDAO productoDAO = new ProductoSnackDAO();

        try {
            //Insertar un nuevo producto
            ProductoSnack nuevoProducto = new ProductoSnack("VXRM001", "Palomitas de Maíz", 2.50);
            productoDAO.insertar(nuevoProducto);
            System.out.println("Producto insertado correctamente: " + nuevoProducto);

            //Listar todos los productos
            System.out.println("\nListado de todos los productos:");
            List<ProductoSnack> lista = productoDAO.listarTodos();
            for (ProductoSnack p : lista) {
                System.out.println(p);
            }

            //Buscar un producto por ID
            String idBuscar = nuevoProducto.getCodigoProducto();
            ProductoSnack encontrado = productoDAO.buscarPorID(idBuscar);
            if (encontrado != null) {
                System.out.println("\nProducto encontrado: " + encontrado);
            } else {
                System.out.println("\nNo se encontró el producto con Código " + idBuscar);
            }

            //Actualizar un producto
            if (encontrado != null) {
                encontrado.setPrecio(3.00); // Nuevo precio
                productoDAO.actualizar(encontrado);
                System.out.println("Producto actualizado: " + productoDAO.buscarPorID(idBuscar));
            }

            //Eliminar un producto
            String idEliminar = nuevoProducto.getCodigoProducto();
            productoDAO.eliminar(idEliminar);
            System.out.println(" Producto con Código " + idEliminar + " eliminado.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
