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
import proyectobd.dao.intefaces.FacturaGeneralDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.FacturaGeneral;

/**
 *
 * @author israe
 */
public class MySQLFacturaGeneral implements FacturaGeneralDAO {

    private final String INSERTAR = "INSERT INTO factura_general VALUES (default, curdate(), ?,?,?,?,?);";
    private final String MODIFICAR = "UPDATE factura_general SET fecha = ?, total = ?, id_empleado = ?, id_empresa = ?, id_tipo_pago = ?, id_tipo_factura = ? WHERE id_factura_general = ?;";
    private final String ELIMINAR = "DELETE FROM factura_general WHERE id_factura_general = ?; ";
    private final String OBTENERPORID = "SELECT id_factura_general, fecha, total, id_empleado, id_empresa, id_tipo_pago, id_tipo_factura FROM factura_general WHERE id_factura_general = ?; ";
    private final String OBTENER = "SELECT id_factura_general, fecha, total, id_empleado, id_empresa, id_tipo_pago, id_tipo_factura FROM factura_general; ";
    private final String OBTENERFECHA = "SELECT id_factura_general, fecha, total, id_empleado, id_empresa, id_tipo_pago, id_tipo_factura FROM factura_general WHERE fecha BETWEEN ? AND ?"; 

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(FacturaGeneral o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setFloat(1, o.getTotal());
            sentencia.setInt(2, o.getIdEmpleado());
            sentencia.setInt(3, o.getIdEmpresa());
            sentencia.setInt(4, o.getIdTipoPago());
            sentencia.setInt(5, o.getIdTipoFactura());

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
    public void modificar(FacturaGeneral o) {
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);
            Timestamp fecha = null;
            if (o.getFecha() != null) {
                fecha = new Timestamp(o.getFecha().getTime());
            }

            sentencia.setTimestamp(1, fecha);
            sentencia.setFloat(2, o.getTotal());
            sentencia.setInt(3, o.getIdEmpleado());
            sentencia.setInt(4, o.getIdEmpresa());
            sentencia.setInt(5, o.getIdTipoPago());
            sentencia.setInt(6, o.getIdTipoFactura());
            sentencia.setInt(7, o.getIdFacturaGeneral());

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
    public FacturaGeneral obtenerId(Integer k) {
        FacturaGeneral facturaGeneral = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                facturaGeneral = parserFacturaGeneral();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return facturaGeneral;
    }

    @Override
    public ArrayList<FacturaGeneral> listar() {
        ArrayList<FacturaGeneral> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                FacturaGeneral facturaGeneral = parserFacturaGeneral();
                lista.add(facturaGeneral);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }
    

    public ArrayList<FacturaGeneral> getByDate(String initialDate, String finalDate) {
        ArrayList<FacturaGeneral> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERFECHA);
            sentencia.setString(1, initialDate);
            sentencia.setString(2, finalDate);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                FacturaGeneral facturaGeneral = parserFacturaGeneral();
                lista.add(facturaGeneral);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private FacturaGeneral parserFacturaGeneral() throws SQLException {
        FacturaGeneral facturaGeneral = new FacturaGeneral();
        facturaGeneral.setIdFacturaGeneral(resultados.getInt("id_factura_general"));
        facturaGeneral.setFecha(new Date(resultados.getTimestamp("fecha").getTime()));
        facturaGeneral.setTotal(resultados.getInt("total"));
        facturaGeneral.setIdEmpleado(resultados.getInt("id_empleado"));
        facturaGeneral.setIdEmpresa(resultados.getInt("id_empresa"));
        facturaGeneral.setIdTipoPago(resultados.getInt("id_tipo_pago"));
        facturaGeneral.setIdTipoFactura(resultados.getInt("id_tipo_factura"));
        return facturaGeneral;
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
    
    public int listarComiciones(Integer mes, Integer anio, Integer id) {
        int cantidad = 0;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT count(id_factura_general) as contador FROM factura_general WHERE month(fecha) = ? AND year(fecha) = ? AND id_tipo_pago = 1 AND id_empleado = ?;");
            sentencia.setInt(1, mes);
            sentencia.setInt(2, anio);
            sentencia.setInt(3, id);
            resultados = sentencia.executeQuery();
            if(resultados.next()) {
                cantidad = resultados.getInt("contador");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return cantidad;
    }
}
