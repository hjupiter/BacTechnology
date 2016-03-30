/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edicion;

import conexion.Conexion;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angel
 */
public class ConsultaUsuario extends javax.swing.JInternalFrame implements ActionListener{
    
    public static boolean ventanaActivaConsultaUsuario = false;
    
    private Conexion conn;
    private DefaultTableModel model;
    private JScrollPane scroll;
    
    private int idDato;
    private int tipoUsuario;
    
    
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCedula;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JComboBox cmbTipo;
    
    private JButton btnEliminar;
    private JButton btnEditar;
    private JButton btnGuardar;
    /**
     * Creates new form ConsultaUsuario
     */
    public ConsultaUsuario() {
        ventanaActivaConsultaUsuario = true;
        conn = new Conexion();
        initComponents();
        llenarTable();
        jTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        setVisible(true);
    }
    
    private void llenarTable(){
        model = new DefaultTableModel(null,getColumnas()){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setFilas();
        jTable.setModel(model);
        setEventoMouseClicked(jTable);
    }
    
    private void setEventoMouseClicked(JTable tbl){
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
 
            @Override
            public void mouseClicked(MouseEvent e) {
                jPanelDatos.removeAll();
                jPanelDatos.revalidate();
                jPanelDatos.repaint();
                tblEjemploMouseClicked(e);
            }
        });
    }
    
    private void cargarDatosPanel(int posicion){
        jPanelDatos.removeAll();
        jPanelDatos.revalidate();
        jPanelDatos.repaint();
        String codigo,nombre,id;
        int row = posicion;
        if (row >= 0 && jTable.isEnabled()){
            id = model.getValueAt(row,0).toString();
            idDato = Integer.parseInt(id);
            System.out.println("---------------"+idDato);
            Conexion conexion =  new Conexion();
            Connection conn = conexion.Conexion();
            try{
                conn.setAutoCommit(false);
                CallableStatement datosMaquinaria = conn.prepareCall("{ ? = call DATOS_USUARIO( ? ) }");
                datosMaquinaria.registerOutParameter(1, Types.OTHER);
                datosMaquinaria.setInt(2,idDato);
                datosMaquinaria.execute();
                ResultSet results = (ResultSet)datosMaquinaria.getObject(1);
                Object datos[] = new Object[8];
                while(results.next()){
                    for(int i = 1; i<=7;i++){
                        datos[i-1] = results.getObject(i).toString();
                    }
                }
                crearPanel(datos[0].toString(), datos[1].toString(), datos[2].toString(), datos[3].toString(), datos[4].toString(), datos[5].toString(), Integer.parseInt(datos[6].toString()));
                conn.close();
            }catch(Exception e){
                System.out.println("Error: "+e.getMessage());
            }
            
        }
    }
    
    private void tblEjemploMouseClicked(java.awt.event.MouseEvent evt) {
        String codigo,nombre,id;
        int row = jTable.rowAtPoint(evt.getPoint());
        if (row >= 0 && jTable.isEnabled()){
            id = model.getValueAt(row,0).toString();
            idDato = Integer.parseInt(id);
            Conexion conexion =  new Conexion();
            Connection conn = conexion.Conexion();
            try{
                conn.setAutoCommit(false);
                CallableStatement datosMaquinaria = conn.prepareCall("{ ? = call DATOS_USUARIO( ? ) }");
                datosMaquinaria.registerOutParameter(1, Types.OTHER);
                datosMaquinaria.setInt(2,idDato);
                datosMaquinaria.execute();
                ResultSet results = (ResultSet)datosMaquinaria.getObject(1);
                Object datos[] = new Object[8];
                while(results.next()){
                    for(int i = 1; i<=7;i++){
                        datos[i-1] = results.getObject(i).toString();
                    }
                }
                crearPanel(datos[0].toString(), datos[1].toString(), datos[2].toString(), datos[3].toString(), datos[4].toString(), datos[5].toString(), Integer.parseInt(datos[6].toString()));
                conn.close();
            }catch(Exception e){
                System.out.println("Error: "+e.getMessage());
            }
            
        }
    }
    
    
    private void crearPanel(String id, String nombre, String apellido, String cedula, String usuario, String contrasena, int tipo){
        
        
        JLabel lblNombre =  new JLabel("Nombre");
        JLabel lblApellido =  new JLabel("Apellido");
        JLabel lblCedula =  new JLabel("Cedula");
        JLabel lblUsuario =  new JLabel("Usuario");
        JLabel lblContrasena =  new JLabel("Contrasena");
        JLabel lblTipo = new JLabel("Tipo");
        
        txtId =  new JTextField();
        txtNombre =  new JTextField();
        txtApellido =  new JTextField();
        txtCedula =  new JTextField();
        txtUsuario =  new JTextField();
        txtContrasena =  new JPasswordField();
        cmbTipo = new JComboBox();
        
        //txtNombre =  new JTextField();
        
        
        btnEliminar = new JButton("Eliminar");
        btnEditar = new JButton("Editar");
        btnGuardar = new JButton("Guardar");
        
        txtId.setText(id);
        txtApellido.setText(apellido);
        txtNombre.setText(nombre);
        txtCedula.setText(cedula);
        txtUsuario.setText(usuario);
        txtContrasena.setText(contrasena);
        List<String> ls = new ArrayList<String>(); 
        tipoUsuario = tipo;
        if(tipo==1){
            ls.add("Usuario");
            cmbTipo.setModel(new DefaultComboBoxModel(ls.toArray()));
            //cmbTipo.setSelectedIndex(1);
        }
        else{
            ls.add("Administrador");
            cmbTipo.setModel(new DefaultComboBoxModel(ls.toArray()));
            //cmbTipo.setSelectedIndex(0);
        }
        
        Font font = new Font("Tahoma",Font.BOLD,18);
        Font font1 = new Font("Tahoma",Font.PLAIN,18);
        
        lblNombre.setFont(font);
        lblNombre.setBounds(20, 70, 170, 20);
        lblApellido.setFont(font);
        lblApellido.setBounds(20, 140, 100, 20);
        lblCedula.setFont(font);
        lblCedula.setBounds(20, 210, 100, 20);
        lblUsuario.setFont(font);
        lblUsuario.setBounds(20, 280, 100, 20);
        lblContrasena.setFont(font);
        lblContrasena.setBounds(20, 350, 150, 20);
        lblTipo.setFont(font);
        lblTipo.setBounds(20, 420, 100, 20);
        
        txtId.setFont(font1);
        txtId.setBounds(340,25,30,30);
        txtId.setEditable(false);
        
        txtNombre.setFont(font1);
        txtNombre.setBounds(200,70,220,30);
        txtNombre.setEditable(false);
        txtApellido.setFont(font1);
        txtApellido.setBounds(200,140,220,30);
        txtApellido.setEditable(false);
        txtCedula.setFont(font1);
        txtCedula.setBounds(200,210,220,30);
        txtCedula.setEditable(false);
        txtUsuario.setFont(font1);
        txtUsuario.setBounds(200,280,220,30);
        txtUsuario.setEditable(false);
        txtContrasena.setFont(font1);
        txtContrasena.setBounds(200,350,220,30);
        txtContrasena.setEditable(false);
        cmbTipo.setFont(font1);
        cmbTipo.setBounds(200,420,220,30);
        cmbTipo.setEditable(false);
        
        btnEliminar.setFont(font1);
        btnEliminar.setBounds(30,480,100,30);
        btnEliminar.addActionListener(this);
        //270
        btnEditar.setFont(font1);
        btnEditar.setBounds(150,480,100,30);
        btnEditar.addActionListener(this);
        
        btnGuardar.setFont(font1);
        btnGuardar.setBounds(270,480,100,30);
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(this);
        
        //jPanelDatos.add(txtId);
        jPanelDatos.add(lblNombre);
        jPanelDatos.add(lblApellido);
        jPanelDatos.add(lblCedula);
        jPanelDatos.add(lblUsuario);
        jPanelDatos.add(lblContrasena);
        jPanelDatos.add(lblTipo);
        jPanelDatos.add(txtNombre);
        jPanelDatos.add(txtApellido);
        jPanelDatos.add(txtCedula);
        jPanelDatos.add(txtUsuario);
        jPanelDatos.add(txtContrasena);
        jPanelDatos.add(cmbTipo);
        jPanelDatos.add(btnEditar);
        jPanelDatos.add(btnEliminar);
        jPanelDatos.add(btnGuardar);
        jPanelDatos.revalidate();
        jPanelDatos.repaint();
    }

    
    public void limpiar_tabla(){
        for (int i = 0; i < jTable.getRowCount(); i++) {
           model.removeRow(i);
           i-=1;
        }
    }
    
    private void setFilas() {
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            conn.setAutoCommit(false);
            CallableStatement todas_usuarios =  conn.prepareCall("{ ? = CALL TODOS_USUARIOS ( ) }");
            todas_usuarios.registerOutParameter(1, Types.OTHER);
            todas_usuarios.execute();
            ResultSet results = (ResultSet)todas_usuarios.getObject(1);
            Object datos[] = new Object[2];
            while(results.next()){
                String nombre = "";
                for(int i = 0; i<=3;i++){
                    if(i==1){
                        String maquinaCodigo = results.getObject(i).toString();
                        int c = Integer.parseInt(maquinaCodigo);
                        datos[0] = c;
                    }
                    if(i==2){
                        nombre = results.getObject(i).toString();
                        //datos[1] = maquinaCodigo;
                    }
                    if(i==3){
                        nombre = nombre + " " + results.getObject(i).toString();
                        datos[1] = nombre;
                    }
                }
                nombre="";
                model.addRow(datos);
            }
            todas_usuarios.close();
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    private String[] getColumnas(){
          String columna[]=new String[]{"id","Usuario"};
          return columna;
    }
    
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnEliminar) {
            jPanelDatos.removeAll();
            jPanelDatos.revalidate();
            jPanelDatos.repaint();
            Conexion conexion =  new Conexion();
            Connection conn = conexion.Conexion();
            try{
                CallableStatement eliminarUsuario = conn.prepareCall("{ ? = call ELIMINAR_USUARIO( ? ) }");
                eliminarUsuario.registerOutParameter(1, Types.BOOLEAN);
                eliminarUsuario.setInt(2, idDato);
                eliminarUsuario.execute();
                conn.close();
            }catch(Exception ex){
                System.out.println("Error: "+ex.getMessage());
            }
            limpiar_tabla();
            llenarTable();
        }
        if(e.getSource()==btnEditar){
            txtNombre.setEditable(true);
            txtApellido.setEditable(true);
            txtCedula.setEditable(true);
            txtUsuario.setEditable(true);
            txtContrasena.setEditable(true);
            cmbTipo.removeAllItems();
            List<String> ls = new ArrayList<String>();
            ls.add("Usuario");
            ls.add("Administrador");
            cmbTipo.setModel(new DefaultComboBoxModel(ls.toArray()));
            if(tipoUsuario == 1){
                cmbTipo.setSelectedIndex(0);
            }
            else{
                cmbTipo.setSelectedIndex(1);
            }
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(true);
            btnEditar.setEnabled(false);
        }
        if(e.getSource()==btnGuardar){
            Conexion conexion =  new Conexion();
            Connection conn = conexion.Conexion();
            try{
                CallableStatement actualizarUsuario = conn.prepareCall("{ ? = call ACTUALIZAR_USUARIO( ? , ? , ? , ? , ? , ? , ?) }");
                actualizarUsuario.registerOutParameter(1, Types.BOOLEAN);
                actualizarUsuario.setInt(2, idDato);
                actualizarUsuario.setString(3, txtNombre.getText().toUpperCase());
                actualizarUsuario.setString(4, txtApellido.getText().toUpperCase());
                actualizarUsuario.setString(5, txtCedula.getText());
                actualizarUsuario.setString(6, txtUsuario.getText());
                actualizarUsuario.setString(7, txtContrasena.getText());
                actualizarUsuario.setInt(8, cmbTipo.getSelectedIndex()+1);
                actualizarUsuario.execute();
                conn.close();
            }catch(Exception ex){
                System.out.println("Error: "+ex.getMessage());
            }
            
            limpiar_tabla();
            llenarTable();
            
            btnEliminar.setEnabled(true);
            btnEditar.setEnabled(true);
            btnGuardar.setEnabled(false);
            
            txtNombre.setEditable(false);
            txtApellido.setEditable(false);
            txtCedula.setEditable(false);
            txtUsuario.setEditable(false);
            txtContrasena.setEditable(false);
            cmbTipo.setEditable(false);
            cmbTipo.removeAllItems();
            List<String> ls = new ArrayList<String>();
            if(cmbTipo.getSelectedIndex() == 0){
                ls.add("Usuario");
                cmbTipo.setModel(new DefaultComboBoxModel(ls.toArray()));
            }
            else{
                ls.add("Administrador");
                cmbTipo.setModel(new DefaultComboBoxModel(ls.toArray()));
            }
            
            JOptionPane.showConfirmDialog(this, "Actualizacion realizada", "Mensaje",JOptionPane.CLOSED_OPTION);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelDatos = new javax.swing.JPanel();

        setClosable(true);
        setTitle("Usuario");
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

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanelDatosLayout = new javax.swing.GroupLayout(jPanelDatos);
        jPanelDatos.setLayout(jPanelDatosLayout);
        jPanelDatosLayout.setHorizontalGroup(
            jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
        );
        jPanelDatosLayout.setVerticalGroup(
            jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator1)
            .addComponent(jPanelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validacion.VentanasActivas.cUsuario = false;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyPressed
        // TODO add your handling code here:
        int keyCode = evt.getKeyCode();
        switch( keyCode ) { 
            case KeyEvent.VK_UP:
                if(jTable.getSelectedRow()-1>0)
                    cargarDatosPanel(jTable.getSelectedRow()-1);
                break;
            case KeyEvent.VK_DOWN:
                if(jTable.getSelectedRow()+1<jTable.getRowCount())
                    cargarDatosPanel(jTable.getSelectedRow()+1);
                break;
            case KeyEvent.VK_PAGE_UP:
                if(jTable.getSelectedRow()-21>0)
                    cargarDatosPanel(jTable.getSelectedRow()-21);
                if(jTable.getSelectedRow()-21<0)
                    cargarDatosPanel(0);
                break;
            case KeyEvent.VK_PAGE_DOWN:
                if(jTable.getSelectedRow()+21<jTable.getRowCount())
                    cargarDatosPanel(jTable.getSelectedRow()+21);
                if(jTable.getSelectedRow()+21>jTable.getRowCount())
                    cargarDatosPanel(jTable.getRowCount()-1);
                break;
        }
    }//GEN-LAST:event_jTableKeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        String keypressed = jTextField1.getText().toUpperCase();
        
        limpiar_tabla();
        System.out.println(keypressed);
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            conn.setAutoCommit(false);
            CallableStatement busqueda_molde =  conn.prepareCall("{ ? = CALL BUSQUEDA_USUARIO ( ? ) }");
            busqueda_molde.registerOutParameter(1, Types.OTHER);
            busqueda_molde.setString(2, keypressed);
            busqueda_molde.execute();
            ResultSet results = (ResultSet)busqueda_molde.getObject(1);
            Object datos[] = new Object[3];
            while(results.next()){
                String nombre = "";
                for(int i = 0; i<=3;i++){
                    if(i==1){
                        String maquinaCodigo = results.getObject(i).toString();
                        int c = Integer.parseInt(maquinaCodigo);
                        datos[0] = c;
                    }
                    if(i==2){
                        nombre = results.getObject(i).toString();
                        //datos[1] = maquinaCodigo;
                    }
                    if(i==3){
                        nombre = nombre + " " + results.getObject(i).toString();
                        datos[1] = nombre;
                    }
                }
                nombre="";
                model.addRow(datos);
            }
            busqueda_molde.close();
            conn.close();
            
            jTable.setModel(model);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jTextField1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelDatos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
