package Email;



import Conexion.Conexion;
import java.awt.Desktop;
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
import java.text.DateFormat;
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
    private String ruta = "C://data//";
    private String nombreArchivo;
    private String nombreArchivoMolde;
    private String rutaArchivo;
    private String rutaArchivoMolde;
    
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
            if(hora == 18 && min == 48 && sec == 0 && obtenerDatosBase() && obtenerDatosBaseMolde()){
                email = new Email("reportes.bactechnology.2016@gmail.com", 
                                  "reportes.bactechnology.2016@gmail.com",
                                  getNombreArchivo(),getRutaArchivo(),
                                  "Reportes BacTechnology",
                                  "Por favor no responda a este mensaje");
                //System.out.println(count);
                email.sendMail();
                email = new Email("reportes.bactechnology.2016@gmail.com", 
                                  "reportes.bactechnology.2016@gmail.com",
                                  getNombreArchivoMolde(),getRutaArchivoMolde(),
                                  "Reportes BacTechnology",
                                  "Por favor no responda a este mensaje");
                email.sendMail();
            }
            else{
                //System.out.println("no hay datos");
            }
                
        }
    }
    
    @SuppressWarnings("empty-statement")
    private boolean obtenerDatosBase(){
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
            int j = 1;
            while(results.next()){
                filas = hoja.createRow(j);
                //System.out.println(" "+i+" RRRRR "+datos[1].toString());
                //fecha = verificarFormatoFecha(fecha,c,dia,mes,annio);
                //System.out.println("PPPP "+fecha);
                //if(datoFinal.equals(fecha)){
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                for(int i = 0; i<11;i++){
                    switch(i){
                        case 0:
                            filas.createCell(0).setCellValue(j);
                            break;
                        case 1:
                            datos[i] = results.getObject(i+1);
                            System.out.println("FECHA --> "+datos[i].toString());
                            filas.createCell(2).setCellValue(datos[i].toString());
                            break;
                        case 2:
                            //maquina
                            String id_maquina_string = results.getObject(i+1).toString();
                            int id_maquina = Integer.parseInt(id_maquina_string);
                            CallableStatement buscar1 = conn.prepareCall("{ ? = call buscar_maquina_id (?) }");
                            buscar1.registerOutParameter(1, Types.VARCHAR);
                            buscar1.setInt(2, id_maquina);
                            buscar1.execute();
                            String maquina = buscar1.getString(1);
                            buscar1.close();
                            datos[i] = maquina;
                            //datos[i] = results.getObject(i+1);
                            //System.out.println("--> id_maquinaria ["+i+"] "+results.getObject(i+1));
                            System.out.println("MAQUINA --> "+maquina);
                            filas.createCell(3).setCellValue(datos[i].toString());
                            break;
                        case 3:
                            //molde
                            String id_molde_string = results.getObject(i+1).toString();
                            int id_molde = Integer.parseInt(id_molde_string);
                            CallableStatement buscar2 = conn.prepareCall("{ ? = call buscar_molde_id (?) }");
                            buscar2.registerOutParameter(1, Types.VARCHAR);
                            buscar2.setInt(2, id_molde);
                            buscar2.execute();
                            String molde = buscar2.getString(1);
                            //System.out.println("2 "+molde);
                            buscar2.close();
                            datos[i] = molde;
                            System.out.println("MOLDE --> "+molde);
                            filas.createCell(4).setCellValue(datos[i].toString());
                            datos[i] = results.getObject(i+1);
                            //System.out.println("--> id_molde ["+i+"] "+results.getObject(i+1));
                            break;
                        case 4:
                            //usuario
                            String id_usu_string = results.getObject(i+1).toString();
                            int id_usu = Integer.parseInt(id_usu_string);
                            CallableStatement buscar = conn.prepareCall("{ ? = call buscar_usuario_id (?) }");
                            buscar.registerOutParameter(1, Types.VARCHAR);
                            buscar.setInt(2, id_usu);
                            buscar.execute();
                            String usuario = buscar.getString(1);
                            //System.out.println("3 "+usuario);
                            buscar.close();
                            datos[i] = usuario;
                            System.out.println("USUARIO --> "+usuario);
                            filas.createCell(1).setCellValue(datos[i].toString());
                            //datos[i] = results.getObject(i+1);
                            //System.out.println("--> id_usuario ["+i+"] "+results.getObject(i+1));
                            break;
                        case 5:
                            //descripcion novedad
                            datos[i] = results.getObject(i+1);
                            System.out.println("DESCRIPCION NOVEDAD --> "+results.getObject(i+1));
                            filas.createCell(7).setCellValue(datos[i].toString());
                            break;
                        case 6:
                            //tipo nocedad
                            datos[i] = results.getObject(i+1);
                            System.out.println("TIPO NOVEDAD --> "+results.getObject(i+1));
                            filas.createCell(6).setCellValue(datos[i].toString());
                            break;
                        case 7:
                            datos[i] = results.getObject(i+1);
                            System.out.println("DESCRIPCION SOLUCION --> "+results.getObject(i+1));
                            filas.createCell(9).setCellValue(datos[i].toString());
                            break;
                        case 8:
                            datos[i] = results.getObject(i+1);
                            System.out.println("TIPO SOLUCION --> "+results.getObject(i+1));
                            filas.createCell(8).setCellValue(datos[i].toString());
                            break;
                        case 9:
                            datos[i] = results.getObject(i+1);
                            System.out.println("NOVEDAD --> "+results.getObject(i+1));
                            filas.createCell(5).setCellValue(datos[i].toString());
                            break;
                        case 10:
                            datos[i] = results.getObject(i+1);
                            System.out.println("FOTO --> "+results.getObject(i+1));
                            break;
                    }
                }
                j++;       
            }
            try {
                setRutaArchivo(ruta+fecha+"reportes"+token+".xlsx");
                setNombreArchivo(fecha+"reportes"+token+".xlsx");
                wb.write(new FileOutputStream(new File(getRutaArchivo())));
                return true;
            } catch (Exception e) {
            }
        }catch(SQLException | NumberFormatException e){
            System.out.println(e.getMessage());
            return false;
        };
        return false;
    }
    
    public boolean obtenerDatosBaseMolde(){
        Random  rnd = new Random();
        int token =  (int) rnd.nextInt(999999999);
        //System.out.println("WWWWWWWWWWWWWWWWWWWWWW");
        Conexion conexion =  new Conexion();
        //System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEE");
        Connection conn = conexion.Conexion();
        //System.out.println("DDDDDDDDDDDDDDDDDDDDDDDD");
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet hoja = wb.createSheet();
        XSSFRow fila = hoja.createRow(0);
        fila.createCell(0).setCellValue("ID");
        fila.createCell(1).setCellValue("USUARIO");//
        fila.createCell(2).setCellValue("FECHA");
        fila.createCell(3).setCellValue("MAQUINARIA");//
        fila.createCell(4).setCellValue("MOLDE");//
        fila.createCell(5).setCellValue("NOVEDAD DETECTADA");
        fila.createCell(6).setCellValue("ARTICULO");//
        fila.createCell(7).setCellValue("TIPO DE NOVEDAD");
        fila.createCell(8).setCellValue("DESCRIPCIÓN NOVEDAD");
        fila.createCell(9).setCellValue("TIPO DE SOLUCIÓN");
        fila.createCell(10).setCellValue("DESCRIPCIÓN SOLUCIÓN");
        hoja.setColumnWidth(0, 256*5);
        hoja.setColumnWidth(1, 256*15);
        hoja.setColumnWidth(2, 256*10);
        hoja.setColumnWidth(3, 256*23);
        hoja.setColumnWidth(4, 256*37);
        hoja.setColumnWidth(5, 256*22);
        hoja.setColumnWidth(6, 256*15);
        hoja.setColumnWidth(7, 256*18);
        hoja.setColumnWidth(8, 256*22);
        hoja.setColumnWidth(9, 256*18);
        hoja.setColumnWidth(10, 256*22);
        XSSFRow filas;
        //String fechaObtenida = df.format(btnFechaInicio.getDate());
        //String fechaCorrecta;
        //String procedure = "{ ? = call CONSULTA_REPORTE_FECHA_POR_MES ( ? ) }";
        //fechaCorrecta = ponerFechaFormatoCorrecto(fechaObtenida);
        //System.out.println(fechaCorrecta);
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH)+1);
        annio = Integer.toString(c.get(Calendar.YEAR));
        fecha = ""+annio+"-"+mes+"-"+dia;
        //fecha = ""+2016+"-"+03+"-"+23;
        System.out.println(fecha);
        //consultar_por_fecha(fecha,procedure);
        try{
            conn.setAutoCommit(false);
            //System.out.println("SSSSSSSSSSSSSSSSSSSSSSSS");
            CallableStatement todas_reportes =  conn.prepareCall("{ ? = CALL todos_reportesmoldes_fechas ( ? ) }");
            todas_reportes.registerOutParameter(1, Types.OTHER);
            todas_reportes.setString(2, fecha);
            todas_reportes.execute();
            ResultSet results = (ResultSet)todas_reportes.getObject(1);
            Object datos[] = new Object[13];
            int j = 1;
            while(results.next()){
                filas = hoja.createRow(j);
                //datos[1] = results.getObject(2);
                //String datoFinal = datos[1].toString();
                //System.out.println(" "+j+" RRRRR "+datos[1].toString());
                //fecha = verificarFormatoFecha(fecha,c,dia,mes,annio);
                //System.out.println("PPPP "+fecha);
                //if(datoFinal.equals(fecha)){
                System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                for(int i = 0; i<12;i++){
                    switch(i){
                        case 0:
                            //datos[i] = results.getObject(i+1);
                            //System.out.println("--> id_reporte ["+i+"] "+results.getObject(i+1));
                            filas.createCell(0).setCellValue(j);
                            break;
                        case 1:
                            //fecha
                            datos[i] = results.getObject(i+1);
                            //System.out.println("FECHA "+results.getObject(i+1));
                            filas.createCell(2).setCellValue(datos[i].toString());
                            break;
                        case 2:
                            //maquina
                            String id_maquina_string = results.getObject(i+1).toString();
                            int id_maquina = Integer.parseInt(id_maquina_string);
                            CallableStatement buscar1 = conn.prepareCall("{ ? = call buscar_maquina_id (?) }");
                            buscar1.registerOutParameter(1, Types.VARCHAR);
                            buscar1.setInt(2, id_maquina);
                            buscar1.execute();
                            String maquina = buscar1.getString(1);
                            buscar1.close();
                            datos[i] = maquina;
                            //datos[i] = results.getObject(i+1);
                            //System.out.println("--> id_maquinaria ["+i+"] "+results.getObject(i+1));
                            //System.out.println("MAQUINA --> "+maquina);
                            filas.createCell(3).setCellValue(datos[i].toString());
                            break;
                        case 3:
                            //molde
                            String id_molde_string = results.getObject(i+1).toString();
                            int id_molde = Integer.parseInt(id_molde_string);
                            CallableStatement buscar2 = conn.prepareCall("{ ? = call buscar_molde_id (?) }");
                            buscar2.registerOutParameter(1, Types.VARCHAR);
                            buscar2.setInt(2, id_molde);
                            buscar2.execute();
                            String molde = buscar2.getString(1);
                            //System.out.println("2 "+molde);
                            buscar2.close();
                            datos[i] = molde;
                            //System.out.println("MOLDE --> "+molde);
                            filas.createCell(4).setCellValue(datos[i].toString());
                            //datos[i] = results.getObject(i+1);
                            //System.out.println("--> id_molde ["+i+"] "+results.getObject(i+1));
                            break;
                        case 4:
                            //usuario
                            String id_usu_string = results.getObject(i+1).toString();
                            int id_usu = Integer.parseInt(id_usu_string);
                            CallableStatement buscar = conn.prepareCall("{ ? = call buscar_usuario_id (?) }");
                            buscar.registerOutParameter(1, Types.VARCHAR);
                            buscar.setInt(2, id_usu);
                            buscar.execute();
                            String usuario = buscar.getString(1);
                            //System.out.println("3 "+usuario);
                            buscar.close();
                            datos[i] = usuario;
                            //System.out.println("USUARIO --> "+usuario);
                            filas.createCell(1).setCellValue(datos[i].toString());
                            //datos[i] = results.getObject(i+1);
                            //System.out.println("--> id_usuario ["+i+"] "+results.getObject(i+1));
                            break;
                        case 5:
                            //descripcion novedad
                            datos[i] = results.getObject(i+1);
                            //System.out.println("DESCRIPCION NOVEDAD --> "+results.getObject(i+1));
                            filas.createCell(8).setCellValue(datos[i].toString());
                            break;
                        case 6:
                            //tipo nocedad
                            datos[i] = results.getObject(i+1);
                            //System.out.println("TIPO NOVEDAD --> "+results.getObject(i+1));
                            filas.createCell(7).setCellValue(datos[i].toString());
                            
                            break;
                        case 7:
                            datos[i] = results.getObject(i+1);
                            //System.out.println("DESCRIPCION SOLUCION --> "+results.getObject(i+1));
                            filas.createCell(10).setCellValue(datos[i].toString());
                            break;
                        case 8:
                            datos[i] = results.getObject(i+1);
                            //System.out.println("TIPO SOLUCION --> "+results.getObject(i+1));
                            filas.createCell(9).setCellValue(datos[i].toString());
                            break;
                        case 9:
                            datos[i] = results.getObject(i+1);
                            //System.out.println("NOVEDAD --> "+results.getObject(i+1));
                            filas.createCell(5).setCellValue(datos[i].toString());
                            break;
                        case 10:
                            datos[i] = results.getObject(i+1);
                            //System.out.println("FOTO --> "+results.getObject(i+1));
                            break;
                        case 11:
                            datos[i] = results.getObject(i+1);
                            //System.out.println("ARTICULO --> "+results.getObject(i+1));
                            filas.createCell(6).setCellValue(datos[i].toString());
                            break;
                    } 
                }
                j++;
                
            }
            todas_reportes.close();
            conn.close();
            try {
                setRutaArchivoMolde(ruta+fecha+"reportesMolde"+token+".xlsx");
                setNombreArchivoMolde(fecha+"reportesMolde"+token+".xlsx");
                wb.write(new FileOutputStream(new File(getRutaArchivoMolde())));
                return true;
            } catch (Exception e) {
            }
        }catch(Exception e){
            System.out.println("NO SE REALIZO LA CONSULTA");
        };
        
        return false;
    }

    public String getNombreArchivoMolde() {
        return nombreArchivoMolde;
    }

    public void setNombreArchivoMolde(String nombreArchivoMolde) {
        this.nombreArchivoMolde = nombreArchivoMolde;
    }

    public String getRutaArchivoMolde() {
        return rutaArchivoMolde;
    }

    public void setRutaArchivoMolde(String rutaArchivoMolde) {
        this.rutaArchivoMolde = rutaArchivoMolde;
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