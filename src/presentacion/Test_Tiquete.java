package presentacion;

import accesoDatos.TiqueteDAO;
import entidades.Tiquete;
import java.util.List;

public class Test_Tiquete {
    public static void main(String[] args) {
        TiqueteDAO tiqueteDAO = new TiqueteDAO();

        try {
            //Insertar un nuevo tiquete
            Tiquete nuevoTiquete = new Tiquete("A5", 8.50, 2, 4, 1, 1);
            tiqueteDAO.insertar(nuevoTiquete);
            System.out.println("Tiquete insertado correctamente: " + nuevoTiquete);

            //Listar todos los tiquetes
            System.out.println("\nListado de todos los tiquetes:");
            List<Tiquete> lista = tiqueteDAO.listarTodos();
            for (Tiquete t : lista) {
                System.out.println(t);
            }

            //Buscar un tiquete por ID
            int idBuscar = nuevoTiquete.getIdBoleto();
            Tiquete encontrado = tiqueteDAO.buscarPorID(idBuscar);
            if (encontrado != null) {
                System.out.println("\nTiquete encontrado: " + encontrado);

                //Actualizar el tiquete
                encontrado.setAsiento("B10");
                encontrado.setPrecio(9.00);
                encontrado.setCantidad(3);
                tiqueteDAO.actualizar(encontrado);
                System.out.println("Tiquete actualizado correctamente: " + tiqueteDAO.buscarPorID(idBuscar));
            } else {
                System.out.println("\nNo se encontró el tiquete con ID " + idBuscar);
            }
            
            //Eliminar el tiquete
            int idEliminar = nuevoTiquete.getIdBoleto();
            tiqueteDAO.eliminar(idEliminar);
            System.out.println("Tiquete con ID " + idEliminar + " eliminado.");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
