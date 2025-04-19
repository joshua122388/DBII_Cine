package logicaNegocio;

import accesoDatos.ReporteVentaSnackDAO;
import entidades.ReporteVentaSnack;
import java.util.List;

public class LogicaReporteSnack {
    private ReporteVentaSnackDAO dao = new ReporteVentaSnackDAO();

    public void insertar(ReporteVentaSnack obj) throws Exception {
        dao.insertar(obj);
    }

    public List<ReporteVentaSnack> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    public void eliminar(int id) throws Exception {
        dao.eliminar(id);
    }

    public ReporteVentaSnack buscarPorID(int id) throws Exception {
        return dao.buscarPorID(id);
    }

    public void actualizar(ReporteVentaSnack obj) throws Exception {
        dao.actualizar(obj);
    }
}