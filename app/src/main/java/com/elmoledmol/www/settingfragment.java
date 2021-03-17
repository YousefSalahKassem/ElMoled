package com.elmoledmol.www;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class settingfragment extends Fragment {
    CardView card;
    LinearLayout layout1, layout2, layout3, layout4, layout5, layout6;
    TextView email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settingfragment, container, false);
        card = v.findViewById(R.id.card6);
        card.setBackgroundResource(R.drawable.corner);
        layout1 = v.findViewById(R.id.OrderHistory);
        layout2 = v.findViewById(R.id.MyAddresses);
        layout3 = v.findViewById(R.id.Settings);
        layout4 = v.findViewById(R.id.MyCards);
        layout5 = v.findViewById(R.id.Vouchers);
        layout6 = v.findViewById(R.id.PickupPoints);
        email=v.findViewById(R.id.textView28);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("checkbox", 0);
        String checkbox = sharedPreferences.getString("email", null);
        if(checkbox!=null) {
            email.setText(checkbox);
        }
        else{
            email.setText("guest123@gmail.com");
        }

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2, new orderhistoryFragment()).commit();
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2, new adressfragment()).commit();
            }
        });

        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2, new moresettings()).commit();
            }
        });

        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=new Toast(getContext());
                toast.setGravity(Gravity.BOTTOM,0,0);
                toast.setDuration(Toast.LENGTH_LONG);
                View view1=LayoutInflater.from(getActivity()).inflate(R.layout.customtoastunavailable,container,false);
                toast.setView(view1);
                toast.show();
            }
        });

        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=new Toast(getContext());
                toast.setGravity(Gravity.BOTTOM,0,0);
                toast.setDuration(Toast.LENGTH_LONG);
                View view1=LayoutInflater.from(getActivity()).inflate(R.layout.customtoastunavailable,container,false);
                toast.setView(view1);
                toast.show();
            }
        });

        layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=new Toast(getContext());
                toast.setGravity(Gravity.BOTTOM,0,0);
                toast.setDuration(Toast.LENGTH_LONG);
                View view1=LayoutInflater.from(getActivity()).inflate(R.layout.customtoastunavailable,container,false);
                toast.setView(view1);
                toast.show();
            }
        });
        return v;
    }
}