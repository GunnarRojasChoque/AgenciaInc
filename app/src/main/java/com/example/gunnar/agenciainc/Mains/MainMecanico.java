package com.example.gunnar.agenciainc.Mains;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.gunnar.agenciainc.R;

import java.util.Calendar;

public class MainMecanico extends AppCompatActivity {

    public static EditText nombresMec;
    public static EditText apellidosMec;
    public static EditText ciMec;
    public static EditText celularMec;
    public static Button registrarMec;
    public static Button cancelarMec;
    public static TextView fechaNow;
    public static Button fech;
    public static EditText diagnostico;
    public static EditText chasis;
    public static int year, month, day;
    public static final int id_dialog = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_mecanico);

        initCalendar();
        initRegistrtoMec();
    }

    private void initCalendar() {
        final Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void initRegistrtoMec() {

        nombresMec = (EditText) findViewById(R.id.etnombres);
        apellidosMec = (EditText) findViewById(R.id.etapellidos);
        chasis = (EditText) findViewById(R.id.chasis);
        diagnostico = (EditText) findViewById(R.id.etdiasnostico) ;
        ciMec = (EditText) findViewById(R.id.etci);
        fechaNow = (TextView) findViewById(R.id.dateNow);
        celularMec = (EditText) findViewById(R.id.etcelular);
        registrarMec = (Button) findViewById(R.id.btnregistrar);
        cancelarMec = (Button) findViewById(R.id.btncancelar);




        //fechaMec.setOnClickListener(new View.OnClickListener() {
          //  @Override
        //    public void onClick(View view) {
          //      showDialog(id_dialog);
            //}
        //});



     //   registrarMec.setOnClickListener(new View.OnClickListener() {
     //       @Override
         //   public void onClick(View view) {
       //         Mecanico mec = new Mecanico(nombresMec.getText().toString(), apellidosMec.getText().toString(),
         //               Integer.valueOf(celularMec.getText().toString()), Integer.valueOf(ciMec.getText().toString()),
       //                 );


         //   }
        //});


        cancelarMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombresMec.setText("");
                apellidosMec.setText("");
                ciMec.setText("");

                celularMec.setText("");

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
        }
    };
}
