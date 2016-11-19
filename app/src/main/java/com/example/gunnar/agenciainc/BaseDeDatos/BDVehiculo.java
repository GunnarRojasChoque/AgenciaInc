package com.example.gunnar.agenciainc.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class for Car
 * Created by Gunnar on 02/10/2016.
 */


public class BDVehiculo extends SQLiteOpenHelper {
    private static final String TAG = BDVehiculo.class.getSimpleName();

    public static final String TABLE_VEHICULO_IMPORTADORA = "Vehiculo";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MODELO = "modelo";
    public static final String COLUMN_MARCA = "marca";
    public static final String COLUMN_CHASIS = "chasis";
    public static final String COLUMN_ANIO = "anio";
    public static final String COLUMN_PRECIO = "precio";
    public static final String COLUMN_POTENCIA = "potencia";
    public static final String COLUMN_FECHA = "fecha";
    public static final String COLUMN_IMAGEN = "imagen";
    public static final String COLUMN_TIPO = "tipo_vehiculo";
    public static final String COLUMN_CARACTERISTICAS = "caractersticas";

    private static final String DATABASE_VEHICULO = "BaseDatosVehiculo.db";
    private static final int DATABASE_VERSION = 1;

    //Setencia SQL para crear la base de datos
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_VEHICULO_IMPORTADORA + " (" + COLUMN_ID + " INTEGER NOT NULL, "
            + COLUMN_MODELO + " TEXT NOT NULL, "
            + COLUMN_MARCA + " TEXT NOT NULL, "
            + COLUMN_CHASIS + " TEXT NOT NULL, "
            + COLUMN_ANIO + " TEXT NOT NULL, "
            + COLUMN_PRECIO + " TEXT NOT NULL, "
            + COLUMN_POTENCIA + " TEXT NOT NULL, "
            + COLUMN_FECHA + " TEXT NOT NULL, "
            + COLUMN_IMAGEN + " BLOB, "
            + COLUMN_TIPO + " TEXT NOT NULL, "
            + COLUMN_CARACTERISTICAS + " TEXT NOT NULL);";

    public BDVehiculo(Context context) {
        super(context, DATABASE_VEHICULO, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ejecuta la sentencia SQL y crea la base de datos
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgradind database from version " + oldVersion + "to" + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICULO_IMPORTADORA);
        onCreate(db);
    }
}
