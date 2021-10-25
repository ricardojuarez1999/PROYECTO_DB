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
import proyectobd.dao.intefaces.VacacionesDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Vacaciones;

/**
 *
 * @author israe
 */
public class MySQLVacaciones implements VacacionesDAO {

    private final String INSERTAR = "INSERT INTO vacaciones VALUES(default,?,?,?);";
    private final String MODIFICAR = "UPDATE vacaciones SET fecha_salida = ?, fecha_entrada = ?, id_empleado = ? WHERE id_vacaciones = ?;";
    private final String ELIMINAR = "DELETE FROM vacaciones WHERE id_vacaciones = ? ;";
    private final String OBTENERPORID = "SELECT id_vacaciones, fecha_salida, fecha_entrada, id_empleado FROM vacaciones WHERE id_vacaciones = ?;";
    private final String OBTENER = "SELECT id_vacaciones, fecha_salida, fecha_entrada, id_empleado FROM vacaciones;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Vacaciones o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            Timestamp fechaSalida = new Timestamp(o.getFecha_salida().getTime());
            sentencia.setTimestamp(1, fechaSalida);
            Timestamp fechaEntrada = new Timestamp(o.getFecha_entrada().getTime());
            sentencia.setTimestamp(2, fechaEntrada);
            sentencia.setInt(3, o.getIdEmpleado());

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
    public void modificar(Vacaciones o) {
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);
            Timestamp fechaSalida = new Timestamp(o.getFecha_salida().getTime());
            sentencia.setTimestamp(1, fechaSalida);
            Timestamp fechaEntrada = new Timestamp(o.getFecha_entrada().getTime());
            sentencia.setTimestamp(2, fechaEntrada);
            sentencia.setInt(3, o.getIdEmpleado());
            sentencia.setInt(4, o.getIdVacaciones());

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
    public Vacaciones obtenerId(Integer k) {
        Vacaciones vacaciones = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                vacaciones = parserVacaciones();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return vacaciones;
    }

    public ArrayList<Vacaciones> listarVacacionesPorIdEmpleado(Integer k) {
        ArrayList<Vacaciones> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT id_vacaciones, fecha_salida, fecha_entrada, id_empleado FROM vacaciones WHERE id_empleado = ?;");
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Vacaciones vacaciones = parserVacaciones();
                lista.add(vacaciones);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    @Override
    public ArrayList<Vacaciones> listar() {
        ArrayList<Vacaciones> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Vacaciones vacaciones = parserVacaciones();
                lista.add(vacaciones);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private Vacaciones parserVacaciones() throws SQLException {
        Vacaciones vacaciones = new Vacaciones();
        vacaciones.setIdVacaciones(resultados.getInt("id_vacaciones"));
        vacaciones.setFecha_salida(new Date(resultados.getTimestamp("fecha_salida").getTime()));
        vacaciones.setFecha_entrada(new Date(resultados.getTimestamp("fecha_entrada").getTime()));
        vacaciones.setIdEmpleado(resultados.getInt("id_empleado"));
        return vacaciones;
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
}
