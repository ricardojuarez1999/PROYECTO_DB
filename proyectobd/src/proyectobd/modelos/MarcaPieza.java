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
public class MarcaPieza {
    private int idMarcaPieza;
    private String marca;

    public MarcaPieza() {
    }

    public MarcaPieza(int idMarcaPieza, String marca) {
        this.idMarcaPieza = idMarcaPieza;
        this.marca = marca;
    }

    public int getIdMarcaPieza() {
        return idMarcaPieza;
    }

    public void setIdMarcaPieza(int idMarcaPieza) {
        this.idMarcaPieza = idMarcaPieza;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }        
}
