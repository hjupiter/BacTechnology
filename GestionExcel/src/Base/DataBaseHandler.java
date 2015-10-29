/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bakamedi
 */
public class DataBaseHandler {
    private static final String conexion = "jdbc:postgresql://localhost:5432/BACTECHNOLOGY";
    private static final String user = "postgres";
    private static final String pass = "root";
    private Connection con;
    
    
    
    public void guardaMoldes(String codigo,String nombre){
        try{
            Class.forName("org.postgresql.Driver");
            Connection conn = getConexion();
                boolean res;
                try (CallableStatement insertar_molde = conn.prepareCall("{ ? = call INSERTAR_MOLDE ( ? , ? ) }")) {
                    insertar_molde.registerOutParameter(1, Types.BOOLEAN);
                    insertar_molde.setString(2, codigo);//codigo
                    insertar_molde.setString(3, nombre);//nombre
                    insertar_molde.execute();
                    res = insertar_molde.getBoolean(1);
                    if(res)
                        System.out.println("insert");
                    else
                        System.out.println("--------");
                    insertar_molde.close();
                    conn.close();
                }
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void guardaMaquinas(String codigo,String nombre){
        try{
            Class.forName("org.postgresql.Driver");
            Connection conn = getConexion();
            CallableStatement insertar_maquinaria =  conn.prepareCall("{ ? = call INSERTAR_MAQUINARIA ( ? , ? ) }");
            insertar_maquinaria.registerOutParameter(1, Types.BOOLEAN);
            insertar_maquinaria.setString(2, codigo);
            insertar_maquinaria.setString(3, nombre);
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
    
    public Connection getConexion() throws SQLException{
        Connection conn = DriverManager.getConnection(conexion,user,pass);
        return conn;
    }
    
}
