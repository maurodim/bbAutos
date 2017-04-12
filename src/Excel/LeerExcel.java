/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Conversores.Numeros;
import Excel.Objetos.ColumnasExcel;
import interfaces.Editables;
import interfaces.Transaccionable;
import interfacesPrograma.Facturar;
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
import objetos.Articulos;
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
    private ArrayList colmm;
    private Double porc1;
    
   public void leerExcel1(String fileName,ArrayList columnas,Double porcentaje) throws SQLException {
       tra=new ConeccionLocal();
       List cellDataList = new ArrayList();
       colmm=columnas;
       porc1=porcentaje;
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
       ColumnasExcel col1; 
       ColumnasExcel col2; 
       ColumnasExcel col3; 
       ColumnasExcel col4; 
       col1=(ColumnasExcel) colmm.get(0);
       col2=(ColumnasExcel) colmm.get(1);
       col3=(ColumnasExcel) colmm.get(2);
      Articulos arti; 
      Facturar fact=new Articulos();
      Editables edi=new Articulos();
    for (int i = 0; i < cellDataList.size(); i++)
    {
        List cellTempList = (List) cellDataList.get(i);
        
        
       
        int alerta=0;
       
        int j=col1.getId();
            HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
            String stringCellValue = hssfCell.toString();
            barra=stringCellValue;
            arti=(Articulos) fact.cargarPorCodigoDeBarra(barra);
            //System.err.println("Contenido: "+j+" "+stringCellValue);
            //descripcion="";
            //if(i > 0){
            
                //if(j==col1.getId())barra=stringCellValue;
            j=col2.getId();
            hssfCell = (HSSFCell) cellTempList.get(j);
            stringCellValue = hssfCell.toString();
            descripcion=stringCellValue;
            descripcion=descripcion.replaceAll("'","");
                //if(j==col2.getId())descripcion=stringCellValue;
            j=col3.getId();
            hssfCell = (HSSFCell) cellTempList.get(j);
            stringCellValue = hssfCell.toString();
                if(j==col3.getId()){
                    if(stringCellValue.equals(col3.getContenido())){
                        
                    }else{
                        System.out.println("RENGLON: "+i);
                        stringCellValue=stringCellValue.replaceAll("$","");
                        costo=Numeros.ConvertirStringADouble(stringCellValue);
                        
                    }
                }
                if(costo!=null){
                if(costo > 0.00){
                    precio=costo * porc1;
                    System.out.println("precio calculado: "+precio);
                    if(arti.getCodigoDeBarra()!=null ){
                        System.out.println("EXISTE EL CODIGO "+arti.getCodigoDeBarra());
                        arti.setPrecioCosto(costo);
                        arti.setPrecioUnitarioNeto(precio);
                        arti.setModificaPrecio(true);
                        arti.setModificaServicio(false);
                        
                        edi.ModificaionObjeto(arti);
                    }else{
                        arti=new Articulos();
                        arti.setCodigoDeBarra(barra);
                        arti.setDescripcionArticulo(descripcion);
                        arti.setPrecioCosto(costo);
                        arti.setPrecioDeCosto(costo);
                        arti.setPrecioUnitarioNeto(precio);
                        arti.setPrecioServicio(precio);
                        arti.setModificaPrecio(true);
                        arti.setModificaServicio(false);
                        arti.setRecargo(1.00);
                        arti.setDolar(1.00);
                        arti.setLista2(precio);
                        arti.setLista3(precio);
                        arti.setLista4(precio);
                        arti.setIdCombo(0);
                        System.out.println("NO ESTA CARGADO "+arti.getDescripcionArticulo());
                        edi.AltaObjeto(arti);
                        
                        
                    }
                    costo=null;
                    
                }
                }
                    
                
                if (j > 1){
                    if(alerta== 0){
                        System.out.println(precio);
                        //if(precio.equals("")){
                        //}else{
                        //barra=barra.replaceAll(".0","");
                            sentencia+="('"+barra+"','"+descripcion+"',0,round("+precio+",2),round("+precio2+",2),round("+precio4+",2),round("+precio3+",2),round("+precio2+",2)),";
                            precio=null;
                        //}
                    
                    }
                }
                //System.out.println("CODIGO: "+rubro+barra+talle+" $ "+precio);
                
                
            //}
            //System.err.println("FINAL");
            
            //fac.modificar(cliente);
            
        
        
        System.err.println(sentencia);
        System.out.println("  FINAL DE RENGLON");
        barra=null;
        fila++;
    }
    //System.err.println(sentencia);
    
            
            
    
    JOptionPane.showMessageDialog(null,"PROCESO EXITOSO \n CANTIDAD DE FILAS PROCESADAS "+fila);
   }
public ArrayList LeerColumnas(String fileName){
   ArrayList columnas;
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
    columnas=printToConsoleA(cellDataList);
return columnas;
}
private ArrayList printToConsoleA(List cellDataList)
{
    String error=""; 
    int fila=0;
    ArrayList columnas1 = new ArrayList();
    ColumnasExcel col;
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
       if(i ==0){
        for (int j = 0; j < cellTempList.size(); j++)
        {
            HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
            String stringCellValue = hssfCell.toString();
            //System.err.println("Contenido: "+j+" "+stringCellValue);
            //descripcion="";
            //if(i > 0){
            
            
                col=new ColumnasExcel();
                col.setId(j);
                col.setContenido(stringCellValue);
               columnas1.add(col);
                //System.out.println("CODIGO: "+rubro+barra+talle+" $ "+precio);
                
                
            //}
            //System.err.println("FINAL");
            
            //fac.modificar(cliente);
            
        }
    }
        
        System.out.println(sentencia);
        System.out.println("  FINAL DE RENGLON");
        barra=null;
        fila++;
    }
           
    
    //JOptionPane.showMessageDialog(null,"PROCESO EXITOSO \n CANTIDAD DE FILAS PROCESADAS "+fila);
    return columnas1;
   }
}
