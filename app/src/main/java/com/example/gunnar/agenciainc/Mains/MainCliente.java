package com.example.gunnar.agenciainc.Mains;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.gunnar.agenciainc.BaseDeDatos.BDCliente;
import com.example.gunnar.agenciainc.Cliente;
import com.example.gunnar.agenciainc.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_cliente);

        context = this;
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
                llenarCliente();
                vaciar();
            }
        });


        cancelarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaciar();
            }
        });

    }

    private void vaciar(){
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
