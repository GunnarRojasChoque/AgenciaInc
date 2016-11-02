package com.example.gunnar.agenciainc;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

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

    private static final String TAG = MainActivity.class.getName();



/*
    ////////////##################################
    public static EditText nombresCli;
    public static EditText apellidosCli;
    public static EditText ciCli;
    public static Button fechaCli;
    public static EditText correoCli;
    public static EditText celularCli;
    public static RadioButton generoMasCli;
    public static RadioButton generoFemCli;
    public static Button registrarCli;
    public static Button cancelarCli;
    public static RadioGroup grupogenero;
    //public static TextView fechaNacCli;

    public static RadioButton rb;



    private void initRegistrtoCli(){

        nombresCli=(EditText)findViewById(R.id.etnombres);
        apellidosCli=(EditText)findViewById(R.id.etapellidos);
        ciCli=(EditText)findViewById(R.id.etci);
        fechaCli=(Button) findViewById(R.id.btnnacimiento);
        correoCli=(EditText)findViewById(R.id.etcorreo);
        celularCli=(EditText)findViewById(R.id.etcelular);
        generoMasCli=(RadioButton) findViewById(R.id.rbtnmasculino);
        generoFemCli=(RadioButton)findViewById(R.id.rbtnfemenino);
        registrarCli=(Button)findViewById(R.id.btnregistrar);
        cancelarCli=(Button)findViewById(R.id.btncancelar);
        grupogenero=(RadioGroup)findViewById(R.id.rbtngenero);
        fechaNow=(TextView)findViewById(R.id.tvfecha);



        fechaNow.setText(year + "/" + month + "/" + day);

        fechaCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(id_dialog);
            }
        });

        fechaCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(id_dialog);
            }
        });

        int IDgenero=grupogenero.getCheckedRadioButtonId();
        rb=(RadioButton)findViewById(IDgenero);



        registrarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente client=new Cliente(nombresCli.getText().toString(),apellidosCli.getText().toString(),
                        Integer.valueOf(celularCli.getText().toString()),Integer.valueOf(ciCli.getText().toString()),
                        fechaCli.getText().toString(),correoCli.getText().toString(),rb.getText().toString());


            }
        });


        cancelarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombresCli.setText("");
                apellidosCli.setText("");
                ciCli.setText("");
                fechaCli.setText("");
                correoCli.setText("");
                celularCli.setText("");

            }
        });

    }



/////////////######################3

*/

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
