/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BacTechnology.service;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.xml.ws.org.objectweb.asm.Type;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.jboss.weld.bean.builtin.CallableMethodHandler;

/**
 *
 * @author Angel
 */
@WebService(serviceName = "BacTechnologyService")
public class BacTechnologyService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        Date x = Date.valueOf(LocalDate.now());
        System.out.println(x.toString());
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "CargarMaquinarias")
    public List CargarMaquinarias() {
        //TODO write your implementation code here:
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        List lista = new ArrayList();
        try{
            conn.setAutoCommit(false);
            CallableStatement todas_maquinarias =  conn.prepareCall("{ ? = CALL TODAS_MAQUINARIAS ( ) }");
            todas_maquinarias.registerOutParameter(1, Types.OTHER);
            todas_maquinarias.execute();
            ResultSet results = (ResultSet)todas_maquinarias.getObject(1);
            while(results.next()){
                lista.add(results.getString(2));
            }
            todas_maquinarias.close();
            conn.close();
            return lista;
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return lista;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Autenticacion")
    public boolean Autenticacion(@WebParam(name = "usuario") String usuario, @WebParam(name = "contrasena") String contraseña) {
        //TODO write your implementation code here:
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            CallableStatement autenticacion =  conn.prepareCall("{ ? = call AUTENTICACION ( ? , ? ) }");
            autenticacion.registerOutParameter(1, Types.INTEGER);
            autenticacion.setString(2, usuario);
            autenticacion.setString(3, contraseña);
            autenticacion.execute();
            int res = autenticacion.getInt(1);
            System.out.println(res);
            boolean rest;
            autenticacion.close();
            conn.close();
            if(res == 1 || res == 2)
                rest = true;
            else 
                rest = false;
            return rest;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "CargarMoldes")
    public List CargarMoldes() {
        //TODO write your implementation code here:
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        List lista = new ArrayList();
        try{
            conn.setAutoCommit(false);
            CallableStatement todos_moldes =  conn.prepareCall("{ ? = CALL TODOS_MOLDES ( ) }");
            todos_moldes.registerOutParameter(1, Types.OTHER);
            todos_moldes.execute();
            ResultSet results = (ResultSet)todos_moldes.getObject(1);
            while(results.next()){
                lista.add(results.getString(2));
            }
            todos_moldes.close();
            conn.close();
            return lista;
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return lista;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "GuardaImagen")
    public Boolean GuardaImagen(@WebParam(name = "foto") String foto) {
        
        byte[] imageInByte = Base64.decode(foto);
        int longitudBytes = imageInByte.length;
        
        InputStream in = new ByteArrayInputStream(imageInByte);
        
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            CallableStatement imagen =  conn.prepareCall("{ ? = call imagen ( ? , ? ) }");
            imagen.registerOutParameter(1, Types.BOOLEAN);
            imagen.setBinaryStream(2,in,longitudBytes);
            imagen.setString(3, "hola");
            
            imagen.execute();
            boolean res = imagen.getBoolean(1);
            imagen.close();
            conn.close();
            System.out.println(res);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "NuevoReporte")
    public Boolean NuevoReporte(@WebParam(name = "maquina") String maquina, @WebParam(name = "molde") String molde, @WebParam(name = "usuario") String usuario, @WebParam(name = "novedad") String novedad, @WebParam(name = "solucion") String solucion, @WebParam(name = "tipoNovedad") String tipoNovedad, @WebParam(name = "tipoSolucion") String tipoSolucion, @WebParam(name = "foto") String foto, @WebParam(name = "novedadDetectada") String novedadDetectada) {
        //TODO write your implementation code here:
        System.out.println(usuario);
        System.out.println(molde);
        System.out.println(maquina);
        System.out.println(novedad);
        System.out.println(tipoNovedad);
        System.out.println(solucion);
        System.out.println(tipoSolucion);
        System.out.println(novedadDetectada);
        System.out.println("------------------");
        Conexion conexion = new Conexion();
        Connection conn =  conexion.Conexion();
        try{
            CallableStatement funcion =  conn.prepareCall("{ ? = call buscar_usuario ( ? ) }");
            funcion.registerOutParameter(1, Types.INTEGER);
            funcion.setString(2, usuario);
            funcion.execute();
            int UsuarioEcontrado = funcion.getInt(1);
            System.out.println(usuario);
            System.out.println("--------------Usuario");
            System.out.println(UsuarioEcontrado);
            funcion.close();
            
            funcion = conn.prepareCall("{ ? = call buscar_molde ( ? ) }");
            funcion.registerOutParameter(1, Types.INTEGER);
            funcion.setString(2, molde);
            funcion.execute();
            int MoldeEcontrado = funcion.getInt(1);
            System.out.println(molde);
            System.out.println("--------------Molde");
            System.out.println(MoldeEcontrado);
            funcion.close();
            
            System.out.println(maquina);
            funcion = conn.prepareCall("{ ? = call buscar_maquina ( ? ) }");
            funcion.registerOutParameter(1, Types.INTEGER);
            funcion.setString(2, maquina);
            funcion.execute();
            int MaquinariaEcontrado = funcion.getInt(1);
            System.out.println(maquina);
            System.out.println("--------------Maquina");
            System.out.println(MaquinariaEcontrado);
            funcion.close();
            
            System.out.println("----------");
            
            if(UsuarioEcontrado!=0 && MoldeEcontrado !=0 && MaquinariaEcontrado != 0){
                System.out.println("asdasdasdsadsa");
                byte[] imageInByte = Base64.decode(foto);
                int longitudBytes = imageInByte.length;
                InputStream in = new ByteArrayInputStream(imageInByte);
                Date fecha = Date.valueOf(LocalDate.now());
                
                funcion = conn.prepareCall("{ ? = call insertar_reporte( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) }");
                funcion.registerOutParameter(1, Types.BOOLEAN);
                funcion.setDate(2,fecha);
                funcion.setInt(3, MaquinariaEcontrado);
                funcion.setInt(4, MoldeEcontrado);
                funcion.setInt(5, UsuarioEcontrado);
                funcion.setString(6, novedad);
                funcion.setString(7, tipoNovedad);
                funcion.setString(8, solucion);
                funcion.setString(9, tipoSolucion);
                funcion.setString(10, novedadDetectada);
                funcion.setBinaryStream(11,in, longitudBytes);
                funcion.setString(12, " ");
                funcion.execute();
                System.out.println("asdasdasdafsadfsafasdfdsafasdfsadfasdfasd");
                
                /*
                byte[] imageInByte1;
                imageInByte1 = Base64.decode(foto);
                InputStream inr = new ByteArrayInputStream(imageInByte1);
                BufferedImage bImageFromConvert1 = ImageIO.read(inr);
                ImageIO.write(bImageFromConvert1, "jpg", new File(
                            "c:/Users/Angel/Desktop/new-darksouls.jpg"));
                */
                boolean a = funcion.getBoolean(1);
                System.out.println(a);
                funcion.close();
                conn.close();
                return a;
            }
            
            
            
        }catch(Exception e){
            return false;
        }
        return null;
    }
    
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "NuevoReporteMolde")
    public Boolean NuevoReporteMolde(@WebParam(name = "maquina") String maquina, @WebParam(name = "molde") String molde, @WebParam(name = "usuario") String usuario, @WebParam(name = "novedad") String novedad, @WebParam(name = "solucion") String solucion, @WebParam(name = "tipoNovedad") String tipoNovedad, @WebParam(name = "tipoSolucion") String tipoSolucion, @WebParam(name = "foto") String foto, @WebParam(name = "novedadDetectada") String novedadDetectada, @WebParam(name = "articulo") String articulo) {
        //TODO write your implementation code here:
        System.out.println("Usuario: "+usuario);
        System.out.println("Molde: "+molde);
        System.out.println("Maquinaria: "+maquina);
        System.out.println("Novedad : "+novedad);
        System.out.println("Tipo Novedad: "+tipoNovedad);
        System.out.println("Solucion: "+solucion);
        System.out.println("Tipo SOlucion: "+tipoSolucion);
        System.out.println("Novedad Detectada: "+novedadDetectada);
        System.out.println("Articulo: "+articulo);
        System.out.println("------------------");
        Conexion conexion = new Conexion();
        Connection conn =  conexion.Conexion();
        try{
            CallableStatement funcion =  conn.prepareCall("{ ? = call buscar_usuario ( ? ) }");
            funcion.registerOutParameter(1, Types.INTEGER);
            funcion.setString(2, usuario);
            funcion.execute();
            int UsuarioEcontrado = funcion.getInt(1);
            System.out.println(usuario);
            System.out.println("--------------Usuario");
            System.out.println(UsuarioEcontrado);
            funcion.close();
            
            funcion = conn.prepareCall("{ ? = call buscar_molde ( ? ) }");
            funcion.registerOutParameter(1, Types.INTEGER);
            funcion.setString(2, molde);
            funcion.execute();
            int MoldeEcontrado = funcion.getInt(1);
            System.out.println(molde);
            System.out.println("--------------Molde");
            System.out.println(MoldeEcontrado);
            funcion.close();
            
            funcion = conn.prepareCall("{ ? = call buscar_maquina ( ? ) }");
            funcion.registerOutParameter(1, Types.INTEGER);
            funcion.setString(2, maquina);
            funcion.execute();
            int MaquinariaEcontrado = funcion.getInt(1);
            System.out.println(maquina);
            System.out.println("--------------Maquina");
            System.out.println(MaquinariaEcontrado);
            funcion.close();
            
            System.out.println("----------");
            
            if(UsuarioEcontrado!=0 && MoldeEcontrado !=0 && MaquinariaEcontrado != 0){
                System.out.println("asdasdasdsadsa");
                byte[] imageInByte = Base64.decode(foto);
                int longitudBytes = imageInByte.length;
                InputStream in = new ByteArrayInputStream(imageInByte);
                Date fecha = Date.valueOf(LocalDate.now());
                System.out.println("----------------->>>>>>");
                funcion = conn.prepareCall("{ ? = call insertar_reporte_molde( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?) }");
                funcion.registerOutParameter(1, Types.BOOLEAN);
                funcion.setDate(2,fecha);
                funcion.setInt(3, MaquinariaEcontrado);
                funcion.setInt(4, MoldeEcontrado);
                funcion.setInt(5, UsuarioEcontrado);
                funcion.setString(6, novedad);
                funcion.setString(7, tipoNovedad);
                funcion.setString(8, solucion);
                funcion.setString(9, tipoSolucion);
                funcion.setString(10, novedadDetectada);
                funcion.setBinaryStream(11,in, longitudBytes);
                funcion.setString(12,articulo);
                funcion.setString(13," ");
                System.out.println("----------------->>>>>>");
                funcion.execute();
                System.out.println("asdasdasdafsadfsafasdfdsafasdfsadfasdfasd");
                
                /*
                byte[] imageInByte1;
                imageInByte1 = Base64.decode(foto);
                InputStream inr = new ByteArrayInputStream(imageInByte1);
                BufferedImage bImageFromConvert1 = ImageIO.read(inr);
                ImageIO.write(bImageFromConvert1, "jpg", new File(
                            "c:/Users/Angel/Desktop/new-darksouls.jpg"));
                */
                boolean a = funcion.getBoolean(1);
                System.out.println(a);
                funcion.close();
                conn.close();
                return a;
            }
            
            
            
        }catch(Exception e){
            return false;
        }
        return null;
    }
}


