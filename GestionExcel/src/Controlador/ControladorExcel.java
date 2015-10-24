/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import Vista.VistaExcel;
import Modelo.ModeloExcel;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControladorExcel implements ActionListener{
    ModeloExcel modeloE = new ModeloExcel();
    VistaExcel vistaE= new VistaExcel();
    JFileChooser selecArchivo = new JFileChooser();
    File archivo;
    File archivoPath = null;
    int contAccion=0;
    
    
    public ControladorExcel(VistaExcel vistaE, ModeloExcel modeloE){
        this.vistaE = vistaE;
        this.modeloE = modeloE;
        this.vistaE.btnImportar.addActionListener(this);
    }
    
    public void AgregarFiltro(){
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        contAccion++;
        if(contAccion==1)AgregarFiltro();
        
        if(e.getSource() == vistaE.btnImportar){
            if(selecArchivo.showDialog(null, "Seleccionar archivo")==JFileChooser.APPROVE_OPTION){
                archivo=selecArchivo.getSelectedFile();
                archivoPath = selecArchivo.getSelectedFile().getAbsoluteFile();
                if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                    JOptionPane.showMessageDialog(null, modeloE.Abrir(archivo, vistaE.jtDatos) + "\n Formato ."+ archivo.getName().substring(archivo.getName().lastIndexOf(".")+1));
                }else{
                    JOptionPane.showMessageDialog(null, "Elija un formato valido.");
                }
            }
        }
    }
    
}
