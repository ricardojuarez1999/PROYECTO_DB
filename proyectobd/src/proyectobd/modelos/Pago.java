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
public class Pago {
    private int idPago; 
    private float pago;
    private Date fecha; 
    private int idEmpleado; 
    private Calendar calendar;
    public Pago() {
    }

    public Pago(int idPago, float pago, Date fecha, int idEmpleado) {
        this.idPago = idPago;
        this.pago = pago;
        this.fecha = fecha;
        this.idEmpleado = idEmpleado;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public float getPago() {
        return pago;
    }

    public void setPago(float pago) {
        this.pago = pago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public Calendar obtenerCalendar(){
        calendar = Calendar.getInstance();
        calendar.setTime(this.fecha);
        return calendar;
    }
}
