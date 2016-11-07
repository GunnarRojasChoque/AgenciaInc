package com.example.gunnar.agenciainc.Mains;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.gunnar.agenciainc.BaseDeDatos.BDVehiculo;
import com.example.gunnar.agenciainc.R;

public class MainActivity extends AppCompatActivity {

    private Button registroV;
    private Button registroCL;
    private Button catalogo;
    private static Context context;

    public SQLiteDatabase database;
    public static SQLiteDatabase db;
    public static BDVehiculo vehiculo;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;
        vehiculo = new BDVehiculo(this);
        db = vehiculo.getReadableDatabase();
        initView();
    }

    private void initView(){
        registroV = (Button) findViewById(R.id.registroV);
        registroCL = (Button) findViewById(R.id.registroCl);
        catalogo = (Button) findViewById(R.id.catalogo);

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

        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainCatalogo.class);
                startActivity(intent);
            }
        });
    }
}
