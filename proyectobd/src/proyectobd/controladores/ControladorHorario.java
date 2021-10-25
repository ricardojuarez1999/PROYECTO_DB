/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
  -> VALIDAR EL FORMULARIO 
*/
package proyectobd.controladores;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import proyectobd.dao.mysql.MySQLHorario;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Horario;

/**
 *
 * @author israe
 */
public class ControladorHorario {
    MySQLHorario conexion;
    public ControladorHorario(){
        conexion = new MySQLHorario();
    }
    public Horario obtenerHorario(int k){
        Horario nuevo = null;
        try{
            nuevo = conexion.obtenerId(k);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nuevo; 
    }
    public ArrayList<Horario> obtenerHorarios(){
        ArrayList<Horario> lista = null; 
        try{
            conexion = new MySQLHorario();
            lista = conexion.listar();
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return lista; 
    }
    public int buscarIdHorario(String horario){
        String[] partes = horario.split("a"); 
        int id = 0;
        try{
            ArrayList<Horario> lista = this.obtenerHorarios();
            for(int i= 0; i<lista.size(); i++){
                if(lista.get(i).getEntrada().equals(partes[0].trim()) && lista.get(i).getSalida().equals(partes[1].trim())){
                    id =  lista.get(i).getIdHorario();
                    break;
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return id; 
    }
    
    public Horario buscarPorHorarios(Horario horario){
        Horario aux = null;
        try{
            ArrayList<Horario> lista = this.obtenerHorarios();
            for(int i= 0; i<lista.size(); i++){
                if(lista.get(i).getEntrada().equals(horario.getEntrada()) && lista.get(i).getSalida().equals(horario.getSalida())){
                    aux =  lista.get(i);
                    break;
                }
            }
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return aux;
    }  
    public void insertarHorario(String fechaEntrada, String fechaSalida){
        try{
            conexion= new MySQLHorario(); 
            
            conexion.insertar(new Horario(0,fechaEntrada,fechaSalida));
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente");
        }catch(Excepcion e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public String parseHora(String hora,String min, String seg){
        if(hora.length() == 1){
            hora = "0"+hora;
        }
        if(min.length() == 1){
            min = "0" + min;
        }
        if(seg.length() == 1){
            seg = "0" + seg;
        }
        String valor = hora+":"+min+":"+seg;   
        return valor;
    }       
}
