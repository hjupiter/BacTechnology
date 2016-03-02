/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacion;

import javax.swing.JTextField;

/**
 *
 * @author Angel
 */
public class ValidacionMaquinaria {
    
    public boolean camposVacios(JTextField t1,JTextField t2){
        if(t1.getText().length()==0 || t2.getText().length()==0){
            return true;
        }
        else{
            return false;
        }
    }
    
}
