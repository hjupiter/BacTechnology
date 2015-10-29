/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Vista.VistaExcel;
import Base.DataBaseHandler;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;



public class ModeloExcel {
    private Workbook wb;
    private DataBaseHandler db =  new DataBaseHandler();
    
    
    public String Abrir(File archivo, JTable tablaD){
        String respuesta="No se pudo realizar la importación.";
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        try {
            wb = WorkbookFactory.create(new FileInputStream(archivo));
            Sheet hoja = wb.getSheetAt(0);
            Iterator filaIterator = hoja.rowIterator();
            int indiceFila=-1;
            
            while (filaIterator.hasNext()) {                
                indiceFila++;
                Row fila = (Row) filaIterator.next();
                Iterator columnaIterator = fila.cellIterator();
                Object[] listaColumna = new Object[5];
                int indiceColumna=-1;
                while (columnaIterator.hasNext()) {                    
                    indiceColumna++;
                    Cell celda = (Cell) columnaIterator.next();
                    if(indiceFila==0){
                        modeloT.addColumn(celda.getStringCellValue());
                    }else{
                        if(celda!=null){
                            switch(celda.getCellType()){
                                case Cell.CELL_TYPE_NUMERIC:
                                    listaColumna[indiceColumna]= (int)Math.round(celda.getNumericCellValue());
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    listaColumna[indiceColumna]= celda.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    listaColumna[indiceColumna]= celda.getBooleanCellValue();
                                    break;
                                default:
                                    listaColumna[indiceColumna]=celda.getDateCellValue();
                                    break;
                            }
                        }
                    }
                }
                if(indiceFila!=0)modeloT.addRow(listaColumna);
            }
            respuesta="Importación exitosa";
            muestraDatos(archivo);
        } catch (Exception e) {
        }
        return respuesta;
    }
    
    
    public void muestraDatos(File archivoExcel){
        List celda = new ArrayList();
        try{
            FileInputStream fileInputStream = new FileInputStream(archivoExcel);
            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet hssSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssSheet.rowIterator();
            while(rowIterator.hasNext()){
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTemp = new ArrayList();
                while(iterator.hasNext()){
                    XSSFCell hssfcell = (XSSFCell) iterator.next();
                    cellTemp.add(hssfcell);
                }
                celda.add(cellTemp);
            }
                
        }catch(Exception e){
            e.printStackTrace();
        }
        obtener(celda);
    }
    
    private void obtener(List cellDataList){
        String id = "",nombre = "";
        int cont = 0;
        try {
            for(int i = 0;i < cellDataList.size() ; i++){
                List cellTempList = (List) cellDataList.get(i);
                for(int j = 0 ; j < cellTempList.size() ; j++){
                    XSSFCell hssfcell = (XSSFCell) cellTempList.get(j);
                    String valorCelda = hssfcell.toString();
                    //guardar en la base
                    if(cellDataList.get(i) != "" && cellTempList.get(j) != ""){
                        //System.out.println("Celda ["+i+"] ["+j+"]: "+valorCelda);
                        if(cont == 0){
                            nombre = cellTempList.get(j).toString();
                            cont++;
                        }else{
                            id = cellTempList.get(j).toString();
                            cont = 0;
                        }
                        //System.out.println("XXXXXXXX"+cellTempList.get(j));
                        //db.connect(valorCelda, valorCelda);
                    }
                }
                System.out.println("ID : "+id+ " Nombre : "+nombre);
                if(id!="" && nombre!="")
                    if(id != "NOMBRE" && nombre != "NOMBRE PRODUCTO"){
                        db.connect(id, nombre);
                    }
                //guardar datos
                System.out.println("");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos");
        }
        
    }
    
    
}
