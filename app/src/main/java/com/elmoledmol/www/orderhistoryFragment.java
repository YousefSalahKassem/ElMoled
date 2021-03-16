package com.elmoledmol.www;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class orderhistoryFragment extends Fragment {
    RecyclerView recyclerView;
    List<orderhistoryinheret> list = new ArrayList<>();
    orderhistoryadapter orderhistoryadapter;
    CardView cardView;
    ImageView back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orderhistory, container, false);
        recyclerView = view.findViewById(R.id.orderhistory);
        cardView = view.findViewById(R.id.card3);
        back = view.findViewById(R.id.backOrderHistory);
        cardView.setBackgroundResource(R.drawable.corner);
        list.add(new orderhistoryinheret("23th of January", "EGP 230.99", "Delivered","ORDER #21412"));
        list.add(new orderhistoryinheret("21th of January", "EGP 220.99", "Canceled","ORDER #21403"));
        list.add(new orderhistoryinheret("20th of January", "EGP 210.99", "Delivered","ORDER #21358"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderhistoryadapter = new orderhistoryadapter(getActivity(), list);
        recyclerView.setAdapter(orderhistoryadapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2,new settingfragment()).commit();
            }
        });

        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,new settingfragment()).commitAllowingStateLoss();
            }
        });

        return view;
    }
}