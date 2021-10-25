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
import proyectobd.dao.intefaces.DetalleFacturaVentaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.DetalleFacturaVenta;

/**
 *
 * @author israe
 */
public class MySQLDetalleFacturaVenta implements DetalleFacturaVentaDAO {

    private final String INSERTAR = "INSERT INTO detalle_factura_venta VALUES(default,?,?,?);";
    private final String MODIFICAR = "UPDATE detalle_factura_venta SET cantidad = ?, id_factura_venta = ?, id_pieza = ? WHERE id_detalle_factura_venta = ?;";
    private final String ELIMINAR = "DELETE FROM detalle_factura_venta WHERE id_detalle_factura_venta = ?;";
    private final String OBTENERPORID = "SELECT id_detalle_factura_venta , cantidad, id_factura_venta ,id_pieza FROM detalle_factura_venta WHERE id_detalle_factura_venta = ?;";
    private final String OBTENER = "SELECT id_detalle_factura_venta , cantidad, id_factura_venta ,id_pieza FROM detalle_factura_venta;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(DetalleFacturaVenta o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getCantidad());
            sentencia.setInt(2, o.getIdFacturaVenta());
            sentencia.setInt(3, o.getIdPieza());

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
    public void modificar(DetalleFacturaVenta o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getCantidad());
            sentencia.setInt(2, o.getIdFacturaVenta());
            sentencia.setInt(3, o.getIdPieza());
            sentencia.setInt(4, o.getIdDetalleFacturaVenta());

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
    public DetalleFacturaVenta obtenerId(Integer k) {
        DetalleFacturaVenta detalleFacturaVenta = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                detalleFacturaVenta = parserDetalleFacturaVenta();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return detalleFacturaVenta;
    }

    @Override
    public ArrayList<DetalleFacturaVenta> listar() {
        ArrayList<DetalleFacturaVenta> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                DetalleFacturaVenta detalleFacturaVenta = parserDetalleFacturaVenta();
                lista.add(detalleFacturaVenta);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private DetalleFacturaVenta parserDetalleFacturaVenta() throws SQLException {
        DetalleFacturaVenta detalleFacturaVenta = new DetalleFacturaVenta();
        detalleFacturaVenta.setIdDetalleFacturaVenta(resultados.getInt("id_detalle_factura_venta"));
        detalleFacturaVenta.setCantidad(resultados.getInt("cantidad"));
        detalleFacturaVenta.setIdFacturaVenta(resultados.getInt("id_factura_venta"));
        detalleFacturaVenta.setIdPieza(resultados.getInt("id_pieza"));
        return detalleFacturaVenta;
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
