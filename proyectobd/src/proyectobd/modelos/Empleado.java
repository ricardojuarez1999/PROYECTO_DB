/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.modelos;

import java.util.Calendar;
import java.util.Date;



/**
 *
 * @author israe
 */
public class Empleado {
    private int idEmpleado;
    private String contrasenia; 
    private Date fecha_inico; 
    private Date fecha_fin; 
    private int idPersona; 
    private int idPuesto; 
    private int idHorario;
    private Calendar calendar_inicio;
    private Calendar calendar_fin; 

    public Empleado() {
    }

    public Empleado(int idEmpleado, String contrasenia, Date fecha_inico, Date fecha_fin, int idPersona, int idPuesto, int idHorario) {
        this.idEmpleado = idEmpleado;
        this.contrasenia = contrasenia;
        this.fecha_inico = fecha_inico;
        this.fecha_fin = fecha_fin;
        this.idPersona = idPersona;
        this.idPuesto = idPuesto;
        this.idHorario = idHorario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Date getFecha_inico() {
        return fecha_inico;
    }

    public void setFecha_inico(Date fecha_inico) {
        this.fecha_inico = fecha_inico;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }
    
    public Calendar obtenerCalendarInicio(){
        calendar_inicio = Calendar.getInstance();
        calendar_inicio.setTime(this.fecha_inico);
        return calendar_inicio;
    }
    
    public Calendar obtenerCalendarFin(){
        calendar_fin = Calendar.getInstance();
        calendar_fin.setTime(this.fecha_fin);
        return calendar_fin;
    }
}
