package Conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Conexion {
    
    
    public Connection Conexion(){
        List resultData =  new ArrayList();
        Connection conexion = null;
        try{
            String cadena = "jdbc:postgresql://192.168.0.105:5432/BACTECHNOLOGY";
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