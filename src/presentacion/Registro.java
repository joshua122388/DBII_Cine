/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import LogicaNegocio.EncriptadorSHA256;
import Seguridad.Encriptador;
import accesoDatos.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author contr
 */
public class Registro extends javax.swing.JFrame {

    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
        cargarMembresias();

        setLocationRelativeTo(null);
    }

private void cargarMembresias() {
    try {
        Connection conn = ConexionSQL.conectar();
        String query = "SELECT tipo FROM membresia";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        cmbMemebresia.removeAllItems(); // Limpia el combo

        // Agregar la opción predeterminada
        cmbMemebresia.addItem("Selecciona tu membresía");

        while (rs.next()) {
            cmbMemebresia.addItem(rs.getString("tipo"));
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar tipos de membresía: " + e.getMessage());
    }
}



    
    private int obtenerIdMembresia(String tipo) {
    int id = -1;
    try (Connection conn = ConexionSQL.conectar()) {
        String query = "SELECT ID_Membresia FROM membresia WHERE Tipo = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, tipo);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            id = rs.getInt("ID_Membresia");
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al obtener ID de membresía: " + e.getMessage());
    }
    return id;
}


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnRegresar = new javax.swing.JButton();
        btnRegistro = new javax.swing.JButton();
        cmbMemebresia = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro de Uusuario");

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Membresia");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Registrate");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Nombre de Usuario");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        txtUsuario.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 160, -1));

        txtPassword.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 170, -1));

        btnRegresar.setBackground(new java.awt.Color(255, 255, 0));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 140, 40));

        btnRegistro.setBackground(new java.awt.Color(51, 255, 51));
        btnRegistro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRegistro.setText("Registrarse");
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 140, 40));

        cmbMemebresia.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.add(cmbMemebresia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 190, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Contraseña");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        LoginWindow login = new LoginWindow();
        login.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
    String usuario = txtUsuario.getText();
    String contrasena = new String(txtPassword.getPassword());
    String tipoMembresia = (String) cmbMemebresia.getSelectedItem(); // Obtener tipo de membresía

    if (usuario.isEmpty() || contrasena.isEmpty() || tipoMembresia == null) {
        JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
        return;
    }

    try {
        // Encriptar la contraseña
        Encriptador encriptador = new LogicaNegocio.EncriptadorSHA256();
        String hash = encriptador.encriptarSHA256(contrasena);

        Connection conn = ConexionSQL.conectar();

        // Obtener el ID_Membresia correspondiente al tipo
        String sqlMembresia = "SELECT ID_Membresia FROM membresia WHERE tipo = ?";
        PreparedStatement stmtMembresia = conn.prepareStatement(sqlMembresia);
        stmtMembresia.setString(1, tipoMembresia);
        ResultSet rs = stmtMembresia.executeQuery();

        int idMembresia = 1; // Valor por defecto

        if (rs.next()) {
            idMembresia = rs.getInt("ID_Membresia");
        } else {
            JOptionPane.showMessageDialog(this, "Tipo de membresía no válido.");
            return;
        }

        // Insertar el nuevo usuario con su membresía
        String query = "INSERT INTO INGRESO (usuario, contrasena, ID_Membresia) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, usuario);
        stmt.setString(2, hash);
        stmt.setInt(3, idMembresia);
        stmt.executeUpdate();

        // Cerrar conexiones
        rs.close();
        stmtMembresia.close();
        stmt.close();
        conn.close();

        JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
        txtUsuario.setText("");
        txtPassword.setText("");
        cmbMemebresia.setSelectedIndex(0); // Reiniciar el combo

        // Redirigir al login
        new LoginWindow().setVisible(true);
        this.dispose();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + e.getMessage());
    }
    }//GEN-LAST:event_btnRegistroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistro;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbMemebresia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
