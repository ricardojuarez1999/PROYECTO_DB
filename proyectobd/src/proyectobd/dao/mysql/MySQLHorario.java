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
import proyectobd.dao.intefaces.HorarioDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Horario;

/**
 *
 * @author israe
 */
public class MySQLHorario implements HorarioDAO {

    private final String INSERTAR = "INSERT INTO horario VALUES (default,?,?);";
    private final String MODIFICAR = "UPDATE horario SET entrada = ?, salida = ? WHERE id_horario = ?; ";
    private final String ELIMINAR = "DELETE FROM horario WHERE id_horario = ?";
    private final String OBTENERPORID = "SELECT id_horario, entrada, salida FROM horario WHERE id_horario = ?;";
    private final String OBTENER = "SELECT id_horario, entrada, salida FROM horario;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Horario o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1,o.getEntrada());
            sentencia.setString(2,o.getSalida());

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
    public void modificar(Horario o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getEntrada());
            sentencia.setString(2, o.getSalida());
            sentencia.setInt(3, o.getIdHorario());

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
    public Horario obtenerId(Integer k) {
        Horario puesto = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                puesto = parserHorario();
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
    public ArrayList<Horario> listar() {
        ArrayList<Horario> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Horario puesto = parserHorario();
                lista.add(puesto);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

     private Horario parserHorario() throws SQLException {
        Horario horario = new Horario();
        horario.setIdHorario(resultados.getInt("id_horario"));
        horario.setEntrada(resultados.getString("entrada"));
        horario.setSalida(resultados.getString("salida"));
        return horario;
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
