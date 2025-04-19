/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author contr
 */
public class HashTest {
    
        public static void checHash(String textoPlano) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(textoPlano.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            System.out.println("Texto original: " + textoPlano);
            System.out.println("Hash SHA-256 generado: " + sb.toString());
        } catch (Exception e) {
            System.out.println("Error generando hash: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        checHash("SecurePaS!");
    }
    
}
