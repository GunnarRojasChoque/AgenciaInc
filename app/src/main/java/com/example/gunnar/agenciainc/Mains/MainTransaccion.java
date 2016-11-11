package com.example.gunnar.agenciainc.Mains;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gunnar.agenciainc.BaseDeDatos.BDTransaccion;
import com.example.gunnar.agenciainc.R;
import com.example.gunnar.agenciainc.Transaccion;


import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import static android.content.ContentValues.TAG;
import static com.example.gunnar.agenciainc.R.id.tipo;
import static com.example.gunnar.agenciainc.R.string.caracteristicas;

/**
 * Created by Max on 10/11/2016.
 */

public class MainTransaccion extends AppCompatActivity {

    private static final String TAG = MainVehiculo.class.getSimpleName();

    //datos del formulario de transaccion
    public static EditText comprador;
    public static EditText vendedor;
    public static EditText modelo;
    public static EditText descuento;
    public static EditText precioTotal;
    public static EditText numeroImport;
    public static Button fechaTra;
    public static EditText ciudad;

    public static Button registroTra;
    public static Button cancelarTra;

    public static int year, month, day;

    // Dialog Date.
    int id_dialog = 0;
    // BD transaccion.
    SQLiteDatabase database;
    //para las imagenes
    ImageView bitma;
    public Bitmap bitmap;

    public static TextView fechaNow;


    private static final int REQUEST_CODE_ACTION_ADD_FROM_STORAGE = 0;
    private static final int REQUEST_CODE_ACTION_ADD_FROM_CAMERA = 1;
    private static final String BUNDLE_SAVED_BITMAPS = "bitmaps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_transaccion);

        initCalendar();
        initRegistrtoCli();

    }


    private void initCalendar() {
        final Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void initRegistrtoCli() {
//
          comprador = (EditText) findViewById(R.id.comprador);
          vendedor = (EditText) findViewById(R.id.vendedor);
          modelo = (EditText) findViewById(R.id.modelo);
          descuento = (EditText) findViewById(R.id.descuento);
          precioTotal = (EditText) findViewById(R.id.precioTotal);
          numeroImport = (EditText) findViewById(R.id.numeroImport);
          fechaTra = (Button) findViewById(R.id.fechaTra);
          ciudad = (EditText) findViewById(R.id.ciudad);
//
//        registroTra = (Button) findViewById(R.id.registrarse);
//        cancelarTra = (Button) findViewById(R.id.cancelar);


        MainTransaccion.fechaNow = (TextView) findViewById(R.id.tvfecha);
        MainTransaccion.fechaNow.setText(year + "/" + month + "/" + day);

        fechaTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(id_dialog);
            }
        });

        registroTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Cliente client = new Cliente(nombresCli.getText().toString(), apellidosCli.getText().toString(),
                        Integer.valueOf(celularCli.getText().toString()), Integer.valueOf(ciCli.getText().toString()),
                        fechaCli.getText().toString(), correoCli.getText().toString(), rb.getText().toString());
                */
                llenarTransaccion();


            }
        });

        cancelarTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vendedor.setText("");
                comprador.setText("");
                modelo.setText("");
                descuento.setText("");
                precioTotal.setText("");
                numeroImport.setText("");
                ciudad.setText("");

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
            MainVehiculo.fechaNow.setText(year + "/" + month + "/" + day);
        }
    };

    private void llenarTransaccion(){
        Transaccion transaccion = new Transaccion(comprador.getText().toString(),vendedor.getText().toString(),
                modelo.getText().toString(), Integer.parseInt(descuento.getText().toString()),
                Integer.parseInt(precioTotal.getText().toString()),
                numeroImport.getText().toString(), ciudad.getText().toString());

        //Base de Datos transaccion
        BDTransaccion bdTransaccion = new BDTransaccion(this);
        database = bdTransaccion.getWritableDatabase();
         /*
        BitmapConvert bitmapConvert = new BitmapConvert();
        byte[] bytes;
        bytes = bitmapConvert.getBytes(bitmap);*/

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
