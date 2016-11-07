package com.example.gunnar.agenciainc.FragmentsCatalogo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.gunnar.agenciainc.R;

public class MainDetallleVehivulo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detallle_vehivulo);

        initDetalle();
    }

    private static TextView tipo,
            modelo,
            marca,
            anio,
            potencia,
            precio,
            caracteristicas;
    private ImageView imagen;
    public void initDetalle(){

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





        tipo.setText(sql);
        modelo.setText(sql);
        marca.setText(sql);
        anio.setText(sql);
        potencia.setText(sql);
        precio.setText(sql);


        caracteristicas.setText(texto);




    }

}
