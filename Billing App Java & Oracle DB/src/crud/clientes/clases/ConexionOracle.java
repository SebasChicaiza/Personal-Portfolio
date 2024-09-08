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
public class ConexionOracle {
    private final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String URL = "jdbc:oracle:thin:@ip:CDB1";
    private final String user = "user";
    private final String clave = "password";
    
    public Connection cadena;
    
    public ConexionOracle(){
        this.cadena = null;
    }
    
    public Connection conectar(){
        try{
            Class.forName(driver);
            this.cadena = DriverManager.getConnection(URL, user, clave);
        }
        catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos");
            System.exit(0);
        }
        return this.cadena;
    }
    //Para querys de selección
    public ResultSet executeQuery(String query) throws SQLException {
        Connection connection = conectar();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al ejecutar query.");
        }
        return resultSet;
    }
    //Para querys de selección
    public void updateQuery(String query) throws SQLException {
        Connection connection = conectar();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            int comprobar = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString());            
            throw new SQLException("Error al ejecutar query.");            
        }
    }
}
