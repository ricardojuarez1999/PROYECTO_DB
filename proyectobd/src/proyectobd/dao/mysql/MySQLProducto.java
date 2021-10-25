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
import proyectobd.dao.intefaces.ProductoDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Producto;

/**
 *
 * @author israe
 */
public class MySQLProducto implements ProductoDAO {

    private final String INSERTAR = "INSERT INTO producto VALUES(default,upper(?),?,upper(?),?);";
    private final String MODIFICAR = "UPDATE producto SET nombre = upper(?), precio = ?, descripcion = upper(?), id_proveedor = ? WHERE id_producto = ?;";
    private final String ELIMINAR = "DELETE FROM producto WHERE id_producto = ?; ";
    private final String OBTENERPORID = "SELECT id_producto, nombre,precio,descripcion, id_proveedor FROM producto WHERE id_producto = ?;";
    private final String OBTENER = "SELECT id_producto, nombre,precio,descripcion, id_proveedor FROM producto;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Producto o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getNombre());
            sentencia.setFloat(2, o.getPrecio());
            sentencia.setString(3, o.getDescripcion());
            sentencia.setInt(4, o.getIdProveedor());

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
    public void modificar(Producto o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getNombre());
            sentencia.setFloat(2, o.getPrecio());
            sentencia.setString(3, o.getDescripcion());
            sentencia.setInt(4, o.getIdProveedor());
            sentencia.setInt(5, o.getIdProducto());

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
    public Producto obtenerId(Integer k) {
        Producto producto = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                producto = parserProducto();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return producto;
    }

    @Override
    public ArrayList<Producto> listar() {
        ArrayList<Producto> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Producto producto = parserProducto();
                lista.add(producto);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private Producto parserProducto() throws SQLException {
        Producto producto = new Producto();
        producto.setIdProducto(resultados.getInt("id_producto"));
        producto.setNombre(resultados.getString("nombre"));
        producto.setPrecio(resultados.getFloat("precio"));
        producto.setDescripcion(resultados.getString("descripcion"));
        producto.setIdProveedor(resultados.getInt("id_proveedor"));
        return producto;
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
