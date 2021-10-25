/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.controladores;

import javax.swing.JOptionPane;
import proyectobd.dao.mysql.MySQLConsultas;
import proyectobd.dao.mysql.MySQLPersona;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Persona;

/**
 *
 * @author israe
 */
public class ControladorPersona {
    MySQLPersona conexion;
    
    public ControladorPersona(){
        conexion = new MySQLPersona();
    }
    public int obtenerIdSiguiente(){
        int id  = 0; 
        try{
             MySQLConsultas consulta = new MySQLConsultas(); 
             id = consulta.obtenerUltimoID("persona");
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return id; 
    }
    public boolean modificarPersona(Persona o){
        try{
            conexion.modificar(o);
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return true;
    }
    public boolean insertarPersona(Persona persona){
        try{
            conexion.insertar(persona);
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return true;
    }
    
    public Persona obtenerPorID(int id){
        Persona persona = null;
        try{
            conexion = new MySQLPersona(); 
            
            persona = conexion.obtenerId(id);
            
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return persona;
    }
    public Persona obtenerIDporDPI(String dpi){
        Persona persona = null;
        try{
            conexion = new MySQLPersona(); 
            
            persona = conexion.obtenerDPI(dpi);
            
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return persona;
    }
}
