package com.elmoledmol.www;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import org.json.JSONStringer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ordercheckout extends AppCompatActivity {
    CardView cardView, cardView2, cardView3;
    RecyclerView recyclerView;
    ArrayList<cartinheret> list = new ArrayList<>();
    ArrayList<ordermodel> list2 = new ArrayList<>();
    ArrayList<requestmodel> list3 = new ArrayList<>();
    ArrayList<Integer> listids = new ArrayList<>();
    checkoutadapter checkoutadapter;
    TextView details, change, change2, subtotal, total;
    ImageView back;
    EditText editText;

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
        recyclerView = findViewById(R.id.myorders);
        details = findViewById(R.id.addressdetails);
        back = findViewById(R.id.backcheckout);
        subtotal = findViewById(R.id.price);
        total = findViewById(R.id.price2);
        change = findViewById(R.id.change1);
        change2 = findViewById(R.id.change2);
        editText = findViewById(R.id.phonenumber);
        list = (ArrayList<cartinheret>) loadData();
        float t = 0;
        for (int i = 0; i < list.size(); i++) {
            t += list.get(i).getPrice();
        }
        subtotal.setText("EGP" + String.valueOf(t));
        total.setText("EGP" + String.valueOf(t));

        Intent intent = getIntent();
        int mainID = intent.getIntExtra("mainProductId", 0);

        SharedPreferences sharedPreferences = getSharedPreferences("mydetails", 0);
        String detail = sharedPreferences.getString("details", null);
        details.setText(detail);
        editText.setEnabled(false);

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
        checkoutadapter = new checkoutadapter(ordercheckout.this, list);
        recyclerView.setAdapter(checkoutadapter);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ordercheckout.this, processsuccess.class));
            }
        });

        System.out.println(loadData().get(0).getColorId());
        System.out.println(loadData().get(0).getSizeId());
        System.out.println(loadData().get(0).getMainId());
        System.out.println("Found");

        for (int i = 0; i < loadData().size(); i++) {

            String url = "http://clothesshopapi2.azurewebsites.net/api/Product/Details?id=" + loadData().get(i).getMainId();
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


        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MyData data = new MyData();
                List<OrderDetails> list = new ArrayList<>();
//                OrderDetails orderDetails = new OrderDetails();
//                orderDetails.setProductId(10);
//                orderDetails.setQuantity(1);
//                list.add(orderDetails);
//                data.setAddress("A20");
//                data.setPhone("01028634578");
//                data.setOrderDetails(list);
                for (int i = 0; i < listids.size(); i++) {
                    list.add(new OrderDetails(listids.get(i), loadData().get(i).getQunatity()));
                }

                data.setAddress(details.getText().toString());
                data.setPhone(editText.getText().toString());
                data.setOrderDetails(list);

                SharedPreferences sharedPreferences2 = getSharedPreferences("checkbox3", 0);

                Call<ResponseBody> call = retrofitclient.getInstance().getApi().method("Bearer " + sharedPreferences2.getString("acesstoken", null), data);
                call.enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        System.out.println(sharedPreferences2.getString("acesstoken", null).toString());
                        System.out.println(response.code());
                        System.out.println(data.toString());

//                        try {
//                            System.out.println(response.body().string().toString());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                        //                        try {
//                            System.out.println(response.errorBody().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        System.out.println("Failure");
                    }
                });


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
        list = gson.fromJson(json, type);

        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ordercheckout.this, myCartActivity.class));
    }
}