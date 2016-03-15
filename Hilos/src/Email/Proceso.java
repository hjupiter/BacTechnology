package Email;



import Conexion.Conexion;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bakamedi
 */
public class Proceso extends Thread{
    private Email email;
    private static Conexion conexion =  new Conexion();
    private static Connection conn = conexion.Conexion();
    private int hora;
    private int min;
    private int sec;
    private String dia,mes,annio,fecha;
    private final Calendar c = Calendar.getInstance();
    private String rutaArchivo = "C://data//";
    private String nombreArchivo;
    
    public Proceso(String msg){
        super(msg);
    }
    
    @Override
    public void run(){
        while(true){
            Date fecha = new Date();
            hora = fecha.getHours();
            min = fecha.getMinutes();
            sec = fecha.getSeconds();
            if(hora == 12 && min == 22 && sec == 0 ){
                obtenerDatosBase();
                email = new Email("reportes.bactechnology.2016@gmail.com", "reportes.bactechnology.2016@gmail.com", getNombreArchivo(), getRutaArchivo(), "Reportes BacTechnology", "Por favor no responda a este mensaje");
                email.sendMail();
            }
        }
    }
    
    @SuppressWarnings("empty-statement")
    private void obtenerDatosBase(){
        Random  rnd = new Random();
        int token =  (int) rnd.nextInt(999999999);
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
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH)+1);
        annio = Integer.toString(c.get(Calendar.YEAR));
        fecha = ""+annio+"-"+mes+"-"+dia;
        try{
            conn.setAutoCommit(false);
            CallableStatement todas_reportes =  conn.prepareCall("{ ? = CALL TODOS_REPORTES_FECHAS ( ? ) }");
            todas_reportes.registerOutParameter(1, Types.OTHER);
            todas_reportes.setString(2, fecha);
            todas_reportes.execute();
            ResultSet results = (ResultSet)todas_reportes.getObject(1);
            Object datos[] = new Object[12];
            int j = 0;
            while(results.next()){
                filas = hoja.createRow(j+1);
                for(int i = 0; i<11;i++){
                    if(i==2){
                        String x = results.getObject(i+1).toString();
                        int entero = Integer.parseInt(x);
                        CallableStatement buscar = conn.prepareCall("{ ? = call buscar_maquina_id (?) }");
                        buscar.registerOutParameter(1, Types.VARCHAR);
                        buscar.setInt(2, entero);
                        buscar.execute();
                        String maquina = buscar.getString(1);
                        //System.out.println("1 "+maquina);
                        buscar.close();
                        datos[i] = maquina;
                        filas.createCell(i).setCellValue(datos[i].toString());
                    }
                    else if(i==3){
                        String x = results.getObject(i+1).toString();
                        int entero = Integer.parseInt(x);
                        CallableStatement buscar = conn.prepareCall("{ ? = call buscar_molde_id (?) }");
                        buscar.registerOutParameter(1, Types.VARCHAR);
                        buscar.setInt(2, entero);
                        buscar.execute();
                        String molde = buscar.getString(1);
                        //System.out.println("2 "+molde);
                        buscar.close();
                        datos[i] = molde;
                        filas.createCell(i).setCellValue(datos[i].toString());
                    }
                    else if(i==4){
                        String x = results.getObject(i+1).toString();
                        int entero = Integer.parseInt(x);
                        CallableStatement buscar = conn.prepareCall("{ ? = call buscar_usuario_id (?) }");
                        buscar.registerOutParameter(1, Types.VARCHAR);
                        buscar.setInt(2, entero);
                        buscar.execute();
                        String usuario = buscar.getString(1);
                        //System.out.println("3 "+usuario);
                        buscar.close();
                        datos[i] = usuario;
                        filas.createCell(i).setCellValue(datos[i].toString());
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
                        filas.createCell(i).setCellValue(datos[i].toString());
                    }
                }
                j++;
            }
            todas_reportes.close();
            conn.close();
            try {
                setRutaArchivo(rutaArchivo+fecha+"reportes"+token+".xlsx");
                setNombreArchivo(fecha+"reportes"+token+".xlsx");
                wb.write(new FileOutputStream(new File(getRutaArchivo())));
            } catch (Exception e) {
            }
        }catch(SQLException | NumberFormatException | IOException e){
            System.out.println(e.getMessage());
        };
    }

    private String getRutaArchivo() {
        return rutaArchivo;
    }

    private void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    private String getNombreArchivo() {
        return nombreArchivo;
    }

    private void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    
    
}