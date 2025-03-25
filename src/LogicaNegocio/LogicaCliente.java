package logicaNegocio;

import accesoDatos.ClienteDAO;
import entidades.Cliente;
import java.util.List;

public class LogicaCliente {
    
    private ClienteDAO dao = new ClienteDAO();

    public void insertar(Cliente cliente) throws Exception {
        dao.insertar(cliente);
    }

    public void actualizar(Cliente cliente) throws Exception {
        dao.actualizar(cliente);
    }

    public void eliminar(int id) throws Exception {
        dao.eliminar(id);
    }

    public Cliente buscarPorID(int id) throws Exception {
        return dao.buscarPorID(id);
    }

    public List<Cliente> listarTodos() throws Exception {
        return dao.listarTodos();
    }
}
