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

        jPanel1 = new javax.swing.JPanel();
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

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFunciones.setText("Funciones del Cine");
        jPanel1.add(lblFunciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 540, 180));

        lblID.setText("ID: ");
        jPanel1.add(lblID, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        cmbPelicula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        lblPeliculas.setText("Películas");
        jPanel1.add(lblPeliculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));
        jPanel1.add(txtSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, -1, -1));

        lblSala.setText("Sala: ");
        jPanel1.add(lblSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, -1, -1));
        jPanel1.add(txtHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        jLabel1.setText("Horario: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, -1, -1));

        btnActualizar.setText("Actualizar");
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        btnEliminar.setText("Eliminar");
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, -1, -1));

        btnAgregar.setText("Agregar");
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, -1, -1));

        btnCargar.setText("Cargar");
        jPanel1.add(btnCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JPanel jPanel1;
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
