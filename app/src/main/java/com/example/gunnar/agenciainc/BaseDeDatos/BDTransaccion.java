package com.example.gunnar.agenciainc.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * BD
 * Created by Max on 04/11/2016.
 */

public class BDTransaccion extends SQLiteOpenHelper {
    private static final String TAG = BDTransaccion.class.getSimpleName();

    public static final String TABLE_FORMULARIO_TRANSACCION = "transaccion";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMPRADOR = "comprador";
    public static final String COLUMN_VENDEDOR = "vendedor";
    public static final String COLUMN_MODELO = "modelo";
    public static final String COLUMN_DESCUENTO = "descuento";
    public static final String COLUMN_PRECIOTOTAL = "precio_total";
    public static final String COLUMN_NUMERO_IMPORTACION = "numero_importacion";
    public static final String COLUMN_FECHA = "fecha";
    public static final String COLUMN_CIUDAD = "ciudad";

    private static final String DATABASE_TRANSACCION = "BaseDatosTransaccion.db";
    private static final int DATABASE_VERSION = 1;

    //Setencia SQL para crear la base de datos
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_FORMULARIO_TRANSACCION + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COMPRADOR + " TEXT NOT NULL, "
            + COLUMN_VENDEDOR + " TEXT NOT NULL, "
            + COLUMN_MODELO + " INTEGER NOT NULL, "
            + COLUMN_DESCUENTO + " INTEGER NOT NULL, "
            + COLUMN_PRECIOTOTAL + " TEXT NOT NULL, "
            + COLUMN_NUMERO_IMPORTACION + " TEXT NOT NULL, "
            + COLUMN_FECHA + " TEXT NOT NULL, "
            + COLUMN_CIUDAD + " TEXT NOT NULL);";


    public BDTransaccion(Context context) {
        super(context, DATABASE_TRANSACCION, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);//Ejecuta la sentencia SQL y crea la base de datos
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgradind database from version " + oldVersion + "to" + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMULARIO_TRANSACCION);
        onCreate(db);
    }


}
