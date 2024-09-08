/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crud.clientes;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author sebas
 */
public class CRUDClientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        VentanaSeleccionarVendedor a = new VentanaSeleccionarVendedor();   
        a.setTitle("Seleccionar vendedor");
        a.setVisible(true);
    }    
    
}
