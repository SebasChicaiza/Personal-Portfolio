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
public class VendedorManager {

    private ConexionOracle o;

    public VendedorManager() {
        o = new ConexionOracle();
    }

    public void insertarVendedor(String venCedulaRuc, String sucCodigo, String venNombre, String venApellido, String venCorreo, String venTelefono, double venPComision, String venCuentaBanco) throws SQLException {
        try {
            Connection c = o.conectar();
            String query = "INSERT INTO vendedor (VEN_CEDULA_RUC, SUC_CODIGO, VEN_NOMBRE, VEN_APELLIDO, VEN_CORREO, VEN_TELEFONO, VEN_P_COMISION, VEN_CUENTA_BANCO) VALUES ('"
                    + venCedulaRuc + "', '"
                    + sucCodigo + "', '"
                    + venNombre + "', '"
                    + venApellido + "', '"
                    + venCorreo + "', '"
                    + venTelefono + "', "
                    + venPComision + ", '"
                    + venCuentaBanco + "')";
            o.updateQuery(query);
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el vendedor");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

    }

    public ResultSet verTodosLosVendedores() throws SQLException {
        String query = "SELECT * FROM vendedor";
        ResultSet rs = o.executeQuery(query);
        return rs;
    }

    public ResultSet buscarVendedorPorQuery(String query) throws SQLException {
        ResultSet rs = o.executeQuery(query);
        return rs;
    }

    public void actualizarVendedor(int venId, String venCedulaRuc, String sucCodigo, String venNombre, String venApellido, String venCorreo, String venTelefono, double venPComision, String venCuentaBanco) throws SQLException {
        String query = "UPDATE vendedor SET "
                + "VEN_CEDULA_RUC = '" + venCedulaRuc + "', "
                + "SUC_CODIGO = '" + sucCodigo + "', "
                + "VEN_NOMBRE = '" + venNombre + "', "
                + "VEN_APELLIDO = '" + venApellido + "', "
                + "VEN_CORREO = '" + venCorreo + "', "
                + "VEN_TELEFONO = '" + venTelefono + "', "
                + "VEN_P_COMISION = " + venPComision + ", "
                + "VEN_CUENTA_BANCO = '" + venCuentaBanco + "' "
                + "WHERE ven_cedula_ruc = " + venId;
        o.updateQuery(query);
    }

    public void eliminarVendedor(int venId) throws SQLException {
        String query = "DELETE FROM vendedor WHERE ven_cedula_ruc = " + venId;
        o.updateQuery(query);
    }
    public void insertarComision(String CMS_NUMERO, String VEN_CEDULA_RUC, String CMS_FECHA_PAGO,String CMS_FECHA_INI, String CMS_FECHA_FIN, double CMS_CANTIDAD_PAGADA, int FAC_NUMERO)
    {
        try{
            Connection c = o.conectar();
            //String query = "INSERT INTO factura VALUES ('"+ facNumero + "', '" + sucCodigo + "', '" + trnCodigo +"', '" + cliCedulaRuc +"', '" + venCedulaRuc +"', TO_DATE('" + facFecha +"','dd/mm/yyyy')," + facMonto+"', '" +facEstado+"')";
            //String query = "INSERT INTO comision VALUES ('" + CMS_NUMERO + "', '" + VEN_CEDULA_RUC + "', '" + "', TO_DATE('" + CMS_FECHA_PAGO + "','dd/mm/yyyy'), " + "', TO_DATE('" + CMS_FECHA_INI + "','dd/mm/yyyy'), " + "', TO_DATE('" + CMS_FECHA_FIN + "','dd/mm/yyyy'), " + CMS_CANTIDAD_PAGADA +  ", " + FAC_NUMERO + ")";
            String query = "INSERT INTO comision (CMS_NUMERO, VEN_CEDULA_RUC, CMS_FECHA_PAGO, CMS_FECHA_INI, CMS_FECHA_FIN, CMS_CANTIDAD_PAGADA, FAC_NUMERO) " +
               "VALUES ('" + CMS_NUMERO + "', '" + VEN_CEDULA_RUC + "', TO_DATE('" + CMS_FECHA_PAGO + "','dd/mm/yyyy'), TO_DATE('" + CMS_FECHA_INI + "','dd/mm/yyyy'), TO_DATE('" + CMS_FECHA_FIN + "','dd/mm/yyyy'), " + CMS_CANTIDAD_PAGADA + ", " + FAC_NUMERO + ")";
            o.updateQuery(query);
            JOptionPane.showMessageDialog(null, "Se insertó correctamente la comision");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    public int obtenerNumeroComisión() throws SQLException {
        String query = "SELECT cms_numero FROM comision ORDER BY cms_numero DESC FETCH FIRST ROW ONLY";
        ResultSet rs = o.executeQuery(query);
        int ncomision = 0;
        if (rs.next()) {
            ncomision = rs.getInt("cms_numero");
        }
        ncomision += 1;
        return ncomision;
    }

}
