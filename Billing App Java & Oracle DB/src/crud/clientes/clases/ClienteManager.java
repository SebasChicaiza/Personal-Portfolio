/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.clientes.clases;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author david
 */
public class ClienteManager {

    private ConexionOracle o ;
    public ClienteManager() {
        o = new ConexionOracle();
    }
    //INSERTAR
    public void insertarCliente(String cedulaRuc, String codigoCiudad, String nombre, String apellido, String correo, String telefono, String direccion, String sector) throws SQLException
    {
        try{
            Connection c = o.conectar();
            String query = "INSERT INTO cliente VALUES ('"+ cedulaRuc + "', '" + codigoCiudad + "', '" + nombre +"', '" + apellido +"', '" + correo +"', '" + telefono +"', '" + direccion+"', '" +sector+"')";
            o.updateQuery(query);
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el cliente");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    public ResultSet verTodosLosClientes() throws SQLException
    {
        String query = "SELECT * FROM cliente";
        ResultSet rs= o.executeQuery(query);
        return rs;
    }
    public ResultSet buscarConQuery(String query) throws SQLException
    {
        ResultSet rs= o.executeQuery(query);
        return rs;
    }
    public void actualizarCliente(String cedulaRuc, String codigoCiudad, String nombre, String apellido, String correo, String telefono, String direccion, String sector) throws SQLException
    {
        String query = "UPDATE cliente SET "
                + "    ciu_codigo = '" + codigoCiudad + "', "
                + "    cli_nombre = '" + nombre + "', "
                + "    cli_apellido = '" + apellido + "', "
                + "    cli_correo = '" + correo + "', "
                + "    cli_telefono = '" + telefono + "', "
                + "    cli_direccion = '" + direccion + "', "
                + "    cli_sector = '" + sector + "' "
                + "    WHERE cli_cedula_ruc = '" + cedulaRuc + "'";
        o.updateQuery(query);
    }
    public void elminarCliente (String cedulaRuc) throws SQLException
    {
        String query = "DELETE FROM cliente WHERE cli_cedula_ruc = '" + cedulaRuc + "'";
        o.updateQuery(query);
    }
    public List<String> obtenerCodCiudades() throws SQLException
    {
        List<String> cod_ciudades = new ArrayList<>(); 
        ResultSet rs = o.executeQuery("SELECT ciu_codigo FROM ciudad");
        while(rs.next())
        {
            String aux = rs.getString("ciu_codigo");
            cod_ciudades.add(aux);
        }
        return cod_ciudades;
    }
}
