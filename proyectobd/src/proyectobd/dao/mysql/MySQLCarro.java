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
import proyectobd.dao.intefaces.CarroDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Carro;

/**
 *
 * @author israe
 */
public class MySQLCarro implements CarroDAO {

    private final String INSERTAR = "INSERT INTO carro VALUES(default,upper(?),upper(?),upper(?));";
    private final String MODIFICAR = "UPDATE carro SET marca = upper(?), modelo = upper(?), linea = upper(?) WHERE id_carro = ?;";
    private final String ELIMINAR = "DELETE FROM carro WHERE id_carro = ?;";
    private final String OBTENERPORID = "SELECT id_carro,marca,modelo,linea FROM carro WHERE id_carro = ?;";
    private final String OBTENER = "SELECT id_carro,marca,modelo,linea FROM carro;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Carro o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getMarca());
            sentencia.setString(2, o.getModelo());
            sentencia.setString(3, o.getLinea());

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
    public void modificar(Carro o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getMarca());
            sentencia.setString(2, o.getModelo());
            sentencia.setString(3, o.getLinea());
            sentencia.setInt(4, o.getIdCarro());

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
    public Carro obtenerId(Integer k) {
        Carro carro = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                carro = parserCarro();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return carro;
    }

    @Override
    public ArrayList<Carro> listar() {
        ArrayList<Carro> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Carro carro = parserCarro();
                lista.add(carro);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }
    
     private Carro parserCarro() throws SQLException {
        Carro carro = new Carro();
        carro.setIdCarro(resultados.getInt("id_carro"));
        carro.setMarca(resultados.getString("marca"));
        carro.setModelo(resultados.getString("modelo"));
        carro.setLinea(resultados.getString("linea"));
        return carro;
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
