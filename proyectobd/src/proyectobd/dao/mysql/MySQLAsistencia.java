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
import proyectobd.dao.intefaces.AsistenciaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Asistencia;

/**
 *
 * @author israe
 */
public class MySQLAsistencia implements AsistenciaDAO {

    private final String INSERTAR = "INSERT INTO asistencia (id_asistencia,hora_entrada,fecha,id_empleado) VALUES(default,curtime(),curdate(),?); ";
    private final String MODIFICAR = "UPDATE asistencia SET hora_entrada = ?, hora_salida = ?, fecha = ? WHERE id_asistencia = ?;";
    private final String ELIMINAR = "DELETE FROM asistencia WHERE id_asistencia = ?";
    private final String OBTENERPORID = "SELECT id_asistencia,hora_entrada,hora_salida, fecha, id_empleado FROM asistencia WHERE id_asistencia = ?;";
    private final String OBTENER = "SELECT id_asistencia,hora_entrada,hora_salida, fecha, id_empleado FROM asistencia;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Asistencia o) {
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getIdEmpleado());

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
    public void modificar(Asistencia o) {
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getHora_entrada());
            sentencia.setString(2, o.getHora_salida());
            
            Timestamp fecha = new Timestamp(o.getFecha().getTime());
            sentencia.setTimestamp(3, fecha);
            
            sentencia.setInt(4,o.getIdAsistencia());
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
    public Asistencia obtenerId(Integer k) {
        Asistencia asistencia = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                asistencia = parserAsistencia();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return asistencia;
    }
    
    public Asistencia obtenerIdyFecha(Integer k) {
        Asistencia asistencia = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("select * from asistencia where id_empleado = ? and fecha = curdate();");
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                asistencia = parserAsistencia();
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return asistencia;
    }
    public ArrayList<Asistencia> listarPorAño(Integer k) {
        ArrayList<Asistencia> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT * FROM asistencia WHERE YEAR(fecha) = ?;");
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Asistencia asistencia = parserAsistencia();
                lista.add(asistencia);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }
    
    public ArrayList<Asistencia> listarPorMesAño(Integer mes,Integer año) {
        ArrayList<Asistencia> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT * FROM asistencia WHERE MONTH(fecha) = ? AND YEAR(fecha) = ?;");
            sentencia.setInt(1, mes);
            sentencia.setInt(2, año);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Asistencia asistencia = parserAsistencia();
                lista.add(asistencia);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }
    
    public ArrayList<Asistencia> listarPorFecha(String fecha) {
        ArrayList<Asistencia> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT * FROM asistencia WHERE fecha = ?;");
            sentencia.setString(1, fecha);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Asistencia asistencia = parserAsistencia();
                lista.add(asistencia);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }
    
    @Override
    public ArrayList<Asistencia> listar(){
        ArrayList<Asistencia> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Asistencia asistencia = parserAsistencia();
                lista.add(asistencia);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }
    private Asistencia parserAsistencia() throws SQLException {
        Asistencia asistencia = new Asistencia();
        asistencia.setIdAsistencia(resultados.getInt("id_asistencia"));
        asistencia.setHora_entrada(resultados.getString("hora_entrada"));
        asistencia.setHora_salida(resultados.getString("hora_salida"));
        if (resultados.getTimestamp("fecha") != null) {
            asistencia.setFecha(new Date(resultados.getTimestamp("fecha").getTime()));
        }
        asistencia.setIdEmpleado(resultados.getInt("id_empleado"));
        return asistencia;
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
