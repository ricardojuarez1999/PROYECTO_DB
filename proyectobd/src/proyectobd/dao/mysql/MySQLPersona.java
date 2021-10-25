/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import proyectobd.dao.intefaces.PersonaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Persona;

/**
 *
 * @author israe
 */
public class MySQLPersona implements PersonaDAO {

    private final String INSERTAR = "INSERT INTO persona VALUES(default,upper(?),upper(?),upper(?),upper(?),upper(?));";
    private final String MODIFICAR = "UPDATE persona SET dpi = ?, nombre = upper(?), apellido = upper(?), direccion = upper(?), nit = upper(?) WHERE persona.id_persona = ?;";
    private final String ELIMINAR = "DELETE FROM persona WHERE id_persona = ?";
    private final String OBTENERPORID = "SELECT id_persona,dpi,nombre,apellido,direccion,nit FROM persona WHERE id_persona = ?;";
    private final String OBTENERPORDPI = "SELECT id_persona,dpi,nombre,apellido,direccion,nit FROM persona WHERE dpi = ?;";
    private final String OBTENER = "SELECT id_persona,dpi,nombre,apellido,direccion,nit FROM persona";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Persona o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getDpi());
            sentencia.setString(2, o.getNombre());
            sentencia.setString(3, o.getApellido());
            sentencia.setString(4, o.getDireccion());
            sentencia.setString(5, o.getNit());

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
    public void modificar(Persona o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getDpi());
            sentencia.setString(2, o.getNombre());
            sentencia.setString(3, o.getApellido());
            sentencia.setString(4, o.getDireccion());
            sentencia.setString(5, o.getNit());
            sentencia.setInt(6, o.getIdPersona());

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
    public Persona obtenerId(Integer k) {
        Persona persona = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                persona = new Persona();
                persona.setIdPersona(resultados.getInt("id_persona"));
                persona.setDpi(resultados.getString("dpi"));
                persona.setNombre(resultados.getString("nombre"));
                persona.setApellido(resultados.getString("apellido"));
                persona.setDireccion(resultados.getString("direccion"));
                persona.setNit(resultados.getString("nit"));
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return persona;
    }

    @Override
    public Persona obtenerDPI(String k) {
        Persona persona = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORDPI);
            sentencia.setString(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                persona = parserPersona();
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return persona;
    }

    @Override
    public ArrayList<Persona> listar() {
        ArrayList<Persona> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Persona persona = parserPersona();
                lista.add(persona);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private Persona parserPersona() throws SQLException {
        Persona persona = new Persona();
        persona.setIdPersona(resultados.getInt("id_persona"));
        persona.setDpi(resultados.getString("dpi"));
        persona.setNombre(resultados.getString("nombre"));
        persona.setApellido(resultados.getString("apellido"));
        persona.setDireccion(resultados.getString("direccion"));
        persona.setNit(resultados.getString("nit"));
        return persona;
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
