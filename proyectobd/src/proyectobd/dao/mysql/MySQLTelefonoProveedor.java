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
import proyectobd.dao.intefaces.TelefonoProveedorDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.TelefonoProveedor;

/**
 *
 * @author israe
 */
public class MySQLTelefonoProveedor implements TelefonoProveedorDAO {

    private final String INSERTAR = "INSERT INTO telefono_proveedor VALUES (default, ?,?);";
    private final String MODIFICAR = "UPDATE telefono_proveedor SET numero = ?, id_proveedor = ? WHERE id_telefono_proveedor = ?; ";
    private final String ELIMINAR = "DELETE FROM telefono_proveedor WHERE id_telefono_proveedor = ?;";
    private final String OBTENERPORID = "SELECT id_telefono_proveedor, numero,id_proveedor FROM telefono_proveedor WHERE id_telefono_proveedor =?; ";
    private final String OBTENER = "SELECT id_telefono_proveedor, numero,id_proveedor FROM telefono_proveedor; ";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(TelefonoProveedor o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getNumero());
            sentencia.setInt(2, o.getIdProveedor());

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
    public void modificar(TelefonoProveedor o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getNumero());
            sentencia.setInt(2, o.getIdProveedor());
            sentencia.setInt(3, o.getIdTelefonoProveedor());

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
    public TelefonoProveedor obtenerId(Integer k) {
        TelefonoProveedor telefonoProveedor = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                telefonoProveedor = parserTelefonoProveedor();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return telefonoProveedor;
    }

    @Override
    public ArrayList<TelefonoProveedor> listar() {
        ArrayList<TelefonoProveedor> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                TelefonoProveedor telefonoProveedor = parserTelefonoProveedor();
                lista.add(telefonoProveedor);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private TelefonoProveedor parserTelefonoProveedor() throws SQLException {
        TelefonoProveedor telefonoProveedor = new TelefonoProveedor();
        telefonoProveedor.setIdTelefonoProveedor(resultados.getInt("id_telefono_proveedor"));
        telefonoProveedor.setNumero(resultados.getInt("numero"));
        telefonoProveedor.setIdProveedor(resultados.getInt("id_proveedor"));
        return telefonoProveedor;
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
