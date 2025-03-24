package LogicaNegocio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import Seguridad.Encriptador;

/**
 *
 * @author contr
 */
public class EncriptadorSHA256 implements Encriptador {

    @Override
    public String encriptarSHA256(String textoPlano) {
        
                try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(textoPlano.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }
        
    }
