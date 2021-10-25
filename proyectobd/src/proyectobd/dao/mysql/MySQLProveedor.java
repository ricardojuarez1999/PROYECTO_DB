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
import proyectobd.dao.intefaces.ProveedorDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Proveedor;

/**
 *
 * @author israe
 */
public class MySQLProveedor implements ProveedorDAO {

    private final String INSERTAR = "INSERT INTO proveedor VALUES (default, upper(?),upper(?)); ";
    private final String MODIFICAR = "UPDATE proveedor SET nombre = upper(?), direccion = upper(?) WHERE id_proveedor = ?;";
    private final String ELIMINAR = "DELETE FROM proveedor WHERE id_proveedor = ?; ";
    private final String OBTENERPORID = "SELECT id_proveedor, nombre, direccion FROM proveedor WHERE id_proveedor = ?; ";
    private final String OBTENER = "SELECT id_proveedor, nombre, direccion FROM proveedor; ";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Proveedor o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getNombre());
            sentencia.setString(2, o.getDireccion());

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
    public void modificar(Proveedor o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getNombre());
            sentencia.setString(2, o.getDireccion());
            sentencia.setInt(3, o.getIdProveedor());

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
    public Proveedor obtenerId(Integer k) {
        Proveedor proveedor = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                proveedor = parserProveedor();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return proveedor;
    }

    @Override
    public ArrayList<Proveedor> listar() {
        ArrayList<Proveedor> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Proveedor proveedor = parserProveedor();
                lista.add(proveedor);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private Proveedor parserProveedor() throws SQLException {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(resultados.getInt("id_proveedor"));
        proveedor.setNombre(resultados.getString("nombre"));
        proveedor.setDireccion(resultados.getString("direccion"));
        return proveedor;
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
