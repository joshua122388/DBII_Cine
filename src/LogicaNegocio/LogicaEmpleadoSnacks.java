package logicaNegocio;

import accesoDatos.EmpleadoSnacksDAO;
import entidades.EmpleadoSnacks;
import java.util.List;

public class LogicaEmpleadoSnacks {
    private EmpleadoSnacksDAO dao = new EmpleadoSnacksDAO();

    public void insertar(EmpleadoSnacks obj) throws Exception {
        dao.insertar(obj);
    }

    public List<EmpleadoSnacks> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    public void eliminar(int id) throws Exception {
        dao.eliminar(id);
    }

    public EmpleadoSnacks buscarPorID(int id) throws Exception {
        return dao.buscarPorID(id);
    }

    public void actualizar(EmpleadoSnacks obj) throws Exception {
        dao.actualizar(obj);
    }
}
