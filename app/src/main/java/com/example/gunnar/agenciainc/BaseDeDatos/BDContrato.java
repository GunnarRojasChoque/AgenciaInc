package com.example.gunnar.agenciainc.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by alvaro on 08/11/2016.
 */

public class BDContrato extends SQLiteOpenHelper {

    private static final String TAG = BDCliente.class.getSimpleName();

    public static final String TABLE_CONTRATO_EMPLEADO = "Contrato";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_APELLIDO = "apellido";
    public static final String COLUMN_TELEFONO = "telefono";
    public static final String COLUMN_CI = "ci";
    public static final String COLUMN_NACIMIENTO = "nacimiento";
    public static final String COLUMN_CORREO = "correo";
    public static final String COLUMN_DIRECCION = "direccion";
    public static final String COLUMN_FECHA_INICIO = "fechaInicio";
    public static final String COLUMN_FECHA_FIN = "fechaFin";
    public static final String COLUMN_CARGO = "cargo";
    public static final String COLUMN_SALARIO = "salario";
    public static final String COLUMN_PAIS = "pais";
    public static final String COLUMN_NOMBRE_EMPLEADOR = "pais";


    public static final String DATABASE_CLIENTE = "BaseDatosContrato.db";
    public static final int DATABASE_VERSION = 1;

    //Setencia SQL para crear la base de datos
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_CONTRATO_EMPLEADO + "(" + COLUMN_ID + " TEXT PRIMARY KEY,"
            + COLUMN_NOMBRE_EMPLEADOR + " TEXT NOT NULL, "
            + COLUMN_NOMBRE + " TEXT NOT NULL, "
            + COLUMN_APELLIDO + " TEXT NOT NULL, "
            + COLUMN_TELEFONO + " TEXT NOT NULL, "
            + COLUMN_CI + " TEXT NOT NULL, "
            + COLUMN_NACIMIENTO + " TEXT NOT NULL, "
            + COLUMN_CORREO + " TEXT NOT NULL, "
            + COLUMN_DIRECCION + " TEXT NOT NULL, "
            + COLUMN_PAIS + " TEXT NOT NULL, "
            + COLUMN_CARGO + " TEXT NOT NULL, "
            + COLUMN_FECHA_INICIO + " TEXT NOT NULL, "
            + COLUMN_FECHA_FIN + " TEXT NOT NULL,"
            + COLUMN_CARGO + "TEXT NOT NULL, "
            + COLUMN_SALARIO + " TEXT NOT NULL );";


    public BDContrato(Context context) {
        super(context, DATABASE_CLIENTE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ejecuta la sentencia SQL y crea la base de datos
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgradind database from version " + oldVersion + "to" + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTRATO_EMPLEADO);
        onCreate(db);
    }
}
