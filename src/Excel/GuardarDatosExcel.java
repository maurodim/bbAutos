/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import interfaces.Modificable;
import java.util.ArrayList;
import objetos.Articulos;

/**
 *
 * @author mauro
 */
public class GuardarDatosExcel implements Runnable{
    Thread hilo;
    Thread hilo2;
    private ArrayList lstNew;
    private ArrayList lstEdit;

    public ArrayList getLstNew() {
        return lstNew;
    }

    public void setLstNew(ArrayList lstNew) {
        this.lstNew = lstNew;
    }

    public ArrayList getLstEdit() {
        return lstEdit;
    }

    public void setLstEdit(ArrayList lstEdit) {
        this.lstEdit = lstEdit;
    }

    public void Inicio() {
        hilo=new Thread(this);
        hilo.start();
        hilo2=new Thread(this);
        hilo2.start();
    }
    
    
    @Override
    public void run() {
        Thread ct=Thread.currentThread();
        Modificable mod=new Articulos();
        //while(ct==hilo){
            
            mod.NuevoMasivo(this.lstNew);
            
        //}
        
        //while(ct==hilo2){
            
             mod.ModificadoMasivo(this.lstEdit);
        //}
        System.err.println("TERMINADOOOOOO");
    }
    
}
