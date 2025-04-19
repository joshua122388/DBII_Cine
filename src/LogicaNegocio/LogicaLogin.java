package LogicaNegocio;

import Seguridad.Encriptador;
import Servicios.Login_Servicio;
import accesoDatos.ConexionSQL;
import entidades.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    
    @Override
public int obtenerIDCliente(String usuario) throws Exception {
    int idCliente = -1;
    Connection conn = ConexionSQL.conectar();

    String query = "SELECT ID_Usuario FROM INGRESO WHERE usuario = ?";
    PreparedStatement stmt = conn.prepareStatement(query);
    stmt.setString(1, usuario);

    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {
        idCliente = rs.getInt("ID_Usuario");
    } else {
        throw new Exception("No se encontró el cliente para el usuario: " + usuario);
    }

    rs.close();
    stmt.close();
    return idCliente;
}


   
}
