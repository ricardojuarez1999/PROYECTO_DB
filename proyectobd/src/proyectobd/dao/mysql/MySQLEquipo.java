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
import proyectobd.dao.intefaces.EquipoDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Equipo;

/**
 *
 * @author israe
 */
public class MySQLEquipo implements EquipoDAO {

    private final String INSERTAR = "INSERT INTO equipo VALUES (default,upper(?),?);";
    private final String MODIFICAR = "UPDATE equipo SET nombre = upper(?), id_empleado = ? WHERE id_equipo = ?;";
    private final String ELIMINAR = "DELETE FROM equipo WHERE id_equipo = ?;";
    private final String OBTENERPORID = "SELECT id_equipo, nombre, id_empleado FROM equipo WHERE id_equipo = ?;";
    private final String OBTENER = "SELECT id_equipo, nombre, id_empleado FROM equipo; ";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Equipo o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getNombre());
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
    public void modificar(Equipo o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getNombre());
            sentencia.setInt(2, o.getIdEmpleado());
            sentencia.setInt(3, o.getIdEquipo());

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
    public Equipo obtenerId(Integer k) {
        Equipo equipo = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                equipo = parserEquipo();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return equipo;
    }

    @Override
    public ArrayList<Equipo> listar() {
        ArrayList<Equipo> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Equipo equipo = parserEquipo();
                lista.add(equipo);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private Equipo parserEquipo() throws SQLException {
        Equipo equipo = new Equipo();
        equipo.setIdEquipo(resultados.getInt("id_equipo"));
        equipo.setNombre(resultados.getString("nombre"));
        equipo.setIdEmpleado(resultados.getInt("id_empleado"));
        return equipo;
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
