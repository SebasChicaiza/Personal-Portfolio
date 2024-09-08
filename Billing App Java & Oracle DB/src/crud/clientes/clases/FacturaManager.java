/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.clientes.clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author david
 */
public class FacturaManager {
    private ConexionOracle o ;
    public FacturaManager() {
        o = new ConexionOracle();
    }
    public ResultSet verTodasLasFacturas() throws SQLException
    {
        String query = "SELECT * FROM factura";
        ResultSet rs = o.executeQuery(query);
        return rs;
    }

    public ResultSet buscarConQuery(String query) throws SQLException {
        ResultSet rs = o.executeQuery(query);
        return rs;
    }

    /*public void insertarFactura(int facNumero, String sucCodigo, String trnCodigo, String cliCedulaRuc, String venCedulaRuc, Date facFecha, double facMonto, String facEstado) throws SQLException {
        try {
            Connection c = o.conectar();
            String query = "INSERT INTO factura (FAC_NUMERO, SUC_CODIGO, TRN_CODIGO, CLI_CEDULA_RUC, VEN_CEDULA_RUC, FAC_FECHA, FAC_MONTO, FAC_ESTADO) VALUES ("
                    + facNumero + ", '"
                    + sucCodigo + "', '"
                    + trnCodigo + "', '"
                    + cliCedulaRuc + "', '"
                    + venCedulaRuc + "', "
                    + "TO_DATE('" + new java.sql.Date(facFecha.getTime()) + "', 'YYYY-MM-DD'), "
                    + facMonto + ", '"
                    + facEstado + "')";
            o.updateQuery(query);
            JOptionPane.showMessageDialog(null, "Se insertó correctamente la factura");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }*/
    public void insertarFactura(int facNumero, String sucCodigo, String trnCodigo, String cliCedulaRuc, String venCedulaRuc, String facFecha, double facMonto, String facEstado) {
        try{
            Connection c = o.conectar();
            //String query = "INSERT INTO factura VALUES ('"+ facNumero + "', '" + sucCodigo + "', '" + trnCodigo +"', '" + cliCedulaRuc +"', '" + venCedulaRuc +"', TO_DATE('" + facFecha +"','dd/mm/yyyy')," + facMonto+"', '" +facEstado+"')";
            String query = "INSERT INTO factura VALUES ('" + facNumero + "', '" + sucCodigo + "', '" + trnCodigo + "', '" + cliCedulaRuc + "', '" + venCedulaRuc + "', TO_DATE('" + facFecha + "','dd/mm/yyyy'), " + facMonto + ", '" + facEstado + "')";
            o.updateQuery(query);
            JOptionPane.showMessageDialog(null, "Se insertó correctamente la factura");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    private String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("'", "''");  // Doble comilla simple para escapar
    }
    public void actualizarFactura(int facNumero, String sucCodigo, String trnCodigo, String cliCedulaRuc, String venCedulaRuc, Date facFecha, double facMonto, String facEstado) throws SQLException {
        String query = "UPDATE factura SET "
                + "SUC_CODIGO = '" + sucCodigo + "', "
                + "TRN_CODIGO = '" + trnCodigo + "', "
                + "CLI_CEDULA_RUC = '" + cliCedulaRuc + "', "
                + "VEN_CEDULA_RUC = '" + venCedulaRuc + "', "
                + "FAC_FECHA = TO_DATE('" + new java.sql.Date(facFecha.getTime()) + "', 'YYYY-MM-DD'), "
                + "FAC_MONTO = " + facMonto + ", "
                + "FAC_ESTADO = '" + facEstado + "' "
                + "WHERE FAC_NUMERO = " + facNumero;
        o.updateQuery(query);
    }

    public void eliminarFactura(int facNumero) throws SQLException {
        String query = "DELETE FROM factura WHERE FAC_NUMERO = " + facNumero;
        o.updateQuery(query);
    }

    /*
    public int obtenerNumeroFacturaDisponible() throws SQLException
    {
        int numerofactura=0;
        String query = "SELECT * FROM factura ORDER BY FAC_NUMERO DESC";
        ResultSet rs = o.executeQuery(query);
        while (rs.next()) {
            numerofactura = rs.getInt("FAC_NUMERO");
        }
        numerofactura++;
        return numerofactura;
    }*/
    public int obtenerNumeroFacturaDisponible() throws SQLException {
        int numerofactura = 1; // Inicializamos en 1 por defecto
        String query = "SELECT FAC_NUMERO FROM factura ORDER BY FAC_NUMERO DESC";

        try (ResultSet rs = o.executeQuery(query)) {
            if (rs.next()) {
                numerofactura = rs.getInt("FAC_NUMERO") + 1; // Incrementamos solo si hay un número de factura existente
            }
        }
        return numerofactura;
    }
    public List<String> obtenerFormasPago() throws SQLException
    {
        List<String> formas_pago = new ArrayList<>(); 
        ResultSet rs = o.executeQuery("SELECT FRP_DESCRIPCION FROM forma_pago");
        while(rs.next())
        {
            String aux = rs.getString("FRP_DESCRIPCION");
            formas_pago.add(aux);
        }
        return formas_pago;
    }
    
}
