/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

/**
 *
 * @author joaquin
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import obj.Producto;
import obj.Reporte;
import obj.ReporteInterpreter;
import sockets.HiloClienteReportes;

public class ReportesFrm extends javax.swing.JFrame {

    ArrayList<Producto> productos = new ArrayList<>();
    final String HOST = "localhost";
    final int PUERTO = 5000;
    DataInputStream in;
    DataOutputStream out;
    Socket sc = null;

    public ReportesFrm() {
        initComponents();
        Producto producto1 = new Producto(1, "television smart", "22 pulgadas");
        Producto producto2 = new Producto(2, "television smart", "30 pulgadas");
        Producto producto3 = new Producto(3, "television smart", "34 pulgadas");
        Producto producto4 = new Producto(4, "television smart", "40 pulgadas");
        Producto producto5 = new Producto(5, "television smart", "50 pulgadas");

        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
        productos.add(producto4);
        productos.add(producto5);

        this.llenarTablaSeleccionadas();
        try {
            sc = new Socket(HOST, PUERTO);
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ProductosFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
        HiloClienteReportes hilo = new HiloClienteReportes(sc, in, this);
        hilo.start();
    }

    private Producto getNombreZonaSeleccionado() {
        int indiceFilaSeleccionada = this.tblProductos.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tblProductos.getModel();
            int indiceColumnaNombre = 0;
            String nombreSeleccionado = (String) modeloTabla.getValueAt(indiceFilaSeleccionada, indiceColumnaNombre);
            String descripcionSeleccionado = (String) modeloTabla.getValueAt(indiceFilaSeleccionada, indiceColumnaNombre + 1);
            Producto producto = new Producto(nombreSeleccionado, descripcionSeleccionado);
            return producto;
        } else {
            return null;
        }
    }

    private void llenarTablaSeleccionadas() {

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblProductos.getModel();

        modeloTabla.setRowCount(0);
        productos.forEach(producto -> {
            Object[] fila = new Object[2];
            fila[0] = producto.getNombre();
            fila[1] = producto.getDescripcion();
            modeloTabla.addRow(fila);
        });
    }

    public void agregarReporte(Producto producto) {

        productos.add(producto);

        this.llenarTablaSeleccionadas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtComentario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "nombre", "descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        jLabel2.setText("Comentarios:");

        jButton1.setText("enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtComentario)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //reporte de prueba
        Producto producto1 = getNombreZonaSeleccionado();
        if (!txtComentario.getText().equalsIgnoreCase("") && !producto1.equals(null)) {

            Reporte reporte = new Reporte(txtComentario.getText(), producto1);
            reporte.setIdReporte(1);
            try {
                out.writeUTF(ReporteInterpreter.toString(reporte));
                out.flush();

            } catch (IOException ex) {

            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay campo/Producto seleccionado",
                    "Error", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ReportesFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportesFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportesFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportesFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportesFrm().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtComentario;
    // End of variables declaration//GEN-END:variables
}
