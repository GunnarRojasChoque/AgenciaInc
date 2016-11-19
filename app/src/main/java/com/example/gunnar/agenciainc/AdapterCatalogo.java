package com.example.gunnar.agenciainc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gunnar.agenciainc.BaseDeDatos.BDVehiculo;
import com.example.gunnar.agenciainc.FragmentsCatalogo.MainDetalleVehiculo;
import com.example.gunnar.agenciainc.Mains.BitmapConvert;
import com.example.gunnar.agenciainc.Mains.MainActivity;
import com.example.gunnar.agenciainc.Mains.MainCatalogo;

/**
 * Adapter for catalogo
 * Created by Gunnar on 4/11/2016.
 */

public class AdapterCatalogo extends RecyclerView.Adapter<AdapterCatalogo.ViewHolderClass> {

    private int posItem;
    private SQLiteDatabase db;
    private String[] projection;
    private String selection;
    private String[] selectionArgs;

    public AdapterCatalogo(int nro) {
        this.posItem = nro;
    }


    class ViewHolderClass extends RecyclerView.ViewHolder {

        ImageView background;
        TextView marca;
        TextView modelo;

        ViewHolderClass(View itemView) {
            super(itemView);

            BDVehiculo vehiculo = new BDVehiculo(MainActivity.context);
            db = vehiculo.getReadableDatabase();

            modelo = (TextView) itemView.findViewById(R.id.modelo_name);
            marca = (TextView) itemView.findViewById(R.id.marca_name);
            background = (ImageView) itemView.findViewById(R.id.imagen_coche);
        }
    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_catalogo, null);

        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {

        projection = new String[]{
                BDVehiculo.COLUMN_MODELO,
                BDVehiculo.COLUMN_MARCA,
                BDVehiculo.COLUMN_CHASIS,
                BDVehiculo.COLUMN_ANIO,
                BDVehiculo.COLUMN_PRECIO,
                BDVehiculo.COLUMN_POTENCIA,
                BDVehiculo.COLUMN_FECHA,
                BDVehiculo.COLUMN_IMAGEN,
                BDVehiculo.COLUMN_TIPO,
                BDVehiculo.COLUMN_CARACTERISTICAS
        };

        selection = BDVehiculo.COLUMN_ID + " = ?" + " AND " + BDVehiculo.COLUMN_TIPO + " =?";
        selectionArgs = null;

        try {
            switch (posItem) {
                case 1:
                    selectionArgs = new String[]{(position + ""), MainCatalogo.context.getString(R.string.automovil)};
                    break;
                case 2:
                    selectionArgs = new String[]{(position + ""), MainCatalogo.context.getString(R.string.vagoneta)};
                    break;
                case 3:
                    selectionArgs = new String[]{(position + ""), MainCatalogo.context.getString(R.string.jepp)};
                    break;
                case 4:
                    selectionArgs = new String[]{(position + ""), MainCatalogo.context.getString(R.string.camioneta)};
                    break;
                case 5:
                    selectionArgs = new String[]{(position + ""), MainCatalogo.context.getString(R.string.minibus)};
                    break;
                case 6:
                    selectionArgs = new String[]{(position + ""), MainCatalogo.context.getString(R.string.trailer)};
                    break;
                case 7:
                    selectionArgs = new String[]{(position + ""), MainCatalogo.context.getString(R.string.motocicleta_name)};
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Cursor c = db.query(
                BDVehiculo.TABLE_VEHICULO_IMPORTADORA,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                holder.marca.setText(c.getString(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_MARCA)));
                holder.modelo.setText(c.getString(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_MODELO)));

                BitmapConvert convert = new BitmapConvert();
                Bitmap bitmap = convert.getImage(c.getBlob(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_IMAGEN)));

                holder.background.setImageBitmap(bitmap);
            } while (c.moveToNext());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = db.query(
                        BDVehiculo.TABLE_VEHICULO_IMPORTADORA,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null
                );

                if(c.moveToNext()){
                    do {

                        Intent intent = new Intent(MainCatalogo.context, MainDetalleVehiculo.class);
                        intent.putExtra("modelo", c.getString(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_MODELO)));
                        intent.putExtra("marca", c.getString(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_MARCA)));
                        intent.putExtra("chasis", c.getString(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_CHASIS)));
                        intent.putExtra("anio", c.getString(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_ANIO)));
                        intent.putExtra("precio", c.getString(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_PRECIO)));
                        intent.putExtra("potencia", c.getString(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_POTENCIA)));
                        intent.putExtra("fecha", c.getString(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_FECHA)));
                        intent.putExtra("imagen", c.getBlob(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_IMAGEN)));
                        intent.putExtra("tipo", c.getString(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_TIPO)));
                        intent.putExtra("caracteristicas", c.getString(c.getColumnIndexOrThrow(BDVehiculo.COLUMN_CARACTERISTICAS)));

                        MainCatalogo.context.startActivity(intent);

                    }while (c.moveToNext());
                }
            }
        });

    }


    private int sizeBD(String cad) {
        BDVehiculo vehiculo = new BDVehiculo(MainCatalogo.context);
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

    private int getID(int posItem) {
        switch (posItem) {
            case 1:
                return sizeBD(MainActivity.context.getString(R.string.automovil));
            case 2:
                return sizeBD(MainActivity.context.getString(R.string.vagoneta));
            case 3:
                return sizeBD(MainActivity.context.getString(R.string.jepp));
            case 4:
                return sizeBD(MainActivity.context.getString(R.string.camioneta));
            case 5:
                return sizeBD(MainActivity.context.getString(R.string.minibus));
            case 6:
                return sizeBD(MainActivity.context.getString(R.string.trailer));
            case 7:
                return sizeBD(MainActivity.context.getString(R.string.motocicleta_name));
            default:
                return 0;
        }
    }

    @Override
    public int getItemCount() {
        return getID(posItem);
    }
}
