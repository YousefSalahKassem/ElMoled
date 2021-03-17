package com.elmoledmol.www;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class SignUpScreen extends Fragment {
EditText email,phone,username;
TextInputEditText password;
Button register;
TextView textView;
CardView facebook,google;
ViewPager viewPager;
ImageView back;
    public SignUpScreen() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign_up_screen, container, false);
        email=view.findViewById(R.id.Email);
        password=view.findViewById(R.id.Password);
        phone=view.findViewById(R.id.Phone);
        viewPager=getActivity().findViewById(R.id.viewPager);
        facebook=view.findViewById(R.id.facebook);
        back=view.findViewById(R.id.back);
        google=view.findViewById(R.id.google);
        textView=view.findViewById(R.id.signin);
        username=view.findViewById(R.id.username);
        register=view.findViewById(R.id.signInfinal);
       register.setOnClickListener(new View.OnClickListener() {
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
               if (TextUtils.isEmpty(phone.getText().toString().trim())) {
                   phone.setError("Phone is required");
                   return;
               }
               if (TextUtils.isEmpty(username.getText().toString().trim())) {
                   username.setError("Username is required");
                   return;
               }
         Call<ResponseBody> call=retrofitclient.getInstance().getApi().createuser(email.getText().toString().trim(),"dddd","ddd","ddd",phone.getText().toString().trim(),username.getText().toString().trim(),password.getText().toString().trim(),password.getText().toString().trim());
               call.enqueue(new Callback<ResponseBody>() {
                   @Override
                   public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                       if(response.body() != null) {
                           Toast.makeText(getActivity(), "Successfully Sign Up !", Toast.LENGTH_SHORT).show();
                           viewPager.setCurrentItem(4);

                       }
                       else {
                           String s = response.message();
                           Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                           System.out.println(s+response.message());
                       }
                   }

                   @Override
                   public void onFailure(Call<ResponseBody> call, Throwable t) {
                       Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                       System.out.println(t.getMessage());

                   }
               });


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
       textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               viewPager.setCurrentItem(4);
           }
       });
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               viewPager.setCurrentItem(4);
           }
       });
        return view;
    }
}