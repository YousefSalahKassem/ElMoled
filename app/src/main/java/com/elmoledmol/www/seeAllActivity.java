package com.elmoledmol.www;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class seeAllActivity extends AppCompatActivity {
    CardView card;
    TextView  category;
    RecyclerView seeAllRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);
        getSupportActionBar().hide();

        card = findViewById(R.id.card8);
        card.setBackgroundResource(R.drawable.corner);

        category = findViewById(R.id.seeAllCategory);

Intent intent=getIntent();
String text=intent.getStringExtra("text");
category.setText(text);
ArrayList<newsinheret> list=new ArrayList<>();

list=intent.getParcelableArrayListExtra("list");
        System.out.println(list);
        seeAllRecycler = findViewById(R.id.seeAllRecycler);
        seeAllRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,RecyclerView.VERTICAL,false));
        seeAllAdapter seeAllAdapter=new seeAllAdapter(list);
        seeAllRecycler.setAdapter(seeAllAdapter);

    }
}