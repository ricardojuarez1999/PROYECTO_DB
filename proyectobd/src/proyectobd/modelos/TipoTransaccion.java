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
public class TipoTransaccion {
    private int idTipoTransaccion; 
    private String tipo;

    public TipoTransaccion() {
    }

    public TipoTransaccion(int idTipoTransaccion, String tipo) {
        this.idTipoTransaccion = idTipoTransaccion;
        this.tipo = tipo;
    }

    public int getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(int idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
