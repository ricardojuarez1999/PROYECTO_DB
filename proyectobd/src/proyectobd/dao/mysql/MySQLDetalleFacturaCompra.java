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
import proyectobd.dao.intefaces.DetalleFacturaCompraDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.DetalleFacturaCompra;

/**
 *
 * @author israe
 */
public class MySQLDetalleFacturaCompra implements DetalleFacturaCompraDAO {

    private final String INSERTAR = "INSERT INTO detalle_factura_compra VALUES(default,?,?,?);";
    private final String MODIFICAR = "UPDATE detalle_factura_compra SET cantidad = ?, id_factura_compra  = ?, id_pieza = ? WHERE id_detalle_factura_compra = ?;";
    private final String ELIMINAR = "DELETE FROM detalle_factura_compra WHERE id_detalle_factura_compra = ?;";
    private final String OBTENERPORID = "SELECT id_detalle_factura_compra, cantidad, id_factura_compra,id_pieza FROM detalle_factura_compra WHERE id_detalle_factura_compra = ?;";
    private final String OBTENER = "SELECT id_detalle_factura_compra, cantidad, id_factura_compra,id_pieza FROM detalle_factura_compra;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(DetalleFacturaCompra o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getCantidad());
            sentencia.setInt(2, o.getIdFacturaCompra());
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
    public void modificar(DetalleFacturaCompra o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getCantidad());
            sentencia.setInt(2, o.getIdFacturaCompra());
            sentencia.setInt(3, o.getIdPieza());
            sentencia.setInt(4, o.getIdDetalleFacturaCompra());
            
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
    public DetalleFacturaCompra obtenerId(Integer k) {
        DetalleFacturaCompra detalleFacturaCompra = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                detalleFacturaCompra = parserDetalleFacturaCompra();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return detalleFacturaCompra;
    }

    @Override
    public ArrayList<DetalleFacturaCompra> listar() {
        ArrayList<DetalleFacturaCompra> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                DetalleFacturaCompra detalleFacturaCompra = parserDetalleFacturaCompra();
                lista.add(detalleFacturaCompra);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private DetalleFacturaCompra parserDetalleFacturaCompra() throws SQLException {
        DetalleFacturaCompra detalleFacturaCompra = new DetalleFacturaCompra();
        detalleFacturaCompra.setIdDetalleFacturaCompra(resultados.getInt("id_detalle_factura_compra"));
        detalleFacturaCompra.setCantidad(resultados.getInt("cantidad"));
        detalleFacturaCompra.setIdFacturaCompra(resultados.getInt("id_factura_compra"));
        detalleFacturaCompra.setIdPieza(resultados.getInt("id_pieza"));
        return detalleFacturaCompra;
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
