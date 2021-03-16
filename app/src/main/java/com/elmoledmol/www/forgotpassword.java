package com.elmoledmol.www;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class forgotpassword extends Fragment {
    ViewPager viewPager;
    CardView cardView;
    ImageView back;
    EditText email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgotpassword, container, false);
        cardView = view.findViewById(R.id.card1);
        viewPager = getActivity().findViewById(R.id.viewPager);
        email = view.findViewById(R.id.checkemail);
        back = view.findViewById(R.id.back);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(6);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3);
            }
        });
        return view;
    }
}