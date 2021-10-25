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
import proyectobd.dao.intefaces.DetalleFacturaExtraordinariaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.DetalleFacturaExtraordinaria;

/**
 *
 * @author israe
 */
public class MySQLDetalleFacturaExtraordinaria implements DetalleFacturaExtraordinariaDAO {

    private final String INSERTAR = "INSERT INTO detalle_factura_extraordinaria VALUES (default,?,upper(?),?);";
    private final String MODIFICAR = "UPDATE detalle_factura_extraordinaria SET cantidad = ?, descripcion = upper(?), id_factura_general = ? WHERE id_detalle_factura_extraordinaria = ?;";
    private final String ELIMINAR = "DELETE FROM detalle_factura_extraordinaria WHERE id_detalle_factura_extraordinaria = ?; ";
    private final String OBTENERPORID = "SELECT id_detalle_factura_extraordinaria, cantidad, descripcion, id_factura_general FROM detalle_factura_extraordinaria WHERE id_detalle_factura_extraordinaria = ?;";
    private final String OBTENER = "SELECT id_detalle_factura_extraordinaria, cantidad, descripcion, id_factura_general FROM detalle_factura_extraordinaria;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(DetalleFacturaExtraordinaria o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getCantidad());
            sentencia.setString(2, o.getDescripcion());
            sentencia.setInt(3, o.getIdFacturaGeneral());

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
    public void modificar(DetalleFacturaExtraordinaria o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getCantidad());
            sentencia.setString(2, o.getDescripcion());
            sentencia.setInt(3, o.getIdFacturaGeneral());
            sentencia.setInt(4, o.getIdDetalleFacturaExtraordinaria());

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
    public DetalleFacturaExtraordinaria obtenerId(Integer k) {
        DetalleFacturaExtraordinaria detalleFacExtra = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                detalleFacExtra = parserDetalleFacturaExtraordinaria();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return detalleFacExtra;
    }

    @Override
    public ArrayList<DetalleFacturaExtraordinaria> listar() {
        ArrayList<DetalleFacturaExtraordinaria> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                DetalleFacturaExtraordinaria detalleFacExtra = parserDetalleFacturaExtraordinaria();
                lista.add(detalleFacExtra);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private DetalleFacturaExtraordinaria parserDetalleFacturaExtraordinaria() throws SQLException {
        DetalleFacturaExtraordinaria detalleFacExtra = new DetalleFacturaExtraordinaria();
        detalleFacExtra.setIdDetalleFacturaExtraordinaria(resultados.getInt("id_detalle_factura_extraordinaria"));
        detalleFacExtra.setCantidad(resultados.getInt("cantidad"));
        detalleFacExtra.setDescripcion(resultados.getString("descripcion"));
        detalleFacExtra.setIdFacturaGeneral(resultados.getInt("id_factura_general"));
        return detalleFacExtra;
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
