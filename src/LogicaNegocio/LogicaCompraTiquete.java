package logicaNegocio;

import accesoDatos.CompraTiqueteDAO;
import entidades.CompraTiquete;
import java.util.List;

public class LogicaCompraTiquete {
    private CompraTiqueteDAO dao = new CompraTiqueteDAO();

    public void insertar(CompraTiquete obj) throws Exception {
        dao.insertar(obj);
    }

    public List<CompraTiquete> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    public void eliminar(int id) throws Exception {
        dao.eliminar(id);
    }

    public CompraTiquete buscarPorID(int id) throws Exception {
        return dao.buscarPorID(id);
    }

    public void actualizar(CompraTiquete obj) throws Exception {
        dao.actualizar(obj);
    }
}