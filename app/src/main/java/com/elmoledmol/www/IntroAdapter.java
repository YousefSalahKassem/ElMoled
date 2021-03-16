package com.elmoledmol.www;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class IntroAdapter extends FragmentPagerAdapter {
    public IntroAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new SplashScreenFragment();
            case 1:
                return new FirstScreen();
            case 2:
               return new SecondScreen();
            case 3:
                return new ThirdScreen();
            case 4:
                return new SignInScreen();
            case 5:
                return new SignUpScreen();
            case 6:
                return new forgotpassword();
            case 7:
                return new checkemail();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 8    ;
    }


}
