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
public class TelefonoPersona {
    private int idTelefonoPersona; 
    private int numero; 
    private int idPersona;

    public TelefonoPersona() {
    }

    public TelefonoPersona(int idTelefonoPersona, int numero, int idPersona) {
        this.idTelefonoPersona = idTelefonoPersona;
        this.numero = numero;
        this.idPersona = idPersona;
    }

    public int getIdTelefonoPersona() {
        return idTelefonoPersona;
    }

    public void setIdTelefonoPersona(int idTelefonoPersona) {
        this.idTelefonoPersona = idTelefonoPersona;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    
    
}
