package com.example.gunnar.agenciainc;

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
import com.example.gunnar.agenciainc.Mains.BitmapConvert;
import com.example.gunnar.agenciainc.Mains.MainCatalogo;

/**
 * Created by Gunnar on 4/11/2016.
 */

public class AdapterCatalogo  extends RecyclerView.Adapter<AdapterCatalogo.ViewHolderClass> {

    private static final String TAG = AdapterCatalogo.class.getSimpleName();
    private int posItem;

    public AdapterCatalogo(int nro){
        this.posItem = nro;
    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {

        ImageView background;
        TextView marca;
        TextView modelo;
        public ViewHolderClass(View itemView) {
            super(itemView);

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

        try {
            switch (posItem){
                case 1:

                    BDVehiculo vehiculo = new BDVehiculo(MainCatalogo.context);
                    SQLiteDatabase db = vehiculo.getReadableDatabase();

                    String[] projection = {
                            vehiculo.COLUMN_MODELO,
                            vehiculo.COLUMN_MARCA,
                            vehiculo.COLUMN_IMAGEN
                    };

                    String sortOrder =
                            vehiculo.COLUMN_MODELO + " DESC";

                    String selection = vehiculo.COLUMN_TIPO + " = ?";
                    String[] selectionArgs = { "Automovil" };

                    Cursor c = db.query(
                            vehiculo.TABLE_VEHICULO_IMPORTADORA,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder
                    );

                    if(c.moveToFirst()){
                        do {
                            holder.marca.setText(c.getString(c.getColumnIndexOrThrow(vehiculo.COLUMN_MARCA)));
                            holder.modelo.setText(c.getString(c.getColumnIndexOrThrow(vehiculo.COLUMN_MODELO)));

                            BitmapConvert convert = new BitmapConvert();
                            Bitmap bitmap = convert.getImage(c.getBlob(c.getColumnIndexOrThrow(vehiculo.COLUMN_IMAGEN)));

                            holder.background.setImageBitmap(bitmap);
                        }while (c.moveToNext());
                    }
            }
        }catch (Exception e){e.printStackTrace();}


    }

    private int sizeBD(){
        BDVehiculo vehiculo = new BDVehiculo(MainCatalogo.context);
        SQLiteDatabase db = vehiculo.getReadableDatabase();

        String[] projection = {
                vehiculo.COLUMN_ID,
        };

        String sortOrder =
                vehiculo.COLUMN_ID + " DESC";

        String selection = vehiculo.COLUMN_TIPO + " = ?";
        String[] selectionArgs = { "Automovil" };

        Cursor c = db.query(
                vehiculo.TABLE_VEHICULO_IMPORTADORA,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        return c.getColumnCount();
    }
    @Override
    public int getItemCount() {
        return 2;
    }
}
