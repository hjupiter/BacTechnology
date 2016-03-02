/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacion;

import java.awt.event.KeyEvent;

/**
 *
 * @author Angel
 */
public class General {
    public void soloLetras(KeyEvent evt){
        char c=evt.getKeyChar(); 
        if(Character.isDigit(c)) { 
            //getToolkit().beep(); 
            evt.consume();
            //Error.setText("Ingresa Solo Letras"; 
        } 
    }
    
    public void soloNumeros(KeyEvent evt){
        char c=evt.getKeyChar(); 
        if(Character.isLetter(c)) { 
            //getToolkit().beep(); 
            evt.consume();
            //Error.setText("Ingresa Solo Letras"; 
        } 
    }
}
