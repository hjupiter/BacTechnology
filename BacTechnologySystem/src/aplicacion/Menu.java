/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import consultas.InternalReporte;
import consultas.InternalReporteMolde;
import edicion.ConsultaMaquinaria;
import edicion.ConsultaMolde;
import edicion.ConsultaUsuario;
import imagenes.Icono;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import nuevoElemento.InternalMaquinaria;
import nuevoElemento.InternalMolde;

import nuevoElemento.InternalUsuario;

/**
 *
 * @author Angel
 */
public class Menu extends javax.swing.JFrame {
    
    private ConsultaMolde molde;
    private ConsultaMaquinaria maquinaria;
    private ConsultaUsuario usuario;
    private InternalMolde internalMolde;
    private InternalMaquinaria internalMaquinaria;
    private InternalUsuario InternalUsuario;
    private Acerca ac;
    private InternalReporte r;
    private InternalReporteMolde rMolde;
    Icono icono = Icono.getInstance();
    
    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        icono.setIcono(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DesktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemUsuario = new javax.swing.JMenuItem();
        itemMaquinaria = new javax.swing.JMenuItem();
        itemMolde = new javax.swing.JMenuItem();
        itemUsuarioConsultar = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        itemMaquinariaConsultar = new javax.swing.JMenuItem();
        itemMoldeConsultar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        itemAyuda = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema");
        setExtendedState(6);

        javax.swing.GroupLayout DesktopPaneLayout = new javax.swing.GroupLayout(DesktopPane);
        DesktopPane.setLayout(DesktopPaneLayout);
        DesktopPaneLayout.setHorizontalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 787, Short.MAX_VALUE)
        );
        DesktopPaneLayout.setVerticalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );

        jMenu1.setText("Nuevo");

        itemUsuario.setText("Usuario");
        itemUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUsuarioActionPerformed(evt);
            }
        });
        jMenu1.add(itemUsuario);

        itemMaquinaria.setText("Maquinaria");
        itemMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMaquinariaActionPerformed(evt);
            }
        });
        jMenu1.add(itemMaquinaria);

        itemMolde.setText("Molde");
        itemMolde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMoldeActionPerformed(evt);
            }
        });
        jMenu1.add(itemMolde);

        jMenuBar1.add(jMenu1);

        itemUsuarioConsultar.setText("Consultar");

        jMenuItem1.setText("Usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        itemUsuarioConsultar.add(jMenuItem1);

        itemMaquinariaConsultar.setText("Maquinaria");
        itemMaquinariaConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMaquinariaConsultarActionPerformed(evt);
            }
        });
        itemUsuarioConsultar.add(itemMaquinariaConsultar);

        itemMoldeConsultar.setText("Molde");
        itemMoldeConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMoldeConsultarActionPerformed(evt);
            }
        });
        itemUsuarioConsultar.add(itemMoldeConsultar);

        jMenuBar1.add(itemUsuarioConsultar);

        jMenu2.setText("Reportes");

        jMenuItem4.setText("Maquina y Molde");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Maquinaria / Inyectores");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        itemAyuda.setText("Ayuda");

        jMenuItem2.setText("Acerca");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        itemAyuda.add(jMenuItem2);

        jMenuBar1.add(itemAyuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DesktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DesktopPane)
        );

        setSize(new java.awt.Dimension(800, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void itemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUsuarioActionPerformed
        // TODO add your handling code here:
        try{
            if(!validacion.VentanasActivas.iUsuario){
                InternalUsuario = new InternalUsuario();
                centerJIF(InternalUsuario);
                DesktopPane.add(InternalUsuario);
                validacion.VentanasActivas.iUsuario = true;
                InternalUsuario.setSelected(true);
            }else{
                //JOptionPane.showMessageDialog(this, "La ventana USUARIO ya esta abierta!!");
                InternalUsuario.setSelected(true);
            }
        }catch(Exception e){}
    }//GEN-LAST:event_itemUsuarioActionPerformed

       public void centerJIF(JInternalFrame JIF){
            Dimension desktopSize = DesktopPane.getSize();
            Dimension jInternalFrameSize = JIF.getSize();
            int width = (desktopSize.width - jInternalFrameSize.width) / 2;
            int height = (desktopSize.height - jInternalFrameSize.height) / 2;
            JIF.setLocation(width, height);
       }
    
    private void itemMaquinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMaquinariaActionPerformed
        // TODO add your handling code here:
        try{
            if(!validacion.VentanasActivas.iMaquinaria){
                internalMaquinaria =  new InternalMaquinaria();
                centerJIF(internalMaquinaria);
                DesktopPane.add(internalMaquinaria);
                internalMaquinaria.setSelected(true);
                validacion.VentanasActivas.iMaquinaria = true;
            }else{
                //JOptionPane.showMessageDialog(this, "La ventana MAQUINARIA ya esta abierta!!");
                internalMaquinaria.setSelected(true);
            }
        }catch(Exception e){}
    }//GEN-LAST:event_itemMaquinariaActionPerformed

    private void itemMoldeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMoldeActionPerformed
        // TODO add your handling code here:
        try{
            if(!validacion.VentanasActivas.iMolde){
                internalMolde = new InternalMolde();
                centerJIF(internalMolde);
                DesktopPane.add(internalMolde);
                internalMolde.setSelected(true);
                validacion.VentanasActivas.iMolde = true;
            }else{
                //JOptionPane.showMessageDialog(this, "La ventana MOLDE ya esta abierta!!");
                internalMolde.setSelected(true);
            }
        }catch(Exception e){}
    }//GEN-LAST:event_itemMoldeActionPerformed
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try{
            if(!validacion.VentanasActivas.cUsuario){
                //InternalReporte r = new InternalReporte();
                usuario = new ConsultaUsuario();
                centerJIF(usuario);
                DesktopPane.add(usuario);
                validacion.VentanasActivas.cUsuario = true;
                usuario.setSelected(true);
            }else{
                //JOptionPane.showMessageDialog(this, "La ventana CONLSULTA USUARIO ya esta abierta!!");
                usuario.setSelected(true);
            }
        }catch(Exception e){}
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    
    private void itemMoldeConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMoldeConsultarActionPerformed
        // TODO add your handling code here:
        try{
            if(!validacion.VentanasActivas.cMolde){
                molde =  new ConsultaMolde();
                centerJIF(molde);
                DesktopPane.add(molde);
                validacion.VentanasActivas.cMolde = true;
                molde.setSelected(true);
            }else{
                //JOptionPane.showMessageDialog(this, "La ventana CONSULTA MOLDE ya esta abierta!!");
                molde.setSelected(true);
            }
        }catch(Exception e){}
    }//GEN-LAST:event_itemMoldeConsultarActionPerformed
    
    
    private void itemMaquinariaConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMaquinariaConsultarActionPerformed
        // TODO add your handling code here:
        //boolean ventanaActivaMaquina = ConsultaMaquinaria.ventanaActivaMaquina;
        try{
            if(!validacion.VentanasActivas.cMaquinaria){
                maquinaria =  new ConsultaMaquinaria();
                centerJIF(maquinaria);
                DesktopPane.add(maquinaria);
                maquinaria.setSelected(true);
                validacion.VentanasActivas.cMaquinaria = true;
            }else{
                //JOptionPane.showMessageDialog(this, "La ventana MAQUINARIA ya esta abierta!!");
                maquinaria.setSelected(true);
            }
        }catch(Exception e){}
        
    }//GEN-LAST:event_itemMaquinariaConsultarActionPerformed

    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        try{
            if(!validacion.VentanasActivas.Acerca){
                ac = new Acerca();
                centerJIF(ac);
                DesktopPane.add(ac);
                ac.setSelected(true);
                validacion.VentanasActivas.Acerca = true;
            }else{
                //JOptionPane.showMessageDialog(this, "La ventana ACERCA ya esta abierta!!");
                ac.setSelected(true);
            }
        }catch(Exception e){}
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        try{
            if(!validacion.VentanasActivas.InternalReporte){
                r = new InternalReporte();
                centerJIF(r);
                DesktopPane.add(r);
                r.setSelected(true);
                validacion.VentanasActivas.InternalReporte = true;
            }else{
                //JOptionPane.showMessageDialog(this, "La ventana REPORTE ya esta abierta!!");
                r.setSelected(true);
            }
        }catch(Exception e){}
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        try{
            if(!validacion.VentanasActivas.InternalReporteMolde){
                rMolde = new InternalReporteMolde();
                centerJIF(rMolde);
                DesktopPane.add(rMolde);
                rMolde.setSelected(true);
                validacion.VentanasActivas.InternalReporteMolde = true;
            }else{
                //JOptionPane.showMessageDialog(this, "La ventana REPORTE ya esta abierta!!");
                rMolde.setSelected(true);
            }
        }catch(Exception e){}
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopPane;
    private javax.swing.JMenu itemAyuda;
    private javax.swing.JMenuItem itemMaquinaria;
    private javax.swing.JMenuItem itemMaquinariaConsultar;
    private javax.swing.JMenuItem itemMolde;
    private javax.swing.JMenuItem itemMoldeConsultar;
    private javax.swing.JMenuItem itemUsuario;
    private javax.swing.JMenu itemUsuarioConsultar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    // End of variables declaration//GEN-END:variables
}
