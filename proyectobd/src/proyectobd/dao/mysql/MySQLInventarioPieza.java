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
import proyectobd.dao.intefaces.InventarioPiezaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.InventarioPieza;

/**
 *
 * @author israe
 */
public class MySQLInventarioPieza implements InventarioPiezaDAO {

    private final String INSERTAR = "INSERT INTO inventario_pieza VALUES(default, ?,?); ";
    private final String MODIFICAR = "UPDATE inventario_pieza SET cantidad = ? , id_pieza = ? WHERE id_inventario_pieza = ?;";
    private final String ELIMINAR = "DELETE FROM inventario_pieza WHERE id_inventario_pieza = ?; ";
    private final String OBTENERPORID = "SELECT id_inventario_pieza, cantidad, id_pieza FROM inventario_pieza WHERE id_inventario_pieza = ?; ";
    private final String OBTENER = "SELECT id_inventario_pieza, cantidad, id_pieza FROM inventario_pieza; ";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(InventarioPieza o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getCantidad());
            sentencia.setInt(2, o.getIdPieza());

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
    public void modificar(InventarioPieza o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getCantidad());
            sentencia.setInt(2, o.getIdPieza());
            sentencia.setInt(3, o.getIdInventarioPieza());

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
    public InventarioPieza obtenerId(Integer k) {
        InventarioPieza inventarioPieza = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                inventarioPieza = parserInventarioPieza();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return inventarioPieza;
    }

    @Override
    public ArrayList<InventarioPieza> listar() {
        ArrayList<InventarioPieza> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                InventarioPieza inventarioPieza = parserInventarioPieza();
                lista.add(inventarioPieza);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private InventarioPieza parserInventarioPieza() throws SQLException {
        InventarioPieza inventarioPieza = new InventarioPieza();
        inventarioPieza.setIdInventarioPieza(resultados.getInt("id_inventario_pieza"));
        inventarioPieza.setCantidad(resultados.getInt("cantidad"));
        inventarioPieza.setIdPieza(resultados.getInt("id_pieza"));
        return inventarioPieza;
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
