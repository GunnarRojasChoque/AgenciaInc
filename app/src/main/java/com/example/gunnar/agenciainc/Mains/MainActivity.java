package com.example.gunnar.agenciainc.Mains;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.gunnar.agenciainc.R;

/**
 * for convert from bitmap to array bitmap.
 * Created by Gunnar on 1/11/2016.
 */
public class MainActivity extends AppCompatActivity {

    public static Context context;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;

        initView();
    }

    private void initView() {
        Button registroV = (Button) findViewById(R.id.registroV);
        Button registroCL = (Button) findViewById(R.id.registroCl);
        Button catalogo = (Button) findViewById(R.id.catalogo);
        Button diagnostico = (Button) findViewById(R.id.diagnostico);

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

        diagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainMecanico.class);
                startActivity(intent);
            }
        });
    }


}
