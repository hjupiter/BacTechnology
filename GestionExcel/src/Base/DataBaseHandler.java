/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author bakamedi
 */
public class DataBaseHandler {
    private static String conexion = "jdbc:postgresql://localhost:5432/BACTECHNOLOGY";
    private static String user = "postgres";
    private static String pass = "root";
    private Connection con;
    
    
    
    public void connect(String codigo,String nombre){
        try{
            Class.forName("org.postgresql.Driver");
            Connection conn = getConexion();
                boolean res;
                try (CallableStatement insertar_maquinaria = conn.prepareCall("{ ? = call INSERTAR_MOLDE ( ? , ? ) }")) {
                    insertar_maquinaria.registerOutParameter(1, Types.BOOLEAN);
                    insertar_maquinaria.setString(2, codigo);//codigo
                    insertar_maquinaria.setString(3, nombre);//nombre
                    insertar_maquinaria.execute();
                    res = insertar_maquinaria.getBoolean(1);
                    if(res)
                        System.out.println("insert");
                    else
                        System.out.println("--------");
                    insertar_maquinaria.close();
                    conn.close();
                }
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public Connection getConexion() throws SQLException{
        Connection conn = DriverManager.getConnection(conexion,user,pass);
        return conn;
    }
    
}
