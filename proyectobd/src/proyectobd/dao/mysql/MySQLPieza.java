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
import proyectobd.dao.intefaces.PiezaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Pieza;

/**
 *
 * @author israe
 */
public class MySQLPieza implements PiezaDAO {

    private final String INSERTAR = "INSERT INTO pieza VALUES(default, ?,?,?,?,?,?,?,?);";
    private final String MODIFICAR = "UPDATE pieza SET numero_pieza = ?, precio_compra = ?, precio_venta = ?,id_proveedor = ?, id_nombre_pieza = ?, id_estado_pieza = ?, id_marca_pieza = ?, id_carro = ? WHERE id_pieza = ?;";
    private final String ELIMINAR = "DELETE FROM pieza WHERE id_pieza; ";
    private final String OBTENERPORID = "SELECT id_pieza, numero_pieza, precio_compra,precio_venta,id_proveedor,id_nombre_pieza,id_estado_pieza,id_marca_pieza,id_carro FROM pieza WHERE id_pieza = ?; ";
    private final String OBTENER = "SELECT id_pieza, numero_pieza, precio_compra,precio_venta,id_proveedor,id_nombre_pieza,id_estado_pieza,id_marca_pieza,id_carro FROM pieza;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Pieza o) {
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getNumeroPieza());
            sentencia.setFloat(2, o.getPrecio_compra());
            sentencia.setFloat(3, o.getPrecio_venta());
            sentencia.setInt(4, o.getIdProveedor());
            sentencia.setInt(5, o.getIdNombrePieza());
            sentencia.setInt(6, o.getIdEstadoPieza());
            sentencia.setInt(7, o.getIdMarcaPieza());
            sentencia.setInt(8, o.getIdCarro());

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
    public void modificar(Pieza o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getNumeroPieza());
            sentencia.setFloat(2, o.getPrecio_compra());
            sentencia.setFloat(3, o.getPrecio_venta());
            sentencia.setInt(4, o.getIdProveedor());
            sentencia.setInt(5, o.getIdNombrePieza());
            sentencia.setInt(6, o.getIdEstadoPieza());
            sentencia.setInt(7, o.getIdMarcaPieza());
            sentencia.setInt(8, o.getIdCarro());
            sentencia.setInt(9, o.getIdPieza());

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
    public Pieza obtenerId(Integer k) {
        Pieza pieza = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                pieza = parserPieza();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return pieza;
    }

    @Override
    public ArrayList<Pieza> listar() {
        ArrayList<Pieza> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Pieza pieza = parserPieza();
                lista.add(pieza);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private Pieza parserPieza() throws SQLException {
        Pieza pieza = new Pieza();
        pieza.setIdPieza(resultados.getInt("id_pieza"));
        pieza.setNumeroPieza(resultados.getInt("numero_pieza"));
        pieza.setPrecio_compra(resultados.getFloat("precio_compra"));
        pieza.setPrecio_venta(resultados.getFloat("precio_venta"));
        pieza.setIdProveedor(resultados.getInt("id_proveedor"));
        pieza.setIdNombrePieza(resultados.getInt("id_nombre_pieza"));
        pieza.setIdEstadoPieza(resultados.getInt("id_estado_pieza"));
        pieza.setIdMarcaPieza(resultados.getInt("id_marca_pieza"));
        pieza.setIdCarro(resultados.getInt("id_carro"));
        return pieza;
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
