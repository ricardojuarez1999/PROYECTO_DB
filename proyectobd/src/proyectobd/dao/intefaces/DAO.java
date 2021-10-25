/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.dao.intefaces;

import java.util.ArrayList;

/**
 *
 * @author israe
 */
public interface DAO <K, O>{
    public void insertar(O o); 
    public void modificar(O o); 
    public void eliminar(K k); 
    public O obtenerId(K k); 
    public ArrayList<O> listar();
}
