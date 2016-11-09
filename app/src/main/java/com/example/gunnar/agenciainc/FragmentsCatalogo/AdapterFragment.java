package com.example.gunnar.agenciainc.FragmentsCatalogo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.gunnar.agenciainc.Mains.MainCatalogo;
import com.example.gunnar.agenciainc.R;

/**
 * Class for Car
 * Created by Gunnar on 05/11/2016.
 */

public class AdapterFragment extends FragmentPagerAdapter {

    public AdapterFragment(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putInt("nro", 1);


        Bundle bundle2 = new Bundle();
        bundle2.putInt("nro", 2);

        Bundle bundle3 = new Bundle();
        bundle3.putInt("nro", 3);


        Bundle bundle4 = new Bundle();
        bundle4.putInt("nro", 4);


        Bundle bundle5 = new Bundle();
        bundle5.putInt("nro", 5);


        Bundle bundle6 = new Bundle();
        bundle6.putInt("nro", 6);


        Bundle bundle7 = new Bundle();
        bundle7.putInt("nro", 7);


        Fragment fs = new FragmentAutomovil();
        fs.setArguments(bundle);


        Fragment fs2 = new FragmentAutomovil();
        fs2.setArguments(bundle2);

        Fragment fg3 = new FragmentAutomovil();
        fg3.setArguments(bundle3);

        Fragment fg4 = new FragmentAutomovil();
        fg4.setArguments(bundle4);

        Fragment fg5 = new FragmentAutomovil();
        fg5.setArguments(bundle5);

        Fragment fg6 = new FragmentAutomovil();
        fg6.setArguments(bundle6);

        Fragment fg7 = new FragmentAutomovil();
        fg7.setArguments(bundle7);

        switch (position) {
            case 0:
                return fs;
            case 1:
                return fs2;
            case 2:
                return fg3;
            case 3:
                return fg4;
            case 4:
                return fg5;
            case 5:
                return fg6;
            case 6:
                return fg7;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return MainCatalogo.context.getString(R.string.automovil);
            case 1:
                return MainCatalogo.context.getString(R.string.vagoneta);
            case 2:
                return MainCatalogo.context.getString(R.string.jepp);
            case 3:
                return MainCatalogo.context.getString(R.string.camioneta);
            case 4:
                return MainCatalogo.context.getString(R.string.minibus);
            case 5:
                return MainCatalogo.context.getString(R.string.trailer);
            case 6:
                return MainCatalogo.context.getString(R.string.motocicleta_name);
        }
        return null;
    }
}
