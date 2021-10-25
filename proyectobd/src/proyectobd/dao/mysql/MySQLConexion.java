/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author israe
 */
public class MySQLConexion {
    public Connection conectar() throws SQLException {
        Connection conexion = null; 
        String basedatos = "proyectobd1"; 
        String usuario = "proyectobd"; 
        String contrasenia = "umg"; 
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+basedatos+"?autoReconnect=true&useSSL=false",usuario,contrasenia);
        }catch(SQLException e){
            throw e; 
        }
        return conexion;
    }
}
