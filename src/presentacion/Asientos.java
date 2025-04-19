/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import accesoDatos.ConexionSQL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.sql.*;
import java.util.List;


/**
 *
 * @author contr
 */
public class Asientos extends javax.swing.JFrame {
    
    private int cantidadPermitida;
    private int idFuncion;
    private CompraTiquetes parent;
    private java.util.List<String> asientosSeleccionados = new ArrayList<>();
    private JButton[][] asientos;
    
    /**
     * Creates new form Asientos
     */
    public Asientos(int cantidadPermitida, int idFuncion, CompraTiquetes parentWindow) {
        this.cantidadPermitida = cantidadPermitida;
        this.idFuncion = idFuncion;
        this.parent = parentWindow;
        this.parent = parent;
        initComponents();
        
        
        setTitle("Selección de Asientos");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        crearPanelAsientos();

    }
    
    private List<String> obtenerAsientosReservados(int idFuncion) {
    List<String> ocupados = new ArrayList<>();
    try (Connection conn = ConexionSQL.conectar()) {
        String query = "SELECT asientos FROM carrito WHERE ID_Funcion = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, idFuncion);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String[] asientos = rs.getString("asientos").split(",\\s*");
            for (String asiento : asientos) {
                ocupados.add(asiento);
            }
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al obtener asientos reservados: " + e.getMessage());
    }
    return ocupados;
}

    
private void crearPanelAsientos() {
    int filas = 18;
    int columnas = 15;
    String letras = "ABCDEFGHIJKLMNOPQR";
    asientos = new JButton[filas][columnas];

    JPanel panelAsientos = new JPanel(new GridLayout(filas, columnas, 2, 2));

    // 🔹 PRIMERO: Creamos todos los botones y los almacenamos en el array
    for (int fila = 0; fila < filas; fila++) {
        for (int col = 0; col < columnas; col++) {
            String etiqueta = (col + 1) + String.valueOf(letras.charAt(fila));
            JButton boton = new JButton(etiqueta);
            boton.setBackground(Color.GREEN);
            boton.setOpaque(true);
            boton.setBorderPainted(false);

            boton.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                if (b.isEnabled() && b.getBackground().equals(Color.GREEN)) {
                    if (asientosSeleccionados.size() >= cantidadPermitida) {
                        JOptionPane.showMessageDialog(this, "Ya no puedes seleccionar más asientos.");
                        return;
                    }

                    b.setBackground(Color.RED);
                    asientosSeleccionados.add(b.getText());

                    if (asientosSeleccionados.size() == cantidadPermitida) {
                        int confirm = JOptionPane.showConfirmDialog(this, "¿Confirmar asientos seleccionados?", "Confirmación", JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            String seleccion = String.join(", ", asientosSeleccionados);
                            parent.setAsientosSeleccionados(seleccion);
                            this.dispose();
                        } else {
                            // Revertir selección
                            for (String asiento : asientosSeleccionados) {
                                char filaLetra = asiento.charAt(asiento.length() - 1);
                                int filaAsiento = letras.indexOf(filaLetra);
                                int columna = Integer.parseInt(asiento.substring(0, asiento.length() - 1)) - 1;

                                if (filaAsiento >= 0 && columna >= 0 && filaAsiento < asientos.length && columna < asientos[0].length) {
                                    JButton botonReset = asientos[filaAsiento][columna];
                                    botonReset.setBackground(Color.GREEN);
                                    botonReset.setEnabled(true);
                                }
                            }
                            asientosSeleccionados.clear();
                        }
                    }
                } else if (b.getBackground().equals(Color.RED)) {
                    b.setBackground(Color.GREEN);
                    asientosSeleccionados.remove(b.getText());
                }
            });

            asientos[fila][col] = boton;
            panelAsientos.add(boton);
        }
    }

    // 🔹 SEGUNDO: Una vez que todos los botones existen, ahora sí bloqueamos los asientos ocupados
    bloquearAsientosReservados(); // ✅ Moverlo aquí

    add(panelAsientos, BorderLayout.CENTER);
}

    
private void bloquearAsientosReservados() {
    try (Connection conn = ConexionSQL.conectar()) {
        String query = "SELECT asientos FROM carrito WHERE ID_Funcion = ?";
        System.out.println("ID de función usado para buscar asientos ocupados: " + idFuncion);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, idFuncion); // Asegurate de estar usando el mismo idFuncion actual
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String asientoStr = rs.getString("asientos"); // Ejemplo: "1A, 2B, 3C"
            if (asientoStr != null && !asientoStr.isEmpty()) {
                String[] asientosOcupados = asientoStr.split(",\\s*"); // separamos por coma y espacio opcional

                for (String asiento : asientosOcupados) {
                    asiento = asiento.trim();
                    char filaLetra = asiento.charAt(asiento.length() - 1);
                    int fila = "ABCDEFGHIJKLMNOPQR".indexOf(filaLetra);
                    int columna = Integer.parseInt(asiento.substring(0, asiento.length() - 1)) - 1;

                    if (fila >= 0 && columna >= 0 && fila < asientos.length && columna < asientos[0].length) {
                        JButton boton = asientos[fila][columna];
                        boton.setEnabled(false);         // lo desactiva
                        boton.setBackground(Color.RED);  // lo pinta de rojo
                    }
                }
            }
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al bloquear asientos: " + e.getMessage());
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

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
            java.util.logging.Logger.getLogger(Asientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asientos(1,1,null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
