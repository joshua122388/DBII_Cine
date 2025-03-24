package accesoDatos;

import entidades.VistaCartelera;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VistaCarteleraDAO {

    public List<VistaCartelera> obtenerCartelera() throws Exception {
        List<VistaCartelera> lista = new ArrayList<>();

        String sql = "SELECT Titulo, Genero, Clasificacion, Horarios FROM vw_PeliculasEnCartelera";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VistaCartelera vc = new VistaCartelera(
                    rs.getString("Titulo"),
                    rs.getString("Genero"),
                    rs.getString("Clasificacion"),
                    rs.getString("Horarios")
                );
                lista.add(vc);
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener cartelera: " + e.getMessage());
        }

        return lista;
    }
    
    public List<String> obtenerTitulosPeliculas() throws Exception {
    List<String> titulos = new ArrayList<>();
    String sql = "SELECT DISTINCT Titulo FROM vw_PeliculasEnCartelera ORDER BY Titulo";

    try (Connection conn = ConexionSQL.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            titulos.add(rs.getString("Titulo"));
        }

    } catch (SQLException e) {
        throw new Exception("Error al obtener títulos: " + e.getMessage());
    }

    return titulos;
}

    
    
}
