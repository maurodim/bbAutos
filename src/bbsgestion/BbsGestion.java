/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbsgestion;

import Actualizaciones.BkDeConeccion;
import Configuracion.Propiedades;
import Sucursales.Usuarios;
import interfaceGraficas.Inicio;
import interfaces.Backpeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JFrame;

/**
 *
 * @author mauro
 */
public class BbsGestion {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        /*
        ArrayList usuariosList=new ArrayList();
        Usuarios usuarios=new Usuarios();
        usuariosList=usuarios.listarUsuario();
        */
        
        File archivos=new File("Informes");
        //File bases=new File("C:\\Gestion\\DB");
        //File imagenes=new File("C:\\Gestion\\imagenes\\saynomore.jpg");
        //File bk;
        //FileInputStream fregis = new FileInputStream("C:\\Users\\mauro\\Pictures\\Camera Uploads\\snm.jpg"); 
        

        File archivo=null;
        
        if(!archivos.isDirectory())archivos.mkdirs();
        Propiedades.CargarPropiedades();
        Usuarios usuario=new Usuarios();
        Usuarios usuarios=new Usuarios();
        //try{
        //usuarios=(Usuarios) usuario.validarClave(jTextField1.getText(),new String(jPasswordField1.getPassword()));
        //}catch(Exception ex){
            Backpeable bk=new BkDeConeccion();
            usuarios=(Usuarios) bk.leerUsuarios("ADM","adm");
        //}
        if(usuarios.getNumero()> 0){
        Inicio in=new Inicio(2);
        Inicio.niv=usuarios.getNivelDeAutorizacion();
        
        Inicio.usuario=usuarios;
        Inicio.sucursal=usuarios.getSucursal();
        Inicio.deposito=Inicio.sucursal.getDepositos();
        in.setNiv(usuarios.getNivelDeAutorizacion());
        in.setTitle(" SISTEMA DE GESTION // "+Propiedades.getNOMBRECOMERCIO()+" --  USUARIO : "+Inicio.usuario.getNombre()+" SUCURSAL :"+Inicio.sucursal.getNumero());
        in.setExtendedState(JFrame.MAXIMIZED_BOTH);
        in.setVisible(true);
        
        //in.pack();
        
        }
    }
}
