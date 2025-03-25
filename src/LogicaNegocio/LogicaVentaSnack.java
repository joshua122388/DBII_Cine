package logicaNegocio;

import accesoDatos.VentaSnackDAO;
import entidades.VentaSnack;
import java.util.List;

public class LogicaVentaSnack {
    private VentaSnackDAO dao = new VentaSnackDAO();

    public void insertar(VentaSnack obj) throws Exception {
        dao.insertar(obj);
    }

    public List<VentaSnack> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    public void eliminar(int id) throws Exception {
        dao.eliminar(id);
    }

    public VentaSnack buscarPorID(int id) throws Exception {
        return dao.buscarPorID(id);
    }

    public void actualizar(VentaSnack obj) throws Exception {
        dao.actualizar(obj);
    }
}

