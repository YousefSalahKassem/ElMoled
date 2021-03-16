package com.elmoledmol.www;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class showimage extends AppCompatActivity {
    PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showimage);
        getSupportActionBar().hide();
        photoView = findViewById(R.id.showmyimage);
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        Picasso.get().load(image).resize(photoView.getContext().getDisplay().getWidth() - 20, photoView.getContext().getDisplay().getHeight() / 2).into(photoView);
    }
}