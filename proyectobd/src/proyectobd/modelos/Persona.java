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
public class Persona {
    private int idPersona; 
    private String dpi;
    private String nombre; 
    private String apellido; 
    private String direccion; 
    private String nit;

    public Persona() {
    }

    public Persona(int idPersona, String dpi, String nombre, String apellido, String direccion, String nit) {
        this.idPersona = idPersona;
        this.dpi = dpi;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.nit = nit;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    } 
}
