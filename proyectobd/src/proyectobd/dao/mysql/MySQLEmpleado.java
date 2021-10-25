/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import proyectobd.dao.intefaces.EmpleadoDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Empleado;

/**
 *
 * @author israe
 */
public class MySQLEmpleado implements EmpleadoDAO {

    private final String INSERTAR = "INSERT INTO empleado (id_empleado,contrasenia,fecha_inicio,id_persona,id_puesto,id_horario)VALUES (default,md5(?),?,?,?,?);";
    private final String MODIFICAR = "UPDATE empleado SET contrasenia = md5(?), fecha_inicio = ?, fecha_fin = ?, id_puesto = ?, id_horario = ? WHERE id_empleado = ?;";
    private final String ELIMINAR = "DELETE FROM empleado WHERE id_empleado = ?";
    private final String OBTENERPORID = "SELECT id_empleado,contrasenia,fecha_inicio, fecha_fin, id_persona, id_puesto,id_horario FROM empleado WHERE id_empleado = ?;";
    private final String OBTENER = "SELECT id_empleado,contrasenia,fecha_inicio, fecha_fin, id_persona, id_puesto,id_horario FROM empleado;";
    private final String LOGIN = "select id_empleado,contrasenia,fecha_inicio, fecha_fin, id_persona, id_puesto,id_horario from empleado WHERE contrasenia = md5(?) and id_empleado = ?";
    
    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Empleado o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getContrasenia());
            
            Timestamp fecha_inicio = new Timestamp(o.getFecha_inico().getTime());
            sentencia.setTimestamp(2, fecha_inicio);
            
            sentencia.setInt(3, o.getIdPersona());
            sentencia.setInt(4, o.getIdPuesto());
            sentencia.setInt(5, o.getIdHorario());

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
    public void modificar(Empleado o) {
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getContrasenia());
            Timestamp fecha_inicio = new Timestamp(o.getFecha_inico().getTime());
            sentencia.setTimestamp(2, fecha_inicio);

            Timestamp fecha_fin = null;
            if (o.getFecha_fin() != null) {
                fecha_fin = new Timestamp(o.getFecha_fin().getTime());
            }

            sentencia.setTimestamp(3, fecha_fin);
            sentencia.setInt(4, o.getIdPuesto());
            sentencia.setInt(5, o.getIdHorario());
            sentencia.setInt(6, o.getIdEmpleado());

            if (sentencia.executeUpdate() == 0) {
                throw new Excepcion("No se modifico el registro ");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
    }
    public void modificar(Empleado o, String consulta) {
        consulta = "UPDATE empleado SET fecha_inicio = ?, fecha_fin = ?, id_puesto = ?, id_horario = ? WHERE id_empleado = ?;";
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(consulta);
            Timestamp fecha_inicio = new Timestamp(o.getFecha_inico().getTime());
            sentencia.setTimestamp(1, fecha_inicio);

            Timestamp fecha_fin = null;
            if (o.getFecha_fin() != null) {
                fecha_fin = new Timestamp(o.getFecha_fin().getTime());
            }

            sentencia.setTimestamp(2, fecha_fin);
            sentencia.setInt(3, o.getIdPuesto());
            sentencia.setInt(4, o.getIdHorario());
            sentencia.setInt(5, o.getIdEmpleado());

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
    public Empleado obtenerId(Integer k) {
        Empleado empleado = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {    
                empleado = parserEmpleado();
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return empleado;
    }
    
    public Empleado obtenerLogin(Integer k,String p) {
        Empleado empleado = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(LOGIN);
            sentencia.setString(1,p);
            sentencia.setInt(2, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {    
                empleado = parserEmpleado();
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return empleado;
    }

    @Override
    public ArrayList<Empleado> listar() {
        ArrayList<Empleado> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Empleado empleado = parserEmpleado();
                lista.add(empleado);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private Empleado parserEmpleado() throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(resultados.getInt("id_empleado"));
        empleado.setContrasenia(resultados.getString("contrasenia"));
        empleado.setFecha_inico(new Date(resultados.getTimestamp("fecha_inicio").getTime()));
        if (resultados.getTimestamp("fecha_fin") != null) {
            empleado.setFecha_fin(new Date(resultados.getTimestamp("fecha_fin").getTime()));
        }
        empleado.setIdPersona(resultados.getInt("id_persona"));
        empleado.setIdPuesto(resultados.getInt("id_puesto"));
        empleado.setIdHorario(resultados.getInt("id_horario"));
        return empleado;
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
