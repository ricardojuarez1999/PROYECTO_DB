/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.controladores;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import proyectobd.dao.mysql.MySQLConsultas;
import proyectobd.dao.mysql.MySQLEquipo;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Equipo;

/**
 *
 * @author israe
 */
public class ControladorEquipo {
    MySQLEquipo conexion;

    public ControladorEquipo() {
        conexion = new  MySQLEquipo();
    }
    
    public int obtenerIdSiguiente(){
        int id  = 0; 
        try{
             MySQLConsultas consulta = new MySQLConsultas(); 
            
             id = consulta.obtenerUltimoID("equipo");
             
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return id; 
    }
    
    public ArrayList<Equipo> obtenerEquipos() {
        ArrayList<Equipo> lista = null;
        try {
            lista = conexion.listar();
            
            if(lista == null){
                throw new Excepcion("No existe ningun puesto consultalo al departamento de informatica");
            }
            
        } catch (Excepcion e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return lista; 
    }
    
    public boolean insertarEquipo(Equipo equipo){
        try{
            conexion.insertar(equipo);
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return true;
    }
    
    public Equipo buscarEquipoPorID(int k){
        Equipo aux = null;
        try{
            aux = conexion.obtenerId(k);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return aux; 
    }
    
    public boolean modificarEquipo(Equipo equipo){
        try{
            conexion.modificar(equipo);
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return true;
    }
}
