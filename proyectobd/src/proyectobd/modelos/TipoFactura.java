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
public class TipoFactura {
    private int idTipoFactura;
    private String tipo; 

    public TipoFactura() {
    }

    public TipoFactura(int idTipoFactura, String tipo) {
        this.idTipoFactura = idTipoFactura;
        this.tipo = tipo;
    }

    public int getIdTipoFactura() {
        return idTipoFactura;
    }

    public void setIdTipoFactura(int idTipoFactura) {
        this.idTipoFactura = idTipoFactura;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
