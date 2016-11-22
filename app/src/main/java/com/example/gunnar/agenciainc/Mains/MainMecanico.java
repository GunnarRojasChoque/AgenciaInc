package com.example.gunnar.agenciainc.Mains;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.app.AlertDialog;

import com.example.gunnar.agenciainc.BaseDeDatos.BDMecanico;
import com.example.gunnar.agenciainc.R;
import com.example.gunnar.agenciainc.Validador;

import java.util.Calendar;

import static com.example.gunnar.agenciainc.BaseDeDatos.BDMecanico.TABLE_MECANICO_IMPORTADORA;

public class MainMecanico extends AppCompatActivity {

    public static EditText nombresMec;
    public static EditText apellidosMec;
    public static EditText ciMec;
    public static Button fecha;
    public static TextView fechaShow;
    public static EditText celularMec;
    public static EditText chasis;
    public static EditText diagnostico;

    public static Button registrarMec;
    public static Button cancelarMec;
    public static int year, month, day;
    public static final int id_dialog = 0;
    public static Context context;
    SQLiteDatabase database;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_mecanico);
        view = findViewById(R.id.container);

        context = this;
        initCalendar();
        initRegistrtoMec();
    }

    private void initCalendar() {
        final Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void aviso() {
        Snackbar.make(view, "Llene todos los campos.", Snackbar.LENGTH_LONG).setAction("Close", null).show();
    }

    private void initRegistrtoMec() {

        nombresMec = (EditText) findViewById(R.id.etnombres);
        apellidosMec = (EditText) findViewById(R.id.etapellidos);
        ciMec = (EditText) findViewById(R.id.etci);
        fechaShow = (TextView) findViewById(R.id.fechaDiagnostico);
        celularMec = (EditText) findViewById(R.id.etcelular);
        chasis = (EditText) findViewById(R.id.chasis);
        diagnostico = (EditText) findViewById(R.id.etdiasnostico);

        fecha = (Button) findViewById(R.id.btnFecha);
        cancelarMec = (Button) findViewById(R.id.btncancelar);
        registrarMec = (Button) findViewById(R.id.btnregistrar);

        fechaShow.setText(year + "/" + month + "/" + day);


        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(id_dialog);
            }
        });


        registrarMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombres = nombresMec.getText().toString();
                String apellidos = apellidosMec.getText().toString();
                String ci = ciMec.getText().toString();
                String celular =  celularMec.getText().toString();
                String chas = chasis.getText().toString();
                    if(estaLleno()) {
                        if(Validador.nombreV(nombres)){
                            if(Validador.apellidoV(apellidos)){
                                if(Validador.ciV(ci)){
                                    if(Validador.celularV(celular)){
                                        if(Validador.chasisV(chas)){
                                            llenarTransaccion();
                                            vaciar();
                                            exito();
                                        }else{chasis.setText(""); error("CHASIS");}
                                    }else{celularMec.setText(""); error("CELULAR");}
                                }else{ciMec.setText(""); error("CI");}
                            }else{apellidosMec.setText(""); error("APELLIDOS");}
                        }else{nombresMec.setText(""); error("NOMBRES");}
                    }else {
                        AlertDialog alerta = new AlertDialog.Builder(context).create();
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
        cancelarMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onBackPressed();
                vaciar();
            }
        });

    }
    public boolean estaLleno(){
        boolean lleno = true;
        if((nombresMec.getText().toString().equalsIgnoreCase(""))||
                (apellidosMec.getText().toString().equalsIgnoreCase(""))||
                (ciMec.getText().toString().equalsIgnoreCase(""))||
                (celularMec.getText().toString().equalsIgnoreCase(""))||
                (chasis.getText().toString().equalsIgnoreCase(""))){
            lleno = false;
        }
        return lleno;

    }
    public void exito(){
        AlertDialog alerta = new AlertDialog.Builder(context).create();
        alerta.setMessage("REGISTRO EXITOSO.");
        alerta.setButton("ACEPTAR", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                return;

            }
        });
        alerta.show();
    }
    public void error(String campo){
        AlertDialog alerta = new AlertDialog.Builder(context).create();
        alerta.setMessage("CAMPO " + campo + " ES INVALIDO");
        alerta.setButton("ACEPTAR", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                return;

            }
        });
        alerta.show();
    }

    private void llenarTransaccion() {

        //Base de Datos transaccion
        BDMecanico bdMecanico = new BDMecanico(this);
        database = bdMecanico.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BDMecanico.COLUMN_NOMBRES, nombresMec.getText().toString());
        values.put(BDMecanico.COLUMN_APELLIDOS, apellidosMec.getText().toString());
        values.put(BDMecanico.COLUMN_CELULAR, Integer.parseInt(celularMec.getText().toString()));
        values.put(BDMecanico.COLUMN_CI, Integer.parseInt(ciMec.getText().toString()));
        values.put(BDMecanico.COLUMN_CHASIS, chasis.getText().toString());
        values.put(BDMecanico.COLUMN_DIAGNOSTICO, diagnostico.getText().toString());
        values.put(BDMecanico.COLUMN_FECHA, fechaShow.getText().toString());

        long newRowId = database.insert(TABLE_MECANICO_IMPORTADORA, null, values);

        //Log.i(TAG, "llenarBdTransaccion: id return " + newRowId);
    }

    private void vaciar() {
        nombresMec.setText("");
        apellidosMec.setText("");
        celularMec.setText("");
        ciMec.setText("");
        chasis.setText("");
        diagnostico.setText("");
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

            fechaShow.setText(year + "/" + month + "/" + day);
        }
    };
}
