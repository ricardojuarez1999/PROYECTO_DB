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
import proyectobd.dao.intefaces.PagoDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Pago;

/**
 *
 * @author israe
 */
public class MySQLPago implements PagoDAO {

    private final String INSERTAR = "INSERT INTO pago VALUES(default, ?,curdate(),? ) ;";
    private final String MODIFICAR = "UPDATE pago SET pago = ?, fecha = ?, id_empleado = ? WHERE id_pago = ?; ";
    private final String ELIMINAR = "DELETE FROM pago WHERE id_pago = ?; ";
    private final String OBTENERPORID = "SELECT id_pago, pago,fecha,id_empleado FROM pago WHERE id_pago = ?;";
    private final String OBTENER = "SELECT id_pago, pago,fecha,id_empleado FROM pago;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Pago o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setFloat(1, o.getPago());
            sentencia.setInt(2, o.getIdEmpleado());

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
    public void modificar(Pago o) {
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setFloat(1, o.getPago());
            Timestamp fecha = new Timestamp(o.getFecha().getTime());
            sentencia.setTimestamp(2, fecha);
            sentencia.setInt(3, o.getIdEmpleado());
            sentencia.setInt(4, o.getIdPago());

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
    public Pago obtenerId(Integer k) {
        Pago  pago = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                pago = parserPago();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return pago;
    }

    @Override
    public ArrayList<Pago> listar() {
        ArrayList<Pago> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Pago pago = parserPago();
                lista.add(pago);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private Pago parserPago() throws SQLException {
        Pago pago = new Pago();
        pago.setIdPago(resultados.getInt("id_pago"));
        pago.setPago(resultados.getFloat("pago"));
        pago.setFecha(new Date(resultados.getTimestamp("fecha").getTime()));
        pago.setIdEmpleado(resultados.getInt("id_empleado"));
        return pago;
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
    
    public ArrayList<Pago> listarPagosDeEmpleado(Integer annio, Integer k) {
        ArrayList<Pago> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("select * from pago WHERE year(fecha) = ? AND id_empleado = ?;");
            sentencia.setInt(1, annio);
            sentencia.setInt(2, k);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Pago pago = parserPago();
                lista.add(pago);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }
}
