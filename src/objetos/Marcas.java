/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class Marcas {
    
    private String descripcion;
    private String proveedor;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    public ArrayList listar(){
        ArrayList lst=new ArrayList();
        Transaccionable tra=new ConeccionLocal();
        ResultSet rs;
        String sql="select marca,prov from articulos group by marca,prov";
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Marcas marca=new Marcas();
                marca.setDescripcion(rs.getString("marca"));
                marca.setProveedor(rs.getString("prov"));
                lst.add(marca);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lst;
    }
    public ArrayList filtrar(String filtro){
        ArrayList lst=new ArrayList();
        Transaccionable tra=new ConeccionLocal();
        ResultSet rs;
        String sql="select marca,prov from articulos where marca like '%"+filtro+"%' group by marca,prov";
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Marcas marca=new Marcas();
                marca.setDescripcion(rs.getString("marca"));
                marca.setProveedor(rs.getString("prov"));
                lst.add(marca);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lst;
    }
    
}
