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
import proyectobd.dao.intefaces.EmpresaDAO;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Empresa;

/**
 *
 * @author israe
 */
public class MySQLEmpresa implements EmpresaDAO {

    private final String INSERTAR = "INSERT INTO empresa VALUES(default, upper(?), upper(?), upper(?));";
    private final String MODIFICAR = "UPDATE empresa SET nombre = upper(?), direccion = upper(?), nit = (?)  WHERE id_empresa = ?;";
    private final String ELIMINAR = "DELETE FROM empresa WHERE id_empresa = ?;";
    private final String OBTENERPORID = "SELECT id_empresa, nombre,direccion,nit FROM empresa WHERE id_empresa = ?;";
    private final String OBTENER = "SELECT id_empresa, nombre,direccion,nit FROM empresa;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;

    @Override
    public void insertar(Empresa o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getNombre());
            sentencia.setString(2, o.getDireccion());
            sentencia.setString(3, o.getNit());

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
    public void modificar(Empresa o) {
        try {
            conexion = new MySQLConexion().conectar();

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getNombre());
            sentencia.setString(2, o.getDireccion());
            sentencia.setString(3, o.getNit());
            sentencia.setInt(4, o.getIdEmpresa());

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
    public Empresa obtenerId(Integer k) {
        Empresa empresa = null;
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                empresa = parserEmpresa();
            } else {
                throw new Excepcion("No se encontro el registro");
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return empresa;
    }

    @Override
    public ArrayList<Empresa> listar() {
        ArrayList<Empresa> lista = new ArrayList();
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Empresa empresa = parserEmpresa();
                lista.add(empresa);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private Empresa parserEmpresa() throws SQLException {
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(resultados.getInt("id_empresa"));
        empresa.setNombre(resultados.getString("nombre"));
        empresa.setDireccion(resultados.getString("direccion"));
        empresa.setNit(resultados.getString("nit"));
        return empresa;
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
