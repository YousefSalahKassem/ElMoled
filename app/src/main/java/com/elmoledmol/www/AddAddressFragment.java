package com.elmoledmol.www;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AddAddressFragment extends Fragment {
    Switch aSwitch;
    TextView check;
    Spinner country, city;
    String[] countries = {"Egypt", "Spain", "Italy", "France", "Germany", "India", "China"};
    String[] cities = {"Alexandria", "Cairo", "Sharm", "Asuit", "Siwa", "Dahab"};
    String[] addressTypes = {"Work Address","Home Address"};
    List<String> list;
    List<String> list2 = new ArrayList<>(Arrays.asList(cities));
    CardView cardView;
    ImageView back;
    List<adressinheret> listAddresses = new ArrayList<>();
    EditText address2;
    Spinner address1;
    CardView card;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_address, container, false);
        country = view.findViewById(R.id.country);
        city = view.findViewById(R.id.city);
        aSwitch = view.findViewById(R.id.switchh);
        cardView = view.findViewById(R.id.add);
        back = view.findViewById(R.id.backAddAddresses);
        address1 = view.findViewById(R.id.address1);
        address2 = view.findViewById(R.id.address2);
        card = view.findViewById(R.id.card3);

        address1.setBackgroundResource(R.drawable.spinner_background);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, addressTypes);
        address1.setAdapter(adapter3);

        card.setBackgroundResource(R.drawable.corner);

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
                listAddresses.addAll(loadData());
                listAddresses.add(new adressinheret(address1.getSelectedItem().toString(),address2.getText().toString()+" "+country.getSelectedItem().toString()+" "+city.getSelectedItem().toString()));
                saveData(listAddresses);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2, new adressfragment()).commitNow();
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



        country.setBackgroundResource(R.drawable.spinner_background);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        country.setAdapter(adapter);



        city.setBackgroundResource(R.drawable.spinner_background);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list2);
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

    private void saveData(List<adressinheret> list) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Addresses",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("address", json);
        editor.apply();
    }
    private List<adressinheret> loadData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Addresses",0);;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("address",null);
        Type type = new TypeToken<ArrayList<adressinheret>>() {}.getType();
        listAddresses = gson.fromJson(json,type);

        if (listAddresses == null) {
            listAddresses = new ArrayList<>();
        }

        return listAddresses;
    }

}