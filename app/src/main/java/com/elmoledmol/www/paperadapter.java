package com.elmoledmol.www;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class paperadapter extends FragmentPagerAdapter {

    public paperadapter(@NonNull FragmentManager fm) {
        super(fm);

    }
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new Description();

            case 1:
                return new reviews();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

}
