/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;
import java.sql.*;
import javax.swing.JOptionPane;
import accesoDatos.ConexionSQL;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author melin
 */
public class CompraBoletosWindow extends javax.swing.JFrame {
         private static final double PRECIO_BOLETO = 5.00;
         private java.util.List<Integer> listaIdFunciones = new java.util.ArrayList<>();
         

    /**
     * Creates new form CompraBoletosWindow
     */
    public CompraBoletosWindow() {
        
        initComponents();
        cargarFunciones();
        
        tblCompras.setModel(new javax.swing.table.DefaultTableModel(
    new Object [][] {},
    new String [] {
        "Película", "Sala", "Duración", "Cantidad", "Asientos"
    }
        ));

    }
    
private void cargarFunciones() {
    try {
        Connection conn = ConexionSQL.conectar();
        String query = "SELECT f.ID_Funcion, p.Titulo, f.Duracion, f.Numero_Sala " +
                       "FROM funcion f JOIN pelicula p ON p.ID_Pelicula = f.ID_Pelicula";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        cmbFunciones.removeAllItems();
        listaIdFunciones.clear(); // Limpiar la lista cada vez

        while (rs.next()) {
            int idFuncion = rs.getInt("ID_Funcion");
            String titulo = rs.getString("Titulo");
            String duracion = rs.getString("Duracion");
            String sala = rs.getString("Numero_Sala");

            // Guardar el ID internamente
            listaIdFunciones.add(idFuncion);

            // Mostrar al usuario solo los datos relevantes
            cmbFunciones.addItem(titulo + " - Sala " + sala + " - " + duracion);
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar funciones: " + ex.getMessage());
    }
}


         
        private void calcularTotal() {
    int cantidad = (int) spnCantidad.getValue();
    double total = cantidad * PRECIO_BOLETO;
    lblTotal.setText("Total: $" + total);
}
        
        private void comprarBoletos() {
       try {
        Connection conn = ConexionSQL.conectar();

        // Compra Tiquete
        String queryCompra = "{CALL sp_insertar_compra_tiquete(?, ?, ?)}";
        CallableStatement stmtCompra = conn.prepareCall(queryCompra);
        int funcionID = listaIdFunciones.get(cmbFunciones.getSelectedIndex());
        int cantidad = (int) spnCantidad.getValue();
        double total = cantidad * PRECIO_BOLETO;
        stmtCompra.setInt(1, funcionID);
        stmtCompra.setInt(2, cantidad);
        stmtCompra.setDouble(3, total);
        stmtCompra.execute();
        stmtCompra.close();

        // Registro Tiquete
        String queryTiquete = "{CALL sp_insertar_tiquete(?, ?, ?, ?)}";
        CallableStatement stmtTiquete = conn.prepareCall(queryTiquete);
        String asiento = txtAsiento.getText();
        stmtTiquete.setString(1, asiento);
        stmtTiquete.setDouble(2, PRECIO_BOLETO);
        stmtTiquete.setInt(3, cantidad);
        stmtTiquete.setInt(4, idCliente);
        stmtTiquete.execute();
        stmtTiquete.close();

        conn.close();
        JOptionPane.showMessageDialog(this, "Compra realizada correctamente (vía SP).");

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al comprar boletos: " + ex.getMessage());
    }
}

private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {                                            
    comprarBoletos();
}
private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {                                            
    calcularTotal();
}
private void cargarCompras() {
    try {
        Connection conn = ConexionSQL.conectar();
        String query = "SELECT * FROM ComprasBoletos";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tblCompras.getModel();
        model.setRowCount(0); // Limpiar tabla

        while (rs.next()) {
            model.addRow(new Object[]{rs.getInt("id"), rs.getInt("funcion_id"), rs.getInt("cantidad"), rs.getDouble("total")});
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar compras: " + ex.getMessage());
    }
}

private void btnVerComprasActionPerformed(java.awt.event.ActionEvent evt) {                                            
    cargarCompras();
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
        lblCompraboletostitle = new javax.swing.JLabel();
        lblFunciones = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        lblCantidad = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnAsiento = new javax.swing.JButton();
        btnComprarBoletos = new javax.swing.JButton();
        btnVerCompras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCompras = new javax.swing.JTable();
        btnCalcular1 = new javax.swing.JButton();
        btnCartelera1 = new javax.swing.JButton();
        cmbFunciones = new javax.swing.JComboBox<>();
        bntRegresar = new javax.swing.JButton();
        lblAsiento = new javax.swing.JLabel();
        txtAsiento = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCompraboletostitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCompraboletostitle.setForeground(new java.awt.Color(255, 255, 255));
        lblCompraboletostitle.setText("Compra de Boletos para el Cine");
        jPanel1.add(lblCompraboletostitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        lblFunciones.setText("Funciones :");
        jPanel1.add(lblFunciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));
        jPanel1.add(spnCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        lblCantidad.setText("Cantidad:");
        jPanel1.add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        lblTotal.setText("Total:");
        jPanel1.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, -1, -1));

        btnAsiento.setBackground(new java.awt.Color(0, 0, 153));
        btnAsiento.setForeground(new java.awt.Color(255, 255, 255));
        btnAsiento.setText("Selección de Asientos");
        btnAsiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsientoActionPerformed(evt);
            }
        });
        jPanel1.add(btnAsiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 160, -1));

        btnComprarBoletos.setBackground(new java.awt.Color(0, 0, 153));
        btnComprarBoletos.setForeground(new java.awt.Color(255, 255, 255));
        btnComprarBoletos.setText("Comprar Boletos: ");
        btnComprarBoletos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarBoletosActionPerformed(evt);
            }
        });
        jPanel1.add(btnComprarBoletos, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, -1, -1));

        btnVerCompras.setBackground(new java.awt.Color(0, 0, 153));
        btnVerCompras.setForeground(new java.awt.Color(255, 255, 255));
        btnVerCompras.setText("Ver Compras");
        btnVerCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerComprasActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 300, 150, -1));

        tblCompras.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblCompras);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 480, 110));

        btnCalcular1.setBackground(new java.awt.Color(0, 0, 153));
        btnCalcular1.setForeground(new java.awt.Color(255, 255, 255));
        btnCalcular1.setText("Calcular Total");
        btnCalcular1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcular1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnCalcular1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        btnCartelera1.setBackground(new java.awt.Color(0, 0, 153));
        btnCartelera1.setForeground(new java.awt.Color(255, 255, 255));
        btnCartelera1.setText("Ver Cartelera");
        jPanel1.add(btnCartelera1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

        cmbFunciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbFunciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFuncionesActionPerformed(evt);
            }
        });
        jPanel1.add(cmbFunciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 320, -1));

        bntRegresar.setBackground(new java.awt.Color(0, 0, 153));
        bntRegresar.setForeground(new java.awt.Color(255, 255, 255));
        bntRegresar.setText("Regresar");
        bntRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(bntRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, -1, -1));

        lblAsiento.setText("Asiento:");
        jPanel1.add(lblAsiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, -1, -1));
        jPanel1.add(txtAsiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, -1, -1));
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAsientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsientoActionPerformed

    String[] partes = cmbFunciones.getSelectedItem().toString().split(" - ");
    String pelicula = partes[0].trim();
    String sala = partes[1].replace("Sala", "").trim();
    String duracion = partes[2].trim();
    int cantidad = (int) spnCantidad.getValue();
    
        if (cantidad <= 0) {
        JOptionPane.showMessageDialog(this, "Debe especificar la cantidad de tiquetes a comprar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
            }

    SeleccionAsientos seleccion = new SeleccionAsientos(
        pelicula,
        sala,
        duracion,
        cantidad
    );
    seleccion.setParentWindow(this); // <- necesario para poder actualizar la tabla desde SeleccionAsientos
    seleccion.setVisible(true);
        
    }//GEN-LAST:event_btnAsientoActionPerformed

    private void bntRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntRegresarActionPerformed
               MainMenu menu = new MainMenu();
               menu.setVisible(true);
               this.dispose();
    }//GEN-LAST:event_bntRegresarActionPerformed

    private void cmbFuncionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFuncionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbFuncionesActionPerformed

    private void btnCalcular1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcular1ActionPerformed
             // Obtener el tipo de boleto seleccionado
    String tipoBoleto = (String) cboTipoBoleto.getSelectedItem(); // O usa el JComboBox si es un combo

    // Obtener la cantidad de boletos
    int cantidad = (int) spnCantidad.getValue(); // O usa el JSpinner si es un spinner

    // Definir los precios
    int precioRegular = 3000;
    int precioVIP = 6000;
    int precioPremium = 12000;
    int precioTotal = 0;

    // Calcular el total basado en el tipo de boleto seleccionado
    switch (tipoBoleto) {
        case "Regular":
            precioTotal = precioRegular * cantidad;
            break;
        case "VIP":
            precioTotal = precioVIP * cantidad;
            break;
        case "Premium":
            precioTotal = precioPremium * cantidad;
            break;
        default:
            JOptionPane.showMessageDialog(this, "Tipo de boleto no válido.");
            return;
    }

    // Mostrar el total en el JLabel de total
    lblTotal.setText("Total: " + precioTotal);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnCalcular1ActionPerformed

    private void btnComprarBoletosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarBoletosActionPerformed
           // Obtener los datos de la compra
    String tipoBoleto = (String) cboTipoBoleto.getSelectedItem();
    int cantidad = (int) spnCantidad.getValue();
    int total = Integer.parseInt(lblTotal.getText().replace("Total: ", ""));
    String asiento = txtAsiento.getText();  // Asiento seleccionado

    // Obtener el ID del cliente (esto puede estar almacenado en la sesión o en un campo de texto oculto)
    int idCliente = obtenerIdCliente();

    try (Connection conn = ConexionSQL.conectar()) {
        // Insertar los datos de la compra en la base de datos
        String query = "INSERT INTO tiquete (Asiento, Precio, Cantidad, ID_Cliente) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, asiento);
        stmt.setInt(2, total);
        stmt.setInt(3, cantidad);
        stmt.setInt(4, idCliente);
        stmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Compra realizada correctamente.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al registrar la compra: " + e.getMessage());
    }  


        // TODO add your handling code here:
    }//GEN-LAST:event_btnComprarBoletosActionPerformed

    private void btnVerComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerComprasActionPerformed
             int idCliente = obtenerIdCliente();

    try (Connection conn = ConexionSQL.conectar()) {
        String query = "SELECT t.Asiento, t.Precio, t.Cantidad, f.Fecha, f.Hora_Inicio "
                     + "FROM tiquete t "
                     + "JOIN funcion f ON t.ID_Funcion = f.ID_Funcion "
                     + "WHERE t.ID_Cliente = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, idCliente);
        ResultSet rs = stmt.executeQuery();

        // Limpiar la tabla de compras anteriores
        DefaultTableModel model = (DefaultTableModel) tblCompras.getModel();
        model.setRowCount(0);  // Limpiar la tabla antes de agregar nuevos datos

        // Mostrar las compras en la tabla
        while (rs.next()) {
            Object[] row = {
                rs.getString("Asiento"),
                rs.getInt("Precio"),
                rs.getInt("Cantidad"),
                rs.getDate("Fecha"),
                rs.getTime("Hora_Inicio")
            };
            model.addRow(row);  // Agregar cada compra a la tabla
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar las compras: " + e.getMessage());
    }


        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerComprasActionPerformed

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
            java.util.logging.Logger.getLogger(CompraBoletosWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompraBoletosWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompraBoletosWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompraBoletosWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompraBoletosWindow().setVisible(true);
            }
        });
    }
    
    public javax.swing.JTable getTblCompras() {
    return tblCompras;
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntRegresar;
    private javax.swing.JButton btnAsiento;
    private javax.swing.JButton btnCalcular1;
    private javax.swing.JButton btnCartelera1;
    private javax.swing.JButton btnComprarBoletos;
    private javax.swing.JButton btnVerCompras;
    private javax.swing.JComboBox<String> cmbFunciones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAsiento;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCompraboletostitle;
    private javax.swing.JLabel lblFunciones;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblCompras;
    private javax.swing.JTextField txtAsiento;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
