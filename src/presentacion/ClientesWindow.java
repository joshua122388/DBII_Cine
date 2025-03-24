
package presentacion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import accesoDatos.ConexionSQL;
/**
 *
 * @author contr
 */
public class ClientesWindow extends javax.swing.JFrame {

    /**
     * Creates new form ClientesWindowJ
     */
    public ClientesWindow() {
        initComponents();
         cargarClientes();
    }
    
         private void cargarClientes() {
        try {
            Connection conn = ConexionSQL.conectar();
            String query = "SELECT * FROM Cliente";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
            model.setRowCount(0); // Limpiar tabla

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("ID_Cliente"), rs.getString("Nombre"), rs.getString("Correo"), rs.getString("Telefono")});
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes: " + ex.getMessage());
        }
    }
         
        private void agregarCliente() {
        try {
            Connection conn = ConexionSQL.conectar();
            String query = "INSERT INTO Clientes (nombre, correo, telefono) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, txtNombre.getText());
            stmt.setString(2, txtCorreo.getText());
            stmt.setString(3, txtTelefono.getText());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.");
            stmt.close();
            conn.close();
            cargarClientes(); // Recargar datos
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar cliente: " + ex.getMessage());
        }
    }
              
        private void actualizarCliente() {
        try {
            Connection conn = ConexionSQL.conectar();
            String query = "UPDATE Clientes SET nombre=?, correo=?, telefono=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, txtNombre.getText());
            stmt.setString(2, txtCorreo.getText());
            stmt.setString(3, txtTelefono.getText());
            stmt.setInt(4, Integer.parseInt(txtIDCliente.getText()));
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente.");
            stmt.close();
            conn.close();
            cargarClientes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar cliente: " + ex.getMessage());
        }
    }
        
      private void eliminarCliente() {
        try {
            Connection conn = ConexionSQL.conectar();
            String query = "DELETE FROM Clientes WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(txtIDCliente.getText()));
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
            stmt.close();
            conn.close();
            cargarClientes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar cliente: " + ex.getMessage());
        }
    }
      
      

                      
              
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtIDCliente = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblClientes);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Clientes del Cine");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        lblId.setText("ID: ");
        jPanel1.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        lblNombre.setText("Nombre del Cliente:");
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));

        lblCorreo.setText("Correo: ");
        jPanel1.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, -1, 20));

        lblTelefono.setText("Telefono:");
        jPanel1.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, -1, -1));

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 110, -1));
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 100, -1));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 90, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, 100, -1));

        btnCargar.setText("Cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 470, 210));
        jPanel1.add(txtIDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarCliente();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarCliente();        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarCliente();        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        cargarClientes();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCargarActionPerformed

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
            java.util.logging.Logger.getLogger(ClientesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientesWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtIDCliente;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
