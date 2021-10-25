/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.controladores;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import proyectobd.dao.mysql.MySQLConsultas;
import proyectobd.dao.mysql.MySQLEmpleado;
import proyectobd.dao.mysql.MySQLPersona;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Empleado;
import proyectobd.modelos.Persona;

/**
 *
 * @author israe
 */
public class ControladorEmpleado {
    MySQLEmpleado conexion;
    
    public ControladorEmpleado(){
        conexion = new MySQLEmpleado(); 
    }
    public int obtenerIdSiguiente(){
        int id  = 0; 
        try{
             MySQLConsultas consulta = new MySQLConsultas(); 
            
             id = consulta.obtenerUltimoID("empleado");
             
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return id; 
    }
    
    public ArrayList<Empleado> listarEmpleados(){
        ArrayList<Empleado> nuevo = null;
        try{
            nuevo = conexion.listar();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nuevo; 
    }
    public Empleado buscarEmpleado(int id){
        Empleado nuevo = null;
        try{
            nuevo = conexion.obtenerId(id);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nuevo; 
    }
    public boolean insertarEmpleado(Empleado empleado){
        try{
            conexion.insertar(empleado);
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return true;
    }
    public boolean modificarEmpleado(Empleado empleado){
        try{
            
            if(empleado.getContrasenia().equals("")){
                conexion.modificar(empleado,"");
            }else{
                conexion.modificar(empleado);
            }
            
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return true;
    }
    public Empleado parseEmpleado(int id,String contrasenia,Date fechaInicio,int idPersona, int idPuesto, int idHorario) {
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(id);
        empleado.setContrasenia(contrasenia);
        empleado.setIdPersona(idPersona);
        empleado.setIdPuesto(idPuesto);
        empleado.setIdHorario(idHorario);
        empleado.setFecha_inico(fechaInicio);
        return empleado;
    }
    
    public Empleado getSesion(int k,String contra){
        try{
            return conexion.obtenerLogin(k, contra);
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }
}
