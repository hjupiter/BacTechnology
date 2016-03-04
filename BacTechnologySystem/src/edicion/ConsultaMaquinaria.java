/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edicion;

import conexion.Conexion;
import consultas.InternalData;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angel
 */
public class ConsultaMaquinaria extends javax.swing.JInternalFrame implements ActionListener{
    private DefaultTableModel model;
    private JScrollPane scroll;
    
    private JButton btnEliminar;
    private JButton btnEditar;
    private JButton btnGuardar;
    
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtId;
    
    private int idDato;
    /**
     * Creates new form CosultaMaquinaria
     */
    public ConsultaMaquinaria() {
        
        initComponents();
        llenarTable();
        setVisible(true);
    }
    
    private void llenarTable(){
        model = new DefaultTableModel(null,getColumnas());
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
                CallableStatement datosMaquinaria = conn.prepareCall("{ ? = call DATOS_MAQUINARIA( ? ) }");
                datosMaquinaria.registerOutParameter(1, Types.OTHER);
                datosMaquinaria.setInt(2,idDato);
                datosMaquinaria.execute();
                ResultSet results = (ResultSet)datosMaquinaria.getObject(1);
                Object datos[] = new Object[3];
                while(results.next()){
                    for(int i = 1; i<=3;i++){
                        datos[i-1] = results.getObject(i).toString();
                    }
                }
                crearPanel(datos[1].toString(), datos[2].toString(), datos[0].toString());
                conn.close();
            }catch(Exception e){
                System.out.println("Error: "+e.getMessage());
            }
            
        }
    }
    
    private void crearPanel(String textCodigo, String textNombre, String textId){
        JLabel codigo =  new JLabel("Codigo");
        JLabel nombre =  new JLabel("Nombre");
        
        txtCodigo =  new JTextField();
        txtNombre =  new JTextField();
        txtId =  new JTextField();
        
        btnEliminar = new JButton("Eliminar");
        btnEditar = new JButton("Editar");
        btnGuardar = new JButton("Guardar");
        
        txtCodigo.setText(textCodigo);
        txtNombre.setText(textNombre);
        txtId.setText(textId);
        
        Font font = new Font("Tahoma",Font.BOLD,18);
        Font font1 = new Font("Tahoma",Font.PLAIN,18);
        
        codigo.setFont(font);
        codigo.setBounds(20, 70, 100, 20);
        
        nombre.setFont(font);
        nombre.setBounds(20, 140, 170, 20);
        
        txtId.setFont(font1);
        txtId.setBounds(340,25,30,30);
        txtId.setEditable(false);
        
        txtCodigo.setFont(font1);
        txtCodigo.setBounds(150,70,220,30);
        txtCodigo.setEditable(false);
        
        txtNombre.setFont(font1);
        txtNombre.setBounds(150,140,220,30);
        txtNombre.setEditable(false);
        
        
        btnEliminar.setFont(font1);
        btnEliminar.setBounds(30,210,100,30);
        btnEliminar.addActionListener(this);
        //270
        btnEditar.setFont(font1);
        btnEditar.setBounds(150,210,100,30);
        btnEditar.addActionListener(this);
        
        btnGuardar.setFont(font1);
        btnGuardar.setBounds(270,210,100,30);
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(this);
        
        jPanelDatos.add(nombre);
        jPanelDatos.add(codigo);
        jPanelDatos.add(txtId);
        jPanelDatos.add(txtNombre);
        jPanelDatos.add(txtCodigo);
        jPanelDatos.add(btnEditar);
        jPanelDatos.add(btnEliminar);
        jPanelDatos.add(btnGuardar);
        jPanelDatos.revalidate();
        jPanelDatos.repaint();
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnEliminar) {
            jPanelDatos.removeAll();
            jPanelDatos.revalidate();
            jPanelDatos.repaint();
            Conexion conexion =  new Conexion();
            Connection conn = conexion.Conexion();
            try{
                CallableStatement eliminarMaquinaria = conn.prepareCall("{ ? = call ELIMINAR_MAQUINARIA( ? ) }");
                eliminarMaquinaria.registerOutParameter(1, Types.BOOLEAN);
                eliminarMaquinaria.setInt(2, idDato);
                eliminarMaquinaria.execute();
                conn.close();
            }catch(Exception ex){
                System.out.println("Error: "+ex.getMessage());
            }
            limpiar_tabla();
            llenarTable();
        }
        if(e.getSource()==btnEditar){
            txtCodigo.setEditable(true);
            txtNombre.setEditable(true);
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(true);
            btnEditar.setEnabled(false);
        }
        if(e.getSource()==btnGuardar){
            Conexion conexion =  new Conexion();
            Connection conn = conexion.Conexion();
            try{
                CallableStatement actualizarMaquinaria = conn.prepareCall("{ ? = call ACTUALIZAR_MAQUINARIA( ? , ?, ? ) }");
                actualizarMaquinaria.registerOutParameter(1, Types.BOOLEAN);
                actualizarMaquinaria.setInt(2, idDato);
                actualizarMaquinaria.setString(3, txtCodigo.getText());
                actualizarMaquinaria.setString(4, txtNombre.getText());
                actualizarMaquinaria.execute();
                conn.close();
            }catch(Exception ex){
                System.out.println("Error: "+ex.getMessage());
            }
            
            limpiar_tabla();
            llenarTable();
            btnEliminar.setEnabled(true);
            btnEditar.setEnabled(true);
            btnGuardar.setEnabled(false);
            txtCodigo.setEditable(false);
            txtNombre.setEditable(false);
            JOptionPane.showConfirmDialog(this, "Actualizacion realizada", "Mensaje",JOptionPane.CLOSED_OPTION);
        }
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
            CallableStatement todas_maquinarias =  conn.prepareCall("{ ? = CALL TODAS_MAQUINARIAS ( ) }");
            todas_maquinarias.registerOutParameter(1, Types.OTHER);
            todas_maquinarias.execute();
            ResultSet results = (ResultSet)todas_maquinarias.getObject(1);
            Object datos[] = new Object[2];
            while(results.next()){
                for(int i = 0; i<3;i++){
                    if(i==1){
                        String maquinaCodigo = results.getObject(i).toString();
                        int c = Integer.parseInt(maquinaCodigo);
                        datos[0] = c;
                    }
                    if(i==2){
                        String maquinaCodigo = results.getObject(i).toString();
                        datos[1] = maquinaCodigo;
                    }
                }
                model.addRow(datos);
            }
            todas_maquinarias.close();
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    private String[] getColumnas(){
          String columna[]=new String[]{"id","Codigo"};
          return columna;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanelDatos = new javax.swing.JPanel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Maquinaria");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable);

        javax.swing.GroupLayout jPanelDatosLayout = new javax.swing.GroupLayout(jPanelDatos);
        jPanelDatos.setLayout(jPanelDatosLayout);
        jPanelDatosLayout.setHorizontalGroup(
            jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );
        jPanelDatosLayout.setVerticalGroup(
            jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelDatos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    
}

