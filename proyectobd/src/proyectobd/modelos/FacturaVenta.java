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
public class FacturaVenta {
    private int idFacturaVenta; 
    private int idPersona; 
    private int idFacturaGeneral;

    public FacturaVenta() {
    }

    public FacturaVenta(int idFacturaVenta, int idPersona, int idFacturaGeneral) {
        this.idFacturaVenta = idFacturaVenta;
        this.idPersona = idPersona;
        this.idFacturaGeneral = idFacturaGeneral;
    }

    public int getIdFacturaVenta() {
        return idFacturaVenta;
    }

    public void setIdFacturaVenta(int idFacturaVenta) {
        this.idFacturaVenta = idFacturaVenta;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdFacturaGeneral() {
        return idFacturaGeneral;
    }

    public void setIdFacturaGeneral(int idFacturaGeneral) {
        this.idFacturaGeneral = idFacturaGeneral;
    }   
}
