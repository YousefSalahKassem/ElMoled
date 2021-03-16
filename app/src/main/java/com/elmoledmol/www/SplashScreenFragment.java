package com.elmoledmol.www;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class SplashScreenFragment extends Fragment {

    ViewPager viewPager;

    public SplashScreenFragment() {
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_splash_screen, container, false);

        viewPager = getActivity().findViewById(R.id.viewPager);


        SharedPreferences preferences = getActivity().getSharedPreferences("preferences", 0);
        SharedPreferences.Editor editor = preferences.edit();
        boolean firstRun = preferences.getBoolean("firstRun", true);


        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("checkbox",0);
        String checkbox = sharedPreferences.getString("remember","true");

        new CountDownTimer(1500, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                if (!firstRun) {
                    if (checkbox.equals("true")) {
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        getContext().startActivity(intent);
                    } else if (checkbox.equals("false")) {
                        viewPager.setCurrentItem(4,false);
                    }
                } else if (firstRun) {
                    viewPager.setCurrentItem(1,true);
                    editor.putBoolean("firstRun", false);
                    editor.apply();
                }
            }
        }.start();


        return view;
    }
}
