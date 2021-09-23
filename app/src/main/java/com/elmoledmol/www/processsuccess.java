package com.elmoledmol.www;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class processsuccess extends AppCompatActivity {
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processsuccess);
        getSupportActionBar().hide();
        cardView=findViewById(R.id.skip);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(processsuccess.this,MainActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(processsuccess.this,MainActivity.class));
    }
}