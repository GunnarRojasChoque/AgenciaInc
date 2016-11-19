package com.example.gunnar.agenciainc.FragmentsCatalogo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gunnar.agenciainc.Mains.BitmapConvert;
import com.example.gunnar.agenciainc.R;

public class MainDetalleVehiculo extends AppCompatActivity {

    private static TextView tipo,
            modelo,
            marca,
            anio,
            potencia,
            precio,
            caracteristicas;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detalle_vehiculo);
        initDetalle();

        llenardatos(savedInstanceState);

    }

    public void llenardatos(Bundle bundle){

        bundle = getIntent().getExtras();

        String modelo_dato=bundle.getString("modelo");
        String marca_dato=bundle.getString("marca");
        String chasis_dato=bundle.getString("chasis");
        String anio_dato=bundle.getString("anio");
        String precio_dato=bundle.getString("precio");
        String potencia_dato=bundle.getString("potencia");
        String fecha_dato=bundle.getString("fecha");
        byte[] imagen_dato=bundle.getByteArray("imagen");
        String tipo_dato=bundle.getString("tipo");
        String caracteristicas_dato=bundle.getString("caracteristicas");

        tipo.setText(tipo_dato);
        modelo.setText(modelo_dato);
        marca.setText(marca_dato);
        anio.setText(anio_dato);
        potencia.setText(potencia_dato);
        precio.setText(precio_dato);

        caracteristicas.setText(caracteristicas_dato);

        BitmapConvert bitmapConvert=new BitmapConvert();
        Bitmap bitmap = bitmapConvert.getImage(imagen_dato);
        imagen.setImageBitmap(bitmap);



    }
    public void initDetalle(){

        tipo=(TextView)findViewById(R.id.tvTipoV);
        modelo=(TextView)findViewById(R.id.tvModeloV);
        marca=(TextView)findViewById(R.id.tvMarcaV);
        anio=(TextView)findViewById(R.id.tvAñoV);
        potencia=(TextView)findViewById(R.id.tvPotenciaV);
        precio=(TextView)findViewById(R.id.tvprecioV) ;
        caracteristicas=(TextView)findViewById(R.id.tvCaracteristicas);
        imagen=(ImageView)findViewById(R.id.ivImagen);
    }

}
