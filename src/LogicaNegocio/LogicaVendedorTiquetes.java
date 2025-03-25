package logicaNegocio;

import accesoDatos.VendedorTiquetesDAO;
import entidades.VendedorTiquetes;
import java.util.List;

public class LogicaVendedorTiquetes {
    private VendedorTiquetesDAO dao = new VendedorTiquetesDAO();

    public void insertar(VendedorTiquetes obj) throws Exception {
        dao.insertar(obj);
    }

    public List<VendedorTiquetes> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    public void eliminar(int id) throws Exception {
        dao.eliminar(id);
    }

    public VendedorTiquetes buscarPorID(int id) throws Exception {
        return dao.buscarPorID(id);
    }

    public void actualizar(VendedorTiquetes obj) throws Exception {
        dao.actualizar(obj);
    }
}

