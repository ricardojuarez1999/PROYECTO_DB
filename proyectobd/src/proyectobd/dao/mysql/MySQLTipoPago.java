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
import proyectobd.dao.intefaces.TipoPagoDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.TipoPago;

/**
 *
 * @author israe
 */
public class MySQLTipoPago implements TipoPagoDAO {

    private final String INSERTAR = "INSERT INTO tipo_pago VALUES (default, upper(?)); ";
    private final String MODIFICAR = "UPDATE tipo_pago  SET tipo = upper(?) WHERE id_tipo_pago  = ?;";
    private final String ELIMINAR = "DELETE FROM tipo_pago WHERE id_tipo_pago = ?; ";
    private final String OBTENERPORID = "SELECT id_tipo_pago , tipo FROM tipo_pago WHERE id_tipo_pago = ?; ";
    private final String OBTENER = "SELECT id_tipo_pago , tipo FROM tipo_pago; ";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(TipoPago o) {
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
    public void modificar(TipoPago o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getTipo());
            sentencia.setInt(2, o.getIdTipoPago());

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
    public TipoPago obtenerId(Integer k) {
        TipoPago tipoPago = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                tipoPago = parserTipoPago();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return tipoPago;
    }

    @Override
    public ArrayList<TipoPago> listar() {
        ArrayList<TipoPago> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                TipoPago tipoPago = parserTipoPago();
                lista.add(tipoPago);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private TipoPago parserTipoPago() throws SQLException {
        TipoPago tipoPago = new TipoPago();
        tipoPago.setIdTipoPago(resultados.getInt("id_tipo_pago"));
        tipoPago.setTipo(resultados.getString("tipo"));
        return tipoPago;
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
