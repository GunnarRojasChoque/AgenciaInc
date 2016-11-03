package com.example.gunnar.agenciainc.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class for Car
 * Created by Gunnar on 02/10/2016.
 */


class BDVehiculo extends SQLiteOpenHelper {
    private static final String TAG = BDVehiculo.class.getSimpleName();

    private static final String TABLE_VEHICULO_IMPORTADORA = "Vehiculo";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MODELO = "modelo";
    private static final String COLUMN_MARCA = "marca";
    private static final String COLUMN_CHASIS = "chasis";
    private static final String COLUMN_ANIO = "año";
    private static final String COLUMN_PRECIO = "precio";
    private static final String COLUMN_FECHA = "fecha";

    private static final String DATABASE_VEHICULO = "BaseDatosCliente.db";
    private static final int DATABASE_VERSION = 1;

    //Setencia SQL para crear la base de datos
    private static final String DATABASE_CREATE = "CREATE TABLE"
            + TABLE_VEHICULO_IMPORTADORA + "(" + COLUMN_ID + " TEXT PRIMARY KEY,"
            + COLUMN_MODELO + "TEXT NOT NULL,"
            + COLUMN_MARCA + "TEXT NOT NULL,"
            + COLUMN_CHASIS + "TEXT NOT NULL,"
            + COLUMN_ANIO + "TEXT NOT NULL,"
            + COLUMN_PRECIO + "TEXT NOT NULL,"
            + COLUMN_FECHA + "TEXT NOT NULL);";

    BDVehiculo(Context context) {
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
