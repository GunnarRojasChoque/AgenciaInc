package com.example.gunnar.agenciainc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gunnar.agenciainc.BaseDeDatos.BDCliente;
import com.example.gunnar.agenciainc.Mains.MainActivity;
import com.example.gunnar.agenciainc.Mains.MainCatalogo;
import com.example.gunnar.agenciainc.Mains.MainCliente;

/**
 * Created by alvaro on 02/11/2016.
 */

public class Cliente {

    private String nombres;
    private String apellidos;
    private int celular;
    private int ci;
    private String fechaNac;
    private String correo;
    private String genero;
    public SQLiteDatabase bdCliente;

    public Cliente(String nombres, String apellidos, int celular, int ci, String fechaNac, String correo, String genero) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.ci = ci;
        this.fechaNac = fechaNac;
        this.correo = correo;
        this.genero = genero;


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

    private void detalleDecliente()
    {
        BDCliente cliente = new BDCliente(MainCliente.context);
        bdCliente = cliente.getReadableDatabase();

        String sql="SELECT * FROM cliente ";
        int id=0;
        String nombres=" ";
        String apellidos=" ";
        String celular=" ";
        String fechaNac=" ";
        String correo=" ";
        String genero=" ";

        Cursor c = bdCliente.rawQuery(sql, null);

    }
}
