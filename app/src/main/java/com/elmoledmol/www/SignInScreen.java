package com.elmoledmol.www;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInScreen extends Fragment {
    Button signIn;
    TextView forgot, signup,guest;
    ViewPager viewPager;
    EditText email;
    TextInputEditText password;
    CardView google, facebook;
    ImageView back;
    CheckBox checkBox;
    public SignInScreen() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in_screen, container, false);
        signIn = view.findViewById(R.id.signInfinal);
        viewPager = getActivity().findViewById(R.id.viewPager);
        email = view.findViewById(R.id.emailsignin);
        password = view.findViewById(R.id.passwordsignin);
        back=view.findViewById(R.id.backk);
        google = view.findViewById(R.id.signingoogle);
        checkBox=view.findViewById(R.id.checkBox);
        guest=view.findViewById(R.id.con);
        facebook = view.findViewById(R.id.signinfacebook);
        signup = view.findViewById(R.id.signuptext);
        forgot = view.findViewById(R.id.forgot);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(6);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(email.getText().toString().trim())) {
                    email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString().trim())) {
                    password.setError("Password is required");
                    return;
                }
                Call<ResponseBody> call = retrofitclient.getInstance().getApi().login(password.getText().toString().trim(), "password", email.getText().toString().trim());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            SharedPreferences sharedPreferences=getActivity().getSharedPreferences("checkbox3", 0);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            String json = null;
                            try {
                                json = response.body().string();
                                JSONObject object = new JSONObject(json);
                                String access = object.getString("access_token");

                                editor.putString("acesstoken",access);
                                editor.apply();
                                System.out.println(access);
                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }

//                                Gson gson = new Gson();
//                                gson.toJson(response.body().string());
//                                JsonParser parser = new JsonParser();
//                                String access = object.getString("access_token");
//                                editor.putString("acesstoken",access);
//                                editor.apply();
//                                System.out.println(access);
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);

                        } else {
                            System.out.println(response.body());
                            Toast.makeText(getActivity(), "Please try again later..", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

                if(checkBox.isChecked()){
                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("checkbox", 0);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("email",email.getText().toString().trim());
                    editor.putString("password",password.getText().toString().trim());
                    editor.putString("remember","true");
                    editor.apply();

                }
                else if (!checkBox.isChecked()){
                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("checkbox", 0);
                    sharedPreferences.edit().putString("remember","false").apply();
                    if (!TextUtils.isEmpty(email.getText().toString().trim())) {
                        if (!TextUtils.isEmpty(password.getText().toString().trim())){
                            sharedPreferences.edit().putString("remember","true").apply();
                            sharedPreferences.edit().putString("email",email.getText().toString().trim()).apply();
                        }

                    }

                }
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = new Toast(getContext());
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.customtoastunavailable, container, false);
                toast.setView(view1);
                toast.show();
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = new Toast(getContext());
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.customtoastunavailable, container, false);
                toast.setView(view1);
                toast.show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(5);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),MainActivity.class));
                if(checkBox.isChecked()){
//                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("checkbox", 0);
//                    SharedPreferences.Editor editor=sharedPreferences.edit();
//                    editor.putString("email",email.getText().toString().trim());
//                    editor.putString("password",password.getText().toString().trim());
//                    editor.putString("remember","true");
//                    editor.apply();

                    checkBox.setChecked(false);


                }
                else if (!checkBox.isChecked()){
                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("checkbox", 0);
                    sharedPreferences.edit().putString("remember","false").apply();
                }
            }
        });
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(buttonView.isChecked()){
//                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("checkbox", 0);
//                    SharedPreferences.Editor editor=sharedPreferences.edit();
//                    editor.putString("email",email.getText().toString().trim());
//                    editor.putString("password",password.getText().toString().trim());
//                    editor.putString("remember","true");
//                    editor.apply();
//
//                }
//                else if (!buttonView.isChecked()){
//                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("checkbox", 0);
//                    sharedPreferences.edit().putString("remember","false").apply();
//                }
//            }
//        });
        return view;
    }
}