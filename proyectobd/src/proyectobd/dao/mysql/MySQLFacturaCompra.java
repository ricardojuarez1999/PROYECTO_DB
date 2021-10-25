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
import proyectobd.dao.intefaces.FacturaCompraDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.FacturaCompra;

/**
 *
 * @author israe
 */
public class MySQLFacturaCompra implements FacturaCompraDAO {

    private final String INSERTAR = "INSERT INTO factura_compra VALUES(default, ?,?);";
    private final String MODIFICAR = "UPDATE factura_compra SET id_proveedor = ?, id_factura_general = ? WHERE id_factura_compra = ?;";
    private final String ELIMINAR = "DELETE FROM factura_compra WHERE id_factura_compra = ?;";
    private final String OBTENERPORID = "SELECT id_factura_compra, id_proveedor, id_factura_general FROM factura_compra WHERE id_factura_compra = ?;";
    private final String OBTENER = "SELECT id_factura_compra, id_proveedor, id_factura_general FROM factura_compra;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(FacturaCompra o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getIdProveedor());
            sentencia.setInt(2, o.getIdFacturaGeneral());

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
    public void modificar(FacturaCompra o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getIdProveedor());
            sentencia.setInt(2, o.getIdFacturaGeneral());
            sentencia.setInt(3, o.getIdFacturaCompra());

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
    public FacturaCompra obtenerId(Integer k) {
        FacturaCompra facturaCompra = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                facturaCompra = parserFacturaCompra();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return facturaCompra;
    }

    @Override
    public ArrayList<FacturaCompra> listar() {
        ArrayList<FacturaCompra> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                FacturaCompra facturaCompra = parserFacturaCompra();
                lista.add(facturaCompra);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private FacturaCompra parserFacturaCompra() throws SQLException {
        FacturaCompra facturaCompra = new FacturaCompra();
        facturaCompra.setIdFacturaCompra(resultados.getInt("id_factura_compra"));
        facturaCompra.setIdProveedor(resultados.getInt("id_proveedor"));
        facturaCompra.setIdFacturaGeneral(resultados.getInt("id_factura_general"));
        return facturaCompra;
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
