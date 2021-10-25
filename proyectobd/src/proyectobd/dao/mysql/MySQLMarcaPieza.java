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
import proyectobd.dao.intefaces.MarcaPiezaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.MarcaPieza;

/**
 *
 * @author israe
 */
public class MySQLMarcaPieza implements MarcaPiezaDAO {

    private final String INSERTAR = "INSERT INTO marca_pieza VALUES(default,upper(?));";
    private final String MODIFICAR = "UPDATE marca_pieza SET marca = upper(?) WHERE id_marca_pieza  = ?;";
    private final String ELIMINAR = "DELETE FROM marca_pieza WHERE id_marca_pieza = ?;";
    private final String OBTENERPORID = "SELECT id_marca_pieza, marca FROM marca_pieza WHERE id_marca_pieza = ?;";
    private final String OBTENER = "SELECT id_marca_pieza, marca FROM marca_pieza;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(MarcaPieza o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getMarca());

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
    public void modificar(MarcaPieza o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getMarca());
            sentencia.setInt(2, o.getIdMarcaPieza());

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
    public MarcaPieza obtenerId(Integer k) {
        MarcaPieza marcaPieza = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                marcaPieza = parserMarcaPieza();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return marcaPieza;
    }

    @Override
    public ArrayList<MarcaPieza> listar() {
        ArrayList<MarcaPieza> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                MarcaPieza marcaPieza = parserMarcaPieza();
                lista.add(marcaPieza);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private MarcaPieza parserMarcaPieza() throws SQLException {
        MarcaPieza marcaPieza = new MarcaPieza();
        marcaPieza.setIdMarcaPieza(resultados.getInt("id_marca_pieza"));
        marcaPieza.setMarca(resultados.getString("marca"));
        return marcaPieza;
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
