package com.example.gunnar.agenciainc.Mains;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gunnar.agenciainc.R;
import com.example.gunnar.agenciainc.Vehiculo;

import java.util.Calendar;

public class Main extends AppCompatActivity {

    public static EditText modelo;
    public static EditText marca;
    public static EditText chasis;
    public static EditText anio;
    public static EditText precio;
    public static TextView fechaNow;
    public static Button fech;
    public static Button registro;
    public static Button cancelar;
    public static int year, month, day;
    public static final int id_dialog = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCalendar();
        initRegistro();

    }

    private void initCalendar() {
        final Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void initRegistro() {
        modelo = (EditText) findViewById(R.id.modelo);
        marca = (EditText) findViewById(R.id.marca);
        chasis = (EditText) findViewById(R.id.chasis);
        anio = (EditText) findViewById(R.id.anio);
        precio = (EditText) findViewById(R.id.precio);
        fechaNow = (TextView) findViewById(R.id.dateNow);
        registro = (Button) findViewById(R.id.registrar);
        cancelar = (Button) findViewById(R.id.Cancelar);
        fech = (Button) findViewById(R.id.date);

        fechaNow.setText(year + "/" + month + "/" + day);

        fechaNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(id_dialog);
            }
        });

        fech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(id_dialog);
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vehiculo vehiculo = new Vehiculo(modelo.getText().toString(), marca.getText().toString(),
                        chasis.getText().toString(), Integer.parseInt(anio.getText().toString()),
                        fechaNow.getText().toString(), Integer.parseInt(precio.getText().toString()));
            }
        });


        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelo.setText("");
                marca.setText("");
                chasis.setText("");
                anio.setText("");
                fechaNow.setText("");
                precio.setText("");
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        if (id == 0) {
            return new DatePickerDialog(this, dateSetListener, year, month, day);
        }
        return super.onCreateDialog(id);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year = i;
            month = i1;
            day = i2;
            fechaNow.setText(year + "/" + month + "/" + day);
        }
    };
}
