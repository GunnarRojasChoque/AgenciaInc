package com.example.gunnar.agenciainc;

/**
 * Class for Car
 * Created by Gunnar on 31/10/2016.
 */

class Vehiculo {

    private String modelo;
    private String marca;
    private String chasis;
    private int año;
    private String fechaIngreso;
    private int precioIni;

    Vehiculo(String modelo, String marca, String chasis, int año, String fechaIngreso, int precioIni) {
        this.modelo = modelo;
        this.marca = marca;
        this.chasis = chasis;
        this.año = año;
        this.fechaIngreso = fechaIngreso;
        this.precioIni = precioIni;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getPrecioIni() {
        return precioIni;
    }

    public void setPrecioIni(int precioIni) {
        this.precioIni = precioIni;
    }
}
