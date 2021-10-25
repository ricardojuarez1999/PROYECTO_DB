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
import proyectobd.dao.intefaces.FacturaVentaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.FacturaVenta;

/**
 *
 * @author israe
 */
public class MySQLFacturaVenta implements FacturaVentaDAO {

    private final String INSERTAR = "INSERT INTO factura_venta VALUES(default, ?,?);";
    private final String MODIFICAR = "UPDATE factura_venta SET id_persona = ?, id_factura_general = ? WHERE id_factura_venta = ?;";
    private final String ELIMINAR = "DELETE FROM factura_venta WHERE id_factura_venta = ?;";
    private final String OBTENERPORID = "SELECT id_factura_venta, id_persona, id_factura_general FROM factura_venta WHERE id_factura_venta = ?;";
    private final String OBTENER = "SELECT id_factura_venta, id_persona, id_factura_general FROM factura_venta;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(FacturaVenta o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getIdPersona());
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
    public void modificar(FacturaVenta o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getIdPersona());
            sentencia.setInt(2, o.getIdFacturaGeneral());
            sentencia.setInt(3, o.getIdFacturaVenta());

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
    public FacturaVenta obtenerId(Integer k) {
        FacturaVenta facturaVenta = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                facturaVenta = parserFacturaVenta();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return facturaVenta;
    }

    @Override
    public ArrayList<FacturaVenta> listar() {
        ArrayList<FacturaVenta> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                FacturaVenta facturaVenta = parserFacturaVenta();
                lista.add(facturaVenta);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private FacturaVenta parserFacturaVenta() throws SQLException {
        FacturaVenta facturaVenta = new FacturaVenta();
        facturaVenta.setIdFacturaVenta(resultados.getInt("id_factura_venta"));
        facturaVenta.setIdPersona(resultados.getInt("id_persona"));
        facturaVenta.setIdFacturaGeneral(resultados.getInt("id_factura_general"));
        return facturaVenta;
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
