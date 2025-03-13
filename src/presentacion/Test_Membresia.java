package presentacion;
//hola
import accesoDatos.MembresiaDAO;
import entidades.Membresia;
import java.util.List;

public class Test_Membresia {
    public static void main(String[] args) {
        MembresiaDAO membresiaDAO = new MembresiaDAO();

        try {
            // Insertar una nueva membresía
            Membresia nuevaMembresia = new Membresia(0, "Cine Platino", 15.0);
            membresiaDAO.insertar(nuevaMembresia);
            System.out.println("Membresía insertada correctamente: " + nuevaMembresia);

            // Listar todas las membresías
            System.out.println("\nListado de todas las membresías:");
            List<Membresia> lista = membresiaDAO.listarTodos();
            for (Membresia m : lista) {
                System.out.println(m);
            }

            //Buscar una membresía por ID
            int idBuscar = nuevaMembresia.getIdMembresia();
            Membresia encontrada = membresiaDAO.buscarPorID(idBuscar);
            if (encontrada != null) {
                System.out.println("\nMembresía encontrada: " + encontrada);
            } else {
                System.out.println("\nNo se encontró la membresía con ID " + idBuscar);
            }

            //Actualizar una membresía
            if (encontrada != null) {
                encontrada.setDescuento(18.0); // Nuevo descuento
                membresiaDAO.actualizar(encontrada);
                System.out.println("Membresía actualizada: " + membresiaDAO.buscarPorID(idBuscar));
            }

            //Eliminar una membresía
            int idEliminar = nuevaMembresia.getIdMembresia();
            membresiaDAO.eliminar(idEliminar);
            System.out.println("Membresía con ID " + idEliminar + " eliminada.");

        } catch (Exception e) {
            System.err.println("No se logro eliminar la membresia: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
