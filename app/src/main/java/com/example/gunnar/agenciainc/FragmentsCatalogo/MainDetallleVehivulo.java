package com.example.gunnar.agenciainc.FragmentsCatalogo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gunnar.agenciainc.BaseDeDatos.BDVehiculo;
import com.example.gunnar.agenciainc.Mains.MainCatalogo;
import com.example.gunnar.agenciainc.R;

public class MainDetallleVehivulo extends AppCompatActivity {

    private static TextView tipo,
            modelo,
            marca,
            anio,
            potencia,
            precio,
            caracteristicas;
    private ImageView imagen;
    public SQLiteDatabase baseVehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detallle_vehivulo);
        initDetalle();


    }


//    public MainDetallleVehivulo(SQLiteDatabase base){
//        this.baseVehiculo=base;
//
//    }
    public void initDetalle(){
        BDVehiculo vehiculo = new BDVehiculo(this);
        baseVehiculo = vehiculo.getReadableDatabase();

        tipo=(TextView)findViewById(R.id.tvTipoV);
        modelo=(TextView)findViewById(R.id.tvModeloV);
        marca=(TextView)findViewById(R.id.tvMarcaV);
        anio=(TextView)findViewById(R.id.tvAñoV);
        potencia=(TextView)findViewById(R.id.tvPotenciaV);
        precio=(TextView)findViewById(R.id.tvprecioV) ;
        caracteristicas=(TextView)findViewById(R.id.tvCaracteristicas);
        imagen=(ImageView)findViewById(R.id.ivImagen);


        imagen.setImageResource(R.drawable.car);


        String sql="consulta sql";
        String texto ="CON EL SISTEMA ALL-MODE 4X4-I PODRÁS AVANZAR " +
                "EN CUALQUIER SUPERFICIE. ESCOGE EL MODO 2WD PARA OBTENER UNA" +
                " EFICIENCIA MÁXIMA EN EL CONSUMO DE COMBUSTIBLE. EL MODO AUTO," +
                " MONITOREA CONSTANTEMENTE LAS CONDICIONES Y AJUSTA LA POTENCIA " +
                "DE LAS RUEDAS DELANTERAS Y TRASERAS PARA UNA MEJOR TRACCIÓN. " +
                "¿CAMINOS DIFÍCILES? EL MODO 4WD LOCK ES TU MEJOR OPCIÓN.*";



        SQLiteDatabase dbv=baseVehiculo;


        String sql2="SELECT * FROM vehiculo ";

        int id_dato=0;
        String modelo_dato=" ";
        String marca_dato=" ";
        String chasis_dato=" ";
        String anio_dato=" ";
        String precio_dato=" ";
        String fecha_dato=" ";
        String imagen_dato=" ";
        String tipo_dato=" ";
        String caracteristicas_dato=" ";


        Cursor c=dbv.rawQuery(sql2,null);

        //try{
            if (c.moveToFirst()){
                id_dato=c.getInt(0);
                modelo_dato=c.getString(1);
                marca_dato=c.getString(2);
                chasis_dato=c.getString(3);
                anio_dato=c.getString(4);
                precio_dato=c.getString(5);
                fecha_dato=c.getString(6);

                tipo_dato=c.getString(8);
                caracteristicas_dato=c.getString(9);


            }


        //}catch (Exception e){


        //}

        tipo.setText(tipo_dato);
        modelo.setText(modelo_dato);
        marca.setText(marca_dato);
        anio.setText(anio_dato);
        potencia.setText(sql);
        precio.setText(precio_dato);


        caracteristicas.setText(caracteristicas_dato);




    }

}
