package com.example.gunnar.agenciainc.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by Max on 04/11/2016.
 */

public class BDTransaccion extends SQLiteOpenHelper{
    private static final String TAG = BDTransaccion.class.getSimpleName();

    private static final String TABLE_FORMULARIO_TRANSACCION = "transaccion";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_COMPRADOR = "comprador";
    private static final String COLUMN_VENDEDOR = "vendedor";
    private static final String COLUMN_MODELO = "modelo";
    private static final String COLUMN_DESCUENTO = "descuento";
    private static final String COLUMN_PRECIOTOTAL ="precio_total";
    private static final String COLUMN_NUMERO_IMPORTACION = "numero_importacion";
    private static final String COLUMN_FECHA= "fecha";
    private static final String COLUMN_CIUDAD= "ciudad";

    private static final String DATABASE_TRANSACCION = "BaseDatosTransaccion.db";
    private static final int DATABASE_VERSION = 1;

    //Setencia SQL para crear la base de datos
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_FORMULARIO_TRANSACCION + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COMPRADOR + " TEXT NOT NULL, "
            + COLUMN_VENDEDOR + " TEXT NOT NULL, "
            + COLUMN_MODELO + " INT NOT NULL, "
            + COLUMN_DESCUENTO + " INT NOT NULL, "
            + COLUMN_PRECIOTOTAL + " TEXT NOT NULL, "
            + COLUMN_NUMERO_IMPORTACION + " TEXT NOT NULL, "
            + COLUMN_FECHA + " TEXT NOT NULL"
            + COLUMN_CIUDAD + " TEXT NOT NULL );";


    public BDTransaccion(Context context){
        super(context, DATABASE_TRANSACCION, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE);//Ejecuta la sentencia SQL y crea la base de datos
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgradind database from version " + oldVersion + "to" + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMULARIO_TRANSACCION);
        onCreate(db);
    }



}
