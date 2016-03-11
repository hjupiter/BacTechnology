/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevoElemento;

import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import javax.swing.JOptionPane;

import validacion.ValidacionUsuario;
import validacion.General;

/**
 *
 * @author Angel
 */
public class InternalUsuario extends javax.swing.JInternalFrame {
    private General val;
    private ValidacionUsuario valUsu;
    public static boolean ventanaActivaUsuario = false;
    /**
     * Creates new form Usuario
     */
    public InternalUsuario() {
        ventanaActivaUsuario = true;
        val = new General();
        valUsu =  new ValidacionUsuario();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnUsuarioCrear = new javax.swing.JButton();
        txtUsuarioApellido = new javax.swing.JTextField();
        txtUsuarioNombre = new javax.swing.JTextField();
        txtUsuarioCedula = new javax.swing.JTextField();
        txtUsuarioNick = new javax.swing.JTextField();
        txtUsuarioPass = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Nuevo Usuario");
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Apellido");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Cedula");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Usuario");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Contrasena");

        btnUsuarioCrear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUsuarioCrear.setText("Crear");
        btnUsuarioCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioCrearActionPerformed(evt);
            }
        });

        txtUsuarioApellido.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsuarioApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioApellidoKeyTyped(evt);
            }
        });

        txtUsuarioNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsuarioNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioNombreKeyTyped(evt);
            }
        });

        txtUsuarioCedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsuarioCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioCedulaKeyTyped(evt);
            }
        });

        txtUsuarioNick.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsuarioNick.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioNickKeyTyped(evt);
            }
        });

        txtUsuarioPass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Tipo");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Usuario", "Administrador" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setText("CREACIÓN DE USUARIO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUsuarioCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuarioPass, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuarioNick, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuarioNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuarioApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuarioCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(124, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuarioNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuarioApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuarioCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuarioNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuarioPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(btnUsuarioCrear)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuarioCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioCrearActionPerformed
        // TODO add your handling code here:
        if(valUsu.camposVacios(txtUsuarioApellido, txtUsuarioApellido, txtUsuarioCedula, txtUsuarioPass, txtUsuarioNick) == false){
            Conexion conexion =  new Conexion();
            Connection conn = conexion.Conexion();
            try{
                CallableStatement nuevoUsuario = conn.prepareCall("{ ? = call INSERTAR_USUARIO( ? , ? , ? , ? , ? , ?) }");
                nuevoUsuario.registerOutParameter(1, Types.BOOLEAN);
                nuevoUsuario.setString(2, txtUsuarioNombre.getText());
                nuevoUsuario.setString(3, txtUsuarioApellido.getText());
                nuevoUsuario.setString(4, txtUsuarioCedula.getText());
                nuevoUsuario.setString(5, txtUsuarioNick.getText());
                nuevoUsuario.setString(6, txtUsuarioPass.getText());
                nuevoUsuario.setInt(7, 1);
                nuevoUsuario.execute();
                Boolean c = nuevoUsuario.getBoolean(1);
                System.out.println(c);
                if(c == true){
                    JOptionPane.showConfirmDialog(this, "Usuario creado con exito", "Mensaje",JOptionPane.CLOSED_OPTION);
                }
                else{
                    JOptionPane.showConfirmDialog(this, "El usuario ya existe", "Mensaje",JOptionPane.CLOSED_OPTION);
                }

            }catch(Exception e){
                System.out.println("Error: "+e.getMessage());
                JOptionPane.showConfirmDialog(this, "Fallo al crear Usuario", "Error",JOptionPane.CLOSED_OPTION);
            }
        }
        else{
            JOptionPane.showConfirmDialog(this, "No debe dejar campos vacios", "Error",JOptionPane.CLOSED_OPTION);
        }
        
    }//GEN-LAST:event_btnUsuarioCrearActionPerformed

    private void txtUsuarioNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioNombreKeyTyped
        // TODO add your handling code here:
        val.soloLetras(evt);
    }//GEN-LAST:event_txtUsuarioNombreKeyTyped

    private void txtUsuarioApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioApellidoKeyTyped
        // TODO add your handling code here:
        val.soloLetras(evt);
    }//GEN-LAST:event_txtUsuarioApellidoKeyTyped

    private void txtUsuarioNickKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioNickKeyTyped
        // TODO add your handling code here:
        val.soloLetras(evt);
    }//GEN-LAST:event_txtUsuarioNickKeyTyped

    private void txtUsuarioCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioCedulaKeyTyped
        // TODO add your handling code here:
        val.soloNumeros(evt);
    }//GEN-LAST:event_txtUsuarioCedulaKeyTyped

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        ventanaActivaUsuario = false;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuarioCrear;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtUsuarioApellido;
    private javax.swing.JTextField txtUsuarioCedula;
    private javax.swing.JTextField txtUsuarioNick;
    private javax.swing.JTextField txtUsuarioNombre;
    private javax.swing.JTextField txtUsuarioPass;
    // End of variables declaration//GEN-END:variables
}
