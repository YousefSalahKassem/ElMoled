package com.elmoledmol.www;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class showmyaddresses extends AppCompatActivity {
    RecyclerView recyclerView;
    CardView cardView;
    List<adressinheret> list = new ArrayList<>();
    showmyaddressadapter showmyaddresses;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmyaddresses);
        recyclerView = findViewById(R.id.address);
        cardView = findViewById(R.id.card3);
        back = findViewById(R.id.backMyAddresses);
        cardView.setBackgroundResource(R.drawable.corner);
        getSupportActionBar().hide();
        list = loadData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        showmyaddresses = new showmyaddressadapter(getApplicationContext(), list);
        recyclerView.setAdapter(showmyaddresses);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void saveData(List<adressinheret> list) {
        SharedPreferences sharedPreferences = getSharedPreferences("Addresses",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("address", json);
        editor.apply();
    }
    private List<adressinheret> loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Addresses",0);;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("address",null);
        Type type = new TypeToken<ArrayList<adressinheret>>() {}.getType();
        list = gson.fromJson(json,type);

        if (list == null) {
            list = new ArrayList<>();
        }

        return list;
    }
}