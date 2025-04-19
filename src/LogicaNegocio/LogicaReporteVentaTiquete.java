package logicaNegocio;

import accesoDatos.ReporteVentaTiqueteDAO;
import entidades.ReporteVentaTiquete;
import java.util.List;

public class LogicaReporteVentaTiquete {
    private ReporteVentaTiqueteDAO dao = new ReporteVentaTiqueteDAO();

    public void insertar(ReporteVentaTiquete obj) throws Exception {
        dao.insertar(obj);
    }

    public List<ReporteVentaTiquete> listarTodos() throws Exception {
        return dao.listarTodos();
    }

    public void eliminar(int id) throws Exception {
        dao.eliminar(id);
    }

    public ReporteVentaTiquete buscarPorID(int id) throws Exception {
        return dao.buscarPorID(id);
    }

    public void actualizar(ReporteVentaTiquete obj) throws Exception {
        dao.actualizar(obj);
    }
}