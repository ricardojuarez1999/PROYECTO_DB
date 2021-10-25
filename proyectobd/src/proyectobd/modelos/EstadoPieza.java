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
public class EstadoPieza {
    private int idEstadoPieza;
    private String estado; 

    public EstadoPieza() {
    }

    public EstadoPieza(int idEstadoPieza, String estado) {
        this.idEstadoPieza = idEstadoPieza;
        this.estado = estado;
    }

    public int getIdEstadoPieza() {
        return idEstadoPieza;
    }

    public void setIdEstadoPieza(int idEstadoPieza) {
        this.idEstadoPieza = idEstadoPieza;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
