/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbsgestion;

import Compras.Remitos;
import Configuracion.Propiedades;
import Sucursales.Usuarios;
import interfaceGraficas.Inicio;
import interfaceGraficas.LoguinBbsGestion;
import interfaces.Comprobable;
import interfaces.Transaccionable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import objetos.ConeccionLocal;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class BbsGestion {

    /**
     * @param args the command line arguments
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
        File bk;
        //FileInputStream fregis = new FileInputStream("C:\\Users\\mauro\\Pictures\\Camera Uploads\\snm.jpg"); 
        

        File archivo=null;
        
        if(!archivos.isDirectory())archivos.mkdirs();
        Propiedades.CargarPropiedades();
        LoguinBbsGestion lBb=new LoguinBbsGestion();
        lBb.setVisible(true);
        lBb.pack();
    }
}
