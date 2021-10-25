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
public class DetalleFacturaVenta {
    private int idDetalleFacturaVenta;
    private int cantidad;
    private int idFacturaVenta;
    private int idPieza; 

    public DetalleFacturaVenta() {
    }

    public DetalleFacturaVenta(int idDetalleFacturaVenta, int cantidad, int idFacturaVenta, int idPieza) {
        this.idDetalleFacturaVenta = idDetalleFacturaVenta;
        this.cantidad = cantidad;
        this.idFacturaVenta = idFacturaVenta;
        this.idPieza = idPieza;
    }

    public int getIdDetalleFacturaVenta() {
        return idDetalleFacturaVenta;
    }

    public void setIdDetalleFacturaVenta(int idDetalleFacturaVenta) {
        this.idDetalleFacturaVenta = idDetalleFacturaVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdFacturaVenta() {
        return idFacturaVenta;
    }

    public void setIdFacturaVenta(int idFacturaVenta) {
        this.idFacturaVenta = idFacturaVenta;
    }

    public int getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(int idPieza) {
        this.idPieza = idPieza;
    }

  
    
            
}
