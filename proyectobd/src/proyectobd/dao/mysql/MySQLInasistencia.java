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
import proyectobd.dao.intefaces.InasistenciaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Inasistencia;

/**
 *
 * @author israe
 */
public class MySQLInasistencia implements InasistenciaDAO {

    private final String INSERTAR = "INSERT INTO inasistencia VALUES (default, curdate(), ?,?); ";
    private final String MODIFICAR = "UPDATE inasistencia SET fecha = ? , id_tipo_inasistencia = ?, id_empleado = ? WHERE id_inasistencia = ?;";
    private final String ELIMINAR = "DELETE FROM inasistencia WHERE id_inasistencia = ?; ";
    private final String OBTENERPORID = "SELECT id_inasistencia, fecha, id_tipo_inasistencia, id_empleado FROM inasistencia WHERE id_inasistencia = ?;";
    private final String OBTENER = "SELECT id_inasistencia, fecha, id_tipo_inasistencia, id_empleado FROM inasistencia;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Inasistencia o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getIdTipoInasistencia());
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
    public void modificar(Inasistencia o) {
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);

            Timestamp fecha = new Timestamp(o.getFecha().getTime());

            sentencia.setTimestamp(1, fecha);
            sentencia.setInt(2, o.getIdTipoInasistencia());
            sentencia.setInt(3, o.getIdEmpleado());
            sentencia.setInt(4, o.getIdInasistencia());

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
    public Inasistencia obtenerId(Integer k) {
        Inasistencia inasistencia = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                inasistencia = parserInasistencia();
            } 
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return inasistencia;
    }
    
    public int numeroInasistencias(Integer mes, Integer anio, Integer id) {
        int num = 0;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT count(id_inasistencia) FROM inasistencia WHERE month(fecha) = ? AND year(fecha) = ?  AND id_empleado = ?;");
            sentencia.setInt(1, mes);
            sentencia.setInt(2, anio);
            sentencia.setInt(3, id);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                num = resultados.getInt(1);
            } 
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return num;
    }
    
    public Inasistencia obtenerIdyporFecha(Integer k) {
        Inasistencia inasistencia = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("select * from inasistencia where id_empleado = ? and fecha = curdate();");
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                inasistencia = parserInasistencia();
            } 
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return inasistencia;
    }

    @Override
    public ArrayList<Inasistencia> listar() {
        ArrayList<Inasistencia> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Inasistencia inasistencia = parserInasistencia();
                lista.add(inasistencia);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }
    
    
     private Inasistencia parserInasistencia() throws SQLException {
        Inasistencia inasistencia = new Inasistencia();
        inasistencia.setIdInasistencia(resultados.getInt("id_inasistencia"));
        inasistencia.setFecha(new Date(resultados.getTimestamp("fecha").getTime()));
        
        inasistencia.setIdTipoInasistencia(resultados.getInt("id_tipo_inasistencia"));
        inasistencia.setIdEmpleado(resultados.getInt("id_empleado"));
        return inasistencia;
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
