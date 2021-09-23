package com.elmoledmol.www;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class productInformation extends AppCompatActivity {
    SlidingUpPanelLayout slidingUpPanelLayout;
    RatingBar ratingBar;
    TabLayout tabLayout;
    ViewPager viewPager;
    CardView cardView, cardView2;
    RecyclerView recyclerView, recyclerView2, recyclerView3;
    List<Integer> list = new ArrayList<>();
    List<newsinheret> list3 = new ArrayList<>();
    List<ViewModel> list2 = new ArrayList<>();
    List<cartinheret> listCart = new ArrayList<>();
    ImageView back;
    private RequestQueue requestQueue;
    private JsonArrayRequest request;
    TextView name, price, brandName;
    int choiceColor;
    int choiceSize;
    int mainId;
    String myimage2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        slidingUpPanelLayout = findViewById(R.id.sliding_layout);
        cardView2 = findViewById(R.id.add);
        recyclerView3 = findViewById(R.id.banner);
        name = findViewById(R.id.productDetailsName);
        price = findViewById(R.id.productDetailsPrice);
        brandName = findViewById(R.id.productDetailsBrand);
        tabLayout = findViewById(R.id.mytabs);
        viewPager = findViewById(R.id.mypaper);
        ratingBar = findViewById(R.id.rating);
        cardView = findViewById(R.id.card10);
        recyclerView = findViewById(R.id.color);
        recyclerView2 = findViewById(R.id.sizes);
        back = findViewById(R.id.backProductInfo);
        cardView.setBackgroundResource(R.drawable.corner);
        getSupportActionBar().hide();
        ratingBar.setClickable(false);
        ratingBar.setFocusable(false);
        paperadapter paperadapter = new paperadapter(getSupportFragmentManager());
        viewPager.setAdapter(paperadapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        Intent intent = getIntent();
         mainId = intent.getIntExtra("mainProductId", 0);
        String productName = intent.getStringExtra("productName");
        int rate = intent.getIntExtra("rate", 0);
        float price2 = intent.getFloatExtra("price", 0);
        int brandId = intent.getIntExtra("brandsId", 0);
        int ID = intent.getIntExtra("productID", 0);
        String img = intent.getStringExtra("imgNameList");
        String myimage2=intent.getStringExtra("image");
        name.setText(productName);
        price.setText("EGP " + price2);
        ratingBar.setRating((float) rate);
        Bundle bundle = new Bundle();
        bundle.putInt("id", mainId);
        reviews fragobj = new reviews();
        fragobj.setArguments(bundle);

        String url = "http://hwayadesigns-001-site3.itempurl.com/api/Product/Color?id=" + mainId;
        request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        JSONArray jsonArray = jsonObject.getJSONArray("listSize");
                        List<Integer> IDs = new ArrayList<>();
                        List<String> sizes = new ArrayList<>();
                        List<String> images = new ArrayList<>();
                        ViewModel viewModel = new ViewModel(IDs,sizes,images);
                        for (int k = 0; k < jsonArray.length(); k++) {
                            JSONObject jsonArrayObject = jsonArray.getJSONObject(k);
                            int id = jsonArrayObject.getInt("sizeid");
                            String size = jsonArrayObject.getString("sizeName");
//                            list2.add(new ViewModel(id,size));
                            IDs.add(id);
                            sizes.add(size);

                        }
                        viewModel.setId(IDs);
                        viewModel.setName(sizes);




                        JSONArray imagesArray = jsonObject.getJSONArray("imgNameList");
                        for (int j=0;j<imagesArray.length();j++) {
                            JSONObject imagesObject = imagesArray.getJSONObject(j);
                            String image = imagesObject.getString("img");
                            String myImg = "http://hwayadesigns-001-site3.itempurl.com/img/products/"+image;
                            images.add(myImg);
                            System.out.println(images);
                        }
                        viewModel.setImages(images);
                        list2.add(viewModel);

                        list3.add(new newsinheret(jsonObject.getInt("colorId"),Color.parseColor(jsonObject.getString("colorCode").substring(0, 7)),list2,images));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
                colorsadapter colorsadapter = new colorsadapter(productInformation.this, list3, recyclerView2, recyclerView3, list,list2);
                recyclerView.setAdapter(colorsadapter);
                System.out.println(list3);
                recyclerView3.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
                banneradapter banneradapter = new banneradapter(Collections.singletonList(list2.get(0).images.get(0)),getApplicationContext());
                recyclerView3.setAdapter(banneradapter);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
                sizesadapter sizesadapter = new sizesadapter(productInformation.this, Collections.singletonList(list2.get(0).name.get(0)), Collections.singletonList(list2.get(0).id.get(0)));
                recyclerView2.setAdapter(sizesadapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                try {
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONArray(jsonString), cacheEntry);
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }



            @Override
            public void deliverError(VolleyError error) {
                super.deliverError(error);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
        };

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);



        slidingUpPanelLayout.setPanelHeight(getDisplay().getHeight() / 2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("colorId",0);
                SharedPreferences sharedPreferences = getSharedPreferences("checkbox3", 0);
                SharedPreferences sharedPreferences1 = getSharedPreferences("checkbox",0);
                String checkbox = sharedPreferences.getString("acesstoken", null);
                String state = sharedPreferences1.getString("remember",null);
                if (state.equals("false")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(productInformation.this);
                    builder.setIcon(R.drawable.logo_splash).setTitle("You must sign in!").setMessage("You must have an account to order this item!").setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            startActivity(new Intent(productInformation.this, SplashLoginActivity.class));
                        }
                    }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            builder.setCancelable(true);
                        }
                    }).show();

                } else if (state.equals("true")) {

                    Intent intent = new Intent(productInformation.this,myCartActivity.class);
                    // send product name
                    // send product picture
                    // send product final price
                    // send product id
                    intent.putExtra("productName",productName);
                    intent.putExtra("price",price2);
                    intent.putExtra("mainProductId",mainId);
                    intent.putExtra("imgNameList",img);

                    System.out.println(productName);
                    listCart.clear();
                    listCart.addAll(loadData());
                    listCart.add(new cartinheret(productName,price2,myimage2,mainId,1,choiceColor,choiceSize));
                    System.out.println(choiceColor + " <--- Color ID going to Cart");
                    System.out.println(choiceSize + " <--- Size ID going to Cart");
                    System.out.println(img+"<--- string");

                    saveData(listCart);
                    startActivity(intent);
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void saveData(List<cartinheret> list) {
        SharedPreferences sharedPreferences = getSharedPreferences("preferences2",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("product", json);
        editor.apply();
    }
    private List<cartinheret> loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("preferences2",0);;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("product",null);
        Type type = new TypeToken<ArrayList<cartinheret>>() {}.getType();
        listCart = gson.fromJson(json,type);

        if (listCart == null) {
            listCart = new ArrayList<>();
        }

        return listCart;
    }




    class colorsadapter extends RecyclerView.Adapter<colorsadapter.mh> {
        Context context;
        List<newsinheret> color = new ArrayList<>();

        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        List<Integer> images = new ArrayList<>();
        List<ViewModel> sizes = new ArrayList<>();
        int choice;

        SharedPreferences preferencesColorId = getSharedPreferences("colorId",0);
//        SharedPreferences preferencesSizeId = getSharedPreferences("sizeId",0);


        public colorsadapter() {
        }

        public colorsadapter(Context context, List<newsinheret> color, RecyclerView recyclerView, RecyclerView recyclerView2, List<Integer> images, List<ViewModel> list2) {
            this.context = context;
            this.color = color;

            this.recyclerView = recyclerView;
            this.recyclerView2 = recyclerView2;
            this.images = images;
            this.sizes = list2;
        }

        @NonNull
        @Override
        public mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.colorscard, parent, false);

            return new mh(view);
        }

        @Override
        public void onBindViewHolder(@NonNull mh holder, int position) {
            holder.cardView.setCardBackgroundColor(color.get(position).getColorcode());
            List<ViewModel> test = new ArrayList<>();
            List<String> mysizes=new ArrayList<>();
            List<String> myImages = new ArrayList<>();
            List<Integer> test2 = new ArrayList<>();
            choice = list3.get(0).colorid;
            productInformation.this.choiceColor = choice;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    images.clear();
                    test.clear();
                    test2.clear();
                    mysizes.clear();
                    mysizes.addAll(list2.get(position).name);
                    myImages.clear();
                    myImages.addAll(list2.get(position).images);

                    sizesadapter sizesadapter = new sizesadapter(holder.itemView.getContext(), mysizes, list2.get(position).id);
                    recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), RecyclerView.HORIZONTAL, false));
                    recyclerView.setAdapter(sizesadapter);

                    recyclerView3.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), RecyclerView.HORIZONTAL, false));
                    banneradapter banneradapter = new banneradapter(myImages,getApplicationContext());
                    recyclerView3.setAdapter(banneradapter);

                    choice = list3.get(position).colorid;
                    productInformation.this.choiceColor = choice;
                    productInformation.this.myimage2= list3.get(position).getImagemodel();


//                    preferencesColorId.edit().clear().commit();
//
//                    preferencesColorId.edit().clear().putInt("colorId",list2.get(position).id.get(position)).apply();
//
//                    System.out.println(preferencesColorId.getInt("colorId",0));

                }
            });

        }


        @Override
        public int getItemCount() {
            return color.size();
        }

        class mh extends RecyclerView.ViewHolder {
            CardView cardView;

            public mh(@NonNull View itemView) {
                super(itemView);
                cardView = itemView.findViewById(R.id.cardcolor);
            }
        }
    }

    public class sizesadapter extends RecyclerView.Adapter<sizesadapter.mh> {
        Context context;
        List<String> list;
        int row;
        int choice;
        List<Integer> list1;

//    SharedPreferences preferencesSizeId = context.getSharedPreferences("sizeId",0);

        public sizesadapter(Context context, List<String> list, List<Integer> list2) {
            this.context = context;
            this.list = list;
            this.list1 = list2;

        }

        @NonNull
        @Override
        public mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.sizescard, parent, false);
            return new mh(view);
        }

        @Override
        public void onBindViewHolder(@NonNull mh holder, int position) {

            holder.size.setText(list.get(position));
            choice = list1.get(0);
            productInformation.this.choiceSize = choice;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    row = position;
                    notifyDataSetChanged();
                    System.out.println(list.get(position).toString());
                }
            });

            holder.size.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                preferencesSizeId.edit().putString("sizeId",).apply();
                    choice = list1.get(position);
                    productInformation.this.choiceSize = choice;
                    System.out.println(choice);
                    row = position;
                    notifyDataSetChanged();
                    System.out.println(list.get(position).toString());
                }
            });

            if (row == position) {
                holder.cardView.setCardBackgroundColor(0X83F10404);
            } else {
                holder.cardView.setCardBackgroundColor(Color.WHITE);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class mh extends RecyclerView.ViewHolder {
            TextView size;
            CardView cardView;

            public mh(@NonNull View itemView) {
                super(itemView);
                size = itemView.findViewById(R.id.size);
                cardView = itemView.findViewById(R.id.cardcolor);
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(productInformation.this,MainActivity.class));
    }
}