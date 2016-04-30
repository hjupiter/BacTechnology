package Conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static final String cadena = "jdbc:postgresql://192.168.1.107:5432/BACTECHNOLOGY";
    private static final String user = "postgres";
    private static final String pass = "root";
    private static Connection conexion = null;
    
    public Connection Conexion(){
        try{
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(cadena,user,pass);
            return conexion;
        }catch(ClassNotFoundException | SQLException e){
            return conexion;
        }
    }
}