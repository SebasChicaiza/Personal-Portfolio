/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.clientes.clases;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author david
 */
public class DetalleFacturaManager {

    ConexionOracle o;

    public DetalleFacturaManager() {
        o = new ConexionOracle();
    }

    public void insertarDetalleFactura(int facNumero, String prdCodigo, int dtfCantidad, double dtfPrecio) throws SQLException {
        try {
            Connection c = o.conectar();
            String query = "INSERT INTO detalle_factura VALUES (" + facNumero + ", '" + prdCodigo + "', " + dtfCantidad + ", " + dtfPrecio + ")";
            o.updateQuery(query);
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el detalle de la factura");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public ResultSet verTodosLosDetallesFactura() throws SQLException {
        String query = "SELECT * FROM detalle_factura";
        ResultSet rs = o.executeQuery(query);
        return rs;
    }

    public ResultSet buscarDetalleFacturaPorQuery(String query) throws SQLException {
        ResultSet rs = o.executeQuery(query);
        return rs;
    }

    public void actualizarDetalleFactura(int facNumero, String prdCodigo, double dtfCantidad, double dtfPrecio) throws SQLException {
        String query = "UPDATE detalle_factura SET "
                + "DTF_CANTIDAD = " + dtfCantidad + ", "
                + "DTF_PRECIO = " + dtfPrecio + " "
                + "WHERE FAC_NUMERO = " + facNumero + " "
                + "AND PRD_CODIGO = '" + prdCodigo + "'";
        o.updateQuery(query);
    }

    public void eliminarDetalleFactura(int facNumero, String prdCodigo) throws SQLException {
        String query = "DELETE FROM detalle_factura WHERE FAC_NUMERO = " + facNumero + " AND PRD_CODIGO = '" + prdCodigo + "'";
        o.updateQuery(query);
    }

}
