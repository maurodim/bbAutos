/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Conversores.Numeros;
import interfaces.Transaccionable;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objetos.ConeccionLocal;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 *
 * @author mauro di
 */
public class LeerExcel {
    private String sql;
    private Transaccionable tra;
    
   public void leerExcel1(String fileName) throws SQLException {
       tra=new ConeccionLocal();
       List cellDataList = new ArrayList();
try
{
/**
* Create a new instance for FileInputStream class
*/
FileInputStream fileInputStream = new FileInputStream(fileName);
/**
* Create a new instance for POIFSFileSystem class
*/
POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
/*
* Create a new instance for HSSFWorkBook Class
*/
HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
HSSFSheet hssfSheet = workBook.getSheetAt(0);
/**
* Iterate the rows and cells of the spreadsheet
* to get all the datas.
*/
Iterator rowIterator = hssfSheet.rowIterator();
while (rowIterator.hasNext())
{
HSSFRow hssfRow = (HSSFRow) rowIterator.next();
Iterator iterator = hssfRow.cellIterator();
List cellTempList = new ArrayList();
while (iterator.hasNext())
{
HSSFCell hssfCell = (HSSFCell) iterator.next();
cellTempList.add(hssfCell);
}
cellDataList.add(cellTempList);
}
}
catch (Exception e)
{
e.printStackTrace();
}
/**
* Call the printToConsole method to print the cell data in the
* console.
*/
printToConsole(cellDataList);
}
/**
* This method is used to print the cell data to the console.
* @param cellDataList - List of the data's in the spreadsheet.
*/
private void printToConsole(List cellDataList)
{
    String error=""; 
    int fila=0;
    
    Boolean verif=false;
    ArrayList lstArt=new ArrayList();
    String unidadDeMedida="";
    Double peso=0.00;
    
    Integer porc=0;
     String barra = null;
        String descripcion = null;
        String rubro = null;
        String talle1 = null;
        String talle2 = null;
        String talle3 = null;
        String talle4 = null;
        String talle5 = null;
        String talle6 = null;
        String talle7 = null;
        String talle8 = null;
        String talle9 = null;
        Double precio = null;
        Double precio2=null;
        Double precio3=null;
        Double precio4=null;
        Double costo=null;
        String talle=null;
        String sentencia="insert into articulos (BARRAS,NOMBRE,SERVICIO,COSTO,PRECIO,lista2,lista3,lista4) value ";
        
        
    for (int i = 0; i < cellDataList.size(); i++)
    {
        List cellTempList = (List) cellDataList.get(i);
        
        
       
        int alerta=0;
       
        for (int j = 0; j < cellTempList.size(); j++)
        {
            HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
            String stringCellValue = hssfCell.toString();
            //System.err.println("Contenido: "+j+" "+stringCellValue);
            //descripcion="";
            //if(i > 0){
                switch (j){
                    case 0:
                        //numeroComprobante=stringCellValue;
                        //descrip=String.valueOf(stringCellValue);
                        
                        //rubro=stringCellValue;
                        int hallado=stringCellValue.indexOf("L");
                        
                            //porc=Numeros.ConvertirStringAInteger(stringCellValue);
                            barra=String.valueOf(stringCellValue);
                            alerta=1;
                            System.out.println(j+" / BARRA: "+barra);
                        
                        break;
                    case 1:
                        System.out.println(j+" / "+stringCellValue);
                        //System.out.println("nombre: "+j+" "+stringCellValue);
                        descripcion=stringCellValue;
                        alerta=1;
                        break;
                    case 2:
                        System.out.println(j+" / "+stringCellValue);
                        //System.out.println("nombre: "+j+" "+stringCellValue);
                        descripcion+=" "+stringCellValue;
                        alerta=1;
                        break;
                    case 3:
                        System.out.println(j+" / "+stringCellValue);
                        //System.out.println("rfid: "+j+" "+stringCellValue);
                        if(stringCellValue.equals("")){
                                
                            }else{
                            precio=Numeros.ConvertirStringADouble(stringCellValue);
                            if(precio !=null){
                                
                            }else{
                                precio=0.00;
                            }
                            //precio=String.valueOf(porc);
                            }
                        alerta=1;
                        break;
                    case 4:
                        System.out.println(j+" / "+stringCellValue);
                        //System.out.println("Direccion: "+j+" "+stringCellValue);
                        //PRECIO DE COSTO
                         if(stringCellValue.equals("")){
                                
                            }else{
                            precio2=Numeros.ConvertirStringADouble(stringCellValue);
                            if(precio2 !=null){
                                
                            }else{
                                precio2=0.00;
                            }
                            //precio=String.valueOf(porc);
                            }
                        alerta=1;
                        break;
                    case 5:
                        System.out.println(j+" / "+stringCellValue);
                        //System.out.println("Teelfono: "+j+" "+stringCellValue);
                        //PRECIO DE LISTA1
                        
                            if(stringCellValue.equals("")){
                                
                            }else{
                            precio4=Numeros.ConvertirStringADouble(stringCellValue);
                            if(precio4 !=null){
                                
                            }else{
                                precio4=0.00;
                            }
                            //precio=String.valueOf(porc);
                            }
                            //talle3=stringCellValue;
                            alerta=0;
                        
                        break;
                    case 6:
                        System.out.println(j+" / "+stringCellValue);
                        //System.out.println("Mail: "+j+" "+stringCellValue);
                        //PRECIO LISTA2
                        if(stringCellValue.equals("")){
                                
                            }else{
                            precio4=Numeros.ConvertirStringADouble(stringCellValue);
                            if(precio4 !=null){
                                
                            }else{
                                precio4=0.00;
                            }
                            //precio=String.valueOf(porc);
                            }
                        alerta=1;
                        break;
                    case 7:
                        System.out.println(j+" / "+stringCellValue);
                        //PRECIO LISTA 3
                        precio4=Numeros.ConvertirStringADouble(stringCellValue);
                        //fila++;
                        alerta=1;
                        break;
                    case 8:
                        System.out.println(j+" / "+stringCellValue);
                        //PRECIO LISTA4
                        //tra.guardarRegistro(sql);
                       //precio5=Numeros.ConvertirStringADouble(stringCellValue);
                       alerta=1;
                        break;
                    case 18:
                        System.out.println(j+" / "+stringCellValue);
                        //fila++;
                        
                        break;
                    case 9:
                        System.out.println(j+" / "+stringCellValue);
                        //fila++;
                        
                        break;
                    case 10:
                        System.out.println(j+" / "+stringCellValue);
                        //fila++;
                        
                        
                        break;
                        
                }
                if (j > 1){
                    if(alerta== 0){
                        System.out.println(precio);
                        //if(precio.equals("")){
                        //}else{
                        barra=barra.replaceAll(".0","");
                            sentencia+="('"+barra+"','"+descripcion+"',0,round("+precio+",2),round("+precio2+",2),round("+precio4+",2),round("+precio3+",2),round("+precio2+",2)),";
                            precio=null;
                        //}
                    
                    }
                }
                //System.out.println("CODIGO: "+rubro+barra+talle+" $ "+precio);
                
                
            //}
            //System.err.println("FINAL");
            
            //fac.modificar(cliente);
            
        }
        
        System.out.println(sentencia);
        System.out.println("  FINAL DE RENGLON");
        barra=null;
        fila++;
    }
    System.err.println(sentencia);
    FileWriter fichero=null;
        Boolean respuesta=false;
        
            String nombreFichero="importacionSql2.sql";
        try {
            fichero=new FileWriter(nombreFichero);
            PrintWriter pw=new PrintWriter(fichero);
            String sent;
            
                
                pw.println(sentencia);
                fichero.close();
        } catch (IOException ex) {
            Logger.getLogger(LeerExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    
    JOptionPane.showMessageDialog(null,"PROCESO EXITOSO \n CANTIDAD DE FILAS PROCESADAS "+fila);
   }
}
