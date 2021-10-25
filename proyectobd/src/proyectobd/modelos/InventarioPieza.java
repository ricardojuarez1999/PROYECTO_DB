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
public class InventarioPieza {
    private int idInventarioPieza; 
    private int cantidad;
    private int idPieza; 

    public InventarioPieza() {
    }

    public InventarioPieza(int idInventarioPieza, int cantidad, int idPieza) {
        this.idInventarioPieza = idInventarioPieza;
        this.cantidad = cantidad;
        this.idPieza = idPieza;
    }

    public int getIdInventarioPieza() {
        return idInventarioPieza;
    }

    public void setIdInventarioPieza(int idInventarioPieza) {
        this.idInventarioPieza = idInventarioPieza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(int idPieza) {
        this.idPieza = idPieza;
    }
    
}
