package com.elmoledmol.www;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pageradaptermain extends FragmentPagerAdapter {
    public pageradaptermain(@NonNull FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new homefragment();
            case 1:
                return new onsale();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2    ;
    }

}
