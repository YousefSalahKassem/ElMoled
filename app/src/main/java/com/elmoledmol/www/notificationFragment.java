package com.elmoledmol.www;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class notificationFragment extends Fragment {

    CardView cardView;
RecyclerView recyclerView;
notificationadapter notificationadapter;
List<String> list=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notification, container, false);

        cardView = v.findViewById(R.id.card2);
        cardView.setBackgroundResource(R.drawable.corner);
list.add("jacket is sold");
list.add("shirt is sold");
        recyclerView=v.findViewById(R.id.notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationadapter=new notificationadapter(getContext(),list);
        recyclerView.setAdapter(notificationadapter);

        return v;

    }
}