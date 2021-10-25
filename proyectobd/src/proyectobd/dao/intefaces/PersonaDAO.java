/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.dao.intefaces;

import proyectobd.modelos.Persona;

/**
 *
 * @author israe
 */
public interface PersonaDAO extends DAO <Integer, Persona>{  
    public Persona obtenerDPI(String k);
}
