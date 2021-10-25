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
import proyectobd.dao.intefaces.NombrePiezaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.NombrePieza;

/**
 *
 * @author israe
 */
public class MySQLNombrePieza implements NombrePiezaDAO {

    private final String INSERTAR = "INSERT INTO nombre_pieza VALUES (default, upper(?)); ";
    private final String MODIFICAR = "UPDATE nombre_pieza SET nombre = upper(?) WHERE id_nombre_pieza = ?; ";
    private final String ELIMINAR = "DELETE FROM nombre_pieza WHERE id_nombre_pieza = ?; ";
    private final String OBTENERPORID = "SELECT id_nombre_pieza, nombre FROM nombre_pieza WHERE id_nombre_pieza = ?; ";
    private final String OBTENER = "SELECT id_nombre_pieza, nombre FROM nombre_pieza;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(NombrePieza o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getNombre());

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
    public void modificar(NombrePieza o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getNombre());;
            sentencia.setInt(2, o.getIdNombrePieza());

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
    public NombrePieza obtenerId(Integer k) {
        NombrePieza nombrePieza = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                nombrePieza = parserNombrePieza();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return nombrePieza;
    }

    @Override
    public ArrayList<NombrePieza> listar() {
        ArrayList<NombrePieza> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                NombrePieza nombrePieza = parserNombrePieza();
                lista.add(nombrePieza);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private NombrePieza parserNombrePieza() throws SQLException {
        NombrePieza nombrePieza = new NombrePieza();
        nombrePieza.setIdNombrePieza(resultados.getInt("id_nombre_pieza"));
        nombrePieza.setNombre(resultados.getString("nombre"));
        return nombrePieza;
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
