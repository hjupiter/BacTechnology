/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author bakamedi
 */
public class Icono {
    
    private static Icono icono;
    private static final String rutaImagen = "/imagenes/icono.png";
    
    public static Icono getInstance(){
        if(icono == null){
            icono = new Icono();
        }
        return icono;
    }
    
    public void setIcono(JFrame frame){
        frame.setIconImage(new ImageIcon(getClass().getResource(rutaImagen)).getImage());
    }
    
}
