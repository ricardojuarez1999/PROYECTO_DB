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
public class DetalleFacturaProducto {
    private int idDetalleFacturaProducto; 
    private int cantidad; 
    private int idFacturaProducto;
    private int idProducto; 

    public DetalleFacturaProducto() {
    }

    public DetalleFacturaProducto(int idDetalleFacturaProducto, int cantidad, int idFacturaProducto, int idProducto) {
        this.idDetalleFacturaProducto = idDetalleFacturaProducto;
        this.cantidad = cantidad;
        this.idFacturaProducto = idFacturaProducto;
        this.idProducto = idProducto;
    }

    public int getIdDetalleFacturaProducto() {
        return idDetalleFacturaProducto;
    }

    public void setIdDetalleFacturaProducto(int idDetalleFacturaProducto) {
        this.idDetalleFacturaProducto = idDetalleFacturaProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdFacturaProducto() {
        return idFacturaProducto;
    }

    public void setIdFacturaProducto(int idFacturaProducto) {
        this.idFacturaProducto = idFacturaProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
        
}
