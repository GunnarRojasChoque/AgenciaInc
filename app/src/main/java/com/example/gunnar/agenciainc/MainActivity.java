package com.example.gunnar.agenciainc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText modelo;
    EditText marca;
    EditText chasis;
    EditText anio;
    EditText fecha;
    EditText precio;

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initRegistro();

    }

    private void initRegistro() {
        modelo = (EditText) findViewById(R.id.modelo);
        marca = (EditText) findViewById(R.id.marca);
        chasis = (EditText) findViewById(R.id.chasis);
        anio = (EditText) findViewById(R.id.anio);
        fecha = (EditText) findViewById(R.id.date);
        precio = (EditText) findViewById(R.id.precio);

        Button registro = (Button) findViewById(R.id.registrar);
        Button cancelar = (Button) findViewById(R.id.Cancelar);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vehiculo vehiculo = new Vehiculo(modelo.getText().toString(), marca.getText().toString(),
                        chasis.getText().toString(), Integer.parseInt(anio.getText().toString()),
                        fecha.getText().toString(), Integer.parseInt(precio.getText().toString()));
            }
        });


        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelo.setText("");
                marca.setText("");
                chasis.setText("");
                anio.setText("");
                fecha.setText("");
                precio.setText("");
            }
        });
    }
}
