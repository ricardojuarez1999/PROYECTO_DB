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
public class DetalleEquipo {
    private int idDetalleEquipo; 
    private int idEquipo; 
    private int idEmpleado; 

    public DetalleEquipo() {
    }

    public DetalleEquipo(int idDetalleEquipo, int idEquipo, int idEmpleado) {
        this.idDetalleEquipo = idDetalleEquipo;
        this.idEquipo = idEquipo;
        this.idEmpleado = idEmpleado;
    }

    public int getIdDetalleEquipo() {
        return idDetalleEquipo;
    }

    public void setIdDetalleEquipo(int idDetalleEquipo) {
        this.idDetalleEquipo = idDetalleEquipo;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    
}
