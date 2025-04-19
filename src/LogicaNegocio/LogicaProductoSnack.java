package logicaNegocio;

import accesoDatos.ProductoSnackDAO;
import entidades.ProductoSnack;
import java.util.List;

public class LogicaProductoSnack {
    private ProductoSnackDAO dao = new ProductoSnackDAO();

    public void insertar(ProductoSnack obj) throws Exception {
        dao.insertar(obj);
    }

    public List<ProductoSnack> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    public void eliminar(String codigo) throws Exception {
        dao.eliminar(codigo);
    }

    public ProductoSnack buscarPorID(String codigo) throws Exception {
        return dao.buscarPorID(codigo);
    }

    public void actualizar(ProductoSnack obj) throws Exception {
        dao.actualizar(obj);
    }
}