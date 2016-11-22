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

import com.example.gunnar.agenciainc.BaseDeDatos.BDTransaccion;
import com.example.gunnar.agenciainc.R;
import com.example.gunnar.agenciainc.Transaccion;

import java.util.Calendar;

/**
 * Created by Max on 10/11/2016.
 */

public class MainTransaccion extends AppCompatActivity {

    //datos del formulario de transaccion
    EditText comprador;
    EditText vendedor;
    EditText modelo;
    EditText descuento;
    EditText precioTotal;
    EditText numeroImport;
    Button fechaTra;
    EditText ciudad;

    Button registroTra;
    Button cancelarTra;

    public static int year, month, day;

    // Dialog Date.
    int id_dialog = 0;
    View view;

    // BD transaccion.
    SQLiteDatabase database;
    TextView fechaNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_transaccion);
        view = findViewById(R.id.container);

        initCalendar();
        initRegistrtoCli();

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

        comprador = (EditText) findViewById(R.id.comprador);
        vendedor = (EditText) findViewById(R.id.vendedor);
        modelo = (EditText) findViewById(R.id.modeloT);
        descuento = (EditText) findViewById(R.id.descuento);
        precioTotal = (EditText) findViewById(R.id.precioTotal);
        numeroImport = (EditText) findViewById(R.id.nroImportacion);
        fechaTra = (Button) findViewById(R.id.fecha);
        ciudad = (EditText) findViewById(R.id.ciudad);

        registroTra = (Button) findViewById(R.id.btnRegistrarT);
        cancelarTra = (Button) findViewById(R.id.btnCancelarT);

        fechaNow = (TextView) findViewById(R.id.tvfecha);
        fechaNow.setText(year + "/" + month + "/" + day);

        fechaTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(id_dialog);
            }
        });

        registroTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llenarTransaccion();
            }
        });

        cancelarTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void vaciar() {
        vendedor.setText("");
        comprador.setText("");
        modelo.setText("");
        descuento.setText("");
        precioTotal.setText("");
        numeroImport.setText("");
        ciudad.setText("");
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

    private void llenarTransaccion() {
        Transaccion transaccion = new Transaccion(comprador.getText().toString(), vendedor.getText().toString(),
                modelo.getText().toString(), Integer.parseInt(descuento.getText().toString()),
                Integer.parseInt(precioTotal.getText().toString()),
                numeroImport.getText().toString(), ciudad.getText().toString());

        //Base de Datos transaccion
        BDTransaccion bdTransaccion = new BDTransaccion(this);
        database = bdTransaccion.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BDTransaccion.COLUMN_COMPRADOR, transaccion.getComprador());
        values.put(BDTransaccion.COLUMN_VENDEDOR, transaccion.getVendedor());
        values.put(BDTransaccion.COLUMN_MODELO, transaccion.getModelo());
        values.put(BDTransaccion.COLUMN_DESCUENTO, transaccion.getDescuento());
        values.put(BDTransaccion.COLUMN_PRECIOTOTAL, transaccion.getPrecioTotal());
        values.put(BDTransaccion.COLUMN_NUMERO_IMPORTACION, transaccion.getNumeroImportacion());

        values.put(BDTransaccion.COLUMN_FECHA, transaccion.getCiudad());
        values.put(BDTransaccion.COLUMN_CIUDAD, transaccion.getCiudad());


        long newRowId = database.insert(BDTransaccion.TABLE_FORMULARIO_TRANSACCION, null, values);

        //Log.i(TAG, "llenarBdTransaccion: id return " + newRowId);
    }


}
