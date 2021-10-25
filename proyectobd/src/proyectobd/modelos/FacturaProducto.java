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
public class FacturaProducto {
    private int idFacturaProducto; 
    private int idProveedor; 
    private int idFacturaGeneral; 

    public FacturaProducto() {
    }

    public FacturaProducto(int idFacturaProducto, int idProveedor, int idFacturaGeneral) {
        this.idFacturaProducto = idFacturaProducto;
        this.idProveedor = idProveedor;
        this.idFacturaGeneral = idFacturaGeneral;
    }

    public int getIdFacturaProducto() {
        return idFacturaProducto;
    }

    public void setIdFacturaProducto(int idFacturaProducto) {
        this.idFacturaProducto = idFacturaProducto;
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
