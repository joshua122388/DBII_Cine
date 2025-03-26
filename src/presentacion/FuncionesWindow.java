/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import accesoDatos.ConexionSQL;
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
        cargarFunciones(); // Llenar la tabla al abrir la ventana
        cargarPeliculas(); // Llenar ComboBox con películas
        setLocationRelativeTo(null);
    }
     private void cargarPeliculas() {
        try {
            Connection conn = ConexionSQL.conectar();
            String query = "SELECT ID_Pelicula, Titulo FROM pelicula";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            cmbPelicula.removeAllItems();
            while (rs.next()) {
                cmbPelicula.addItem(rs.getInt("ID_Pelicula") + " - " + rs.getString("Titulo"));
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
        String query = "SELECT fu.Fecha, pel.Titulo, fu.Hora_Inicio, fu.Duracion, fu.Numero_Sala " +
                       "FROM funcion fu JOIN pelicula pel ON fu.ID_Pelicula = pel.ID_Pelicula;";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        // Crear el modelo con nombres de columnas personalizados
        DefaultTableModel model = new DefaultTableModel(
            new Object[] { "Fecha", "Título", "Hora Inicio", "Duración", "Número de Sala" }, 0
        );

        // Llenar el modelo con los datos del resultset
        while (rs.next()) {
            model.addRow(new Object[] {
                rs.getDate("Fecha"),
                rs.getString("Titulo"),
                rs.getTime("Hora_Inicio"),
                rs.getString("Duracion"),
                rs.getInt("Numero_Sala")
            });
        }

        // Asignar el modelo a la tabla
        tblFunciones.setModel(model);

        // Cierre de recursos
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

        jPanel1 = new javax.swing.JPanel();
        lblFunciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFunciones = new javax.swing.JTable();
        cmbPelicula = new javax.swing.JComboBox<>();
        lblPeliculas = new javax.swing.JLabel();
        lblSala = new javax.swing.JLabel();
        brnFiltro = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFunciones.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblFunciones.setText("Funciones del Cine");
        jPanel1.add(lblFunciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        tblFunciones.setBackground(new java.awt.Color(153, 153, 0));
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 610, 180));

        jPanel1.add(cmbPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 200, -1));

        lblPeliculas.setText("Películas");
        jPanel1.add(lblPeliculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        lblSala.setText("Fecha Función");
        jPanel1.add(lblSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, -1, -1));

        brnFiltro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        brnFiltro.setText("Aplicar");
        jPanel1.add(brnFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, -1, -1));

        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, -1, -1));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 130, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
       MainMenu menu = new MainMenu();
       menu.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

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
    private javax.swing.JButton brnFiltro;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbPelicula;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFunciones;
    private javax.swing.JLabel lblPeliculas;
    private javax.swing.JLabel lblSala;
    private javax.swing.JTable tblFunciones;
    // End of variables declaration//GEN-END:variables
}
