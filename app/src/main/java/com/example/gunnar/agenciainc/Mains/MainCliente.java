package com.example.gunnar.agenciainc.Mains;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.AlertDialog;
//import android.app.Dia

import com.example.gunnar.agenciainc.BaseDeDatos.BDCliente;
import com.example.gunnar.agenciainc.Cliente;
import com.example.gunnar.agenciainc.R;
import com.example.gunnar.agenciainc.Validador;

import java.util.Calendar;

public class MainCliente extends AppCompatActivity {

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
    public static RadioButton rb;
    public static int year, month, day;
    public static final int id_dialog = 0;
    public static Context context;

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_cliente);
        view = findViewById(R.id.container);

        context = this;
        initCalendar();
        initRegistrtoCli();

        generoMasCli.setChecked(true);
    }

    private void aviso() {
        Snackbar.make(view, "Llene todos los campos.", Snackbar.LENGTH_LONG).setAction("Close", null).show();
    }

    private void initCalendar() {
        final Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void initRegistrtoCli() {

        nombresCli = (EditText) findViewById(R.id.etnombres);
        apellidosCli = (EditText) findViewById(R.id.etapellidos);
        ciCli = (EditText) findViewById(R.id.etci);
        fechaCli = (Button) findViewById(R.id.btnnacimiento);
        correoCli = (EditText) findViewById(R.id.etcorreo);
        celularCli = (EditText) findViewById(R.id.etcelular);
        generoMasCli = (RadioButton) findViewById(R.id.rbtnmasculino);
        generoFemCli = (RadioButton) findViewById(R.id.rbtnfemenino);
        registrarCli = (Button) findViewById(R.id.btnregistrar);
        cancelarCli = (Button) findViewById(R.id.btncancelar);
        grupogenero = (RadioGroup) findViewById(R.id.rbtngenero);
        MainVehiculo.fechaNow = (TextView) findViewById(R.id.tvfecha);


        MainVehiculo.fechaNow.setText(year + "/" + month + "/" + day);

        fechaCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(id_dialog);
            }
        });

        int IDgenero = grupogenero.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(IDgenero);


        registrarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo= correoCli.getText().toString();
                String nombre=nombresCli.getText().toString();
                String apellido=apellidosCli.getText().toString();
                String ci=ciCli.getText().toString();
                String celular=celularCli.getText().toString();
                   if(estaLleno()){

                           if(Validador.nombreV(nombre)){
                                if(Validador.apellidoV(apellido)){
                                    if(Validador.ciV(ci)){
                                        if(Validador.correoV(correo)){
                                            if(Validador.celularV(celular)){
                                                llenarCliente();
                                                vaciar();
                                                exito();
                                            }else{celularCli.setText("");error("CELULAR");}
                                        }else{correoCli.setText("");error("CORREO");  }
                                    }else{ciCli.setText("");error("CI");  }
                                }else {apellidosCli.setText(""); error("APELLIDO"); }
                           }else{nombresCli.setText(""); error("NOMBRE");}

                   }else{

                       AlertDialog alerta = new AlertDialog.Builder(context).create(); //Aqui me marca el siguiente error The constructor AlertDialog.Builder(new View.OnClickListener(){}) is undefined
                        alerta.setMessage("TODOS LOS CAMPOS DEBEN SER LLENADOS.");
                        alerta.setButton("ACEPTAR", new DialogInterface.OnClickListener() {

                           public void onClick(DialogInterface dialog, int which) {

                               return;
                           }
                       });

                       alerta.show();
                   }
            }
        });
        cancelarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaciar();
            }
        });

    }
    public boolean estaLleno(){
        boolean llenado=true;
        if((nombresCli.getText().toString().equalsIgnoreCase(""))||
                (apellidosCli.getText().toString().equalsIgnoreCase(""))||
                (ciCli.getText().toString().equalsIgnoreCase(""))||
                //(fechaCli.getText().toString().equalsIgnoreCase(""))||
                (correoCli.getText().toString().equalsIgnoreCase(""))||
                        (celularCli.getText().toString().equalsIgnoreCase(""))){

            llenado=false;
        }
        return llenado;

    }
    public void exito(){
        AlertDialog alerta = new AlertDialog.Builder(context).create(); //Aqui me marca el siguiente error The constructor AlertDialog.Builder(new View.OnClickListener(){}) is undefined
        alerta.setMessage("EL REGISTRO FUE EXITOSO.");

        alerta.setButton("ACEPTAR", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                return;

            }
        });

        alerta.show();

    }
    public void error(String campo){

        AlertDialog alerta = new AlertDialog.Builder(context).create(); //Aqui me marca el siguiente error The constructor AlertDialog.Builder(new View.OnClickListener(){}) is undefined

        //alerta.setTitle("Alert");

        alerta.setMessage("EL CAMPO "+campo+" ES INVALIDO");

        alerta.setButton("ACEPTAR", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                return;

            }
        });

        alerta.show();

    }

    private void vaciar() {
        nombresCli.setText("");
        apellidosCli.setText("");
        ciCli.setText("");
        fechaCli.setText("");
        correoCli.setText("");
        celularCli.setText("");
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
            MainVehiculo.fechaNow.setText(year + "/" + month + "/" + day);
        }
    };

    public void llenarCliente() {
        /*
        String cad = "s";
        boolean res = Validador.placaV(cad);
        */

        Cliente client = new Cliente(nombresCli.getText().toString(), apellidosCli.getText().toString(),
                Integer.valueOf(celularCli.getText().toString()), Integer.valueOf(ciCli.getText().toString()),
                MainVehiculo.fechaNow.getText().toString(), correoCli.getText().toString(),
                "Masculino");

        BDCliente baseCliHelper = new BDCliente(this);
        SQLiteDatabase base = baseCliHelper.getWritableDatabase();

        ContentValues contenido = new ContentValues();

        contenido.put(BDCliente.COLUMN_NOMBRE, client.getNombres());
        contenido.put(BDCliente.COLUMN_APELLIDO, client.getApellidos());
        contenido.put(BDCliente.COLUMN_TELEFONO, client.getCelular());
        contenido.put(BDCliente.COLUMN_CI, client.getCi());
        contenido.put(BDCliente.COLUMN_NACIMIENTO, client.getFechaNac());
        contenido.put(BDCliente.COLUMN_CORREO, client.getCorreo());
        contenido.put(BDCliente.COLUMN_SEXO, client.getGenero());

        long nuevaFila = base.insert(baseCliHelper.TABLE_CLIENTE_IMPORTADORA, null, contenido);

        //ContentValues conte=new ContentValues();
        //conte.put(baseCliHelper.COLUMN_ID,nuevaFila);
        //base.insert(baseCliHelper.TABLE_CLIENTE_IMPORTADORA,null,conte);


    }
}
