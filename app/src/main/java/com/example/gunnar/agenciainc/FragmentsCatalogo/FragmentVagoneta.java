package com.example.gunnar.agenciainc.FragmentsCatalogo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gunnar.agenciainc.AdapterCatalogo;
import com.example.gunnar.agenciainc.R;

/**
 * Fragment for Automovil
 * Created by Gunnar on 6/11/2016.
 */

public class FragmentVagoneta extends Fragment {

    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    public static RecyclerView.LayoutManager layoutManager;
    public static View contentView;
    public static boolean activo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.catalogo_vagoneta, null);
        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activo = true;

        recyclerView = (RecyclerView) contentView.findViewById(R.id.lista_vagoneta);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterCatalogo(2);
        recyclerView.setAdapter(adapter);
    }

}
