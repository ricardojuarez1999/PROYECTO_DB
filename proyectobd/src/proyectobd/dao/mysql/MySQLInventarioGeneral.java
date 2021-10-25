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
import proyectobd.dao.intefaces.InventarioGeneralDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.InventarioGeneral;

/**
 *
 * @author israe
 */
public class MySQLInventarioGeneral implements InventarioGeneralDAO {

    private final String INSERTAR = "INSERT INTO inventario_general VALUES(default, ?,?); ";
    private final String MODIFICAR = "UPDATE inventario_general SET cantidad = ? , id_producto = ? WHERE id_inventario_general = ?;";
    private final String ELIMINAR = "DELETE FROM inventario_general WHERE id_inventario_general = ?; ";
    private final String OBTENERPORID = "SELECT id_inventario_general, cantidad, id_producto FROM inventario_general WHERE id_inventario_general = ?; ";
    private final String OBTENER = "SELECT id_inventario_general, cantidad, id_producto FROM inventario_general; ";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(InventarioGeneral o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getCantidad());
            sentencia.setInt(2, o.getIdProducto());

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
    public void modificar(InventarioGeneral o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getCantidad());
            sentencia.setInt(2, o.getIdProducto());
            sentencia.setInt(3, o.getIdInventarioGeneral());

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
    public InventarioGeneral obtenerId(Integer k) {
        InventarioGeneral inventarioGeneral= null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                inventarioGeneral = parserInventarioGeneral();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return inventarioGeneral;
    }

    @Override
    public ArrayList<InventarioGeneral> listar() {
        ArrayList<InventarioGeneral> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                InventarioGeneral inventarioGeneral = parserInventarioGeneral();
                lista.add(inventarioGeneral);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private InventarioGeneral parserInventarioGeneral() throws SQLException {
        InventarioGeneral inventarioGeneral = new InventarioGeneral();
        inventarioGeneral.setIdInventarioGeneral(resultados.getInt("id_inventario_general"));
        inventarioGeneral.setCantidad(resultados.getInt("cantidad"));
        inventarioGeneral.setIdProducto(resultados.getInt("id_producto"));
        return inventarioGeneral;
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
