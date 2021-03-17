package com.elmoledmol.www;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class seeAllSearch extends AppCompatActivity {
    CardView card;
    TextView category;
    RecyclerView seeAllSearchBy;
    int ind=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_search);
        Objects.requireNonNull(getSupportActionBar()).hide();


        card = findViewById(R.id.card88);
        card.setCardBackgroundColor(R.drawable.corner);

        category = findViewById(R.id.seeAllSearchText);

        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        category.setText(text);
        ArrayList<newsinheret> list = new ArrayList<>();

        list = intent.getParcelableArrayListExtra("list");
        System.out.println(list);
        seeAllSearchBy = findViewById(R.id.seeAllSearchRecycler);
        seeAllSearchBy.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,RecyclerView.VERTICAL,false));
        searchByAdapter searchByAdapter = new searchByAdapter(list,getApplicationContext());
        seeAllSearchBy.setAdapter(searchByAdapter);
    }
}