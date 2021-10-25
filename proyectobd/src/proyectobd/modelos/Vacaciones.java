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
public class Vacaciones {
    private int idVacaciones; 
    private Date fecha_salida;
    private Date fecha_entrada;
    private int idEmpleado; 
    private Calendar calendar_salida;
    private Calendar calendar_entrada; 

    public Vacaciones() {
    }

    public Vacaciones(int idVacaciones, Date fecha_salida, Date fecha_entrada, int idEmpleado) {
        this.idVacaciones = idVacaciones;
        this.fecha_salida = fecha_salida;
        this.fecha_entrada = fecha_entrada;
        this.idEmpleado = idEmpleado;
    }

    public int getIdVacaciones() {
        return idVacaciones;
    }

    public void setIdVacaciones(int idVacaciones) {
        this.idVacaciones = idVacaciones;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
     public Calendar obtenerCalendarEntrada(){
        calendar_entrada = Calendar.getInstance();
        calendar_entrada.setTime(this.fecha_entrada);
        return calendar_entrada;
    }
    
    public Calendar obtenerCalendarSalida(){
        calendar_salida = Calendar.getInstance();
        calendar_salida.setTime(this.fecha_salida);
        return calendar_salida;
    }
}
