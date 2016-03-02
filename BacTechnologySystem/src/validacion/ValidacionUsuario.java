/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacion;

import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import validacion.ValidacionUsuario;

/**
 *
 * @author Angel
 */
public class ValidacionUsuario {
    
    public boolean camposVacios(JTextField t1,JTextField t2,JTextField t3,JTextField t4,JTextField t5){
        if(t1.getText().length()==0 || t2.getText().length()==0 || t3.getText().length()==0 || t4.getText().length()==0 || t5.getText().length()==0){
            return false;
        }
        else{
            return true;
        }
    }

}
