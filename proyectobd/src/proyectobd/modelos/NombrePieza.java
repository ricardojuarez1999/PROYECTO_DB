/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.modelos;

/**
 *
 * @author israe
 */
public class NombrePieza {
    private int idNombrePieza; 
    private String nombre; 

    public NombrePieza() {
    }

    public NombrePieza(int idNombrePieza, String nombre) {
        this.idNombrePieza = idNombrePieza;
        this.nombre = nombre;
    }

    public int getIdNombrePieza() {
        return idNombrePieza;
    }

    public void setIdNombrePieza(int idNombrePieza) {
        this.idNombrePieza = idNombrePieza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
