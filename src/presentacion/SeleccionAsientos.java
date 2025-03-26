/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import javax.swing.*;
import java.awt.*;
import accesoDatos.ConexionSQL;
import java.sql.*;
/**
 *
 * @author contr
 */
public class SeleccionAsientos extends javax.swing.JFrame {
    
    private JPanel panelAsientos;
    private int idFuncion;
    private final int filas = 18;
    private final int columnas = 20;
    private final String letras = "ABCDEFGHIJKLMNOPQR";
    private JButton[][] asientos = new JButton[filas][columnas];
    /**
     * Creates new form SeleccionAsientos
     */
    public SeleccionAsientos(int idFuncion) {
        this.idFuncion = idFuncion;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        crearPanelAsientos();
        crearMapaAsientos();
        bloquearAsientosReservados();  // <- Aquí consultamos la BD
        
        
                setTitle("Asientos Dispobibles");
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Etiquetas laterales (filas)
        JPanel etiquetasIzquierda = new JPanel(new GridLayout(filas, 1));
        for (int i = 0; i < filas; i++) {
            JLabel lbl = new JLabel(letras.charAt(i) + "", SwingConstants.RIGHT);
            etiquetasIzquierda.add(lbl);
        }

        // Etiquetas superiores (columnas)
        JPanel etiquetasArriba = new JPanel(new GridLayout(1, columnas));
        for (int i = 1; i <= columnas; i++) {
            JLabel lbl = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            etiquetasArriba.add(lbl);
        }

        // Panel principal de asientos
        panelAsientos = new JPanel(new GridLayout(filas, columnas, 2, 2));
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                // Etiqueta tipo "1A", "2A", ..., "33R"
                String etiqueta = (col + 1) + String.valueOf(letras.charAt(fila));
                JButton boton = new JButton(etiqueta); // Mostrar la etiqueta en el botón
                boton.setPreferredSize(new Dimension(30, 30));

                // Colores por zona
                if (fila < 2) {
                    boton.setBackground(Color.GREEN); // VIP
                } else if (fila < 6) {
                    boton.setBackground(Color.GREEN); // Preferencial
                } else {
                    boton.setBackground(Color.GREEN); // General
                }

                boton.setOpaque(true);
                boton.setBorderPainted(false);
                boton.setToolTipText("Asiento: " + etiqueta);
                boton.setActionCommand(etiqueta);

                asientos[fila][col] = boton;
                panelAsientos.add(boton);
            }
        }


        // Contenedor con etiquetas + panel de asientos
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(etiquetasIzquierda, BorderLayout.WEST);
        panelCentral.add(etiquetasArriba, BorderLayout.NORTH);
        panelCentral.add(panelAsientos, BorderLayout.CENTER);

        JPanel leyenda = new JPanel(new FlowLayout(FlowLayout.CENTER));
        leyenda.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));


        // Preferencial
        JLabel preferencialColor = crearColorBox(Color.GREEN);
        leyenda.add(preferencialColor);
        leyenda.add(new JLabel("Asientos"));
        
        add(panelCentral, BorderLayout.CENTER);
        add(leyenda, BorderLayout.SOUTH);
        
    }
    
    
    private void bloquearAsientosReservados() {
    try (Connection conn = ConexionSQL.conectar()) {
        String query = "SELECT Asiento FROM ReservaAsientos WHERE ID_Funcion = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, idFuncion);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String asiento = rs.getString("Asiento");
            char filaLetra = asiento.charAt(asiento.length() - 1);
            int fila = "ABCDEFGHIJKLMNOPQR".indexOf(filaLetra);
            int columna = Integer.parseInt(asiento.substring(0, asiento.length() - 1)) - 1;

            if (fila >= 0 && columna >= 0 && fila < filas && columna < columnas) {
                JButton boton = asientos[fila][columna];
                boton.setEnabled(false);               // Deshabilita botón
                boton.setBackground(Color.GRAY);       // Color gris para ocupado
            }
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar asientos reservados: " + e.getMessage());
    }
}


    private void crearMapaAsientos() {
    JPanel panelAsientos = new JPanel();
    panelAsientos.setLayout(new GridLayout(filas, columnas, 2, 2));

    String letras = "ABCDEFGHIJKLMNOPQR";
    
    for (int fila = 0; fila < filas; fila++) {
        for (int col = 0; col < columnas; col++) {
            String etiqueta = (col + 1) + String.valueOf(letras.charAt(fila));
            JButton boton = new JButton(etiqueta);
            boton.setPreferredSize(new Dimension(50, 30));
            boton.setFont(new Font("Arial", Font.PLAIN, 6));
            boton.setBackground(Color.GREEN);
            String label = letras.charAt(fila) + String.valueOf(col + 1);
            boton.setToolTipText("Asiento: " + etiqueta);
            boton.setActionCommand(etiqueta);

            // Acción al hacer clic
            boton.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                if (b.getBackground() == Color.GREEN) {
                    b.setBackground(Color.RED); // Seleccionado
                } else {
                    b.setBackground(Color.GREEN); // Deseleccionado
                }
            });

            asientos[fila][col] = boton;
            panelAsientos.add(boton);
        }
    }

    JScrollPane scroll = new JScrollPane(panelAsientos);
    getContentPane().add(scroll);
    pack();
    setLocationRelativeTo(null);
}
    
    private void crearPanelAsientos() {
    // Etiquetas laterales (filas)
    JPanel etiquetasIzquierda = new JPanel(new GridLayout(filas, 1));
    for (int i = 0; i < filas; i++) {
        JLabel lbl = new JLabel(letras.charAt(i) + "", SwingConstants.RIGHT);
        etiquetasIzquierda.add(lbl);
    }

    // Etiquetas superiores (columnas)
    JPanel etiquetasArriba = new JPanel(new GridLayout(1, columnas));
    for (int i = 1; i <= columnas; i++) {
        JLabel lbl = new JLabel(String.valueOf(i), SwingConstants.CENTER);
        etiquetasArriba.add(lbl);
    }

    // Panel principal de asientos
    panelAsientos = new JPanel(new GridLayout(filas, columnas, 2, 2));
    for (int fila = 0; fila < filas; fila++) {
        for (int col = 0; col < columnas; col++) {
            String etiqueta = (col + 1) + String.valueOf(letras.charAt(fila));
            JButton boton = new JButton(etiqueta);
            boton.setPreferredSize(new Dimension(30, 30));
            boton.setBackground(Color.GREEN); // Por defecto
            boton.setOpaque(true);
            boton.setBorderPainted(false);
            boton.setToolTipText("Asiento: " + etiqueta);
            boton.setActionCommand(etiqueta);

            asientos[fila][col] = boton;
            panelAsientos.add(boton);
        }
    }

    JPanel panelCentral = new JPanel(new BorderLayout());
    panelCentral.add(etiquetasIzquierda, BorderLayout.WEST);
    panelCentral.add(etiquetasArriba, BorderLayout.NORTH);
    panelCentral.add(panelAsientos, BorderLayout.CENTER);

    JPanel leyenda = new JPanel(new FlowLayout(FlowLayout.CENTER));
    leyenda.add(crearColorBox(Color.GREEN));
    leyenda.add(new JLabel("Asiento disponible"));
    leyenda.add(crearColorBox(Color.GRAY));
    leyenda.add(new JLabel("Asiento reservado"));

    add(panelCentral, BorderLayout.CENTER);
    add(leyenda, BorderLayout.SOUTH);
}

    

    
    private JLabel crearColorBox(Color color) {
    JLabel label = new JLabel();
    label.setOpaque(true);
    label.setBackground(color);
    label.setPreferredSize(new Dimension(20, 20));
    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return label;
}

    
    
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
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new CompraBoletosWindow().setVisible(true);
        }
    });
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
