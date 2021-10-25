/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import proyectobd.dao.intefaces.FacturaProductoDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.FacturaProducto;

/**
 *
 * @author israe
 */
public class MySQLFacturaProducto implements FacturaProductoDAO {

    private final String INSERTAR = "INSERT INTO factura_producto VALUES(default, ?,?);";
    private final String MODIFICAR = "UPDATE factura_producto SET id_proveedor = ?, id_factura_general = ? WHERE id_factura_producto = ?;";
    private final String ELIMINAR = "DELETE FROM factura_producto WHERE id_factura_producto = ?;";
    private final String OBTENERPORID = "SELECT id_factura_producto , id_proveedor, id_factura_general FROM factura_producto WHERE id_factura_producto = ?;";
    private final String OBTENER = "SELECT id_factura_producto , id_proveedor, id_factura_general FROM factura_producto;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(FacturaProducto o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getIdProveedor());
            sentencia.setInt(2, o.getIdFacturaGeneral());

            if (sentencia.executeUpdate() == 0) {
                throw new Excepcion("No se inserto el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public void modificar(FacturaProducto o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getIdProveedor());
            sentencia.setInt(2, o.getIdFacturaGeneral());
            sentencia.setInt(3, o.getIdFacturaProducto());

            if (sentencia.executeUpdate() == 0) {
                throw new Excepcion("No se modifico el registro ");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public void eliminar(Integer k) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(ELIMINAR);
            sentencia.setInt(1, k);

            if (sentencia.executeUpdate() == 0) {
                throw new Excepcion("No se elimino el registro ");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public FacturaProducto obtenerId(Integer k) {
        FacturaProducto facturaProducto = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                facturaProducto = parserFacturaProducto();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return facturaProducto;
    }

    @Override
    public ArrayList<FacturaProducto> listar() {
        ArrayList<FacturaProducto> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                FacturaProducto facturaProducto = parserFacturaProducto();
                lista.add(facturaProducto);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private FacturaProducto parserFacturaProducto() throws SQLException {
        FacturaProducto facturaProducto = new FacturaProducto();
        facturaProducto.setIdFacturaProducto(resultados.getInt("id_factura_producto"));
        facturaProducto.setIdProveedor(resultados.getInt("id_proveedor"));
        facturaProducto.setIdFacturaGeneral(resultados.getInt("id_factura_general"));
        return facturaProducto;
    }

    private void cerrarConexiones() {
        try {
            if (resultados != null) {
                resultados.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
        }
    }
}
