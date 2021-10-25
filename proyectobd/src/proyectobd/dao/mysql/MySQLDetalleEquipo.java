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
import proyectobd.dao.intefaces.DetalleEquipoDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.DetalleEquipo;

/**
 *
 * @author israe
 */
public class MySQLDetalleEquipo implements DetalleEquipoDAO {

    private final String INSERTAR = "INSERT INTO detalle_equipo VALUES(default,?,?);";
    private final String MODIFICAR = "UPDATE detalle_equipo SET id_equipo = ?, id_empleado = ? WHERE id_detalle_equipo = ?;";
    private final String ELIMINAR = "DELETE FROM detalle_equipo WHERE id_detalle_equipo = ?;";
    private final String OBTENERPORID = "SELECT id_detalle_equipo, id_equipo, id_empleado FROM detalle_equipo WHERE id_detalle_equipo = ?;";
    private final String OBTENER = "SELECT id_detalle_equipo, id_equipo, id_empleado FROM detalle_equipo;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(DetalleEquipo o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getIdEquipo());
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
    public void modificar(DetalleEquipo o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getIdEquipo());
            sentencia.setInt(2, o.getIdEmpleado());
            sentencia.setInt(3, o.getIdDetalleEquipo());

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
    public DetalleEquipo obtenerId(Integer k) {
        DetalleEquipo detalleEquipo = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                detalleEquipo = parserDetalleEquipo();
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return detalleEquipo;
    }
    
    public DetalleEquipo obtenerIdEmpleado(Integer k) {
        DetalleEquipo detalleEquipo = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT id_detalle_equipo, id_equipo, id_empleado FROM detalle_equipo WHERE id_empleado = ?;");
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                detalleEquipo = parserDetalleEquipo();
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return detalleEquipo;
    }

    public DetalleEquipo obtenerIdEquipo(Integer k) {
        DetalleEquipo detalleEquipo = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT id_detalle_equipo, id_equipo, id_empleado FROM detalle_equipo WHERE id_equipo = ?;");
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                detalleEquipo = parserDetalleEquipo();
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return detalleEquipo;
    }
    
    public ArrayList<DetalleEquipo> listarIdEquipo(Integer k) {
        ArrayList<DetalleEquipo> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT id_detalle_equipo, id_equipo, id_empleado FROM detalle_equipo WHERE id_equipo = ?;");
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                DetalleEquipo detalleEquipo = parserDetalleEquipo();
                lista.add(detalleEquipo);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }
    @Override
    public ArrayList<DetalleEquipo> listar() {
        ArrayList<DetalleEquipo> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                DetalleEquipo detalleEquipo = parserDetalleEquipo();
                lista.add(detalleEquipo);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }
    
    private DetalleEquipo parserDetalleEquipo() throws SQLException {
        DetalleEquipo detalleEquipo = new DetalleEquipo();
        detalleEquipo.setIdDetalleEquipo(resultados.getInt("id_detalle_equipo"));
        detalleEquipo.setIdEquipo(resultados.getInt("id_equipo"));
        detalleEquipo.setIdEmpleado(resultados.getInt("id_empleado"));
        return detalleEquipo;
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
