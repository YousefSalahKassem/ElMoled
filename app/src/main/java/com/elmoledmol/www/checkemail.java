package com.elmoledmol.www;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class checkemail extends Fragment {
CardView cardView;
ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
View view=inflater.inflate(R.layout.fragment_checkemail, container, false);
cardView=view.findViewById(R.id.imageView15);
        viewPager = getActivity().findViewById(R.id.viewPager);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });

cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        viewPager.setCurrentItem(4);
    }
});
        return view;
    }

}