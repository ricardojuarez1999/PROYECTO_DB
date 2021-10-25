/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.controladores;

import javax.swing.JOptionPane;
import proyectobd.dao.mysql.MySQLAsistencia;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Asistencia;

/**
 *
 * @author israe
 */
public class ControladorAsistencia {
    private MySQLAsistencia conexion;
    
    public ControladorAsistencia(){
        this.conexion = new MySQLAsistencia();
    }
    
    public boolean insertarAsistencia(Asistencia asistencia){
        try{
            conexion.insertar(asistencia);
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }
    
    public Asistencia obtenerPorFechayID(int k){
        Asistencia nuevo = null;
        try{
            nuevo = conexion.obtenerIdyFecha(k);
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return nuevo;
    }
}
