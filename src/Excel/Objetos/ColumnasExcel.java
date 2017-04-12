/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel.Objetos;

import interfaces.Modelable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro
 */
public class ColumnasExcel implements Modelable{
    private Integer id;
    private String contenido;
    private Integer idDatos;
    private String nombreDato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Integer getIdDatos() {
        return idDatos;
    }

    public void setIdDatos(Integer idDatos) {
        this.idDatos = idDatos;
    }

    public String getNombreDato() {
        return nombreDato;
    }

    public void setNombreDato(String nombreDato) {
        this.nombreDato = nombreDato;
    }

    @Override
    public DefaultComboBoxModel MostrarEnCombo(ArrayList listado) {
        Iterator it=listado.listIterator();
        DefaultComboBoxModel modelo=new DefaultComboBoxModel();
        ColumnasExcel col;
        while(it.hasNext()){
            col=(ColumnasExcel) it.next();
            modelo.addElement(col.getContenido());
        }
        return modelo;
    }

    @Override
    public DefaultTableModel MostrarEnTabla(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultListModel MostrarEnLista(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
