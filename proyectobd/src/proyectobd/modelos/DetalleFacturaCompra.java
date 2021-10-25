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
public class DetalleFacturaCompra {
    private int idDetalleFacturaCompra; 
    private int cantidad; 
    private int idFacturaCompra; 
    private int idPieza; 

    public DetalleFacturaCompra() {
    }

    public DetalleFacturaCompra(int idDetalleFacturaCompra, int cantidad, int idFacturaCompra, int idPieza) {
        this.idDetalleFacturaCompra = idDetalleFacturaCompra;
        this.cantidad = cantidad;
        this.idFacturaCompra = idFacturaCompra;
        this.idPieza = idPieza;
    }

    public int getIdDetalleFacturaCompra() {
        return idDetalleFacturaCompra;
    }

    public void setIdDetalleFacturaCompra(int idDetalleFacturaCompra) {
        this.idDetalleFacturaCompra = idDetalleFacturaCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdFacturaCompra() {
        return idFacturaCompra;
    }

    public void setIdFacturaCompra(int idFacturaCompra) {
        this.idFacturaCompra = idFacturaCompra;
    }

    public int getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(int idPieza) {
        this.idPieza = idPieza;
    }

 
    
}
