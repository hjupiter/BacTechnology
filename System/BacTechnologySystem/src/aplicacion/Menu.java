/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import nuevoElemento.InternalMaquinaria;
import nuevoElemento.InternalMolde;

import nuevoElemento.InternalUsuario;

/**
 *
 * @author Angel
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
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

        jMenu1.setText("Archivo");

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
        InternalUsuario usuario = new InternalUsuario();
        //Usuario usuario = new Usuario();
        
        centerJIF(usuario);
        
        DesktopPane.add(usuario);
        
        try {
            usuario.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        InternalMaquinaria maquinaria =  new InternalMaquinaria();
        centerJIF(maquinaria);
        DesktopPane.add(maquinaria);
        
        try {
            maquinaria.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemMaquinariaActionPerformed

    private void itemMoldeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMoldeActionPerformed
        // TODO add your handling code here:
        InternalMolde molde = new InternalMolde();
        centerJIF(molde);
        DesktopPane.add(molde);
        
        try {
            molde.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemMoldeActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
        InternalReporte r = new InternalReporte();
        centerJIF(r);
        DesktopPane.add(r);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void itemMoldeConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMoldeConsultarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemMoldeConsultarActionPerformed

    private void itemMaquinariaConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMaquinariaConsultarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemMaquinariaConsultarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopPane;
    private javax.swing.JMenuItem itemMaquinaria;
    private javax.swing.JMenuItem itemMaquinariaConsultar;
    private javax.swing.JMenuItem itemMolde;
    private javax.swing.JMenuItem itemMoldeConsultar;
    private javax.swing.JMenuItem itemUsuario;
    private javax.swing.JMenu itemUsuarioConsultar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
