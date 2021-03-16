package com.elmoledmol.www;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class bookmarksfragment extends Fragment {

    CardView cardView;
    RecyclerView recyclerView;
    List<featuredinheret> list = new ArrayList<>();
    bookmarksadapter bookmarksadapter;
    ImageView search;
    EditText searchText;
    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bookmarksfragment, container, false);
        cardView = v.findViewById(R.id.card3);
        recyclerView = v.findViewById(R.id.bookmarkrecyclerview);
        search = v.findViewById(R.id.searchBookmarks);
        searchText = v.findViewById(R.id.searchEditText);
        title = v.findViewById(R.id.bookmarksTitle);

        cardView.setBackgroundResource(R.drawable.corner);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
        bookmarksadapter = new bookmarksadapter(getContext(), list);
        list.add(new featuredinheret("Long Puffer", "799.00", "899.00EFP", R.drawable.model1));
        list.add(new featuredinheret(" Puffer", "799.00", "899.00EFP", R.drawable.model1));
        list.add(new featuredinheret("Long r", "799.00", "899.00EFP", R.drawable.model1));
        list.add(new featuredinheret("Lonfer", "799.00", "", R.drawable.model1));
        list.add(new featuredinheret("Long Puffer", "799.00", "", R.drawable.model1));
        recyclerView.setAdapter(bookmarksadapter);
        list.add(new featuredinheret(" Puffer", "799.00", "", R.drawable.model1));
        list.add(new featuredinheret("Long r", "799.00", "", R.drawable.model1));
        list.add(new featuredinheret("Lonfer", "799.00", "", R.drawable.model1));
        final int[] flag = {0};

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchText.getVisibility() == View.INVISIBLE) {
                    title.animate().alpha(0f).setDuration(300).start();
                    searchText.setVisibility(View.VISIBLE);
                    searchText.animate().alpha(1f).setDuration(500).start();
                    flag[0] =1;
                } else if (flag[0]==1) {
                    title.animate().alpha(1f).setDuration(500).start();
                    searchText.animate().alpha(0f).setDuration(300).start();
                    searchText.setVisibility(View.INVISIBLE);
                }

            }
        });

        return v;
    }
}