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
import proyectobd.dao.intefaces.TipoFacturaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.TipoFactura;

/**
 *
 * @author israe
 */
public class MySQLTipoFactura implements TipoFacturaDAO {

    private final String INSERTAR = "INSERT INTO tipo_factura VALUES (default, upper(?)); ";
    private final String MODIFICAR = "UPDATE tipo_factura SET tipo = upper(?) WHERE id_tipo_factura = ?; ";
    private final String ELIMINAR = "DELETE FROM tipo_factura WHERE id_tipo_factura = ?; ";
    private final String OBTENERPORID = "SELECT id_tipo_factura, tipo FROM tipo_factura WHERE id_tipo_factura = ?; ";
    private final String OBTENER = "SELECT id_tipo_factura, tipo FROM tipo_factura; ";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(TipoFactura o) {
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
    public void modificar(TipoFactura o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getTipo());
            sentencia.setInt(2, o.getIdTipoFactura());
           

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
    public TipoFactura obtenerId(Integer k) {
        TipoFactura tipoFactura = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                tipoFactura = parserTipoFactura();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return tipoFactura;
    }

    @Override
    public ArrayList<TipoFactura> listar() {
        ArrayList<TipoFactura> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                TipoFactura tipoFactura = parserTipoFactura();
                lista.add(tipoFactura);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private TipoFactura parserTipoFactura() throws SQLException {
        TipoFactura tipoFactura = new TipoFactura();
        tipoFactura.setIdTipoFactura(resultados.getInt("id_tipo_factura"));
        tipoFactura.setTipo(resultados.getString("tipo"));
        return tipoFactura;
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
