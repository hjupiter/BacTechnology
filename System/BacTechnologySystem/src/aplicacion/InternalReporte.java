/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import javax.imageio.ImageIO;
import javax.sql.rowset.CachedRowSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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

    /**
     * Creates new form InternalReporte
     */
    DefaultTableModel model;
    JScrollPane scroll;
    public InternalReporte() {
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
    private void setEventoMouseClicked(JTable tbl)
    {
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
    
    private String[] getColumnas()
    {
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
                        System.out.println(maquina);
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
                        System.out.println(molde);
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
                        System.out.println(usuario);
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
                        datos[i] = results.getObject(i+1);
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

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

        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jButton2.setText("Exportar Tabla");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(27, 27, 27)
                                .addComponent(jButton2)
                                .addGap(44, 44, 44))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox3)))
                                .addGap(379, 379, 379)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jCheckBox1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCheckBox2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

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
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        limpiar_tabla();
        
        
        if(jCheckBox1.isSelected()==true && jCheckBox2.isSelected()==false && jCheckBox3.isSelected()==false && jCheckBox4.isSelected()==false){
            System.out.println("usuario");
            String procedure = "{ ? = call CONSULTA_SOLO_USUARIO ( ? ) }";
            System.out.println(jTextField2.getText());
            consultar(jTextField1.getText(), procedure);
        }
        if(jCheckBox2.isSelected()==true && jCheckBox1.isSelected()==false && jCheckBox3.isSelected()==false && jCheckBox4.isSelected()==false){
            System.out.println("maquinaria");
            String procedure = "{ ? = call CONSULTA_SOLO_MAQUINARIA ( ? ) }";
            System.out.println(jTextField2.getText());
            consultar(jTextField2.getText(), procedure);
        }
        if(jCheckBox3.isSelected()==true && jCheckBox2.isSelected()==false && jCheckBox1.isSelected()==false && jCheckBox4.isSelected()==false){
            System.out.println("molde");
            String procedure = "{ ? = call CONSULTA_SOLO_MOLDE ( ? ) }";
            System.out.println(jTextField2.getText());
            consultar(jTextField3.getText(), procedure);
        }
        if(jCheckBox4.isSelected()==true && jCheckBox2.isSelected()==false && jCheckBox3.isSelected()==false && jCheckBox1.isSelected()==false){
            System.out.println("novedad");
            String procedure = "{ ? = call CONSULTA_SOLO_NOVEDAD ( ? ) }";
            System.out.println(jTextField2.getText());
            consultar(jTextField4.getText(), procedure);
        }
        if(jCheckBox1.isSelected()==true && jCheckBox2.isSelected()==true && jCheckBox3.isSelected()==false && jCheckBox4.isSelected()==false){
            System.out.println("usuario maquinaria");
            String procedure = "{ ? = call CONSULTA_USUARIO_MAQUINARIA ( ? , ?) }";
            consultar_dos(jTextField1.getText(),jTextField2.getText(), procedure);
        }
        
        if(jCheckBox1.isSelected()==true && jCheckBox2.isSelected()==false && jCheckBox3.isSelected()==true && jCheckBox4.isSelected()==false){
            System.out.println("usuario molde");
            String procedure = "{ ? = call CONSULTA_USUARIO_MOLDE ( ? , ?) }";
            System.out.println(jTextField1.getText() + " " + jTextField3.getText());
            consultar_dos(jTextField1.getText(),jTextField3.getText(), procedure);
        }
        
        if(jCheckBox1.isSelected()==true && jCheckBox2.isSelected()==false && jCheckBox3.isSelected()==false && jCheckBox4.isSelected()==true){
            System.out.println("usuario novedad");
            String procedure = "{ ? = call CONSULTA_USUARIO_NOVEDAD ( ? , ?) }";
            System.out.println(jTextField1.getText() + " " + jTextField3.getText());
            consultar_dos(jTextField1.getText(),jTextField4.getText(), procedure);
        }
        
        if(jCheckBox1.isSelected()==true && jCheckBox2.isSelected()==true && jCheckBox3.isSelected()==true && jCheckBox4.isSelected()==false){
            System.out.println("usuario mmaquinaria molde");
            String procedure = "{ ? = call CONSULTA_USUARIO_MAQUINARIA_MOLDE ( ? , ? , ?) }";
            System.out.println(jTextField1.getText() + " " + jTextField3.getText());
            consultar_tres(jTextField1.getText(),jTextField2.getText(),jTextField3.getText(), procedure);
        }
        
        if(jCheckBox1.isSelected()==true && jCheckBox2.isSelected()==true && jCheckBox3.isSelected()==false && jCheckBox4.isSelected()==true){
            System.out.println("usuario maquinaria novedad");
            String procedure = "{ ? = call CONSULTA_USUARIO_MAQUINARIA_NOVEDAD ( ? , ? , ? ) }";
            System.out.println(jTextField1.getText() + " " + jTextField3.getText());
            consultar_tres(jTextField1.getText(),jTextField2.getText(),jTextField4.getText(), procedure);
        }
        
        if(jCheckBox1.isSelected()==true && jCheckBox2.isSelected()==false && jCheckBox3.isSelected()==true && jCheckBox4.isSelected()==true){
            System.out.println("usuario molde novedad");
            String procedure = "{ ? = call CONSULTA_USUARIO_MOLDE_NOVEDAD ( ? , ? , ?) }";
            System.out.println(jTextField1.getText() + " " + jTextField3.getText() + " " + jTextField4.getText());
            consultar_tres(jTextField1.getText(),jTextField3.getText(),jTextField4.getText(), procedure);
        }
        
        if(jCheckBox1.isSelected()==false && jCheckBox2.isSelected()==true && jCheckBox3.isSelected()==true && jCheckBox4.isSelected()==false){
            System.out.println("usuario molde novedad");
            String procedure = "{ ? = call CONSULTA_MAQUINARIA_MOLDE ( ? , ? ) }";
            System.out.println(jTextField1.getText() + " " + jTextField3.getText() + " " + jTextField4.getText());
            consultar_dos(jTextField2.getText(),jTextField3.getText(), procedure);
        }
        
        if(jCheckBox1.isSelected()==false && jCheckBox2.isSelected()==true && jCheckBox3.isSelected()==false && jCheckBox4.isSelected()==true){
            System.out.println("usuario molde novedad");
            String procedure = "{ ? = call CONSULTA_MAQUINARIA_NOVEDAD ( ? , ? ) }";
            System.out.println(jTextField1.getText() + " " + jTextField3.getText() + " " + jTextField4.getText());
            consultar_dos(jTextField2.getText(),jTextField4.getText(), procedure);
        }
        
        
        if(jCheckBox1.isSelected()==false && jCheckBox2.isSelected()==true && jCheckBox3.isSelected()==true && jCheckBox4.isSelected()==true){
            System.out.println("usuario molde novedad");
            String procedure = "{ ? = call CONSULTA_MAQUINARIA_MOLDE_NOVEDAD ( ? , ? ,?) }";
            System.out.println(jTextField1.getText() + " " + jTextField3.getText() + " " + jTextField4.getText());
            consultar_tres(jTextField2.getText(),jTextField3.getText(),jTextField4.getText(), procedure);
        }
        
        if(jCheckBox1.isSelected()==false && jCheckBox2.isSelected()==false && jCheckBox3.isSelected()==true && jCheckBox4.isSelected()==true){
            System.out.println("usuario molde novedad");
            String procedure = "{ ? = call CONSULTA_MOLDE_NOVEDAD ( ? , ? ) }";
            System.out.println(jTextField1.getText() + " " + jTextField3.getText() + " " + jTextField4.getText());
            consultar_dos(jTextField3.getText(),jTextField4.getText(), procedure);
        }
    }                                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        JTable tabla = new JTable();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet hoja = wb.createSheet();
        XSSFRow fila = hoja.createRow(0);
        fila.createCell(0).setCellValue("ID");
        fila.createCell(1).setCellValue("Fecha");
        fila.createCell(2).setCellValue("Maquinaria");
        fila.createCell(3).setCellValue("Usuario");
        fila.createCell(4).setCellValue("Descripcion");
        fila.createCell(5).setCellValue("Tipo de Novedad");
        fila.createCell(6).setCellValue("Descripcion");
        fila.createCell(7).setCellValue("Solucion");
        fila.createCell(8).setCellValue("Novedad");
        
        XSSFRow filas;
        
        for (int i = 0 ; i < jTable.getRowCount();i++){
            filas = hoja.createRow(i+1);
            
            for(int j = 0 ; j < 9 ; j++){
                System.out.println("Tabla --> "+jTable.getValueAt(i, j).toString());
                filas.createCell(j).setCellValue(jTable.getValueAt(i, j).toString());
                //filas.createCell(0).setCellValue(jTable.getValueAt(i, j).toString());
            }
            
            //filas.createCell(0).setCellValue(jTable.getValueAt(i, 1).toString());
            //filas.createCell(0).setCellValue(jTable.getValueAt(i, 2).toString());
            //filas.createCell(0).setCellValue(jTable.getValueAt(i, 3).toString());
            //filas.createCell(0).setCellValue(jTable.getValueAt(i, 4).toString());
            //filas.createCell(0).setCellValue(jTable.getValueAt(i, 5).toString());
            //filas.createCell(0).setCellValue(jTable.getValueAt(i, 6).toString());
            //filas.createCell(0).setCellValue(jTable.getValueAt(i, 7).toString());
            //filas.createCell(0).setCellValue(jTable.getValueAt(i, 8).toString());
            
        }
        try {
            wb.write(new FileOutputStream(new File("C://data//data.xlsx")));
            Desktop.getDesktop().open(new File("C://data//data.xlsx"));
        } catch (Exception e) {
        }
        
    }                                        


    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration                   
}


