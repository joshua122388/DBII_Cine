package presentacion;

import accesoDatos.SalaDAO;
import entidades.Sala;
import java.util.List;

public class Test_Sala {
    public static void main(String[] args) {
        SalaDAO salaDAO = new SalaDAO();

        try {
            //Insertar una nueva sala
            Sala nuevaSala = new Sala(100, "IMAX");
            salaDAO.insertar(nuevaSala);
            System.out.println("Sala insertada correctamente: " + nuevaSala);

            //Listar todas las salas
            System.out.println("\n Listado de todas las salas:");
            List<Sala> lista = salaDAO.listarTodos();
            for (Sala s : lista) {
                System.out.println(s);
            }

            // Buscar una sala por ID
            int idBuscar = nuevaSala.getNumeroSala();
            Sala encontrada = salaDAO.buscarPorID(idBuscar);
            if (encontrada != null) {
                System.out.println("\nSala encontrada: " + encontrada);
            } else {
                System.out.println("\nNo se encontró la sala con Número " + idBuscar);
            }

            // Actualizar una sala
            if (encontrada != null) {
                encontrada.setCapacidad(120); // Nueva capacidad
                salaDAO.actualizar(encontrada);
                System.out.println("Sala actualizada: " + salaDAO.buscarPorID(idBuscar));
            }

       
             //Eliminar una sala
            int idEliminar = nuevaSala.getNumeroSala();
            salaDAO.eliminar(idEliminar);
            System.out.println("✅ Sala con Número " + idEliminar + " eliminada.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
       
    }
}
