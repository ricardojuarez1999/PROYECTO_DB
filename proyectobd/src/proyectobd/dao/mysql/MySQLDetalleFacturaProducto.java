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
import proyectobd.dao.intefaces.DetalleFacturaProductoDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.DetalleFacturaProducto;

/**
 *
 * @author israe
 */
public class MySQLDetalleFacturaProducto implements DetalleFacturaProductoDAO {

    private final String INSERTAR = "INSERT INTO detalle_factura_producto VALUES(default,?,?,?);";
    private final String MODIFICAR = "UPDATE detalle_factura_producto SET cantidad = ?, id_factura_producto  = ?, id_producto = ? WHERE id_detalle_factura_producto = ?;";
    private final String ELIMINAR = "DELETE FROM detalle_factura_producto WHERE id_detalle_factura_producto = ?;";
    private final String OBTENERPORID = "SELECT id_detalle_factura_producto , cantidad, id_factura_producto ,id_producto FROM detalle_factura_producto WHERE id_detalle_factura_producto = ?;";
    private final String OBTENER = "SELECT id_detalle_factura_producto , cantidad, id_factura_producto ,id_producto FROM detalle_factura_producto;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(DetalleFacturaProducto o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getCantidad());
            sentencia.setInt(2, o.getIdFacturaProducto());
            sentencia.setInt(3, o.getIdProducto());

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
    public void modificar(DetalleFacturaProducto o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getCantidad());
            sentencia.setInt(2, o.getIdFacturaProducto());
            sentencia.setInt(3, o.getIdProducto());
            sentencia.setInt(4, o.getIdDetalleFacturaProducto());

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
    public DetalleFacturaProducto obtenerId(Integer k) {
        DetalleFacturaProducto detalleFacturaProducto = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                detalleFacturaProducto= parserDetalleFacturaProducto();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return detalleFacturaProducto;
    }

    @Override
    public ArrayList<DetalleFacturaProducto> listar() {
        ArrayList<DetalleFacturaProducto> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                DetalleFacturaProducto detalleFacturaProducto = parserDetalleFacturaProducto();
                lista.add(detalleFacturaProducto);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private DetalleFacturaProducto parserDetalleFacturaProducto() throws SQLException {
        DetalleFacturaProducto detalleFacturaProducto = new DetalleFacturaProducto();
        detalleFacturaProducto.setIdDetalleFacturaProducto(resultados.getInt("id_detalle_factura_producto"));
        detalleFacturaProducto.setCantidad(resultados.getInt("cantidad"));
        detalleFacturaProducto.setIdFacturaProducto(resultados.getInt("id_factura_producto"));
        detalleFacturaProducto.setIdProducto(resultados.getInt("id_producto"));
        return detalleFacturaProducto;
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
