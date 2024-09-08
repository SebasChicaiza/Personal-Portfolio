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
public class SucursalManager {
    ConexionOracle o;

    public SucursalManager() {
        o = new ConexionOracle();
    }

    public void insertarSucursal(String sucCodigo, String ciuCodigo, String sucNombre, String sucDireccion, String sucTelefono, String sucResponsable) throws SQLException {
        try {
            Connection c = o.conectar();
            String query = "INSERT INTO sucursal (SUC_CODIGO, CIU_CODIGO, SUC_NOMBRE, SUC_DIRECCION, SUC_TELEFONO, SUC_RESPONSABLE) VALUES ('"
                    + sucCodigo + "', '"
                    + ciuCodigo + "', '"
                    + sucNombre + "', '"
                    + sucDireccion + "', '"
                    + sucTelefono + "', '"
                    + sucResponsable + "')";
            o.updateQuery(query);
            JOptionPane.showMessageDialog(null, "Se insertó correctamente la sucursal");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public ResultSet verTodasLasSucursales() throws SQLException {
        String query = "SELECT * FROM sucursal";
        ResultSet rs = o.executeQuery(query);
        return rs;
    }

    public ResultSet buscarSucursalPorQuery(String query) throws SQLException {
        ResultSet rs = o.executeQuery(query);
        return rs;
    }

    public void actualizarSucursal(int sucId, String sucCodigo, String ciuCodigo, String sucNombre, String sucDireccion, String sucTelefono, String sucResponsable) throws SQLException {
        String query = "UPDATE sucursal SET "
                + "SUC_CODIGO = '" + sucCodigo + "', "
                + "CIU_CODIGO = '" + ciuCodigo + "', "
                + "SUC_NOMBRE = '" + sucNombre + "', "
                + "SUC_DIRECCION = '" + sucDireccion + "', "
                + "SUC_TELEFONO = '" + sucTelefono + "', "
                + "SUC_RESPONSABLE = '" + sucResponsable + "' "
                + "WHERE suc_codigo = " + sucId;
        o.updateQuery(query);
    }

    public void eliminarSucursal(int sucId) throws SQLException {
        String query = "DELETE FROM sucursal WHERE suc_codigo = " + sucId;
        o.updateQuery(query);
    }

}
