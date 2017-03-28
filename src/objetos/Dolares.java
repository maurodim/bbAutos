/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class Dolares {
    private Double cotizacion;
    private static Transaccionable tra=new ConeccionLocal();
    private static ResultSet rr;

    public Double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Double cotizacion) {
        this.cotizacion = cotizacion;
    }
    
    public Double cotizacionActual(){
        String sql="select dolar from articulos limit 0,1";
        rr=tra.leerConjuntoDeRegistros(sql);
        Double valor=0.00;
        try {
            while(rr.next()){
                valor=rr.getDouble("dolar");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dolares.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }
    public void modificarCotizacion(Double doll){
        String sql="update articulos set dolar=round("+doll+",2)";
        tra.guardarRegistro(sql);
    }
}
