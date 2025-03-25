package logicaNegocio;

import accesoDatos.VistaCarteleraDAO;
import entidades.VistaCartelera;
import java.util.List;

public class LogicaVistaCartelera {
    private VistaCarteleraDAO dao = new VistaCarteleraDAO();

    public List<VistaCartelera> obtenerCartelera() throws Exception {
        return dao.obtenerCartelera();
    }

    public List<String> obtenerTitulosPeliculas() throws Exception {
        return dao.obtenerTitulosPeliculas();
    }
}
