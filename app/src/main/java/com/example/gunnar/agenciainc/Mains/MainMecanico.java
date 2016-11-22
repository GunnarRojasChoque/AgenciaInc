package com.example.gunnar.agenciainc.Mains;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gunnar.agenciainc.BaseDeDatos.BDMecanico;
import com.example.gunnar.agenciainc.R;

import java.util.Calendar;

import static com.example.gunnar.agenciainc.BaseDeDatos.BDMecanico.TABLE_MECANICO_IMPORTADORA;

public class MainMecanico extends AppCompatActivity {

    EditText nombresMec;
    EditText apellidosMec;
    EditText ciMec;
    Button fecha;
    TextView fechaShow;
    EditText celularMec;
    EditText chasis;
    EditText diagnostico;

    Button registrarMec;
    Button cancelarMec;
    SQLiteDatabase database;
    View view;
    public static int year, month, day;
    public static final int id_dialog = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_mecanico);
        view = findViewById(R.id.container);

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
                llenarTransaccion();
                vaciar();
            }
        });


        cancelarMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
        ciMec.setText("");
        celularMec.setText("");
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
