/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angel
 */
public class Conexion {
        public Connection Conexion(){
        List resultData =  new ArrayList();
        Connection conexion = null;
        try{
            String cadena = "jdbc:postgresql://192.168.1.4:5432/BACTECHNOLOGY";
            String user = "postgres";
            String pass = "root";
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(cadena,user,pass);
            return conexion;
        }catch(Exception e){
            return conexion;
        }
    }
    
}
