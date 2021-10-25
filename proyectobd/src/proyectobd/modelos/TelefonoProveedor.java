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
public class TelefonoProveedor {
    private int idTelefonoProveedor; 
    private int numero; 
    private int idProveedor;

    public TelefonoProveedor() {
    }

    public TelefonoProveedor(int idTelefonoProveedor, int numero, int idProveedor) {
        this.idTelefonoProveedor = idTelefonoProveedor;
        this.numero = numero;
        this.idProveedor = idProveedor;
    }

    public int getIdTelefonoProveedor() {
        return idTelefonoProveedor;
    }

    public void setIdTelefonoProveedor(int idTelefonoProveedor) {
        this.idTelefonoProveedor = idTelefonoProveedor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    
}
