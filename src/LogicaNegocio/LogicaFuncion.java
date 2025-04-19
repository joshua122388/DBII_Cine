package logicaNegocio;

import accesoDatos.FuncionDAO;
import entidades.Funcion;
import java.util.List;

public class LogicaFuncion {
    private FuncionDAO dao = new FuncionDAO();

    public void insertar(Funcion obj) throws Exception {
        dao.insertar(obj);
    }

    public List<Funcion> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    public void eliminar(int id) throws Exception {
        dao.eliminar(id);
    }

    public Funcion buscarPorID(int id) throws Exception {
        return dao.buscarPorID(id);
    }

    public void actualizar(Funcion obj) throws Exception {
        dao.actualizar(obj);
    }
}