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
import proyectobd.dao.intefaces.TipoInasistenciaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.TipoInasistencia;

/**
 *
 * @author israe
 */
public class MySQLTipoInasistencia implements TipoInasistenciaDAO {

    private final String INSERTAR = "INSERT INTO tipo_inasistencia VALUES (default, upper(?)); ";
    private final String MODIFICAR = "UPDATE tipo_inasistencia SET tipo = upper(?) WHERE id_tipo_inasistencia = ?; ";
    private final String ELIMINAR = "DELETE FROM tipo_inasistencia  WHERE id_tipo_inasistencia  = ?; ";
    private final String OBTENERPORID = "SELECT id_tipo_inasistencia , tipo FROM tipo_inasistencia  WHERE id_tipo_inasistencia  = ?; ";
    private final String OBTENER = "SELECT id_tipo_inasistencia , tipo FROM tipo_inasistencia; ";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(TipoInasistencia o) {
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
    public void modificar(TipoInasistencia o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getTipo());
            sentencia.setInt(2, o.getIdTipoInasistencia());

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
    public TipoInasistencia obtenerId(Integer k) {
        TipoInasistencia tipoInasistencia = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                tipoInasistencia = parserTipoInasistencia();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return tipoInasistencia;
    }

    @Override
    public ArrayList<TipoInasistencia> listar() {
        ArrayList<TipoInasistencia> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                TipoInasistencia tipoInasistencia = parserTipoInasistencia();
                lista.add(tipoInasistencia);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private TipoInasistencia parserTipoInasistencia() throws SQLException {
        TipoInasistencia tipoInasistencia = new TipoInasistencia();
        tipoInasistencia.setIdTipoInasistencia(resultados.getInt("id_tipo_inasistencia"));
        tipoInasistencia.setTipo(resultados.getString("tipo"));
        return tipoInasistencia;
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
