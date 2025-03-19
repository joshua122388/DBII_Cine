/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author melin
 */
public class FuncionesWindow extends javax.swing.JFrame {

    /**
     * Creates new form FuncionesWindow
     */
    public FuncionesWindow() {
        initComponents();
         cargarPeliculas(); // Llenar ComboBox con películas
        cargarFunciones(); // Llenar la tabla al abrir la ventana
    }
     private void cargarPeliculas() {
        try {
            Connection conn = ConexionSQL.conectar();
            String query = "SELECT id, titulo FROM Peliculas";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            cmbPelicula.removeAllItems();
            while (rs.next()) {
                cmbPelicula.addItem(rs.getInt("id") + " - " + rs.getString("titulo"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar películas: " + ex.getMessage());
        }
    }

    private void cargarFunciones() {
        try {
            Connection conn = ConexionSQL.conectar();
            String query = "SELECT * FROM Funciones";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) tblFunciones.getModel();
            model.setRowCount(0); // Limpiar tabla

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getInt("pelicula_id"), rs.getString("sala"), rs.getString("horario")});
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar funciones: " + ex.getMessage());
        }
        }
        private void agregarFuncion() {
        try {
            Connection conn = ConexionSQL.conectar();
            String query = "INSERT INTO Funciones (pelicula_id, sala, horario) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            int peliculaID = Integer.parseInt(cmbPelicula.getSelectedItem().toString().split(" - ")[0]);
            stmt.setInt(1, peliculaID);
            stmt.setString(2, txtSala.getText());
            stmt.setString(3, txtHorario.getText());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Función agregada correctamente.");
            stmt.close();
            conn.close();
            cargarFunciones();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar función: " + ex.getMessage());
        }
    }

    private void actualizarFuncion() {
        try {
            Connection conn = ConexionSQL.conectar();
            String query = "UPDATE Funciones SET pelicula_id=?, sala=?, horario=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            int peliculaID = Integer.parseInt(cmbPelicula.getSelectedItem().toString().split(" - ")[0]);
            stmt.setInt(1, peliculaID);
            stmt.setString(2, txtSala.getText());
            stmt.setString(3, txtHorario.getText());
            stmt.setInt(4, Integer.parseInt(txtID.getText()));
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Función actualizada correctamente.");
            stmt.close();
            conn.close();
            cargarFunciones();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar función: " + ex.getMessage());
        }
    }

    private void eliminarFuncion() {
        try {
            Connection conn = ConexionSQL.conectar();
            String query = "DELETE FROM Funciones WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(txtID.getText()));
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Función eliminada correctamente.");
            stmt.close();
            conn.close();
            cargarFunciones();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar función: " + ex.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFunciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFunciones = new javax.swing.JTable();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        cmbPelicula = new javax.swing.JComboBox<>();
        lblPeliculas = new javax.swing.JLabel();
        txtSala = new javax.swing.JTextField();
        lblSala = new javax.swing.JLabel();
        txtHorario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFunciones.setText("Funciones del Cine");
        getContentPane().add(lblFunciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        tblFunciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblFunciones);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 380, 180));

        lblID.setText("ID: ");
        getContentPane().add(lblID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));
        getContentPane().add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        cmbPelicula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cmbPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        lblPeliculas.setText("Películas");
        getContentPane().add(lblPeliculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, -1, -1));
        getContentPane().add(txtSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, -1, -1));

        lblSala.setText("Sala: ");
        getContentPane().add(lblSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));
        getContentPane().add(txtHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, -1));

        jLabel1.setText("Horario: ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, -1, -1));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, -1, -1));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, -1, -1));

        btnCargar.setText("Cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        cargarFunciones();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
         agregarFuncion();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarFuncion();        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarFuncion();        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(FuncionesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FuncionesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FuncionesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FuncionesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FuncionesWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cmbPelicula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFunciones;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblPeliculas;
    private javax.swing.JLabel lblSala;
    private javax.swing.JTable tblFunciones;
    private javax.swing.JTextField txtHorario;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtSala;
    // End of variables declaration//GEN-END:variables
}
