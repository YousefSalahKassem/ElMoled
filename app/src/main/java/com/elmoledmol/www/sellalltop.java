package com.elmoledmol.www;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class sellalltop extends AppCompatActivity {
RecyclerView recyclerView;
CardView cardView;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellalltop);
        recyclerView=findViewById(R.id.seeAllRecycler);
        textView=findViewById(R.id.seeAllCategory);
cardView=findViewById(R.id.card8);
cardView.setBackgroundResource(R.drawable.corner);
        getSupportActionBar().hide();

        Intent intent=getIntent();
        String text=intent.getStringExtra("text");
        textView.setText(text);
        ArrayList<newsinheret> list=new ArrayList<>();
        list=intent.getParcelableArrayListExtra("list");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        seealltopadapter seealltopadapter=new seealltopadapter(list);
        recyclerView.setAdapter(seealltopadapter);
    }
}