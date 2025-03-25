package logicaNegocio;

import accesoDatos.TiqueteDAO;
import entidades.Tiquete;
import java.util.List;

public class LogicaTiquete {
    private TiqueteDAO dao = new TiqueteDAO();

    public void insertar(Tiquete obj) throws Exception {
        dao.insertar(obj);
    }

    public List<Tiquete> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    public void eliminar(int id) throws Exception {
        dao.eliminar(id);
    }

    public Tiquete buscarPorID(int id) throws Exception {
        return dao.buscarPorID(id);
    }

    public void actualizar(Tiquete obj) throws Exception {
        dao.actualizar(obj);
    }
}