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

/**
 *
 * @author Angel
 */
public class InternalUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form Usuario
     */
    public InternalUsuario() {
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
        txtUsuaioNombre = new javax.swing.JTextField();
        txtUsuarioCedula = new javax.swing.JTextField();
        txtUsuarioNick = new javax.swing.JTextField();
        txtUsuarioPass = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Nuevo Usuario");

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

        txtUsuaioNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtUsuarioCedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtUsuarioNick.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtUsuarioPass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

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
                            .addComponent(jLabel1))
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuarioPass, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuarioNick, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuaioNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuarioApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuarioCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuaioNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuarioPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(btnUsuarioCrear)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuarioCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioCrearActionPerformed
        // TODO add your handling code here:
        
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            CallableStatement nuevoUsuario = conn.prepareCall("{ ? = call INSERTAR_USUARIO( ? , ? , ? , ? , ? , ?) }");
            nuevoUsuario.registerOutParameter(1, Types.BOOLEAN);
            nuevoUsuario.setString(2, txtUsuaioNombre.getText());
            nuevoUsuario.setString(3, txtUsuarioApellido.getText());
            nuevoUsuario.setString(4, txtUsuarioCedula.getText());
            nuevoUsuario.setString(5, txtUsuarioNick.getText());
            nuevoUsuario.setString(6, txtUsuarioPass.getText());
            nuevoUsuario.setInt(7, 1);
            nuevoUsuario.execute();
            Boolean c = nuevoUsuario.getBoolean(1);
            System.out.println(c);
            JOptionPane.showConfirmDialog(this, "Usuario creado con exito", "Mensaje",JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            JOptionPane.showConfirmDialog(this, "Fallo al crear Usuario", "Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUsuarioCrearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuarioCrear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtUsuaioNombre;
    private javax.swing.JTextField txtUsuarioApellido;
    private javax.swing.JTextField txtUsuarioCedula;
    private javax.swing.JTextField txtUsuarioNick;
    private javax.swing.JTextField txtUsuarioPass;
    // End of variables declaration//GEN-END:variables
}
