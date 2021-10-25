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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import proyectobd.dao.intefaces.MovimientoFinancieroDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.MovimientoFinanciero;

/**
 *
 * @author israe
 */
public class MySQLMovimientoFinanciero implements MovimientoFinancieroDAO {

    private final String INSERTAR = "INSERT INTO movimiento_financiero VALUES(default, ?,?,curdate(),?);";
    private final String MODIFICAR = "UPDATE  movimiento_financiero SET total = ?,  monto = ?, fecha = ?, id_tipo_transaccion = ?  WHERE id_movimiento_financiero = ? ;";
    private final String ELIMINAR = "DELETE FROM movimiento_financiero  WHERE movimiento_financiero = ?; ";
    private final String OBTENERPORID = "SELECT id_movimiento_financiero, total,monto,fecha,id_tipo_transaccion FROM movimiento_financiero WHERE id_movimiento_financiero = ?;";
    private final String OBTENER = "SELECT id_movimiento_financiero, total,monto,fecha,id_tipo_transaccion FROM movimiento_financiero;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(MovimientoFinanciero o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setFloat(1, o.getTotal());
            sentencia.setFloat(2, o.getMonto());
            sentencia.setInt(3, o.getIdTipoTransaccion());

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
    public void modificar(MovimientoFinanciero o) {
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setFloat(1, o.getTotal());
            sentencia.setFloat(2, o.getMonto());
            Timestamp fecha = new Timestamp(o.getFecha().getTime());
            sentencia.setTimestamp(3, fecha);
            sentencia.setInt(4, o.getIdTipoTransaccion());
            sentencia.setInt(5, o.getIdMovimientoFinanciero());

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
    public MovimientoFinanciero obtenerId(Integer k) {
        MovimientoFinanciero movimientoFinanciero = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                movimientoFinanciero = parserMovimientoFinanciero();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return movimientoFinanciero;
    }

    @Override
    public ArrayList<MovimientoFinanciero> listar() {
        ArrayList<MovimientoFinanciero> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                MovimientoFinanciero movimientoFinanciero = parserMovimientoFinanciero();
                lista.add(movimientoFinanciero);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private MovimientoFinanciero parserMovimientoFinanciero() throws SQLException {
        MovimientoFinanciero movimientoFinanciero = new MovimientoFinanciero();
        movimientoFinanciero.setIdMovimientoFinanciero(resultados.getInt("id_movimiento_financiero"));
        movimientoFinanciero.setTotal(resultados.getFloat("total"));
        movimientoFinanciero.setMonto(resultados.getFloat("monto"));
        movimientoFinanciero.setFecha(new Date(resultados.getTimestamp("fecha").getTime()));
        movimientoFinanciero.setIdTipoTransaccion(resultados.getInt("id_tipo_transaccion"));
        return movimientoFinanciero;
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

    public static java.util.Date obtenerDate(String fecha) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
    }

    public void insertarFechaActual(MovimientoFinanciero o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement("INSERT INTO movimiento_financiero VALUES(default, ?,?,curdate(),?);");
            sentencia.setFloat(1, o.getTotal());
            sentencia.setFloat(2, o.getMonto());
            sentencia.setInt(3, o.getIdTipoTransaccion());

            if (sentencia.executeUpdate() == 0) {
                throw new Excepcion("No se inserto el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
    }
}
