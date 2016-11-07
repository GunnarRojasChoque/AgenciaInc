package com.example.gunnar.agenciainc.Mains;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gunnar.agenciainc.BaseDeDatos.BDMecanico;
import com.example.gunnar.agenciainc.R;

public class MainActivity extends AppCompatActivity {

    private Button registroV;
    private Button registroCL;
    private static Context context;

    public SQLiteDatabase database;

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;

        BDMecanico bdMecanico = new BDMecanico(this);
        database = bdMecanico.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(BDMecanico.COLUMN_NOMBRES, "asdas");
        contentValues.put(BDMecanico.COLUMN_APELLIDOS, "asdas");
        contentValues.put(BDMecanico.COLUMN_CELULAR, "asdas");
        contentValues.put(BDMecanico.COLUMN_CI, "asdas");
        contentValues.put(BDMecanico.COLUMN_FECHA_NAC, "asdas");
        contentValues.put(BDMecanico.COLUMN_CORREO, "asdasas");
        contentValues.put(BDMecanico.COLUMN_GENERO, "asasdasas");

        long row = database.insert(BDMecanico.TABLE_MECANICO_IMPORTADORA, null, contentValues);

        Log.i(TAG, "onCreate: id = " + row);





//Prueba
        initView();
    }

    private void initView(){
        registroV = (Button) findViewById(R.id.registroV);
        registroCL = (Button) findViewById(R.id.registroCl);

        registroV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainVehiculo.class);
                startActivity(intent);
            }
        });

        registroCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainCliente.class);
                startActivity(intent);
            }
        });
    }
}
