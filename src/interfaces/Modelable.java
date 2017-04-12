/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro
 */
public interface Modelable {
    public DefaultComboBoxModel MostrarEnCombo(ArrayList listado);
    public DefaultTableModel MostrarEnTabla(ArrayList listado);
    public DefaultListModel MostrarEnLista(ArrayList listado);
    
}
