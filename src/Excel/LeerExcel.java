/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Conversores.Numeros;
import Excel.Objetos.ColumnasExcel;
import interfaceGraficas.AbmIva;
import interfaces.Editables;
import interfaces.Modificable;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
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
    private int iaa;
    private int ori;
    private Double desc;
    private ConcurrentHashMap listadoArt;
    
   public void leerExcel1(String fileName,ArrayList columnas,Double porcentaje,int iva,int origi,Double descuen) throws SQLException {
       tra=new ConeccionLocal();
       List cellDataList = new ArrayList();
       colmm=columnas;
       porc1=porcentaje;
       iaa=iva;
       ori=origi;
       desc=descuen;
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
    ArrayList lstNuevos=new ArrayList();
    ArrayList lstModificador=new ArrayList();
    int fila=0;
    //DefaultListModel modeloL=new DefaultListModel();
    //ListadoDeProcesos listador=new ListadoDeProcesos();
    //listador.setVisible(true);
    //listador.toFront();
    /*
    Barra barrr=new Barra(null,false);
    barrr.setVisible(true);
    barrr.toFront();
    barrr.jProgressBar1.setMinimum(0);
    barrr.jProgressBar1.setMaximum(100000);
    barrr.jProgressBar1.setVisible(true);
    int progrr=0;
    */
    Boolean verif=false;
    ArrayList lstArt=new ArrayList();
    String unidadDeMedida="";
    Double peso=0.00;
    Articulos.CargarMap();
    listadoArt=Articulos.getListadoBarr();
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
        String marca="";
        String proveedor="";
        Double precio = null;
        Double precio2=null;
        Double precio3=null;
        Double precio4=null;
        Double costo=null;
        String talle=null;
        String sentencia="insert into articulos (BARRAS,NOMBRE,SERVICIO,COSTO,PRECIO,lista2,lista3,lista4,marca,prov) value ";
       ColumnasExcel col1; 
       ColumnasExcel col2; 
       ColumnasExcel col3; 
       ColumnasExcel col4;
       ColumnasExcel col5;
       ColumnasExcel col6;
       
       col1=(ColumnasExcel) colmm.get(0);//codigo
       col2=(ColumnasExcel) colmm.get(1);//descripcion
       col3=(ColumnasExcel) colmm.get(2);//costo
       col4=(ColumnasExcel) colmm.get(3);//precio de venta
       col5=(ColumnasExcel) colmm.get(4);//marca
       col6=(ColumnasExcel) colmm.get(5);//proveedor
      Articulos arti; 
      Facturar fact=new Articulos();
      Editables edi=new Articulos();
    for (int i = 0; i < cellDataList.size(); i++)
    {
        List cellTempList = (List) cellDataList.get(i);
        
        
       //progrr++;
        int alerta=0;
       int j;
            
            j=col1.getId();
            HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
            String stringCellValue = hssfCell.toString();
            barra=stringCellValue;
            
            if(col5.getId()!=null){
                j=col5.getId();
                hssfCell = (HSSFCell) cellTempList.get(j);
                stringCellValue = hssfCell.toString();
                marca=stringCellValue.replaceAll("'","");
                barra+=" "+stringCellValue;
            }else{
                marca="";
            }
            
            if(col6.getId()!=null){
                
                j=col6.getId();
                System.out.println("columna "+j+" "+proveedor+" fila "+i);
                try{
                hssfCell = (HSSFCell) cellTempList.get(j);
                stringCellValue = hssfCell.toString();
                }catch(java.lang.IndexOutOfBoundsException eex){
                    stringCellValue="";
                    System.err.println(eex);
                }
                proveedor=stringCellValue.replaceAll("'","");
                barra+=" "+stringCellValue;
            }else{
                proveedor="";
            }
            barra=barra.replaceAll("'","");
            //arti=(Articulos) fact.cargarPorCodigoDeBarra(barra);
            arti=(Articulos) listadoArt.get(barra);
            if(arti==null){
                arti=new Articulos();
            }
            //System.err.println("Contenido: "+j+" "+stringCellValue);
            //descripcion="";
            //if(i > 0){
            
                //if(j==col1.getId())barra=stringCellValue;
            if(col2.getId()!=null){
                j=col2.getId();
                hssfCell = (HSSFCell) cellTempList.get(j);
                stringCellValue = hssfCell.toString();
                descripcion=stringCellValue;
                descripcion=descripcion.replaceAll("'","");
            }
                //if(j==col2.getId())descripcion=stringCellValue;
            if(col4.getId() !=null){
                j=col4.getId();
            hssfCell = (HSSFCell) cellTempList.get(j);
            stringCellValue = hssfCell.toString();
                if(j==col4.getId()){
                    if(stringCellValue.equals(col4.getContenido())){
                        
                    }else{
                        //System.out.println("RENGLON: "+i);
                        stringCellValue=stringCellValue.replaceAll("$","");
                        precio=Numeros.ConvertirStringADouble(stringCellValue);
                        if(iaa ==1 && ori==2){
                            precio=precio * 1.21;
                        }
                        
                    }
                }

            }
            
            
            if(col3.getId() !=null){
                j=col3.getId();
                try{
                hssfCell = (HSSFCell) cellTempList.get(j);
                stringCellValue = hssfCell.toString();
                }catch(java.lang.IndexOutOfBoundsException eex){
                    stringCellValue="0.00";
                    System.err.println(eex);
                }
                    //if(j==col3.getId()){
                        if(stringCellValue.equals(col3.getContenido())){
                            costo=null;
                        }else{
                            //System.out.println("RENGLON: "+i);
                            stringCellValue=stringCellValue.replaceAll("$","");
                            costo=Numeros.ConvertirStringADouble(stringCellValue);

                        }
                   // }
                    if(costo!=null){
                        
                    
                        if(desc > 0.00){
                            
                            Double costo1=costo * desc;
                            costo= costo - costo1;
                        }
                        if(iaa==1 && ori==1){
                            costo=costo * 1.21;
                        }
                        if(porc1 > 0.00){
                            precio=costo * porc1;
                            
                        }
                    }
                        //System.out.println("precio calculado: "+precio);
                    /*    
                    if(i==2302){
                            JOptionPane.showMessageDialog(null,"VEAMOS "+i);
                        }
                    */
                        if(arti.getCodigoDeBarra()!=null){
                            System.err.println("EXISTE EL CODIGO "+arti.getCodigoDeBarra());
                            //modeloL.addElement("EXISTE EL CODIGO "+arti.getCodigoDeBarra()+".....");
                            arti.setPrecioCosto(costo);
                            arti.setPrecioUnitarioNeto(precio);
                            arti.setModificaPrecio(true);
                            arti.setModificaServicio(false);
                            arti.setMarca(marca);
                            arti.setProveedor(proveedor);
                            lstModificador.add(arti);
                            //edi.ModificaionObjeto(arti);
                            //barrr.jProgressBar1.setString("EXISTE EL CODIGO ");
                            
                           // barrr.jProgressBar1.setValue(progrr);
                            
                        }else{
                            //arti=new Articulos();
                            arti.setCodigoDeBarra(barra);
                            if(descripcion.length() > 100){
                                descripcion=descripcion.substring(0,100);
                            }
                            if(precio!=null){
                                
                            }else{
                                precio=0.00;
                            }
                            arti.setDescripcionArticulo(descripcion);
                            arti.setPrecioCosto(costo);
                            arti.setPrecioDeCosto(costo);
                            arti.setPrecioUnitarioNeto(precio);
                            arti.setPrecioServicio(precio);
                            arti.setModificaPrecio(true);
                            arti.setModificaServicio(false);
                            arti.setRecargo(1.00);
                            arti.setDolar(1.00);
                            arti.setStockMinimo(0.00);
                            arti.setLista2(precio);
                            arti.setLista3(precio);
                            arti.setLista4(precio);
                            arti.setMarca(marca);
                            arti.setProveedor(proveedor);
                            arti.setIdCombo(0);
                            System.out.println("NO ESTA CARGADO "+arti.getDescripcionArticulo()+" // "+barra);
                            //modeloL.addElement("NUEVO ARTICULO "+arti.getDescripcionArticulo()+".....");
                            lstNuevos.add(arti);
                            //edi.AltaObjeto(arti);
                           // barrr.jProgressBar1.setValue(progrr);


                        }
                        costo=null;

                    
                    
    }   
                
                if (j > 1){
                    if(alerta== 0){
                        //System.out.println(precio);
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
            
        
        
        //System.err.println(sentencia);
        //System.out.println("  FINAL DE RENGLON");
    
        barra=null;
        fila++;
        
    }
    //System.err.println(sentencia);
    
            
            
    //barrr.dispose();
    System.out.println("NUEVO: "+lstNuevos.size()+" MODIFICADOS: "+lstModificador.size());
    GuardarDatosExcel guarda=new GuardarDatosExcel();
    guarda.setLstEdit(lstModificador);
    guarda.setLstNew(lstNuevos);
    guarda.Inicio();
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
        
        //System.out.println(sentencia);
        //System.out.println("  FINAL DE RENGLON");
        barra=null;
        fila++;
    }
           
    col=new ColumnasExcel();
                col.setId(null);
                col.setContenido("NO SELECCIONADO");
               columnas1.add(col);
    //JOptionPane.showMessageDialog(null,"PROCESO EXITOSO \n CANTIDAD DE FILAS PROCESADAS "+fila);
    
    return columnas1;
   }
}
