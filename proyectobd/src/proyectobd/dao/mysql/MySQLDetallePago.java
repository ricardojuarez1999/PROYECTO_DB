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
import proyectobd.dao.intefaces.DetallePagoDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.DetallePago;

/**
 *
 * @author israe
 */
public class MySQLDetallePago implements DetallePagoDAO {

    private final String INSERTAR = "INSERT INTO detalle_pago VALUES (default, upper(?),?,?); ";
    private final String MODIFICAR = "UPDATE detalle_pago SET detalle = upper(?), monto = ?, id_pago = ? WHERE id_detalle_pago = ?; ";
    private final String ELIMINAR = "DELETE FROM detalle_pago WHERE id_detalle_pago = ?; ";
    private final String OBTENERPORID = "SELECT id_detalle_pago, detalle, monto,id_pago FROM detalle_pago WHERE id_detalle_pago = ?;";
    private final String OBTENER = "SELECT id_detalle_pago, detalle, monto,id_pago FROM detalle_pago;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(DetallePago o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getDetalle());
            sentencia.setFloat(2, o.getMonto());
            sentencia.setInt(3, o.getIdPago());

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
    public void modificar(DetallePago o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getDetalle());
            sentencia.setFloat(2, o.getMonto());
            sentencia.setInt(3, o.getIdPago());
            sentencia.setInt(4, o.getIdDetallePago());

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
    public DetallePago obtenerId(Integer k) {
        DetallePago detallePago = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                detallePago = parserDetallePago();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return detallePago;
    }

    @Override
    public ArrayList<DetallePago> listar() {
        ArrayList<DetallePago> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                DetallePago detallePago = parserDetallePago();
                lista.add(detallePago);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private DetallePago parserDetallePago() throws SQLException {
        DetallePago detallePago = new DetallePago();
        detallePago.setIdDetallePago(resultados.getInt("id_detalle_pago"));
        detallePago.setDetalle(resultados.getString("detalle"));
        detallePago.setMonto(resultados.getFloat("monto"));
        detallePago.setIdPago(resultados.getInt("id_pago"));
        return detallePago;
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
    
     public ArrayList<DetallePago> listarPorIDPago(Integer k) {
        ArrayList<DetallePago> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT * FROM detalle_pago WHERE id_pago = ?;");
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                DetallePago detallePago = parserDetallePago();
                lista.add(detallePago);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }
}
