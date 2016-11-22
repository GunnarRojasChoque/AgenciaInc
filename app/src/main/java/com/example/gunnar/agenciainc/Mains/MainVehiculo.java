package com.example.gunnar.agenciainc.Mains;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

import com.example.gunnar.agenciainc.BaseDeDatos.BDContrato;
import com.example.gunnar.agenciainc.BaseDeDatos.BDVehiculo;
import com.example.gunnar.agenciainc.R;
import com.example.gunnar.agenciainc.Validador;
import com.example.gunnar.agenciainc.Vehiculo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * for convert from bitmap to array bitmap.
 * Created by Gunnar on 6/11/2016.
 */

public class MainVehiculo extends AppCompatActivity {

    private static final String TAG = MainVehiculo.class.getSimpleName();
    // Datos del formulario.
    EditText modelo;
    EditText marca;
    EditText chasis;
    EditText anio;
    EditText precio;
    EditText potencia;
    Spinner tipo;
    EditText caracteristicas;
    public static TextView fechaNow;
    Button fech;
    Button registro;
    Button cancelar;

    ImageView bitma;
    public Bitmap bitmap;
    View view;

    public int year, month, day;
    // Dialog Date.
    int id_dialog = 0;
    // BD vehiculo.
    SQLiteDatabase database;

    private static final int REQUEST_CODE_ACTION_ADD_FROM_STORAGE = 0;
    private static final int REQUEST_CODE_ACTION_ADD_FROM_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_vehiculo);
        view = findViewById(R.id.container);

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
        potencia = (EditText) findViewById(R.id.potencia);
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
            }
        });


        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaciar();
            }
        });
    }

    private void vaciar() {
        modelo.setText("");
        marca.setText("");
        chasis.setText("");
        anio.setText("");
        fechaNow.setText("");
        precio.setText("");
        potencia.setText("");
        caracteristicas.setText("");
    }

    private void llenarBdVehiculo() {
        //Base de Datos Registro de vehiculo
        BDVehiculo bdVehiculo = new BDVehiculo(this);
        database = bdVehiculo.getWritableDatabase();

        Vehiculo vehiculo = null;

        byte[] bytes = null;
        int res = getID(tipo.getSelectedItemPosition() + 1);
//        Log.i(TAG, "llenarBdVehiculo: spinner pos" + res);

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        boolean estado = true;
        try {
            bytes = BitmapConvert.getBytes(bitmap);
        } catch (Exception e) {
            estado = false;
        }


        String vaModelo = modelo.getText().toString();
        String vaMarca = marca.getText().toString();
        String vaChasis = chasis.getText().toString();
        String vaAio = anio.getText().toString();
        String vaFecha = fechaNow.getText().toString();
        String vaPrecio = precio.getText().toString();
        String vaPotencia = potencia.getText().toString();
        String vaTipo = tipo.getSelectedItem().toString();
        String vaCaracteristicas = caracteristicas.getText().toString();

        if (vaModelo.length() == 0) {
            aviso();
        } else if (vaMarca.length() == 0) {
            aviso();
        } else if (vaChasis.length() == 0) {
            aviso();
        } else if (vaAio.length() == 0) {
            aviso();
        } else if (vaPrecio.length() == 0) {
            aviso();
        } else if (vaPotencia.length() == 0) {
            aviso();
        } else if (vaTipo.length() == 0) {
            aviso();
        } else if (vaCaracteristicas.length() == 0) {
            aviso();
        } else {
            if (!Validador.modeloV(vaModelo)) {
                alertDialog.setMessage("Nombre modelo no valido.");
                alertDialog.setButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
                modelo.setText("");
            } else if (!Validador.marcaV(vaMarca)) {
                alertDialog.setMessage("Nombre de marca no valido.");
                alertDialog.setButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
                marca.setText("");
            } else if (!Validador.chasisV(vaChasis)) {
                alertDialog.setMessage("Chasis no valido.");
                alertDialog.setButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
                chasis.setText("");
            } else if (!Validador.anioV(vaAio)) {
                alertDialog.setMessage("Año no valido.");
                alertDialog.setButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
                anio.setText("");
            } else if (!Validador.precioV(vaPrecio)) {
                alertDialog.setMessage("Precio no valido.");
                alertDialog.setButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
                precio.setText("");
            } else if (!estado) {
                alertDialog.setMessage("Por favor seleccione una Imagen o tome una fotografia.");
                alertDialog.setButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();

            } else {

                vehiculo = new Vehiculo(vaModelo, vaMarca, vaChasis, Integer.parseInt(vaAio), fechaNow.getText().toString(),
                        Integer.parseInt(vaPrecio), vaPotencia, vaTipo,
                        vaCaracteristicas, bytes);

                ContentValues values = new ContentValues();
                values.put(BDVehiculo.COLUMN_ID, res);
                values.put(BDVehiculo.COLUMN_MODELO, vehiculo.getModelo());
                values.put(BDVehiculo.COLUMN_MARCA, vehiculo.getMarca());
                values.put(BDVehiculo.COLUMN_CHASIS, vehiculo.getChasis());
                values.put(BDVehiculo.COLUMN_ANIO, vehiculo.getAño());
                values.put(BDVehiculo.COLUMN_PRECIO, vehiculo.getPrecioIni());
                values.put(BDVehiculo.COLUMN_POTENCIA, vehiculo.getPotencia());
                values.put(BDVehiculo.COLUMN_FECHA, vehiculo.getFechaIngreso());
                values.put(BDVehiculo.COLUMN_TIPO, vehiculo.getTipo());
                values.put(BDVehiculo.COLUMN_CARACTERISTICAS, vehiculo.getCaracteristicas());
                values.put(BDVehiculo.COLUMN_IMAGEN, bytes);

                long newRowId = database.insert(BDVehiculo.TABLE_VEHICULO_IMPORTADORA, null, values);

                Log.i(TAG, "llenarBdVehiculo: id return " + newRowId);
                vaciar();
            }
        }


    }

    private void aviso() {
        Snackbar.make(view, "Llene todos los campos.", Snackbar.LENGTH_LONG).setAction("Close", null).show();
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

    private int getID(int posItem) {
        switch (posItem) {
            case 1:
                return sizeBD(getString(R.string.automovil));
            case 2:
                return sizeBD(getString(R.string.vagoneta));
            case 3:
                return sizeBD(getString(R.string.jepp));
            case 4:
                return sizeBD(getString(R.string.camioneta));
            case 5:
                return sizeBD(getString(R.string.minibus));
            case 6:
                return sizeBD(getString(R.string.trailer));
            case 7:
                return sizeBD(getString(R.string.motocicleta_name));
            default:
                return 0;
        }
    }

    private int sizeBD(String cad) {
        BDVehiculo vehiculo = new BDVehiculo(this);
        SQLiteDatabase db = vehiculo.getReadableDatabase();

        String[] projection = {
                vehiculo.COLUMN_ID,
        };

        String selection = vehiculo.COLUMN_TIPO + " = ?";
        String[] selectionArgs = {cad};

        Cursor c = db.query(
                vehiculo.TABLE_VEHICULO_IMPORTADORA,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return c.getCount();
    }


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

    private void llenarDatosContrato() {
        ContentValues values = new ContentValues();
        values.put(BDContrato.COLUMN_APELLIDO, "");
        values.put(BDContrato.COLUMN_CARGO, "");
        values.put(BDContrato.COLUMN_CI, "");
        values.put(BDContrato.COLUMN_CORREO, "");
        values.put(BDContrato.COLUMN_DIRECCION, "");
        values.put(BDContrato.COLUMN_FECHA_FIN, "");
        values.put(BDContrato.COLUMN_FECHA_INICIO, "");
        values.put(BDContrato.COLUMN_NACIMIENTO, "");
        values.put(BDContrato.COLUMN_NOMBRE, "");
        values.put(BDContrato.COLUMN_CARGO, "");
        values.put(BDContrato.COLUMN_NOMBRE_EMPLEADOR, "");
        values.put(BDContrato.COLUMN_PAIS, "");
        values.put(BDContrato.COLUMN_SALARIO, "");
        values.put(BDContrato.COLUMN_TELEFONO, "");
        values.put(BDContrato.DATABASE_CLIENTE, "");
        values.put(BDContrato.TABLE_CONTRATO_EMPLEADO, "");
        values.put(BDContrato.COLUMN_ID, "");

    }
}

