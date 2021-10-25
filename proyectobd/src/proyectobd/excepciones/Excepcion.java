/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.excepciones;

/**
 *
 * @author israe
 */
public class Excepcion extends RuntimeException{

    public Excepcion() {
        this("Ocurrio una excepcion"); 
    }
    public Excepcion(String mensaje){
        super(mensaje);
    }
}
