package com.elmoledmol.www;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashLoginActivity extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_login);
        getSupportActionBar().hide();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        IntroAdapter adapter = new IntroAdapter(getSupportFragmentManager(),getApplicationContext());
        viewPager.setAdapter(adapter);
    }



}