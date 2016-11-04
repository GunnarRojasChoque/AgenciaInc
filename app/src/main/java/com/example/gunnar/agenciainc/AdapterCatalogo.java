package com.example.gunnar.agenciainc;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gunnar on 4/11/2016.
 */

public class AdapterCatalogo  extends RecyclerView.Adapter<AdapterCatalogo.ViewHolderClass> {

    private static final String TAG = AdapterCatalogo.class.getSimpleName();
    private View view;
    public static int posItem;

    public class ViewHolderClass extends RecyclerView.ViewHolder {

        public ViewHolderClass(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
