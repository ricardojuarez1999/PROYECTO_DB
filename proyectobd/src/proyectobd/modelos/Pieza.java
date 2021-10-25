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
public class Pieza {
    private int idPieza;
    private int numeroPieza; 
    private float precio_compra;
    private float precio_venta; 
    private int idProveedor; 
    private int idNombrePieza; 
    private int idEstadoPieza;
    private int idMarcaPieza;
    private int idCarro;

    public Pieza() {
    }

    public Pieza(int idPieza, int numeroPieza, float precio_compra, float precio_venta, int idProveedor, int idNombrePieza, int idEstadoPieza, int idMarcaPieza, int idCarro) {
        this.idPieza = idPieza;
        this.numeroPieza = numeroPieza;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.idProveedor = idProveedor;
        this.idNombrePieza = idNombrePieza;
        this.idEstadoPieza = idEstadoPieza;
        this.idMarcaPieza = idMarcaPieza;
        this.idCarro = idCarro;
    }

    public int getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(int idPieza) {
        this.idPieza = idPieza;
    }

    public int getNumeroPieza() {
        return numeroPieza;
    }

    public void setNumeroPieza(int numeroPieza) {
        this.numeroPieza = numeroPieza;
    }

    public float getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(float precio_compra) {
        this.precio_compra = precio_compra;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdNombrePieza() {
        return idNombrePieza;
    }

    public void setIdNombrePieza(int idNombrePieza) {
        this.idNombrePieza = idNombrePieza;
    }

    public int getIdEstadoPieza() {
        return idEstadoPieza;
    }

    public void setIdEstadoPieza(int idEstadoPieza) {
        this.idEstadoPieza = idEstadoPieza;
    }

    public int getIdMarcaPieza() {
        return idMarcaPieza;
    }

    public void setIdMarcaPieza(int idMarcaPieza) {
        this.idMarcaPieza = idMarcaPieza;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }        
}
