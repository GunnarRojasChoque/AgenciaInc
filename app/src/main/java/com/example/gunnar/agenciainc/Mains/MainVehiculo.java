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

import com.example.gunnar.agenciainc.BaseDeDatos.BDVehiculo;
import com.example.gunnar.agenciainc.R;
import com.example.gunnar.agenciainc.Vehiculo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class MainVehiculo extends AppCompatActivity {

    private static final String TAG = MainVehiculo.class.getSimpleName();
    // Datos del formulario.
    EditText modelo;
    EditText marca;
    EditText chasis;
    EditText anio;
    EditText precio;
    Spinner tipo;
    EditText caracteristicas;
    public static TextView fechaNow;
    Button fech;
    Button registro;
    Button cancelar;

    ImageView bitma;
    public Bitmap bitmap;
    public int year, month, day;
    // Dialog Date.
    int id_dialog = 0;
    // BD vehiculo.
    SQLiteDatabase database;

    private static final int REQUEST_CODE_ACTION_ADD_FROM_STORAGE = 0;
    private static final int REQUEST_CODE_ACTION_ADD_FROM_CAMERA = 1;
    private static final String BUNDLE_SAVED_BITMAPS = "bitmaps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_vehiculo);

        initCalendar();
        initRegistro();
       // MainDetalleVehiculo mdv=new MainDetalleVehiculo();

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
        tipo = (Spinner) findViewById(R.id.tipo);
        caracteristicas = (EditText) findViewById(R.id.carac);
        precio = (EditText) findViewById(R.id.precio);
        fechaNow = (TextView) findViewById(R.id.dateNow);
        registro = (Button) findViewById(R.id.registrar);
        cancelar = (Button) findViewById(R.id.Cancelar);
        fech = (Button) findViewById(R.id.date);
        bitma = (ImageView) findViewById(R.id.add);

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
                llenarBdVehiculo();
                vaciar();
            }
        });


        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaciar();
            }
        });
    }

    private void vaciar(){
        modelo.setText("");
        marca.setText("");
        chasis.setText("");
        anio.setText("");
        fechaNow.setText("");
        precio.setText("");
    }

    private void llenarBdVehiculo(){
        //Base de Datos Registro de vehiculo
        BDVehiculo bdVehiculo = new BDVehiculo(this);
        database = bdVehiculo.getWritableDatabase();

        Vehiculo vehiculo = new Vehiculo(modelo.getText().toString(), marca.getText().toString(),
                chasis.getText().toString(), Integer.parseInt(anio.getText().toString()),
                fechaNow.getText().toString(), Integer.parseInt(precio.getText().toString()));



        BitmapConvert bitmapConvert = new BitmapConvert();
        byte[] bytes;

        bytes = bitmapConvert.getBytes(bitmap);

        ContentValues values = new ContentValues();
        values.put(bdVehiculo.COLUMN_MODELO, vehiculo.getModelo());
        values.put(bdVehiculo.COLUMN_MARCA, vehiculo.getMarca());
        values.put(bdVehiculo.COLUMN_CHASIS, vehiculo.getChasis());
        values.put(bdVehiculo.COLUMN_ANIO, vehiculo.getAño());
        values.put(bdVehiculo.COLUMN_PRECIO, vehiculo.getPrecioIni());
        values.put(bdVehiculo.COLUMN_FECHA, vehiculo.getFechaIngreso());
        values.put(bdVehiculo.COLUMN_TIPO, tipo.getSelectedItem().toString());
        values.put(bdVehiculo.COLUMN_CARACTERISTICAS, caracteristicas.getText().toString());
        values.put(bdVehiculo.COLUMN_IMAGEN, bytes);

        long newRowId = database.insert(BDVehiculo.TABLE_VEHICULO_IMPORTADORA, null, values);

//         ContentValues valuesID = new ContentValues();
//         valuesID.put(BDVehiculo.COLUMN_ID, newRowId);
//
//         database.insert(BDVehiculo.COLUMN_ID, null, valuesID);
        Log.i(TAG, "llenarBdVehiculo: id return " + newRowId);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.action_add_from_camera == item.getItemId()) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, REQUEST_CODE_ACTION_ADD_FROM_CAMERA);
            return true;
        } else if (R.id.action_add_from_storage == item.getItemId()) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, REQUEST_CODE_ACTION_ADD_FROM_STORAGE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            if (REQUEST_CODE_ACTION_ADD_FROM_STORAGE == requestCode) {
                try {
                    InputStream stream = getContentResolver().openInputStream(
                            data.getData());
                    bitmap = BitmapFactory.decodeStream(stream);
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (REQUEST_CODE_ACTION_ADD_FROM_CAMERA == requestCode) {
                Bundle extras = data.getExtras();
                bitmap = (Bitmap) extras.get("data"); // Just a thumbnail, but works okay for this.
            }
        }

        if (bitmap != null) {
            bitma.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
