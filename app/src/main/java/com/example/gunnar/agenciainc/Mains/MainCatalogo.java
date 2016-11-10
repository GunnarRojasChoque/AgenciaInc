package com.example.gunnar.agenciainc.Mains;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.gunnar.agenciainc.FragmentsCatalogo.AdapterFragment;
import com.example.gunnar.agenciainc.R;

/**
 * for convert from bitmap to array bitmap.
 * Created by Gunnar on 4/11/2016.
 */

public class MainCatalogo extends AppCompatActivity {

    public static int posTab = 0;

    private static final String TAG = MainCatalogo.class.getSimpleName();
    public static Toolbar toolbar;
    public static ViewPager viewPager;
    public static AdapterFragment adapterFragment;
    public static TabLayout tabLayout;
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_catalogo);



        context = this;
        initToolbar();
        initViewPager();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapterFragment = new AdapterFragment(getSupportFragmentManager());

        viewPager.setAdapter(adapterFragment);

        viewPager.setOffscreenPageLimit(6);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.i(TAG, "onTabSelected: " + tab.getPosition());
                posTab = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //Prueba for Jhonajjjj
}
