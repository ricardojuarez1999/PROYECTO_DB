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
import proyectobd.dao.intefaces.EstadoPiezaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.EstadoPieza;

/**
 *
 * @author israe
 */
public class MySQLEstadoPieza implements EstadoPiezaDAO {

    private final String INSERTAR = "INSERT INTO estado_pieza VALUES(default, upper(?));";
    private final String MODIFICAR = "UPDATE estado_pieza SET estado = upper(?) WHERE id_estado_pieza = ?;";
    private final String ELIMINAR = "DELETE FROM estado_pieza WHERE id_estado_pieza = ?; ";
    private final String OBTENERPORID = "SELECT id_estado_pieza, estado FROM  estado_pieza WHERE id_estado_pieza = ?; ";
    private final String OBTENER = "SELECT id_estado_pieza, estado FROM  estado_pieza;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(EstadoPieza o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getEstado());

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
    public void modificar(EstadoPieza o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getEstado());
            sentencia.setInt(2, o.getIdEstadoPieza());

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
    public EstadoPieza obtenerId(Integer k) {
        EstadoPieza estadoPieza = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                estadoPieza = parserEstadoPieza();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return estadoPieza;
    }

    @Override
    public ArrayList<EstadoPieza> listar() {
        ArrayList<EstadoPieza> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                EstadoPieza estadoPieza = parserEstadoPieza();
                lista.add(estadoPieza);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private EstadoPieza parserEstadoPieza() throws SQLException {
        EstadoPieza estadoPieza = new EstadoPieza();
        estadoPieza.setIdEstadoPieza(resultados.getInt("id_estado_pieza"));
        estadoPieza.setEstado(resultados.getString("estado"));
        return estadoPieza;
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
