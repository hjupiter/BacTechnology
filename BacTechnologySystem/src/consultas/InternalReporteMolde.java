/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas;

import conexion.Conexion;
import static consultas.InternalReporte.ventanaActivaReporteUsuario;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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

/**
 *
 * @author bakamedi
 */
public class InternalReporteMolde extends javax.swing.JInternalFrame {

    public static boolean ventanaActivaReporteUsuarioMolde = false;
    DateFormat df = DateFormat.getDateInstance();
    private static final String rutaArchivo = "C://data//data.xlsx";
    private static final File archivo = new File(rutaArchivo);
    private String usuario;
    private String maquinaria;
    private String molde;
    private String novedad;
    DefaultTableModel model;
    JScrollPane scroll;
    /**
     * Creates new form InternalReporteMolde
     */
    public InternalReporteMolde() {
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

        //setSize(1200, 600);
        setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        btnConsultar = new javax.swing.JButton();
        btnExportarExcel = new javax.swing.JButton();
        checkBoxFecha = new javax.swing.JCheckBox();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 600));

        jPanel1.setMaximumSize(new java.awt.Dimension(40000, 40000));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 149));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Maquinaria");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Molde");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Tipo de Novedad");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha");

        comboBoxUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnExportarExcel.setText("Exportar a Excel");
        btnExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarExcelActionPerformed(evt);
            }
        });

        checkBoxFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboBoxMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtMolde, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboBoxMolde, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtNovedad, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboBoxNovedad, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(comboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(176, 176, 176)
                                .addComponent(checkBoxFecha))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(btnExportarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMolde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxMolde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(checkBoxFecha)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNovedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxNovedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnExportarExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jProgressBar1.setStringPainted(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1200, 53));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setText("CONSULTAS Y GENERACIÓN DE REPORTES MOLDES");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(262, 262, 262))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        jTable.setAutoCreateRowSorter(true);
        jTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1184, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void llenarTable(){
        model = new DefaultTableModel(null,getColumnas());
        setFilas();
        jTable.setModel(model);
        setEventoMouseClicked(jTable);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(90);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(7).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(8).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(9).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(10).setPreferredWidth(60);
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
       String articulo ="";
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
                articulo = model.getValueAt(row, 7).toString();
                descripcion_novedad = model.getValueAt(row,8).toString();
                solucion = model.getValueAt(row,9).toString();
                novedad = model.getValueAt(row,10).toString();
                System.out.println(descripcion+" "+descripcion_novedad);
                //InternalData d = new InternalData(id, fecha, maquinaria, molde, Usuario, descripcion, tipo_novedad, descripcion_novedad, solucion, novedad);
                
            //}
        }
 
        //JOptionPane.showMessageDialog(null, fecha);
    }
    
    private String[] getColumnas(){
          String columna[]=new String[]{"ID",
                                        "USUARIO",
                                        "FECHA",
                                        "MAQUINARIA",
                                        "MOLDE",
                                        "NOVEDAD DETECTADA",
                                        "ARTICULO",
                                        "TIPO NOVEDAD",
                                        "DESCRIPCIÓN NOVEDAD",
                                        "TIPO DE SOLUCIÓN",
                                        "DESCRIPCIÓN SOLUCIÓN"};
          return columna;
    }
    
    private void setFilas(){
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            conn.setAutoCommit(false);
            CallableStatement todas_reportes =  conn.prepareCall("{ ? = CALL TODOS_REPORTESMOLDE ( ) }");
            todas_reportes.registerOutParameter(1, Types.OTHER);
            todas_reportes.execute();
            ResultSet results = (ResultSet)todas_reportes.getObject(1);
            Object datos[] = new Object[13];
            llenarTablaCentral(datos, results, conn);
            todas_reportes.close();
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        };
    }
    
    private void limpiar_tabla(){
        for (int i = 0; i < jTable.getRowCount(); i++) {
           model.removeRow(i);
           i-=1;
        }
    }
    
    
    private  void llenarTablaCentral(Object datos[],ResultSet results,Connection conn) throws Exception{
        while(results.next()){
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
            for(int i = 0; i<13;i++){
                if(i==2){
                    String x = results.getObject(i+1).toString();
                    int entero = Integer.parseInt(x);
                    CallableStatement buscar = conn.prepareCall("{ ? = call buscar_maquina_id (?) }");
                    buscar.registerOutParameter(1, Types.VARCHAR);
                    buscar.setInt(2, entero);
                    buscar.execute();
                    String maquina = buscar.getString(1);
                    buscar.close();
                    datos[3] = maquina;
                    System.out.println("MAQUINA "+maquina);
                }
                else if(i == 1){
                    datos[2] = results.getObject(i+1);
                    System.out.println("FECHA --> ["+i+"] "+datos[2].toString());
                }
                else if(i==3){
                    String x = results.getObject(i+1).toString();
                    int entero = Integer.parseInt(x);
                    CallableStatement buscar = conn.prepareCall("{ ? = call buscar_molde_id (?) }");
                    buscar.registerOutParameter(1, Types.VARCHAR);
                    buscar.setInt(2, entero);
                    buscar.execute();
                    String molde = buscar.getString(1);
                    buscar.close();
                    datos[4] = molde;
                    System.out.println("MOLDE "+molde);
                }
                else if(i==4){
                    String x = results.getObject(i+1).toString();
                    int entero = Integer.parseInt(x);
                    CallableStatement buscar = conn.prepareCall("{ ? = call buscar_usuario_id (?) }");
                    buscar.registerOutParameter(1, Types.VARCHAR);
                    buscar.setInt(2, entero);
                    buscar.execute();
                    String usuario = buscar.getString(1);
                    buscar.close();
                    datos[1] = usuario;
                    System.out.println("USUARIO -->"+usuario);
                }
                else if(i==10){
                    InputStream is;
                    ImageIcon foto;
                    is =  results.getBinaryStream(i+1);
                    if(is.available()>=1000){
                        BufferedImage bi = ImageIO.read(is);
                        foto = new ImageIcon(bi);
                        Image img = foto.getImage();
                        Image newimg = img.getScaledInstance(140, 170, java.awt.Image.SCALE_SMOOTH);
                        ImageIcon newicon = new ImageIcon(newimg);
                    }
                    else{

                    }
                }
                else if(i == 5){
                    datos[8] = results.getObject(i+1);
                    System.out.println("DESCRIPCION NOVEDAD --> "+results.getObject(i+1));
                }
                else if(i == 6){
                    //tipo nocedad
                    datos[7] = results.getObject(i+1);
                    System.out.println("TIPO NOVEDAD --> "+results.getObject(i+1));
                }
                else if(i == 7){
                    datos[10] = results.getObject(i+1);
                    System.out.println("DESCRIPCION SOLUCION --> "+results.getObject(i+1));
                }
                else if(i == 8){
                    datos[9] = results.getObject(i+1);
                    System.out.println("TIPO SOLUCION --> "+results.getObject(i+1));
                }
                else if(i == 9){
                    datos[5] = results.getObject(i+1);
                    System.out.println("NOVEDAD --> "+results.getObject(i+1));
                }
                else if(i == 11){
                    datos[6] = results.getObject(i+1);
                    System.out.println("ARTICULO --> "+results.getObject(i+1));
                }
                else{
                    datos[i] = results.getObject(i+1);
                }
                //datos[i] = results.getObject(i+1);
                //System.out.println("["+i+"]"+datos[i].toString());
            }
            model.addRow(datos);
        }
    }
    
    private void llenarTablaCentralConsultas(Object datos[],ResultSet results,Connection conn) throws Exception{
        while(results.next()){
            String name = "",idMolde="",nameMolde="";
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            for(int i = 0; i<26;i++){
                switch(i){
                    case 0:
                        break;
                    case 1:
                        name = results.getObject(i+1).toString();
                        //System.out.println(" ["+i+"] --> "+name);
                        break;
                    case 2:
                        String apellido = results.getObject(i+1).toString();
                        String nombreCompleto = name+" "+apellido;
                        datos[1] = nombreCompleto;
                        System.out.println("USUARIO ["+i+"] --> "+datos[1].toString());
                        break;
                    case 7:
                        datos[0] = results.getObject(i+1).toString();
                        System.out.println("ID ["+i+"] --> "+datos[0].toString());
                        break;
                    case 8:
                        datos[2] = results.getObject(i+1);
                        System.out.println("FECHA ["+i+"] --> "+datos[2].toString());
                        break;
                    case 12:
                        datos[8] = results.getObject(i+1);
                        System.out.println("DESCRIPCION NOVEDAD ["+i+"] --> "+datos[8].toString());
                        break;
                    case 13:
                        datos[7] = results.getObject(i+1);
                        System.out.println("TIPO NOVEDAD ["+i+"] --> "+datos[7].toString());
                        break;
                    case 14:
                        datos[10] = results.getObject(i+1);
                        System.out.println("DESCRIPCION SOLUCION ["+i+"] --> "+datos[10].toString());
                        break;    
                    case 15:
                        datos[9] = results.getObject(i+1);
                        System.out.println("TIPO SOLUCION ["+i+"] --> "+datos[9].toString());
                        break;
                    case 16:
                        datos[5] = results.getObject(i+1);
                        System.out.println("NOVEDAD ["+i+"] --> "+datos[5].toString());
                        break;
                    case 17:
                        //datos[i] = results.getObject(i+1);
                        System.out.println("FOTO ["+i+"] --> "+datos[i].toString());
                        break;
                    case 18:
                        datos[6] = results.getObject(i+1);
                        System.out.println("ARTICULO ["+i+"] --> "+datos[6].toString());
                        break;
                    case 21:
                        datos[3] = results.getObject(i+1);
                        System.out.println("MAQUINARIA ["+i+"] --> "+datos[3].toString());
                        break;
                    case 24:
                        idMolde = results.getObject(i+1).toString();
                        System.out.println("MOLDE ["+i+"] --> "+datos[i].toString());
                        break;
                }
            }
            model.addRow(datos);
        }
    }
    
    private String fechaCorrecta(){
        String fechaCorrecta = null;
       // String fechaObtenida = df.format(btnFechaInicio.getDate());
        //fechaCorrecta = ponerFechaFormatoCorrecto(fechaObtenida);
        return fechaCorrecta;
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
    
    public void consultar(String consulta, String procedure){
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            conn.setAutoCommit(false);
            CallableStatement todas_reportes =  conn.prepareCall(procedure);
            todas_reportes.registerOutParameter(1, Types.OTHER);
            todas_reportes.setString(2, consulta);
            todas_reportes.execute();
            ResultSet results = (ResultSet)todas_reportes.getObject(1);
            Object datos[] = new Object[13];
            llenarTablaCentralConsultas(datos, results, conn);
        }
        catch(Exception e){
        }
    }
    
    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtMaquinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaquinariaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaquinariaActionPerformed

    private void txtMoldeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoldeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoldeActionPerformed

    private void txtNovedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNovedadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNovedadActionPerformed

    private void comboBoxMaquinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxMaquinariaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxMaquinariaActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        limpiar_tabla();
        try {
            usuario = txtUsuario.getText().toUpperCase();
            maquinaria = txtMaquinaria.getText().toUpperCase();
            molde = txtMolde.getText().toUpperCase();
            novedad = txtNovedad.getText();
            if(usuario.equals("") && maquinaria.equals("") && molde.equals("") && novedad.equals("")){
                llenarTable();
            }       
            if(!usuario.equals("") && molde.equals("") && maquinaria.equals("") && novedad.equals("") && !checkBoxFecha.isSelected()){
                limpiar_tabla();
                String procedure = "{ ? = call CONSULTAMOLDE_SOLO_USUARIO ( ? ) }";
                System.out.println("********************************************");
                System.out.println(usuario);
                consultar(usuario, procedure);
            }
//            if(!maquinaria.equals("") && molde.equals("") && usuario.equals("") && novedad.equals("") && !checkBoxFecha.isSelected() ){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_SOLO_MAQUINARIA ( ? ) }";
//                consultar(maquinaria, procedure);
//            }
//            if(!molde.equals("") && usuario.equals("") && maquinaria.equals("") && novedad.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_SOLO_MOLDE ( ? ) }";
//                System.out.println("------------------------");
//                consultar(molde, procedure);
//            }
//            if(!novedad.equals("") && molde.equals("") && maquinaria.equals("") && usuario.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_SOLO_NOVEDAD ( ? ) }";
//                consultar(novedad, procedure);
//            }
//            if(!usuario.equals("") && !maquinaria.equals("") && molde.equals("") && novedad.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_USUARIO_MAQUINARIA ( ? , ?) }";
//                consultar_dos(usuario,maquinaria, procedure);
//            }
//            if(!usuario.equals("") && !molde.equals("") && maquinaria.equals("") && novedad.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_USUARIO_MOLDE ( ? , ?) }";
//                consultar_dos(usuario,molde, procedure); 
//            }
//            if(!usuario.equals("") && !novedad.equals("") && maquinaria.equals("") && molde.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_USUARIO_NOVEDAD ( ? , ?) }";
//                consultar_dos(txtUsuario.getText(),txtNovedad.getText(), procedure);
//            }
//            if(!usuario.equals("") && !maquinaria.equals("") && !molde.equals("") && novedad.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_USUARIO_MAQUINARIA_MOLDE ( ? , ? , ?) }";
//                consultar_tres(usuario,maquinaria,molde, procedure);
//            }
//            if(!usuario.equals("") && !maquinaria.equals("") && !novedad.equals("") && molde.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_USUARIO_MAQUINARIA_NOVEDAD ( ? , ? , ? ) }";
//                consultar_tres(usuario,maquinaria,novedad, procedure);
//            }
//            if(!usuario.equals("") && !molde.equals("") && !novedad.equals("") && maquinaria.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_USUARIO_MOLDE_NOVEDAD ( ? , ? , ?) }";
//                consultar_tres(usuario,maquinaria,novedad, procedure);
//            }
//            if(!maquinaria.equals("") && !molde.equals("") && novedad.equals("") && usuario.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_MAQUINARIA_MOLDE ( ? , ? ) }";
//                consultar_dos(maquinaria,molde, procedure);
//            }
//            if(!maquinaria.equals("") && !novedad.equals("") && molde.equals("") && usuario.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_MAQUINARIA_NOVEDAD ( ? , ? ) }";
//                consultar_dos(maquinaria,novedad, procedure);
//            }
//            if(!maquinaria.equals("") && !molde.equals("") && !novedad.equals("") && usuario.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_MAQUINARIA_MOLDE_NOVEDAD ( ? , ? ,?) }";
//                consultar_tres(maquinaria,molde,novedad, procedure);
//            }
//            if(!molde.equals("") && !novedad.equals("") && usuario.equals("") && maquinaria.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_MOLDE_NOVEDAD ( ? , ? ) }";
//                consultar_dos(molde,novedad, procedure);
//            }
//            if(!usuario.equals("") && !maquinaria.equals("") && !molde.equals("") && !novedad.equals("") && !checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_USUARIO_MAQUINARIA_MOLDE_NOVEDAD ( ? , ? , ? , ?) }";
//                consultar_usuario_maquinaria_molde_novedad(usuario,maquinaria,molde,novedad,procedure);
//            }
//            if(checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_POR_MES ( ? ) }";
//                consultar_por_fecha(fechaCorrecta(),procedure);
//            }
//            if(!usuario.equals("") && checkBoxFecha.isSelected() && molde.equals("") && maquinaria.equals("") && novedad.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_USUARIO_FECHA_POR_MES ( ?, ? ) }";
//                consultar_usuario_fecha(usuario,fechaCorrecta(),procedure);
//            }
//            if(!maquinaria.equals("") && checkBoxFecha.isSelected() && molde.equals("") && usuario.equals("") && novedad.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_MAQUINA_POR_MES ( ?, ? ) }";
//                consultar_fecha_maquina(fechaCorrecta(),maquinaria, procedure);
//            }
//            if(!molde.equals("") && checkBoxFecha.isSelected() && novedad.equals("") && molde.equals("") && maquinaria.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_MOLDE_POR_MES ( ?, ? ) }";
//                consultar_fecha_molde(fechaCorrecta(), molde, procedure);
//            }
//            if(!novedad.equals("") && checkBoxFecha.isSelected() && usuario.equals("") && molde.equals("") && maquinaria.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_NOVEDAD_POR_MES ( ?, ? ) }";
//                consultar_fecha_novedad(fechaCorrecta(), novedad, procedure);
//            }
//            if(!usuario.equals("") && !molde.equals("") && checkBoxFecha.isSelected() && maquinaria.equals("") && novedad.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_USUARIO_MOLDE ( ? , ? , ?) }";
//                consulta_reporte_fecha_molde(usuario,fechaCorrecta(),molde,procedure);
//            }
//            if(!usuario.equals("") && !maquinaria.equals("") && checkBoxFecha.isSelected() && molde.equals("") && novedad.equals("") ){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_USUARIO_FECHA_MAQUINA_POR_MES ( ?, ? , ?) }";
//                consultar_usuario_fecha_maquina(usuario,fechaCorrecta(),maquinaria,procedure);
//            }
//            if(!usuario.equals("") && !novedad.equals("") && checkBoxFecha.isSelected() && molde.equals("") && maquinaria.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_USUARIO_NOVEDAD ( ?, ? , ?) }";
//                consultar_usuario_fecha_novedad(usuario,fechaCorrecta(),novedad,procedure);
//            }
//            if(!maquinaria.equals("") && !molde.equals("") && checkBoxFecha.isSelected() && usuario.equals("") && novedad.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_MAQUINA_MOLDE ( ?, ? , ?) }";
//                consultar_fecha_maquina_molde(fechaCorrecta(), maquinaria, molde, procedure);
//            }
//            if(!maquinaria.equals("") && !novedad.equals("") && checkBoxFecha.isSelected() && usuario.equals("") && molde.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_MAQUINA_NOVEDAD ( ?, ? , ?) }";
//                consultar_fecha_maquina_novedad(fechaCorrecta(), maquinaria, molde, procedure);
//            }
//            if(!molde.equals("") && !novedad.equals("") && checkBoxFecha.isSelected() && usuario.equals("") && maquinaria.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_MOLDE_NOVEDAD ( ?, ? , ?) }";
//                consultar_fecha_molde_novedad(fechaCorrecta(), molde, novedad, procedure);
//            }
//            if(!usuario.equals("") && !maquinaria.equals("") && !molde.equals("") && checkBoxFecha.isSelected() && novedad.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_USUARIO_MAQUINA_MOLDE ( ?, ? , ? ,?) }";
//                consultar_fecha_usuario_maquina_molde(fechaCorrecta(), usuario, maquinaria, molde, procedure);
//            }
//            if(!usuario.equals("") && !molde.equals("") && !novedad.equals("") && checkBoxFecha.isSelected() && maquinaria.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_USUARIO_MOLDE_NOVEDAD ( ?, ? , ? ,?) }";
//                consultar_fecha_usuario_molde_novedad(fechaCorrecta(), usuario, molde, novedad, procedure);
//            }
//            if(!usuario.equals("") && !maquinaria.equals("") && !novedad.equals("") && checkBoxFecha.isSelected() && molde.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_USUARIO_MAQUINA_NOVEDAD ( ?, ? , ? ,?) }";
//                consultar_fecha_usuario_maquina_novedad(fechaCorrecta(), usuario, maquinaria, novedad, procedure);
//            }
//            if(!maquinaria.equals("") && !molde.equals("") && !novedad.equals("") && checkBoxFecha.isSelected() && usuario.equals("")){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_MAQUINA_MOLDE_NOVEDAD ( ?, ? , ? ,?) }";
//                consultar_fecha_maquina_molde_novedad(fechaCorrecta(), maquinaria, molde, novedad, procedure);
//            }
//            if(!usuario.equals("") && !maquinaria.equals("") && !molde.equals("") && !novedad.equals("") && checkBoxFecha.isSelected()){
//                limpiar_tabla();
//                String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_USUARIO_MAQUINA_MOLDE_NOVEDAD ( ? , ? , ? , ? , ?) }";
//                consultar_reporte_fecha_usuario_maquina_molde_novedad(fechaCorrecta(),usuario,maquinaria,molde,novedad,procedure);
//            }

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Eliga un Campo de Busqueda");
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarExcelActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnExportarExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnExportarExcel;
    private javax.swing.JCheckBox checkBoxFecha;
    private javax.swing.JComboBox<String> comboBoxMaquinaria;
    private javax.swing.JComboBox<String> comboBoxMolde;
    private javax.swing.JComboBox<String> comboBoxNovedad;
    private javax.swing.JComboBox<String> comboBoxUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField txtMaquinaria;
    private javax.swing.JTextField txtMolde;
    private javax.swing.JTextField txtNovedad;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
