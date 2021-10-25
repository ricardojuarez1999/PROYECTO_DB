/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import proyectobd.dao.mysql.MySQLPuesto;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Puesto;

/**
 *
 * @author israe
 */
public class ControladorPuesto {

    private MySQLPuesto puesto;

    public ControladorPuesto() {
        puesto = new MySQLPuesto();
    }

    public ArrayList<Puesto> obtenerPuestos() {
        ArrayList<Puesto> lista = null;
        try {
            lista = puesto.listar();
            
            if(lista == null){
                throw new Excepcion("No existe ningun puesto consultalo al departamento de informatica");
            }
            
        } catch (Excepcion e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return lista; 
    }
    public Puesto buscarPuesto(int k){
        Puesto aux = null;
        try{
            aux = puesto.obtenerId(k);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return aux; 
    }
    public int buscarIdPuesto(String puesto){
        ArrayList<Puesto> lista; 
        int id = 0; 
        try{
            lista = this.obtenerPuestos();
            for(int i=0; i<lista.size(); i++){
                if(lista.get(i).getNombre().equals(puesto)){
                    id = lista.get(i).getIdPuesto();
                    break; 
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return id;
    }
}
