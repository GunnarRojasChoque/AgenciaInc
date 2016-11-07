package com.example.gunnar.agenciainc.Mains;



public class Mecanico {

    private String nombres;
    private String apellidos;
    private int celular;
    private int ci;
    private String fechaNac;
    private String correo;
    private String genero;


    public Mecanico (String nombres,String apellidos,int celular,int ci,String fechaNac,String correo,String genero){
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.celular=celular;
        this.ci=ci;
        this.fechaNac=fechaNac;
        this.correo=correo;
        this.genero=genero;




    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}

