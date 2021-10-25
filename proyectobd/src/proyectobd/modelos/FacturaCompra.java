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
public class FacturaCompra {
    private int idFacturaCompra; 
    private int idProveedor; 
    private int idFacturaGeneral; 

    public FacturaCompra() {
    }

    public FacturaCompra(int idFacturaCompra, int idProveedor, int idFacturaGeneral) {
        this.idFacturaCompra = idFacturaCompra;
        this.idProveedor = idProveedor;
        this.idFacturaGeneral = idFacturaGeneral;
    }

    public int getIdFacturaCompra() {
        return idFacturaCompra;
    }

    public void setIdFacturaCompra(int idFacturaCompra) {
        this.idFacturaCompra = idFacturaCompra;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdFacturaGeneral() {
        return idFacturaGeneral;
    }

    public void setIdFacturaGeneral(int idFacturaGeneral) {
        this.idFacturaGeneral = idFacturaGeneral;
    }
    
    
}
