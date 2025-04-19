package logicaNegocio;

import accesoDatos.SalaDAO;
import entidades.Sala;
import java.util.List;

public class LogicaSala {
    private SalaDAO dao = new SalaDAO();

    public void insertar(Sala obj) throws Exception {
        dao.insertar(obj);
    }

    public List<Sala> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    public void eliminar(int id) throws Exception {
        dao.eliminar(id);
    }

    public Sala buscarPorID(int id) throws Exception {
        return dao.buscarPorID(id);
    }

    public void actualizar(Sala obj) throws Exception {
        dao.actualizar(obj);
    }
}
