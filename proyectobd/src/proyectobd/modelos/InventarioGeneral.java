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
public class InventarioGeneral {
    private int idInventarioGeneral; 
    private int cantidad;
    private int idProducto;

    public InventarioGeneral() {
    }

    public InventarioGeneral(int idInventarioGeneral, int cantidad, int idProducto) {
        this.idInventarioGeneral = idInventarioGeneral;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
    }

    public int getIdInventarioGeneral() {
        return idInventarioGeneral;
    }

    public void setIdInventarioGeneral(int idInventarioGeneral) {
        this.idInventarioGeneral = idInventarioGeneral;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
}
