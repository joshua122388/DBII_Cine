
package presentacion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import accesoDatos.ConexionSQL;
import java.awt.Color;
import javax.swing.JTextField;
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
         
         tblClentes.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        int filaSeleccionada = tblClentes.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtIDCliente.setText(tblClentes.getValueAt(filaSeleccionada, 0).toString());
            txtNombreCliente.setText(tblClentes.getValueAt(filaSeleccionada, 1).toString());
            txtCorreoCliente.setText(tblClentes.getValueAt(filaSeleccionada, 2).toString());
            txtIDMembresia.setText(tblClentes.getValueAt(filaSeleccionada, 3).toString());

            txtIDCliente.setEditable(false); // Desactiva edición
            txtIDCliente.setHorizontalAlignment(JTextField.CENTER); // Centra el texto
            txtIDCliente.setBackground(Color.LIGHT_GRAY); // Cambia el fondo para que se note que está desactivado
        }
    }
});

         
    }
    
private void cargarClientes() {
    try {
        Connection conn = ConexionSQL.conectar();
        String query = "SELECT cli.ID_Cliente, cli.Nombre, cli.Correo, cli.Telefono, m.Tipo " +
               "FROM cliente cli JOIN membresia m ON cli.ID_Membresia = m.ID_Membresia";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        // Crear modelo con nombres personalizados de columnas
DefaultTableModel model = new DefaultTableModel(
    new Object[] { "ID_Cliente", "Nombre", "Correo", "Teléfono", "Membresia" }, 0
            );

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("ID_Cliente"),
                rs.getString("Nombre"),
                rs.getString("Correo"),
                rs.getString("Telefono"),
                rs.getString("Tipo")
            });
        }

        tblClentes.setModel(model); // Asignar modelo a la tabla

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
        String query = "{CALL sp_insertar_cliente(?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(query);

        stmt.setString(1, txtNombreCliente.getText());
        stmt.setString(2, txtCorreoCliente.getText());
        stmt.setString(3, txtIDMembresia.getText()); // Este es tu campo "Teléfono"
        stmt.setInt(4, 1); // ID_Membresia fijo por ahora, podés ajustarlo si es editable

        stmt.execute();
        JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente (vía SP).");

        stmt.close();
        conn.close();
        cargarClientes();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al agregar cliente con SP: " + ex.getMessage());
    }
}
             
private void actualizarCliente() {
    String telefono = txtTelefonoCliente.getText().trim();

    // Validación: el número de teléfono debe contener solo dígitos
    if (!telefono.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "El número de teléfono solo debe contener dígitos.");
        return; // Cancelar la actualización
    }

    try {
        Connection conn = ConexionSQL.conectar();
        String query = "UPDATE Cliente SET Nombre = ?, Correo = ?, Telefono = ? WHERE ID_Cliente = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, txtNombreCliente.getText());
        stmt.setString(2, txtCorreoCliente.getText());
        stmt.setString(3, telefono); // ya validado
        stmt.setInt(4, Integer.parseInt(txtIDCliente.getText()));
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente.");
        stmt.close();
        conn.close();
        cargarClientes(); // Recargar datos
        
        // limpiar campos de texto
        txtIDCliente.setText("");
        txtNombreCliente.setText("");
        txtCorreoCliente.setText("");
        txtTelefonoCliente.setText("");
        txtIDMembresia.setText("");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al actualizar cliente: " + ex.getMessage());
    }
}


        
private void eliminarCliente() {
    try {
        Connection conn = ConexionSQL.conectar();
        String query = "DELETE FROM Cliente WHERE ID_Cliente = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, Integer.parseInt(txtIDCliente.getText()));
        stmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
        stmt.close();
        conn.close();
        cargarClientes(); // Recargar datos
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
        txtIDCliente = new javax.swing.JTextField();
        txtIDMembresia = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClentes = new javax.swing.JTable();
        txtNombreCliente = new javax.swing.JTextField();
        txtCorreoCliente = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        lblTelefono1 = new javax.swing.JLabel();
        txtTelefonoCliente = new javax.swing.JTextField();
        btnActualizar1 = new javax.swing.JButton();
        brnRegresar = new javax.swing.JButton();

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

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Clientes del Cine");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        lblId.setText("ID: ");
        jPanel1.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        lblNombre.setText("Nombre del Cliente:");
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 20));

        lblCorreo.setText("Correo: ");
        jPanel1.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 20));

        lblTelefono.setText("Membresia:");
        jPanel1.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        txtIDCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtIDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 130, -1));
        jPanel1.add(txtIDMembresia, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 170, -1));

        btnEliminar.setBackground(new java.awt.Color(255, 153, 0));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 320, 120, 40));

        tblClentes.setBackground(new java.awt.Color(153, 153, 153));
        tblClentes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClentesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClentes);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 470, 210));

        txtNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 110, -1));
        jPanel1.add(txtCorreoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 180, -1));

        btnActualizar.setBackground(new java.awt.Color(0, 102, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 110, 40));

        lblTelefono1.setText("Telefono:");
        jPanel1.add(lblTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));
        jPanel1.add(txtTelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 170, -1));

        btnActualizar1.setText("Actualizar");
        btnActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 110, 40));

        brnRegresar.setBackground(new java.awt.Color(204, 204, 0));
        brnRegresar.setText("Regresar");
        brnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(brnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 110, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDClienteActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarCliente();
        cargarClientes();// TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    int filaSeleccionada = tblClentes.getSelectedRow();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione la fila deseada");
        return;
    }
        
                txtIDMembresia.setEditable(false);
                txtIDMembresia.setHorizontalAlignment(JTextField.CENTER);
                txtIDMembresia.setBackground(new Color(220, 220, 220)); // Gris claro
    
    actualizarCliente(); // Llama al método ya existente
    }//GEN-LAST:event_btnActualizarActionPerformed
    
    
    private void tblClentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClentesMouseClicked
        
            int fila = tblClentes.getSelectedRow();
    
    if (fila != -1) {
        txtIDCliente.setText(tblClentes.getValueAt(fila, 0).toString());
        txtNombreCliente.setText(tblClentes.getValueAt(fila, 1).toString());
        txtCorreoCliente.setText(tblClentes.getValueAt(fila, 2).toString());
        txtTelefonoCliente.setText(tblClentes.getValueAt(fila, 3).toString());
        txtIDMembresia.setText(tblClentes.getValueAt(fila, 4).toString());

        // Bloquear y centrar los campos ID
        txtIDCliente.setEditable(false);
        txtIDCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDCliente.setBackground(new java.awt.Color(220, 220, 220)); // gris claro

        txtIDMembresia.setEditable(false);
        txtIDMembresia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDMembresia.setBackground(new java.awt.Color(220, 220, 220)); // gris claro
        }      
    }//GEN-LAST:event_tblClentesMouseClicked

    private void btnActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizar1ActionPerformed

    private void brnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnRegresarActionPerformed
       MenuAdmin menu = new MenuAdmin();
       menu.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_brnRegresarActionPerformed

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
    private javax.swing.JButton brnRegresar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnActualizar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JTable tblClentes;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtIDCliente;
    private javax.swing.JTextField txtIDMembresia;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtTelefonoCliente;
    // End of variables declaration//GEN-END:variables
}
