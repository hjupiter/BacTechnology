/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BacTechnology.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
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
                lista.add(results.getString(1));
            }
            todas_maquinarias.close();
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
    public Boolean Autenticacion(@WebParam(name = "usuario") String usuario, @WebParam(name = "contrasena") String contraseña) {
        //TODO write your implementation code here:
        Conexion conexion =  new Conexion();
        Connection conn = conexion.Conexion();
        try{
            CallableStatement autenticacion =  conn.prepareCall("{ ? = call AUTENTICACION ( ? , ? ) }");
            autenticacion.registerOutParameter(1, Types.BOOLEAN);
            autenticacion.setString(2, usuario);
            autenticacion.setString(3, contraseña);
            autenticacion.execute();
            boolean res = autenticacion.getBoolean(1);
            autenticacion.close();
            return res;
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
                lista.add(results.getString(1));
            }
            todos_moldes.close();
            return lista;
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return lista;
        }
    }
    
    
}

