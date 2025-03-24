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
    /**
     * Creates new form CompraBoletosWindow
     */
    public CompraBoletosWindow() {
        initComponents();
        cargarFunciones();
    }
         private void cargarFunciones() {
        try {
            Connection conn = ConexionSQL.conectar();
            String query = "SELECT ID_Funcion, ID_Pelicula, Numero_Sala, Duracion FROM funcion";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            cmbFunciones.removeAllItems();
            while (rs.next()) {
                cmbFunciones.addItem(rs.getInt("ID_Funcion") + " - Película " + rs.getInt("ID_Pelicula") + " - Sala " + rs.getString("Numero_Sala") + " - " + rs.getString("Duracion"));
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
        String query = "INSERT INTO ComprasBoletos (funcion_id, cantidad, total) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);

        int funcionID = Integer.parseInt(cmbFunciones.getSelectedItem().toString().split(" - ")[0]);
        int cantidad = (int) spnCantidad.getValue();
        double total = cantidad * PRECIO_BOLETO;

        stmt.setInt(1, funcionID);
        stmt.setInt(2, cantidad);
        stmt.setDouble(3, total);
        stmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Boletos comprados exitosamente.");
        stmt.close();
        conn.close();
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

        lblCompraboletostitle = new javax.swing.JLabel();
        lblFunciones = new javax.swing.JLabel();
        cmbFunciones = new javax.swing.JComboBox<>();
        spnCantidad = new javax.swing.JSpinner();
        lblCantidad = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnCartelera = new javax.swing.JButton();
        btnComprar = new javax.swing.JButton();
        btnVerCompras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCompras = new javax.swing.JTable();
        btnCalcular1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCompraboletostitle.setText("Compra de Boletos para el Cine");
        getContentPane().add(lblCompraboletostitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        lblFunciones.setText("Funciones :");
        getContentPane().add(lblFunciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        cmbFunciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cmbFunciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));
        getContentPane().add(spnCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));

        lblCantidad.setText("Cantidad:");
        getContentPane().add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        lblTotal.setText("Total:");
        getContentPane().add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, -1, -1));

        btnCartelera.setText("Ver Cartelera");
        btnCartelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarteleraActionPerformed(evt);
            }
        });
        getContentPane().add(btnCartelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, -1, -1));

        btnComprar.setText("Comprar Boletos: ");
        getContentPane().add(btnComprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        btnVerCompras.setText("Ver Compras : ");
        getContentPane().add(btnVerCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, -1, -1));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 320, 110));

        btnCalcular1.setText("Calcular Total");
        getContentPane().add(btnCalcular1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCarteleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarteleraActionPerformed
        VistasCartelera cartelera = new VistasCartelera();
        cartelera.setVisible(true);
    }//GEN-LAST:event_btnCarteleraActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular1;
    private javax.swing.JButton btnCartelera;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnVerCompras;
    private javax.swing.JComboBox<String> cmbFunciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCompraboletostitle;
    private javax.swing.JLabel lblFunciones;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblCompras;
    // End of variables declaration//GEN-END:variables
}
