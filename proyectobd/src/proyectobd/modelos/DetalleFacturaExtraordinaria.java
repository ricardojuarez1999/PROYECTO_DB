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
public class DetalleFacturaExtraordinaria {
    private int idDetalleFacturaExtraordinaria; 
    private int cantidad; 
    private String descripcion;
    private int idFacturaGeneral;

    public DetalleFacturaExtraordinaria() {
    }

    public DetalleFacturaExtraordinaria(int idDetalleFacturaExtraordinaria, int cantidad, String descripcion, int idFacturaGeneral) {
        this.idDetalleFacturaExtraordinaria = idDetalleFacturaExtraordinaria;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.idFacturaGeneral = idFacturaGeneral;
    }

    public int getIdDetalleFacturaExtraordinaria() {
        return idDetalleFacturaExtraordinaria;
    }

    public void setIdDetalleFacturaExtraordinaria(int idDetalleFacturaExtraordinaria) {
        this.idDetalleFacturaExtraordinaria = idDetalleFacturaExtraordinaria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdFacturaGeneral() {
        return idFacturaGeneral;
    }

    public void setIdFacturaGeneral(int idFacturaGeneral) {
        this.idFacturaGeneral = idFacturaGeneral;
    }
    
    
}
