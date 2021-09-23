package com.elmoledmol.www;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ordercheckout extends AppCompatActivity {
    CardView cardView, cardView2, cardView3;
    RecyclerView recyclerView;
    List<cartinheret> listCart = new ArrayList<>();
    ArrayList<ordermodel> list2 = new ArrayList<>();
    ArrayList<requestmodel> list3 = new ArrayList<>();
    ArrayList<Integer> listids = new ArrayList<>();
    checkoutadapter checkoutadapter;
    TextView details, change, change2, subtotal, total,shippingprice;
    ImageView back;
    EditText editText;
    List<adressinheret> listDetails = new ArrayList<>();
    List<orderhistoryinheret> listHistory = new ArrayList<>();

    RequestQueue requestQueue;
    JsonArrayRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordercheckout);
        calling();
        getSupportActionBar().hide();
        cardView.setBackgroundResource(R.drawable.corner);
        cardView2.setBackgroundResource(R.drawable.cornerblack);
        shippingprice=findViewById(R.id.shipprice);
        recyclerView = findViewById(R.id.myorders);
        details = findViewById(R.id.addressdetails);
        back = findViewById(R.id.backcheckout);
        subtotal = findViewById(R.id.price);
        total = findViewById(R.id.price2);
        change = findViewById(R.id.change1);
        change2 = findViewById(R.id.change2);
        editText = findViewById(R.id.phonenumber);
        listCart = (ArrayList<cartinheret>) loadData();
        float t = 0;
        for (int i = 0; i < listCart.size(); i++) {
            t += listCart.get(i).getPrice();
        }
        subtotal.setText("EGP " + String.valueOf(t));
        shippingprice.setText("EGP 10.00");
        total.setText("EGP " + String.valueOf(t+10));

        Intent intent = getIntent();
        int mainID = intent.getIntExtra("mainProductId", 0);

        listDetails = loadDataAddresses();
        if(listDetails.isEmpty()){
            details.setText("");
        }
        else {
            details.setText(listDetails.get(0).details);
            editText.setEnabled(false);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ordercheckout.this, myCartActivity.class));
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ordercheckout.this, showmyaddresses.class));
            }
        });


        change2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setEnabled(true);
                editText.performClick();

            }
        });

        SharedPreferences sharedPreferences2 = getSharedPreferences("phone", 0);
        String silent2 = sharedPreferences2.getString("myphone", null);
        editText.setText(silent2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkoutadapter = new checkoutadapter(ordercheckout.this, listCart);
        recyclerView.setAdapter(checkoutadapter);


        System.out.println(loadData().get(0).getColorId());
        System.out.println(loadData().get(0).getSizeId());
        System.out.println(loadData().get(0).getMainId());
        System.out.println("Found");

        for (int i = 0; i < loadData().size(); i++) {

            String url = "http://hwayadesigns-001-site3.itempurl.com/api/Product/Details?id=" + loadData().get(i).getMainId();
            int finalI = i;
            request = new JsonArrayRequest(url, new com.android.volley.Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    JSONObject jsonObject = null;
                    for (int j = 0; j < response.length(); j++) {
                        try {
                            jsonObject = response.getJSONObject(j);
                            int id = jsonObject.getInt("productId");
                 if(loadData().get(finalI).getColorId()==jsonObject.getInt("colorId")){
                     if(loadData().get(finalI).getSizeId()==jsonObject.getInt("sizeId")){
                         listids.add(id);
                     }
                     System.out.println("found");
                 } else {
                     System.out.println("not found");
                 }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        }


        float finalT = t;
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText().toString().trim())) {
                    editText.setError("Phone is required");
                    return;
                }
                if (TextUtils.isEmpty(details.getText().toString().trim())) {
                    details.setError("Address is required");
                    return;
                }
                boolean connected=false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    MyData data = new MyData();
                    List<OrderDetails> list = new ArrayList<>();

                    for (int i = 0; i < listids.size(); i++) {
                        list.add(new OrderDetails(listids.get(i), loadData().get(i).getQunatity()));
                    }

                    data.setAddress(details.getText().toString());
                    data.setPhone(editText.getText().toString());
                    data.setOrderDetails(list);

                    SharedPreferences sharedPreferences2 = getSharedPreferences("checkbox3", 0);

                    Call<ResponseBody> call = retrofitclient.getInstance().getApi().method("Bearer " + sharedPreferences2.getString("acesstoken", null), data);
                    call.enqueue(new Callback<ResponseBody>() {

                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            System.out.println(sharedPreferences2.getString("acesstoken", null).toString());
                            System.out.println(response.code());
                            System.out.println(data.toString());



                            listCart.addAll(loadData());
                            listCart.clear();
                            saveData(listCart);


                            SecureRandom random = new SecureRandom();
                            int num = random.nextInt(100000);
                            String formatted = String.format("%05d", num);

                            listHistory.clear();
                            listHistory.addAll(loadDataHistory());
                            listHistory.add(new orderhistoryinheret(java.time.LocalDate.now().toString(),"EGP "+ finalT,"Processed","Order #"+formatted));
                            saveDataHistory(listHistory);

                            System.out.println(listHistory.get(0).ordernumber);

                            System.out.println(listCart.size()+"<--- list size after checkout");
                            Intent intent1=new Intent(ordercheckout.this,processsuccess.class);
                            startActivity(intent1);

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            System.out.println("Failure");
                        }
                    });

                }
                else {
                    Toast.makeText(ordercheckout.this, "Check Your Connection please !", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void calling() {
        cardView = findViewById(R.id.card3);
        cardView2 = findViewById(R.id.card4);
        cardView3 = findViewById(R.id.save);
    }

    private List<cartinheret> loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("preferences2", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("product", "");
        Type type = new TypeToken<ArrayList<cartinheret>>() {
        }.getType();
        listCart = gson.fromJson(json, type);

        if (listCart == null) {
            listCart = new ArrayList<>();
        }
        return listCart;
    }
    private void saveData(List<cartinheret> list) {
        SharedPreferences sharedPreferences = getSharedPreferences("preferences2",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("product", json);
        editor.apply();
    }
    private void saveDataAddresses(List<adressinheret> list) {
        SharedPreferences sharedPreferences = getSharedPreferences("Addresses",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("address", json);
        editor.apply();
    }
    private List<adressinheret> loadDataAddresses() {
        SharedPreferences sharedPreferences = getSharedPreferences("Addresses",0);;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("address",null);
        Type type = new TypeToken<ArrayList<adressinheret>>() {}.getType();
        listDetails = gson.fromJson(json,type);

        if (listDetails == null) {
            listDetails = new ArrayList<>();
        }

        return listDetails;
    }
    private void saveDataHistory(List<orderhistoryinheret> list) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("History",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("history", json);
        editor.apply();
    }
    private List<orderhistoryinheret> loadDataHistory() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("History",0);;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("history",null);
        Type type = new TypeToken<ArrayList<orderhistoryinheret>>() {}.getType();
        listHistory = gson.fromJson(json,type);

        if (listHistory == null) {
            listHistory = new ArrayList<>();
        }

        return listHistory;
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(ordercheckout.this, myCartActivity.class));
    }


}