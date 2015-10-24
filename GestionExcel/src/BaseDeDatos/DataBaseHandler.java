/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author bakamedi
 */
public class DataBaseHandler {
    private static String conexion = "jdbc:postgresql://localhost:5433/BACTECHNOLOGY";
    private static String user = "postgres";
    private static String pass = "root";
    private Connection con;
    
    public DataBaseHandler() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            con = DriverManager.getConnection(conexion, user, pass);
            java.sql.Statement st = con.createStatement();
        }
        
        
    }
    
    public void connect(String codigo,String nombre){
        String cadena = "jdbc:postgresql://192.168.1.1:5432/BACTECHNOLOGY";
        String user = "postgres";
        String pass = "root";
        try{
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(cadena,user,pass);
            
            CallableStatement insertar_maquinaria =  conn.prepareCall("{ ? = call INSERTAR_MAQUINARIA ( ? , ? ) }");
            insertar_maquinaria.registerOutParameter(1, Types.BOOLEAN);
            insertar_maquinaria.setString(2, codigo);//codigo
            insertar_maquinaria.setString(3, nombre);//nombre
            insertar_maquinaria.execute();
            boolean res = insertar_maquinaria.getBoolean(1);
            insertar_maquinaria.close();
            
            if(res){
                System.out.println("insert");
            }
            
        }catch(Exception e){
            System.out.println("Errro: "+e.getMessage());
        }
    }
    
}
