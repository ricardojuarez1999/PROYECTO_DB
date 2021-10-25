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
import proyectobd.dao.intefaces.PuestoDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Puesto;

/**
 *
 * @author israe
 */
public class MySQLPuesto implements PuestoDAO {

    private final String INSERTAR = "INSERT INTO puesto VALUES(default, upper(?),?);";
    private final String MODIFICAR = "UPDATE puesto SET nombre = upper(?), sueldo = ? WHERE id_puesto = ?;";
    private final String ELIMINAR = "DELETE FROM puesto WHERE id_puesto = ?";
    private final String OBTENERPORID = "SELECT id_puesto,nombre,sueldo FROM puesto WHERE id_puesto = ?;";
    private final String OBTENER = "SELECT id_puesto,nombre,sueldo FROM puesto;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Puesto o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getNombre());
            sentencia.setFloat(2, o.getSueldo());

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
    public void modificar(Puesto o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getNombre());
            sentencia.setFloat(2, o.getSueldo());
            sentencia.setInt(3, o.getIdPuesto());

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
    public Puesto obtenerId(Integer k) {
        Puesto puesto = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                puesto = parserPuesto();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return puesto;
    }

    @Override
    public ArrayList<Puesto> listar() {
        ArrayList<Puesto> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Puesto puesto = parserPuesto();
                lista.add(puesto);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private Puesto parserPuesto() throws SQLException {
        Puesto puesto = new Puesto();
        puesto.setIdPuesto(resultados.getInt("id_puesto"));
        puesto.setNombre(resultados.getString("nombre"));
        puesto.setSueldo(resultados.getFloat("sueldo"));
        return puesto;
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
