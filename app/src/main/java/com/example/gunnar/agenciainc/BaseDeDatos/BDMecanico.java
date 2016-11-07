package com.example.gunnar.agenciainc.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by willian on 04-11-16.
 */

public class BDMecanico extends SQLiteOpenHelper {
    private static final String TAG = BDMecanico.class.getSimpleName();

    public static final String TABLE_MECANICO_IMPORTADORA = "Mecanico";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOMBRES = "nombres";
    public static final String COLUMN_APELLIDOS = "apellidos";
    public static final String COLUMN_CELULAR = "celular";
    public static final String COLUMN_CI = "ci";
    public static final String COLUMN_CHASIS = "chasis";
    public static final String COLUMN_DIAGNOSTICO = "diagnostico";
    public static final String COLUMN_FECHA = "fecha";


    private static final String DATABASE_MECANICO = "BaseDatosMecanico.db";
    private static final int DATABASE_VERSION = 1;

    //Setencia SQL para crear la base de datos
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_MECANICO_IMPORTADORA + " (" + COLUMN_ID + " INT NOT NULL AUTOINCREMENT PRIMARY KEY, "
            + COLUMN_NOMBRES + " TEXT NOT NULL, "
            + COLUMN_APELLIDOS + " TEXT NOT NULL, "
            + COLUMN_CELULAR + " INT NOT NULL, "
            + COLUMN_CI + " INT NOT NULL, "
            + COLUMN_CHASIS + " TEXT NOT NULL, "
            + COLUMN_DIAGNOSTICO + " TEXT NOT NULL, "
            + COLUMN_FECHA + " TEXT NOT NULL);";


    public BDMecanico(Context context) {
        super(context, DATABASE_MECANICO, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ejecuta la sentencia SQL y crea la base de datos
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgradind database from version " + oldVersion + "to" + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MECANICO_IMPORTADORA);
        onCreate(db);
    }
}
