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
import proyectobd.dao.intefaces.TipoTransaccionDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.TipoTransaccion;

/**
 *
 * @author israe
 */
public class MySQLTipoTransaccion implements TipoTransaccionDAO {

    private final String INSERTAR = "INSERT INTO tipo_transaccion VALUES (default, upper(?)); ";
    private final String MODIFICAR = "UPDATE tipo_transaccion SET tipo = upper(?) WHERE id_tipo_transaccion = ?; ";
    private final String ELIMINAR = "DELETE FROM tipo_transaccion WHERE id_tipo_transaccion = ?;";
    private final String OBTENERPORID = "SELECT id_tipo_transaccion , tipo FROM tipo_transaccion WHERE id_tipo_transaccion = ?; ";
    private final String OBTENER = "SELECT id_tipo_transaccion , tipo FROM tipo_transaccion; ";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(TipoTransaccion o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getTipo());

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
    public void modificar(TipoTransaccion o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getTipo());
            sentencia.setInt(2, o.getIdTipoTransaccion());

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
    public TipoTransaccion obtenerId(Integer k) {
        TipoTransaccion tipoTransaccion = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                tipoTransaccion = parserTipoTransaccion();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return tipoTransaccion;
    }

    @Override
    public ArrayList<TipoTransaccion> listar() {
        ArrayList<TipoTransaccion> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                TipoTransaccion tipoTransaccion = parserTipoTransaccion();
                lista.add(tipoTransaccion);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private TipoTransaccion parserTipoTransaccion() throws SQLException {
        TipoTransaccion tipoTransaccion = new TipoTransaccion();
        tipoTransaccion.setIdTipoTransaccion(resultados.getInt("id_tipo_transaccion"));
        tipoTransaccion.setTipo(resultados.getString("tipo"));
        return tipoTransaccion;
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
