package com.elmoledmol.www;

import android.content.SharedPreferences;
import android.os.Bundle;
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

import androidx.activity.OnBackPressedCallback;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class moresettings extends Fragment {
    CardView cardView, save;
    TextView change1, change2, edit, edit3;
    EditText phone;
    Spinner spinner1, spinner2;
    TextInputEditText editText;
    Switch aSwitch1, aSwitch2;
    String[] countries = {"Egypt"};
    String[] cities = {"Alexandria", "Cairo", "Sharm", "Asuit", "Siwa", "Dahab"};
    List<String> list = new ArrayList<>(Arrays.asList(countries));
    List<String> list2 = new ArrayList<>(Arrays.asList(cities));
    ImageView back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moresettings, container, false);
        cardView = view.findViewById(R.id.card3);
        cardView.setBackgroundResource(R.drawable.corner);
        change1 = view.findViewById(R.id.change1);
        change2 = view.findViewById(R.id.change2);
        edit = view.findViewById(R.id.edit);
        spinner1 = view.findViewById(R.id.language);
        spinner2 = view.findViewById(R.id.country);
        editText = view.findViewById(R.id.password2);
        aSwitch1 = view.findViewById(R.id.switchh);
        aSwitch2 = view.findViewById(R.id.switchh2);
        save = view.findViewById(R.id.save);
        back = view.findViewById(R.id.backMoreSettings);
        edit3 = view.findViewById(R.id.edit3);
        phone = view.findViewById(R.id.phonenumber2);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("checkbox2", 0);
        int language = sharedPreferences.getInt("language", -1);
        int location = sharedPreferences.getInt("location", -1);
        String password = sharedPreferences.getString("password", null);


        editText.setText(password);
        spinner1.setEnabled(false);
        spinner2.setEnabled(false);
        editText.setEnabled(false);
        phone.setEnabled(false);


        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone.setEnabled(true);
                phone.performClick();

            }
        });
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("phone", 0);
        String silent3 = sharedPreferences2.getString("myphone", null);
        phone.setText(silent3);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list2);
        SharedPreferences settings = getActivity().getSharedPreferences("myswitch", 0);
        boolean silent = settings.getBoolean("switchkey", false);
        SharedPreferences settings2 = getActivity().getSharedPreferences("myswitch2", 0);
        boolean silent2 = settings2.getBoolean("switchkey2", false);
        aSwitch1.setChecked(silent);
        aSwitch2.setChecked(silent2);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
        if (language != -1) {
            spinner1.setSelection(language, true);
        }
        if (location != -1) {
            spinner2.setSelection(location, true);
        }
        change1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner1.setEnabled(true);
                spinner1.performClick();

            }
        });

        change2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setEnabled(true);
                editText.performClick();

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner2.setEnabled(true);
                spinner2.performClick();

            }
        });

        SharedPreferences passwordChange = getContext().getSharedPreferences("checkbox3", 0);

        ChangePasswordBody body = new ChangePasswordBody();
        body.setNewPassword(editText.getText().toString());
        body.setOldPassword(sharedPreferences.getString("password",null));
        body.setConfirmPassword(editText.getText().toString());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("checkbox2", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("language", spinner1.getSelectedItemPosition());
                editor.putString("password", editText.getText().toString().trim());
                editor.putInt("location", spinner2.getSelectedItemPosition());
                editor.apply();

                SharedPreferences sharedPreferences3 = getContext().getSharedPreferences("phone", 0);
                SharedPreferences.Editor editor2 = sharedPreferences3.edit();
                editor2.putString("myphone", phone.getText().toString().trim());
                editor2.apply();

                Call<ResponseBody> call = retrofitclient.getInstance().getApi().changePassword("Bearer "+passwordChange.getString("acesstoken", null),body);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        System.out.println(response.code());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                save.setVisibility(View.GONE);
                Toast toast = new Toast(getContext());
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.customtoast, container, false);
                toast.setView(view1);
                toast.show();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2, new settingfragment()).commitNow();

            }
        });
        aSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aSwitch1.setHint("Enabled");
                } else {
                    aSwitch1.setHint("Disabled");
                }
                SharedPreferences settings = getActivity().getSharedPreferences("myswitch", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("switchkey", isChecked);
                editor.commit();
            }
        });

        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aSwitch2.setHint("Enabled");
                } else {
                    aSwitch2.setHint("Disabled");
                }
                SharedPreferences settings = getActivity().getSharedPreferences("myswitch2", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("switchkey2", isChecked);
                editor.commit();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2, new settingfragment()).commitNow();
            }
        });

        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment2, new settingfragment()).commitNow();
            }
        });
        return view;

    }


}