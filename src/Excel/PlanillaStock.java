/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import interfaces.Editables;
import interfaces.Transaccionable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Articulos;
import objetos.ConeccionLocal;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author mauro di
 */
public class PlanillaStock {
 public void GenerarInforme() throws SQLException{
              HSSFWorkbook libro=new HSSFWorkbook();
        HSSFSheet hoja=libro.createSheet("Listado de Articulos");
        ArrayList listadoPorSucursal=new ArrayList();
        Editables edi=new Articulos();
        
        /*
         * GENERAR LAS SIGUIENTES HOJAS
         * 1- DETALLE DE MOVIMIENTOS DE CAJA - LEE EN MOVIMIENTOS CAJA INDENTIFICANDO EL TIPO DE MOVIMIENTO, USUARIOS Y 
         * NUMERO DE CAJA
         * 2- DETALLE DE ARTICULOS VENDIDOS: LISTADO DE MOVIEMIENTOS DE ARTICULOS, CON USUARIOS Y CAJA
         * 3- DETALLE DE GASTOS : MOVIMIENTOS DE CAJA DETALLANDO LOS GASTOS
         * 
         */
        
        String ttx="celda numero :";
        HSSFRow fila=null;
        HSSFCell celda;
        HSSFCell celda1;
        HSSFCell celda2;
        HSSFCell celda3;
        HSSFCell celda4;
        HSSFCell celda5;
        HSSFCell celda6;
        HSSFCell celda7;
        HSSFCell celda8;
        HSSFCell celda9;
        HSSFCell celda10;
        HSSFCell celda11;
        HSSFFont fuente=libro.createFont();
        //fuente.setFontHeight((short)21);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        String form=null;
        
        HSSFCellStyle titulo=libro.createCellStyle();
       //Iterator iCli=listadoClientes.listIterator();
        Articulos cliente;
        titulo.setFont(fuente);
        //titulo.setFillBackgroundColor((short)22);
        titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFCellStyle  celdas=libro.createCellStyle();
        celdas.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        celdas.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        celdas.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        celdas.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        celdas.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        celdas.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        celdas.setRightBorderColor(IndexedColors.BLACK.getIndex());
        celdas.setTopBorderColor(IndexedColors.BLACK.getIndex());
        int a=0;
        String sql="select *,(select sum(cantidad) FROM movimientosarticulos where movimientosarticulos.idArticulo=articulos.ID group by idArticulo,numeroDeposito limit 0,1)as sstock,round((articulos.dolar * articulos.PRECIO),2)as pl1,round((articulos.dolar * articulos.lista2),2)as pl2,round((articulos.dolar * articulos.lista3),2)as pl3,round((articulos.lista4 * articulos.dolar),2)as pl4,round((articulos.dolar * articulos.COSTO),2)as pcto from articulos where (select sum(cantidad) FROM movimientosarticulos where movimientosarticulos.idArticulo=articulos.ID group by idArticulo limit 0,1) > 0 order by sstock desc";
        
        Transaccionable tra1=new ConeccionLocal();
        ResultSet rr1=tra1.leerConjuntoDeRegistros(sql);
        //Facturar fact=new Articulos();
        //ArrayList listadoClientes=fact.listadoBusqueda("");
        System.out.println("cantidad registros "+rr1.getRow());
        while (rr1.next()){
        int col=0;
        //int a=0;
            if(a==0){
                    fila=hoja.createRow(a);
                celda=fila.createCell(0);
                celda.setCellStyle(titulo);
                celda.setCellValue("Codigo");
                celda1=fila.createCell(1);
                celda1.setCellStyle(titulo);
                celda1.setCellValue("Descripcion");
                celda2=fila.createCell(2);
                celda2.setCellStyle(titulo);
                celda2.setCellValue("cod. barras");
                celda3=fila.createCell(3);
                celda3.setCellStyle(titulo);
                celda3.setCellValue("Cantidad");
                
                a++;
            }
               // cliente=new Articulos();
            //cliente=(Articulos)listadoClientes.get(a);
            fila=hoja.createRow(a);
            celda=fila.createCell(0);
            ttx=ttx;
            celda.setCellStyle(celdas);
            celda.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda.setCellValue(rr1.getString("ID"));
            
            
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellStyle(celdas);
            celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda1.setCellValue(rr1.getString("NOMBRE"));
            
            
            celda2=fila.createCell(2);
            celda2.setCellStyle(celdas);
            celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda2.setCellValue(rr1.getString("BARRAS"));
            
            
            celda3=fila.createCell(3);
            celda3.setCellStyle(celdas);
            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda3.setCellValue(rr1.getDouble("sstock"));
            
            
            celda4=fila.createCell(4);
            celda4.setCellStyle(celdas);
            celda4.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda4.setCellValue("");
            
           
            celda5=fila.createCell(5);
            celda5.setCellStyle(celdas);            
//celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda5.setCellValue("");
            //celda5.setCellValue(rs.getDate("fecha"));
            
            
            celda6=fila.createCell(6);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda6.setCellStyle(celdas);
            celda6.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda6.setCellValue("");
            //listadoPorSucursal=edi.ListarPorSucursal(cliente);
            //Iterator il=listadoPorSucursal.listIterator();
            
            
            a++;
        }
        rr1.close();
        
             
                       String ruta="Informes\\planillaStock.xls";
        try {
            FileOutputStream elFichero=new FileOutputStream(ruta);
            try {
                libro.write(elFichero);
                elFichero.close();
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ruta);
            } catch (IOException ex) {
                Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
        }

            }
        }
          
            
        
  