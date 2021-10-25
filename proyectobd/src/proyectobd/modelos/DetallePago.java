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
public class DetallePago {
    private int idDetallePago;
    private String detalle; 
    private float monto; 
    private int idPago; 

    public DetallePago() {
    }

    public DetallePago(int idDetallePago, String detalle, float monto, int idPago) {
        this.idDetallePago = idDetallePago;
        this.detalle = detalle;
        this.monto = monto;
        this.idPago = idPago;
    }

    public int getIdDetallePago() {
        return idDetallePago;
    }

    public void setIdDetallePago(int idDetallePago) {
        this.idDetallePago = idDetallePago;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }
    
    
}
