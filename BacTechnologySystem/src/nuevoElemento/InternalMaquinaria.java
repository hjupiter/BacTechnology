/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevoElemento;

import conexion.Conexion;
import consultas.InternalReporte;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import javax.swing.JOptionPane;

import validacion.ValidacionMaquinaria;

/**
 *
 * @author Angel
 */
public class InternalMaquinaria extends javax.swing.JInternalFrame {

    public static boolean ventanaActivaMaquinaria;
    private ValidacionMaquinaria valMaquinaria;
    /**
     * Creates new form InternalMaquinaria
     */
    public InternalMaquinaria() {
        ventanaActivaMaquinaria = true;
        valMaquinaria = new ValidacionMaquinaria();
        initComponents();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMaquinariaCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtMaquinariaNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnMaquinariaCrear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Nueva Maquinaria");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        txtMaquinariaCodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Codigo");

        txtMaquinariaNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Nombre");

        btnMaquinariaCrear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMaquinariaCrear.setText("Crear");
        btnMaquinariaCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaquinariaCrearActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setText("CREACIÓN DE MAQUINARIA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMaquinariaCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(84, 84, 84)
                            .addComponent(txtMaquinariaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaquinariaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(105, 105, 105))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaquinariaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaquinariaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(33, 33, 33)
                .addComponent(btnMaquinariaCrear)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMaquinariaCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaquinariaCrearActionPerformed
        // TODO add your handling code here:
        if(valMaquinaria.camposVacios(txtMaquinariaCodigo, txtMaquinariaNombre) == false){
            Conexion conexion =  new Conexion();
            Connection conn = conexion.Conexion();
            try{
                CallableStatement insertar_maquinaria =  conn.prepareCall("{ ? = call INSERTAR_MAQUINARIA ( ? , ? ) }");
                insertar_maquinaria.registerOutParameter(1, Types.BOOLEAN);
                insertar_maquinaria.setString(2, txtMaquinariaCodigo.getText());
                insertar_maquinaria.setString(3, txtMaquinariaNombre.getText().toUpperCase());
                insertar_maquinaria.execute();
                boolean res = insertar_maquinaria.getBoolean(1);
                insertar_maquinaria.close();
                if(res == true){
                    JOptionPane.showConfirmDialog(this, "Maquinaria creado con exito", "Mensaje",JOptionPane.CLOSED_OPTION);
                }
                else{
                    JOptionPane.showConfirmDialog(this, "La Maquinaria ya existe", "Mensaje",JOptionPane.CLOSED_OPTION);
                }
                
            }catch(Exception e){
                System.out.println("Error: "+e.getMessage());
                JOptionPane.showConfirmDialog(this, "No de be dejar campos vacios", "Error",JOptionPane.CLOSED_OPTION);
            }
        }
        else{
            JOptionPane.showConfirmDialog(this, "Fallo al crear Maquinaria", "Error",JOptionPane.CLOSED_OPTION);
        }
        
    }//GEN-LAST:event_btnMaquinariaCrearActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        //ventanaActivaMaquinaria = false;
        validacion.VentanasActivas.iMaquinaria = false;
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMaquinariaCrear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtMaquinariaCodigo;
    private javax.swing.JTextField txtMaquinariaNombre;
    // End of variables declaration//GEN-END:variables
}
