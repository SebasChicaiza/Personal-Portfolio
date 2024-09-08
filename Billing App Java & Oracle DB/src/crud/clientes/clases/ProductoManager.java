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
public class ProductoManager {
    ConexionOracle o;

    public ProductoManager() {
        o= new ConexionOracle();
    }

    public void insertarProducto(String prdCodigo, String catCodigo, String prdDescripcion, double prdPrecio, double prdPrecioAnterior, String prdCodigoBarra, String prdPagaIva) throws SQLException {
        try {
            Connection c = o.conectar();
            String query = "INSERT INTO producto (PRD_CODIGO, CAT_CODIGO, PRD_DESCRIPCION, PRD_PRECIO, PRD_PRECIO_ANTERIOR, PRD_CODIGO_BARRA, PRD_PAGA_IVA) VALUES ('"
                    + prdCodigo + "', '"
                    + catCodigo + "', '"
                    + prdDescripcion + "', "
                    + prdPrecio + ", "
                    + prdPrecioAnterior + ", '"
                    + prdCodigoBarra + "', '"
                    + prdPagaIva + "')";
            o.updateQuery(query);
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el producto");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public ResultSet verTodosLosProductos() throws SQLException {
        String query = "SELECT * FROM producto";
        ResultSet rs = o.executeQuery(query);
        return rs;
    }

    public ResultSet buscarProductoPorQuery(String query) throws SQLException {
        ResultSet rs = o.executeQuery(query);
        return rs;
    }

    public void actualizarProducto(int prdId, String prdCodigo, String catCodigo, String prdDescripcion, double prdPrecio, double prdPrecioAnterior, String prdCodigoBarra, String prdPagaIva) throws SQLException {
        String query = "UPDATE producto SET "
                + "PRD_CODIGO = '" + prdCodigo + "', "
                + "CAT_CODIGO = '" + catCodigo + "', "
                + "PRD_DESCRIPCION = '" + prdDescripcion + "', "
                + "PRD_PRECIO = " + prdPrecio + ", "
                + "PRD_PRECIO_ANTERIOR = " + prdPrecioAnterior + ", "
                + "PRD_CODIGO_BARRA = '" + prdCodigoBarra + "', "
                + "PRD_PAGA_IVA = '" + prdPagaIva + "' "
                + "WHERE PRD_CODIGO = " + prdId;
        o.updateQuery(query);
    }

    public void eliminarProducto(int prdId) throws SQLException {
        String query = "DELETE FROM producto WHERE PRD_CODIGO = " + prdId;
        o.updateQuery(query);
    }
    public String obtenerQueryProductosPorSucursal(String codigoSucursal) {
        String query = "SELECT "
                + "p.PRD_CODIGO, "
                + "p.PRD_DESCRIPCION, "
                + "p.PRD_PRECIO, "
                + "pb.PRB_EXISTENCIA, "
                + "pb.PRB_FECHA_EXPIRA "
                + "FROM sucursal s "
                + "JOIN bodega b ON s.SUC_CODIGO = b.SUC_CODIGO "
                + "JOIN producto_bodega pb ON b.BOD_CODIGO = pb.BOD_CODIGO "
                + "JOIN producto p ON pb.PRD_CODIGO = p.PRD_CODIGO "
                + "WHERE s.SUC_CODIGO = '"+codigoSucursal+"'";
        return query;
    }

    public ResultSet buscarProductoPorQuery(String query, String sucCodigo, String codigo, String descripcion, String cbarras) throws SQLException {
        Connection c = o.conectar();
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, sucCodigo);  // Enlaza el primer parámetro

        // Enlaza los parámetros adicionales si existen
        if (!codigo.isEmpty()) {
            ps.setString(2, "%" + codigo + "%");  // Ajusta el índice según corresponda
        }
        if (!descripcion.isEmpty()) {
            ps.setString(3, "%" + descripcion + "%");  // Ajusta el índice según corresponda
        }
        if (!cbarras.isEmpty()) {
            ps.setString(4, "%" + cbarras + "%");  // Ajusta el índice según corresponda
        }

        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public int obtenerCantidadEnStock(String prdCodigo, String sucCodigo) throws SQLException {
        int cantidadEnStock = 0;
        Connection c = o.conectar();

        // Consulta SQL para obtener la cantidad en stock
        String query = "SELECT SUM(pb.PRB_EXISTENCIA) AS total_existencia "
                + "FROM producto_bodega pb "
                + "JOIN bodega b ON pb.BOD_CODIGO = b.BOD_CODIGO "
                + "JOIN sucursal s ON b.SUC_CODIGO = s.SUC_CODIGO "
                + "WHERE pb.PRD_CODIGO = ? AND s.SUC_CODIGO = ?";

        // Usar PreparedStatement para evitar inyecciones SQL
        try (PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, prdCodigo);
            ps.setString(2, sucCodigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Obtener la cantidad en stock
                    cantidadEnStock = rs.getInt("total_existencia");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Cerrar la conexión
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }
        return cantidadEnStock;
    }
    public ResultSet obtenerProductoPorCodigo(String codigo) throws SQLException
    {
        ResultSet rs = o.executeQuery("SELECT * FROM producto WHERE prd_codigo = '"+codigo+"'");
        return rs;
    }
}
