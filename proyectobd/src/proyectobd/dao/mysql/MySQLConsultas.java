/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.ConsultaInnerEmpleado;

/**
 *
 * @author israe
 */
public class MySQLConsultas {
    Connection conexion;
    PreparedStatement sentencia; 
    ResultSet resultados;
    
    public int obtenerUltimoID(String tabla){
        
      //  final String CONSULTA = "SELECT max(id_"+tabla+")+1 FROM "+tabla+";";
        final String CONSULTA = "SELECT count(id_"+tabla+")+1 FROM "+tabla+";";
        //final String CONSULTA = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'proyectobd1' AND TABLE_NAME = '"+tabla+"';";
        int valor = 0; 
        try {
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement(CONSULTA);
            resultados = sentencia.executeQuery();
            if(resultados.next()){
                valor = resultados.getInt(1);
            }
        } catch (SQLException e) {
            throw new Excepcion(e.getMessage());
        }finally{
            cerrarConexiones();
        }
        return valor;
    }
    
    public ArrayList<ConsultaInnerEmpleado> obtenerInfoEmpleados(){
        ArrayList<ConsultaInnerEmpleado>lista = new ArrayList(); 
        try{
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT empleado.id_empleado, persona.dpi,persona.nombre,persona.apellido, persona.direccion,persona.nit, empleado.fecha_inicio,empleado.fecha_fin, puesto.nombre,puesto.sueldo, horario.entrada,horario.salida FROM empleado INNER JOIN persona ON empleado.id_persona = persona.id_persona INNER JOIN puesto ON empleado.id_puesto = puesto.id_puesto INNER JOIN horario ON empleado.id_horario = horario.id_horario;");
            resultados = sentencia.executeQuery();
            while(resultados.next()){
                ConsultaInnerEmpleado aux = new ConsultaInnerEmpleado();
                aux.setIdEmpleado(resultados.getInt(1));
                aux.setDpi(resultados.getString(2));
                aux.setNombre(resultados.getString(3));
                aux.setApellido(resultados.getString(4));
                aux.setDireccion(resultados.getString(5));
                aux.setNit(resultados.getString(6));
                aux.setFechaInicio(new Date(resultados.getTimestamp(7).getTime()));
                
                if(resultados.getTimestamp(8) != null){
                    aux.setFechaFin(new Date(resultados.getTimestamp(8).getTime()));
                }
                aux.setPuesto(resultados.getString(9));
                aux.setSueldo(resultados.getFloat(10));
                aux.setHoraEntrada(resultados.getString(11));
                aux.setHoraSalida(resultados.getString(12));
                
                lista.add(aux);
            }
        }catch(SQLException e){
            throw new Excepcion(e.getMessage());
        }finally{
            cerrarConexiones();
        }
        
        return lista; 
    }
    
    public ConsultaInnerEmpleado obtenerInfoEmpleadoPorID(Integer k){
        ConsultaInnerEmpleado aux = null; 
        try{
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT empleado.id_empleado, persona.dpi,persona.nombre,persona.apellido, persona.direccion,persona.nit, empleado.fecha_inicio,empleado.fecha_fin, puesto.nombre,puesto.sueldo, horario.entrada,horario.salida FROM empleado INNER JOIN persona ON empleado.id_persona = persona.id_persona INNER JOIN puesto ON empleado.id_puesto = puesto.id_puesto INNER JOIN horario ON empleado.id_horario = horario.id_horario WHERE empleado.id_empleado = ?;");
            sentencia.setInt(1, k);
            resultados = sentencia.executeQuery();
            while(resultados.next()){
                aux = new ConsultaInnerEmpleado();
                aux.setIdEmpleado(resultados.getInt(1));
                aux.setDpi(resultados.getString(2));
                aux.setNombre(resultados.getString(3));
                aux.setApellido(resultados.getString(4));
                aux.setDireccion(resultados.getString(5));
                aux.setNit(resultados.getString(6));
                aux.setFechaInicio(new Date(resultados.getTimestamp(7).getTime()));
                
                if(resultados.getTimestamp(8) != null){
                    aux.setFechaFin(new Date(resultados.getTimestamp(8).getTime()));
                }
                aux.setPuesto(resultados.getString(9));
                aux.setSueldo(resultados.getFloat(10));
                aux.setHoraEntrada(resultados.getString(11));
                aux.setHoraSalida(resultados.getString(12));
            }
        }catch(SQLException e){
            throw new Excepcion(e.getMessage());
        }finally{
            cerrarConexiones();
        }
        
        return aux; 
    }
    
    
    public ArrayList<ConsultaInnerEmpleado> obtenerInfoEmpleadosPorPuesto(String puesto){
        ArrayList<ConsultaInnerEmpleado>lista = new ArrayList(); 
        try{
            conexion = new MySQLConexion().conectar();
            sentencia = conexion.prepareStatement("SELECT empleado.id_empleado, persona.dpi,persona.nombre,persona.apellido, persona.direccion,persona.nit, empleado.fecha_inicio,empleado.fecha_fin, puesto.nombre,puesto.sueldo, horario.entrada,horario.salida FROM empleado INNER JOIN persona ON empleado.id_persona = persona.id_persona INNER JOIN puesto ON empleado.id_puesto = puesto.id_puesto INNER JOIN horario ON empleado.id_horario = horario.id_horario WHERE puesto.nombre = ?;");
            sentencia.setString(1, puesto);
            resultados = sentencia.executeQuery();
            
            while(resultados.next()){
                ConsultaInnerEmpleado aux = new ConsultaInnerEmpleado();
                aux.setIdEmpleado(resultados.getInt(1));
                aux.setDpi(resultados.getString(2));
                aux.setNombre(resultados.getString(3));
                aux.setApellido(resultados.getString(4));
                aux.setDireccion(resultados.getString(5));
                aux.setNit(resultados.getString(6));
                aux.setFechaInicio(new Date(resultados.getTimestamp(7).getTime()));
                
                if(resultados.getTimestamp(8) != null){
                    aux.setFechaFin(new Date(resultados.getTimestamp(8).getTime()));
                }
                aux.setPuesto(resultados.getString(9));
                aux.setSueldo(resultados.getFloat(10));
                aux.setHoraEntrada(resultados.getString(11));
                aux.setHoraSalida(resultados.getString(12));
                
                lista.add(aux);
            }
        }catch(SQLException e){
            throw new Excepcion(e.getMessage());
        }finally{
            cerrarConexiones();
        }
        
        return lista; 
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
