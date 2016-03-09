/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas;

import conexion.Conexion;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DateFormat;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Angel
 */
public class InternalReporte extends javax.swing.JInternalFrame {

    public static boolean ventanaActivaReporteUsuario = false;
    DateFormat df = DateFormat.getDateInstance();
    private static final String rutaArchivo = "C://data//data.xlsx";
    private static final File archivo = new File(rutaArchivo);
    private String usuario;
    private String maquinaria;
    private String molde;
    private String novedad;
    /**
     * Creates new form InternalReporte
     */
    DefaultTableModel model;
    JScrollPane scroll;
    public InternalReporte() {
        ventanaActivaReporteUsuario = true;
        initComponents();
        llenarTable();
        llenarComboMolde();
        llenaComboNovedad();
        llenarComboMaquina();
        llenarComboUsuarioNombre();
        comboBoxMaquinaria.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String seleccionado=(String)comboBoxMaquinaria.getSelectedItem();
                txtMaquinaria.setText(seleccionado);
            }
        });
        comboBoxMolde.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String seleccionado = (String)comboBoxMolde.getSelectedItem();
                txtMolde.setText(seleccionado);
            }
        });
        comboBoxNovedad.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String seleccionado = (String)comboBoxNovedad.getSelectedItem();
                txtNovedad.setText(seleccionado);
            }
        });
        comboBoxUsuario.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String seleccionado = (String)comboBoxUsuario.getSelectedItem();
                txtUsuario.setText(seleccionado);
            }
        });

        
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
                tblEjemploMouseClicked(e);
            }
        });
    }
    
    private void tblEjemploMouseClicked(java.awt.event.MouseEvent evt) {
 
       String id="";
       String fecha = "";
       String maquinaria = "";
       String molde = "";
       String Usuario = "";
       String descripcion = "";
       String tipo_novedad = "";
       String descripcion_novedad = "";
       String solucion = "";
       String novedad = "";
 
        int row = jTable.rowAtPoint(evt.getPoint());
        if (row >= 0 && jTable.isEnabled())
        {
            //for (int i=0; i < model.getColumnCount();i++)
            //{
               //cadena=cadena + " " +  model.getValueAt(row,i).toString();
                id = model.getValueAt(row,0).toString();
                fecha = model.getValueAt(row,1).toString();
                maquinaria = model.getValueAt(row,2).toString();
                molde = model.getValueAt(row,3).toString();
                Usuario = model.getValueAt(row,4).toString();
                descripcion = model.getValueAt(row,5).toString();
                tipo_novedad = model.getValueAt(row,6).toString();
                descripcion_novedad = model.getValueAt(row,7).toString();
                solucion = model.getValueAt(row,8).toString();
                novedad = model.getValueAt(row,9).toString();
                System.out.println(descripcion+" "+descripcion_novedad);
                InternalData d = new InternalData(id, fecha, maquinaria, molde, Usuario, descripcion, tipo_novedad, descripcion_novedad, solucion, novedad);
                
            //}
        }
 
        //JOptionPane.showMessageDialog(null, fecha);
    }
    
    private String[] getColumnas(){
          String columna[]=new String[]{"1","Fecha","Maquinaria","Molde","Usuario","Descripcion","Tipo Novedad","Descripcion","Solucion","Novedad"};
          return columna;
    }
    /*
    private void llenarTable(){
        scroll = new JScrollPane();
        
        model = new DefaultTableModel(null,getColumnas());
        setFilas();
        table.setModel(model);
        scroll.add(table);
        this.add(scroll);
        this.setSize(500,200);
        scroll.setViewportView(table);
    }
    
    private String[] getColumnas()
    {
          String columna[]=new String[]{"1","Fecha","Maquinaria","Molde","Usuario","Descripcion","Tipo Novedad","Descripcion","Solucion","Novedad","Imagen"};
          return columna;
    }
 */
    private void setFilas(){
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            conn.setAutoCommit(false);
            CallableStatement todas_reportes =  conn.prepareCall("{ ? = CALL TODOS_REPORTES ( ) }");
            todas_reportes.registerOutParameter(1, Types.OTHER);
            todas_reportes.execute();
            ResultSet results = (ResultSet)todas_reportes.getObject(1);
            Object datos[] = new Object[12];
            while(results.next()){
                for(int i = 0; i<11;i++){
                    if(i==2){
                        String x = results.getObject(i+1).toString();
                        int entero = Integer.parseInt(x);
                        CallableStatement buscar = conn.prepareCall("{ ? = call buscar_maquina_id (?) }");
                        buscar.registerOutParameter(1, Types.VARCHAR);
                        buscar.setInt(2, entero);
                        buscar.execute();
                        String maquina = buscar.getString(1);
                        System.out.println("1 "+maquina);
                        buscar.close();
                        datos[i] = maquina;
                    }
                    else if(i==3){
                        String x = results.getObject(i+1).toString();
                        int entero = Integer.parseInt(x);
                        CallableStatement buscar = conn.prepareCall("{ ? = call buscar_molde_id (?) }");
                        buscar.registerOutParameter(1, Types.VARCHAR);
                        buscar.setInt(2, entero);
                        buscar.execute();
                        String molde = buscar.getString(1);
                        System.out.println("2 "+molde);
                        buscar.close();
                        datos[i] = molde;
                    }
                    else if(i==4){
                        String x = results.getObject(i+1).toString();
                        int entero = Integer.parseInt(x);
                        CallableStatement buscar = conn.prepareCall("{ ? = call buscar_usuario_id (?) }");
                        buscar.registerOutParameter(1, Types.VARCHAR);
                        buscar.setInt(2, entero);
                        buscar.execute();
                        String usuario = buscar.getString(1);
                        System.out.println("3 "+usuario);
                        buscar.close();
                        datos[i] = usuario;
                    }
                    else if(i==10){
                        InputStream is;
                        ImageIcon foto;
                        is =  results.getBinaryStream(i+1);
                        BufferedImage bi = ImageIO.read(is);
                        foto = new ImageIcon(bi);
                        Image img = foto.getImage();
                        Image newimg = img.getScaledInstance(140, 170, java.awt.Image.SCALE_SMOOTH);
                        ImageIcon newicon = new ImageIcon(newimg);
                    }
                    else{
                        String dato;
                        datos[i] = results.getObject(i+1);
                        System.out.println(" "+datos[i].toString());
                    }   
                    //datos[i] = results.getObject(i+1);
                    //System.out.println(results.getObject(i+1));
                }
                System.out.println("----");
                //System.out.println(datos[5]);
                //System.out.println("----");
                model.addRow(datos);
            }
            todas_reportes.close();
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        };
    }
    
    public void limpiar_tabla(){
        for (int i = 0; i < jTable.getRowCount(); i++) {
           model.removeRow(i);
           i-=1;
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
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtMaquinaria = new javax.swing.JTextField();
        txtMolde = new javax.swing.JTextField();
        txtNovedad = new javax.swing.JTextField();
        comboBoxMaquinaria = new javax.swing.JComboBox<>();
        comboBoxMolde = new javax.swing.JComboBox<>();
        comboBoxNovedad = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        comboBoxUsuario = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(889, 600));
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
        jScrollPane1.setViewportView(jTable);

        jLabel1.setText("Usuario");

        jLabel2.setText("Maquinaria");

        jLabel3.setText("Molde");

        jLabel4.setText("Tipo de Novedad");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        txtMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaquinariaActionPerformed(evt);
            }
        });

        txtMolde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoldeActionPerformed(evt);
            }
        });

        txtNovedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNovedadActionPerformed(evt);
            }
        });

        comboBoxMaquinaria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxMaquinariaActionPerformed(evt);
            }
        });

        comboBoxMolde.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxNovedad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Fecha");

        comboBoxUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exportar Tabla");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtMolde, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(161, 161, 161))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtNovedad, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(255, 255, 255)
                                        .addComponent(comboBoxMolde, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(21, 21, 21))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboBoxUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(21, 21, 21)))
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(comboBoxNovedad, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(comboBoxMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(50, 50, 50))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(262, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMolde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxMolde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNovedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxNovedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void consultar(String consulta, String procedure){
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            System.out.println("++++++++++++++++++++");
            conn.setAutoCommit(false);
            CallableStatement todas_reportes =  conn.prepareCall(procedure);
            System.out.println("+++++++++++++");
            todas_reportes.registerOutParameter(1, Types.OTHER);
            todas_reportes.setString(2, consulta);
            System.out.println("++++++++++++++++++++");
            todas_reportes.execute();
            System.out.println("++++++++++++++++++++");
            ResultSet results = (ResultSet)todas_reportes.getObject(1);
            System.out.println("++++++++++++++++++++");
            Object datos[] = new Object[11];
            System.out.println("++++++++++++++++++++");
            while(results.next()){
                String n = "";
                for(int i = 0; i<25;i++){
                    if(i==16){
                        datos[9] = results.getObject(i+1);
                    }
                    if(i==1){
                        n = results.getObject(i+1).toString();
                    }
                    if(i==2){
                        String a = results.getObject(i+1).toString();
                        String f = n+" "+a;
                        datos[4] = f;
                    }
                    if(i==15){
                        datos[8] = results.getObject(i+1);
                    }
                    if(i==14){
                        datos[7] = results.getObject(i+1);
                    }
                    if(i==13){
                        datos[6] = results.getObject(i+1);
                    }
                    if(i==12){
                        datos[5] = results.getObject(i+1);
                    }
                    if(i==7){
                        datos[0] = results.getObject(i+1);
                    }
                    if(i==8){
                        datos[1] = results.getObject(i+1);
                    }
                    if(i==23){
                        datos[3] = results.getObject(i+1);
                    }
                    if(i==20){
                        datos[2] = results.getObject(i+1);
                    }
                }
                model.addRow(datos);
            }
            System.out.println("++++++++++++++++++++");
        }
        catch(Exception e){
        }
    }
    
    public void consultar_dos(String consulta,String consulta_2, String procedure){
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            System.out.println("++++++++++++++++++++");
            conn.setAutoCommit(false);
            CallableStatement todas_reportes =  conn.prepareCall(procedure);
            System.out.println("+++++++++++++");
            todas_reportes.registerOutParameter(1, Types.OTHER);
            todas_reportes.setString(2, consulta);
            todas_reportes.setString(3, consulta_2);
            System.out.println("++++++++++++++++++++");
            todas_reportes.execute();
            System.out.println("++++++++++++++++++++");
            ResultSet results = (ResultSet)todas_reportes.getObject(1);
            System.out.println("++++++++++++++++++++");
            Object datos[] = new Object[11];
            System.out.println("++++++++++++++++++++");
            while(results.next()){
                String n = "";
                for(int i = 0; i<25;i++){
                    if(i==16){
                        datos[9] = results.getObject(i+1);
                    }
                    if(i==1){
                        n = results.getObject(i+1).toString();
                    }
                    if(i==2){
                        String a = results.getObject(i+1).toString();
                        String f = n+" "+a;
                        datos[4] = f;
                    }
                    if(i==15){
                        datos[8] = results.getObject(i+1);
                    }
                    if(i==14){
                        datos[7] = results.getObject(i+1);
                    }
                    if(i==13){
                        datos[6] = results.getObject(i+1);
                    }
                    if(i==12){
                        datos[5] = results.getObject(i+1);
                    }
                    if(i==7){
                        datos[0] = results.getObject(i+1);
                    }
                    if(i==8){
                        datos[1] = results.getObject(i+1);
                    }
                    if(i==23){
                        datos[3] = results.getObject(i+1);
                    }
                    if(i==20){
                        datos[2] = results.getObject(i+1);
                    }
                }
                model.addRow(datos);
            }
            System.out.println("++++++++++++++++++++");
        }
        catch(Exception e){
        }
    }
    
    
    
    public void consultar_tres(String consulta,String consulta_2,String consulta_3, String procedure){
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            System.out.println("++++++++++++++++++++");
            conn.setAutoCommit(false);
            CallableStatement todas_reportes =  conn.prepareCall(procedure);
            System.out.println("+++++++++++++");
            todas_reportes.registerOutParameter(1, Types.OTHER);
            System.out.println(consulta+" "+ consulta_2 + " " + consulta_3);
            todas_reportes.setString(2, consulta);
            todas_reportes.setString(3, consulta_2);
            todas_reportes.setString(4, consulta_3);
            System.out.println("++++++++++++++++++++");
            todas_reportes.execute();
            System.out.println("++++++++++++++++++++");
            ResultSet results = (ResultSet)todas_reportes.getObject(1);
            System.out.println("++++++++++++++++++++");
            Object datos[] = new Object[11];
            System.out.println("++++++++++++++++++++");
            while(results.next()){
                String n = "";
                for(int i = 0; i<25;i++){
                    if(i==16){
                        datos[9] = results.getObject(i+1);
                    }
                    if(i==1){
                        n = results.getObject(i+1).toString();
                    }
                    if(i==2){
                        String a = results.getObject(i+1).toString();
                        String f = n+" "+a;
                        datos[4] = f;
                    }
                    if(i==15){
                        datos[8] = results.getObject(i+1);
                    }
                    if(i==14){
                        datos[7] = results.getObject(i+1);
                    }
                    if(i==13){
                        datos[6] = results.getObject(i+1);
                    }
                    if(i==12){
                        datos[5] = results.getObject(i+1);
                    }
                    if(i==7){
                        datos[0] = results.getObject(i+1);
                    }
                    if(i==8){
                        datos[1] = results.getObject(i+1);
                    }
                    if(i==23){
                        datos[3] = results.getObject(i+1);
                    }
                    if(i==20){
                        datos[2] = results.getObject(i+1);
                    }
                }
                model.addRow(datos);
            }
            System.out.println("++++++++++++++++++++");
        }
        catch(Exception e){
        }
    }
    
    private void consultar_por_fecha(String fecha,String procedure){
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            conn.setAutoCommit(false);
            CallableStatement todas_reportes_por_fecha =  conn.prepareCall(procedure);
            todas_reportes_por_fecha.registerOutParameter(1, Types.OTHER);
            todas_reportes_por_fecha.setString(2,fecha);
            System.out.println("+++++++++++++++++++++++++++++++++++++");
            todas_reportes_por_fecha.execute();
            ResultSet results = (ResultSet)todas_reportes_por_fecha.getObject(1);
            Object datos[] = new Object[11];
            System.out.println("---------------------------");
            while(results.next()){
                String n = "";
                for(int i = 0; i<25;i++){
                    if(i==16){
                        datos[9] = results.getObject(i+1);
                    }
                    if(i==1){
                        n = results.getObject(i+1).toString();
                    }
                    if(i==2){
                        String a = results.getObject(i+1).toString();
                        String f = n+" "+a;
                        datos[4] = f;
                    }
                    if(i==15){
                        datos[8] = results.getObject(i+1);
                    }
                    if(i==14){
                        datos[7] = results.getObject(i+1);
                    }
                    if(i==13){
                        datos[6] = results.getObject(i+1);
                    }
                    if(i==12){
                        datos[5] = results.getObject(i+1);
                    }
                    if(i==7){
                        datos[0] = results.getObject(i+1);
                    }
                    if(i==8){
                        datos[1] = results.getObject(i+1);
                    }
                    if(i==23){
                        datos[3] = results.getObject(i+1);
                    }
                    if(i==20){
                        datos[2] = results.getObject(i+1);
                    }
                }
                model.addRow(datos);
            }
            System.out.println("---------------------------");
        }catch(Exception e){
            System.out.println("xxx Error en consultar_por_fecha xxx");
        }
    }
    
    private void consultaReportesFechaRango(String fechaObtenidaInicial,String fechaObtenidaFinal,String procedure){
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            conn.setAutoCommit(false);
            CallableStatement todas_reportes_por_fecha =  conn.prepareCall(procedure);
            todas_reportes_por_fecha.registerOutParameter(1, Types.OTHER);
            todas_reportes_por_fecha.setString(2,fechaObtenidaInicial);
            todas_reportes_por_fecha.setString(3,fechaObtenidaFinal);
            System.out.println("+++++++++++++++++++++++++++++++++++++");
            todas_reportes_por_fecha.execute();
            ResultSet results = (ResultSet)todas_reportes_por_fecha.getObject(1);
            Object datos[] = new Object[11];
            System.out.println("---------------------------");
            while(results.next()){
                String n = "";
                for(int i = 0; i<25;i++){
                    if(i==16){
                        datos[9] = results.getObject(i+1);
                    }
                    if(i==1){
                        n = results.getObject(i+1).toString();
                    }
                    if(i==2){
                        String a = results.getObject(i+1).toString();
                        String f = n+" "+a;
                        datos[4] = f;
                    }
                    if(i==15){
                        datos[8] = results.getObject(i+1);
                    }
                    if(i==14){
                        datos[7] = results.getObject(i+1);
                    }
                    if(i==13){
                        datos[6] = results.getObject(i+1);
                    }
                    if(i==12){
                        datos[5] = results.getObject(i+1);
                    }
                    if(i==7){
                        datos[0] = results.getObject(i+1);
                    }
                    if(i==8){
                        datos[1] = results.getObject(i+1);
                    }
                    if(i==23){
                        datos[3] = results.getObject(i+1);
                    }
                    if(i==20){
                        datos[2] = results.getObject(i+1);
                    }
                }
                model.addRow(datos);
            }
            System.out.println("---------------------------");
        }catch(Exception e){
            System.out.println("xxx Error en consultar_por_fecha_rango xxx");
        }
    }
    
    private void consultar_usuario_maquinaria_molde_novedad(String usuario,String maquinaria,String molde,String novedad,String procedure){
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            System.out.println("++++++++++++++++++++");
            conn.setAutoCommit(false);
            CallableStatement todas_reportes =  conn.prepareCall(procedure);
            System.out.println("+++++++++++++");
            todas_reportes.registerOutParameter(1, Types.OTHER);
            System.out.println(usuario+" "+ maquinaria + " " + molde + " " + novedad +" ");
            todas_reportes.setString(2, usuario);
            todas_reportes.setString(3, maquinaria);
            todas_reportes.setString(4, molde);
            todas_reportes.setString(5, novedad);
            System.out.println("++++++++++++++++++++");
            todas_reportes.execute();
            System.out.println("++++++++++++++++++++");
            ResultSet results = (ResultSet)todas_reportes.getObject(1);
            System.out.println("++++++++++++++++++++");
            Object datos[] = new Object[11];
            System.out.println("++++++++++++++++++++");
            while(results.next()){
                String n = "";
                for(int i = 0; i<25;i++){
                    if(i==16){
                        datos[9] = results.getObject(i+1);
                    }
                    if(i==1){
                        n = results.getObject(i+1).toString();
                    }
                    if(i==2){
                        String a = results.getObject(i+1).toString();
                        String f = n+" "+a;
                        datos[4] = f;
                    }
                    if(i==15){
                        datos[8] = results.getObject(i+1);
                    }
                    if(i==14){
                        datos[7] = results.getObject(i+1);
                    }
                    if(i==13){
                        datos[6] = results.getObject(i+1);
                    }
                    if(i==12){
                        datos[5] = results.getObject(i+1);
                    }
                    if(i==7){
                        datos[0] = results.getObject(i+1);
                    }
                    if(i==8){
                        datos[1] = results.getObject(i+1);
                    }
                    if(i==23){
                        datos[3] = results.getObject(i+1);
                    }
                    if(i==20){
                        datos[2] = results.getObject(i+1);
                    }
                }
                model.addRow(datos);
            }
            System.out.println("++++++++++++++++++++");
        }
        catch(Exception e){
        }
    }
    
    private String ponerFechaFormatoCorrecto(String fecha){
        String arreglo[];
        arreglo = fecha.split("/");
        return ""+arreglo[2]+"-"+arreglo[1]+"-"+arreglo[0];
    }
    
    private void llenaComboNovedad(){
        limpiarCombo(comboBoxNovedad);
        comboBoxNovedad.addItem("Correción");
        comboBoxNovedad.addItem("Reparación");
        comboBoxNovedad.addItem("Acción Tomada");
    }
    
    private void limpiarCombo(JComboBox c){
        int itemCount = c.getItemCount();

        for(int i=0;i<itemCount;i++){
            c.removeItemAt(0);
        }
    }
    
    private void llenarComboMolde(){
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        limpiarCombo(comboBoxMolde);
        try{
            conn.setAutoCommit(false);
            CallableStatement todos_moldes =  conn.prepareCall("{ ? = CALL TODOS_MOLDES ( ) }");
            todos_moldes.registerOutParameter(1, Types.OTHER);
            todos_moldes.execute();
            ResultSet results = (ResultSet)todos_moldes.getObject(1);
            while(results.next()){
                comboBoxMolde.addItem(results.getString(2));
            }
            todos_moldes.close();
            conn.close();
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    private void llenarComboUsuarioNombre(){
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        limpiarCombo(comboBoxUsuario);
        try{
            conn.setAutoCommit(false);
            CallableStatement todos_usuarios =  conn.prepareCall("{ ? = CALL TODOS_USUARIO_NOMBRE ( ) }");
            todos_usuarios.registerOutParameter(1, Types.OTHER);
            todos_usuarios.execute();
            ResultSet results = (ResultSet)todos_usuarios.getObject(1);
            while(results.next()){
                System.out.println("--> "+results.getString(1));
                comboBoxUsuario.addItem(results.getString(1));
            }
            todos_usuarios.close();
            conn.close();
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    private void llenarComboMaquina(){
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        limpiarCombo(comboBoxMaquinaria);
        try{
            conn.setAutoCommit(false);
            CallableStatement todas_maquinarias =  conn.prepareCall("{ ? = CALL TODAS_MAQUINARIAS ( ) }");
            todas_maquinarias.registerOutParameter(1, Types.OTHER);
            todas_maquinarias.execute();
            ResultSet results = (ResultSet)todas_maquinarias.getObject(1);
            while(results.next()){
                comboBoxMaquinaria.addItem(results.getString(2));
            }
            todas_maquinarias.close();
            conn.close();
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        limpiar_tabla();
        usuario = txtUsuario.getText().toLowerCase();
        maquinaria = txtMaquinaria.getText();
        molde = txtMolde.getText();
        novedad = txtNovedad.getText();
        if(usuario.equals("") && maquinaria.equals("") && molde.equals("") && novedad.equals("")){
            llenarTable();
        }       
        if(!usuario.equals("")){
            System.out.println("usuario");
            String procedure = "{ ? = call CONSULTA_SOLO_USUARIO ( ? ) }";
            consultar(usuario, procedure);
        }
        if(!maquinaria.equals("")){
            System.out.println("maquinaria");
            String procedure = "{ ? = call CONSULTA_SOLO_MAQUINARIA ( ? ) }";
            consultar(maquinaria, procedure);
        }
        if(!molde.equals("")){
            System.out.println("molde");
            String procedure = "{ ? = call CONSULTA_SOLO_MOLDE ( ? ) }";
            consultar(molde, procedure);
        }
        if(!novedad.equals("")){
            System.out.println("novedad");
            String procedure = "{ ? = call CONSULTA_SOLO_NOVEDAD ( ? ) }";
            consultar(novedad, procedure);
        }
        if(!usuario.equals("") && !maquinaria.equals("")){
            System.out.println("usuario maquinaria");
            String procedure = "{ ? = call CONSULTA_USUARIO_MAQUINARIA ( ? , ?) }";
            consultar_dos(usuario,maquinaria, procedure);
        }
        if(!usuario.equals("") && !molde.equals("")){
            System.out.println("usuario molde");
            String procedure = "{ ? = call CONSULTA_USUARIO_MOLDE ( ? , ?) }";
            consultar_dos(usuario,molde, procedure); 
        }
        if(!usuario.equals("") && !novedad.equals("")){
            System.out.println("usuario novedad");
            String procedure = "{ ? = call CONSULTA_USUARIO_NOVEDAD ( ? , ?) }";
            consultar_dos(txtUsuario.getText(),txtNovedad.getText(), procedure);
        }
        if(!usuario.equals("") && !maquinaria.equals("") && !molde.equals("")){
            System.out.println("usuario mmaquinaria molde");
            String procedure = "{ ? = call CONSULTA_USUARIO_MAQUINARIA_MOLDE ( ? , ? , ?) }";
            consultar_tres(usuario,maquinaria,molde, procedure);
        }
        if(!usuario.equals("") && !maquinaria.equals("") && !novedad.equals("")){
            System.out.println("usuario maquinaria novedad");
            String procedure = "{ ? = call CONSULTA_USUARIO_MAQUINARIA_NOVEDAD ( ? , ? , ? ) }";
            consultar_tres(usuario,maquinaria,novedad, procedure);
        }
        if(!usuario.equals("") && !molde.equals("") && !novedad.equals("")){
            System.out.println("usuario molde novedad");
            String procedure = "{ ? = call CONSULTA_USUARIO_MOLDE_NOVEDAD ( ? , ? , ?) }";
            consultar_tres(usuario,maquinaria,novedad, procedure);
        }
        if(!maquinaria.equals("") && !molde.equals("")){
            System.out.println("maquinaria molde");
            String procedure = "{ ? = call CONSULTA_MAQUINARIA_MOLDE ( ? , ? ) }";
            consultar_dos(maquinaria,molde, procedure);
        }
        if(!maquinaria.equals("") && !novedad.equals("")){
            System.out.println("maquinaria novedad");
            String procedure = "{ ? = call CONSULTA_MAQUINARIA_NOVEDAD ( ? , ? ) }";
            consultar_dos(maquinaria,novedad, procedure);
        }
        if(!maquinaria.equals("") && !molde.equals("") && !novedad.equals("")){
            System.out.println("maquinaria molde novedad");
            String procedure = "{ ? = call CONSULTA_MAQUINARIA_MOLDE_NOVEDAD ( ? , ? ,?) }";
            consultar_tres(maquinaria,molde,novedad, procedure);
        }
        if(!molde.equals("") && !novedad.equals("")){
            System.out.println("molde novedad");
            String procedure = "{ ? = call CONSULTA_MOLDE_NOVEDAD ( ? , ? ) }";
            consultar_dos(molde,novedad, procedure);
        }
        if(!usuario.equals("") && !maquinaria.equals("") && !molde.equals("") && !novedad.equals("")){
            limpiar_tabla();
            System.out.println("usuario maquina molde novedad");
            String procedure = "{ ? = call CONSULTA_USUARIO_MAQUINARIA_MOLDE_NOVEDAD ( ? , ? , ? , ?) }";
            consultar_usuario_maquinaria_molde_novedad(usuario,maquinaria,molde,novedad,procedure);
        }
//        if(btnFechaInicio != null){
//            String fechaObtenida = df.format(btnFechaInicio.getDate());
//            String fechaCorrecta;
//            String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_POR_MES ( ? ) }";
//            fechaCorrecta = ponerFechaFormatoCorrecto(fechaObtenida);
//            System.out.println(fechaCorrecta);
//            consultar_por_fecha(fechaCorrecta,procedure);
//        }
//        if(checkBoxFechaInicio.isSelected() == true && checkBoxFechaFinal.isSelected() == false){
//            String fechaObtenida = df.format(btnFechaInicio.getDate());
//            String fechaCorrecta;
//            String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_POR_MES ( ? ) }";
//            fechaCorrecta = ponerFechaFormatoCorrecto(fechaObtenida);
//            System.out.println(fechaCorrecta);
//            consultar_por_fecha(fechaCorrecta,procedure);
//        }
//        if(checkBoxFechaFinal.isSelected() == true && checkBoxFechaInicio.isSelected() == true){
//            String fechaObtenidaInicial = df.format(btnFechaInicio.getDate());
//            String fechaObtenidaFinal = df.format(btnFechaFinal.getDate());
//            String fechaCorrectaInicial,fechaCorrectaFinal;
//            String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_POR_MES_RANGO ( ? , ? ) }";
//            fechaCorrectaInicial = ponerFechaFormatoCorrecto(fechaObtenidaInicial);
//            fechaCorrectaFinal = ponerFechaFormatoCorrecto(fechaObtenidaFinal);
//            consultaReportesFechaRango(fechaObtenidaInicial,fechaObtenidaFinal,procedure);
//        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            Thread hilo = new Thread(){
                public void run(){
                    XSSFWorkbook wb = new XSSFWorkbook();
                    XSSFSheet hoja = wb.createSheet();
                    XSSFRow fila = hoja.createRow(0);
                    fila.createCell(0).setCellValue("ID");
                    fila.createCell(1).setCellValue("FECHA");
                    fila.createCell(2).setCellValue("MAQUINARIA");
                    fila.createCell(3).setCellValue("MOLDE");
                    fila.createCell(4).setCellValue("USUARIO");
                    fila.createCell(5).setCellValue("DESCRIPCIÓN");
                    fila.createCell(6).setCellValue("TIPO DE NOVEDAD");
                    fila.createCell(7).setCellValue("DESCRIPCIÓN");
                    fila.createCell(8).setCellValue("SOLUCIÓN");
                    fila.createCell(9).setCellValue("NOVEDAD");
                    hoja.setColumnWidth(0, 256*10);
                    hoja.setColumnWidth(1, 256*13);
                    hoja.setColumnWidth(2, 256*40);
                    hoja.setColumnWidth(3, 256*30);
                    hoja.setColumnWidth(4, 256*15);
                    hoja.setColumnWidth(5, 256*15);
                    hoja.setColumnWidth(6, 256*15);
                    hoja.setColumnWidth(7, 256*30);
                    hoja.setColumnWidth(8, 256*15);
                    hoja.setColumnWidth(9, 256*30);
                    XSSFRow filas;
                    Rectangle rect;
                    jProgressBar1.setMaximum(jTable.getRowCount());
                    for (int i = 0 ; i < jTable.getRowCount();i++){
                        filas = hoja.createRow(i+1);
                        rect = jTable.getCellRect(i, 0, true);
                        jTable.scrollRectToVisible(rect);
                        for(int j = 0 ; j <= 9 ; j++){
                            filas.createCell(j).setCellValue(jTable.getValueAt(i, j).toString());
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                        }
                        jTable.setRowSelectionInterval(i, i);
                        jProgressBar1.setValue(i+1);

                    }
                    jProgressBar1.setValue(0);
                    jProgressBar1.setString("Abriendo Excel.....");
                    try {
                        wb.write(new FileOutputStream(archivo));
                        Desktop.getDesktop().open(archivo);
                    } catch (Exception e) {

                    }
                }
            };
            hilo.start();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtMaquinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaquinariaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaquinariaActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtNovedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNovedadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNovedadActionPerformed

    private void txtMoldeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoldeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoldeActionPerformed

    private void comboBoxMaquinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxMaquinariaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxMaquinariaActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        ventanaActivaReporteUsuario = false;
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxMaquinaria;
    private javax.swing.JComboBox<String> comboBoxMolde;
    private javax.swing.JComboBox<String> comboBoxNovedad;
    private javax.swing.JComboBox<String> comboBoxUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField txtMaquinaria;
    private javax.swing.JTextField txtMolde;
    private javax.swing.JTextField txtNovedad;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}


