package com.elmoledmol.www;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AddAddressFragment extends Fragment {
    Switch aSwitch;
    TextView check;
    Spinner country, city;
    String[] countries = {"Egypt", "Spain", "Italy", "France", "Germany", "India", "China"};
    String[] cities = {"Alexandria", "Cairo", "Sharm", "Asuit", "Siwa", "Dahab"};
    List<String> list;
    List<String> list2 = new ArrayList<>(Arrays.asList(cities));
    CardView cardView;
    ImageView back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_address, container, false);
        country = view.findViewById(R.id.country);
        city = view.findViewById(R.id.city);
        aSwitch = view.findViewById(R.id.switchh);
        cardView = view.findViewById(R.id.add);
        back = view.findViewById(R.id.backAddAddresses);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView.setVisibility(View.GONE);
                Toast toast = new Toast(getContext());
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.customtoast, container, false);
                toast.setView(view1);
                toast.show();
            }
        });
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aSwitch.setHint("Enable");
                } else {
                    aSwitch.setHint("Disable");
                }
            }
        });

        list = new ArrayList<>(Arrays.asList(countries));
        list2 = new ArrayList<>(Arrays.asList(cities));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list2);

        country.setAdapter(adapter);
        city.setAdapter(adapter2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2,new adressfragment()).commit();
            }
        });

        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,new adressfragment()).commitAllowingStateLoss();
            }
        });

        return view;
    }

}