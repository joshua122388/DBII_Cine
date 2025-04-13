package LogicaNegocio;

import Seguridad.Encriptador;
import Servicios.Login_Servicio;
import accesoDatos.ConexionSQL;
import entidades.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Implementación de la lógica de negocio para el inicio de sesión.
 */
public class LogicaLogin implements Login_Servicio {

    @Override
    public boolean login(Login login) throws Exception {
        boolean resultado = false;

        Encriptador encriptador = new EncriptadorSHA256();
        String passwordEncriptada = encriptador.encriptarSHA256(login.getContrasena());

        Connection conn = ConexionSQL.conectar();
        String query = "SELECT * FROM INGRESO WHERE usuario = ? AND contrasena = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, login.getUsuario());
        stmt.setString(2, passwordEncriptada);

        ResultSet rs = stmt.executeQuery();
        resultado = rs.next();  // true si encuentra coincidencia

        rs.close();
        stmt.close();
        conn.close();

        return resultado;
    }
    public int obtenerIdCliente(Login login) throws Exception {
    int id = -1;
    String passwordEncriptada = new EncriptadorSHA256().encriptarSHA256(login.getContrasena());
    Connection conn = ConexionSQL.conectar();

    String query = "SELECT ID_Cliente FROM ingreso WHERE usuario = ? AND contrasena = ?";
    PreparedStatement stmt = conn.prepareStatement(query);
    stmt.setString(1, login.getUsuario());
    stmt.setString(2, passwordEncriptada);
    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {
        id = rs.getInt("ID_Cliente");
    }

    rs.close();
    stmt.close();
    conn.close();

    return id;
}
    
}
