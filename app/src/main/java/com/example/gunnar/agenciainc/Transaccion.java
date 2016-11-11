package com.example.gunnar.agenciainc;

/**
 * Created by Max on 10/11/2016.
 */

public class Transaccion{

    private String comprador;
    private String vendedor;
    private String modelo;
    private int descuento;
    private int precioTotal;
    private String numeroImportacion;
    private String ciudad;


    public Transaccion(String comprador, String vendedor,String modelo,int descuento,int precioTotal,String numeroImportacion,String ciudad)
    {

        this.comprador=comprador;
        this.vendedor=vendedor;
        this.modelo=modelo;
        this.descuento=descuento;
        this.precioTotal=precioTotal;
        this.numeroImportacion=numeroImportacion;
        this.ciudad=ciudad;
     }

    public String getVendedor(){
        return vendedor;
    }
    public void setVendedor(String v){
        vendedor=v;
    }

    public String getComprador() {
        return comprador;
    }
    public void setComprador(String c){
        comprador=c;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String m){modelo=m;}

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getNumeroImportacion() {
        return numeroImportacion;
    }

    public void setNumeroImportacion(String numeroImportacion) {
        this.numeroImportacion = numeroImportacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
