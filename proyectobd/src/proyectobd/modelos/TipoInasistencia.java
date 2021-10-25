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
public class TipoInasistencia {
    private int idTipoInasistencia; 
    private String tipo; 

    public TipoInasistencia() {
    }

    public TipoInasistencia(int idTipoInasistencia, String tipo) {
        this.idTipoInasistencia = idTipoInasistencia;
        this.tipo = tipo;
    }

    public int getIdTipoInasistencia() {
        return idTipoInasistencia;
    }

    public void setIdTipoInasistencia(int idTipoInasistencia) {
        this.idTipoInasistencia = idTipoInasistencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
