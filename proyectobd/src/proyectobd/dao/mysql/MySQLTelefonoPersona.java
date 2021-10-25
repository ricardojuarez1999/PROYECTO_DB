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
import proyectobd.dao.intefaces.TelefonoPersonaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.TelefonoPersona;

/**
 *
 * @author israe
 */
public class MySQLTelefonoPersona implements TelefonoPersonaDAO {

    private final String INSERTAR = "INSERT INTO telefono_persona VALUES (default, ?,?);";
    private final String MODIFICAR = "UPDATE telefono_persona SET numero = ?, id_persona = ? WHERE id_telefono_persona = ?; ";
    private final String ELIMINAR = "DELETE FROM telefono_persona WHERE id_telefono_persona = ?;";
    private final String OBTENERPORID = "SELECT id_telefono_persona, numero,id_persona FROM telefono_persona WHERE id_telefono_persona =?; ";
    private final String OBTENER = "SELECT id_telefono_persona, numero,id_persona FROM telefono_persona ;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(TelefonoPersona o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getNumero());
            sentencia.setInt(2, o.getIdPersona());

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
    public void modificar(TelefonoPersona o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setInt(1, o.getNumero());
            sentencia.setInt(2, o.getIdPersona());
            sentencia.setInt(3, o.getIdTelefonoPersona());

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
    public TelefonoPersona obtenerId(Integer k) {
        TelefonoPersona telefonoPersona = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                telefonoPersona = parserTelefonoPersona();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return telefonoPersona;
    }

    @Override
    public ArrayList<TelefonoPersona> listar() {
        ArrayList<TelefonoPersona> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                TelefonoPersona telefonoPersona = parserTelefonoPersona();
                lista.add(telefonoPersona);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private TelefonoPersona parserTelefonoPersona() throws SQLException {
        TelefonoPersona telefonoPersona = new TelefonoPersona();
        telefonoPersona.setIdTelefonoPersona(resultados.getInt("id_telefono_persona"));
        telefonoPersona.setNumero(resultados.getInt("numero"));
        telefonoPersona.setIdPersona(resultados.getInt("id_persona"));
        return telefonoPersona;
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
