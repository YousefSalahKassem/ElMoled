package com.elmoledmol.www;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class categoreyfragment extends Fragment {
    CardView card;
    RecyclerView headers, categories;
    List<String> items = new ArrayList<>();
    List<ChildItem> itemList = new ArrayList<>();
    TextView men, women, children, showtext;
    ImageView search;
    EditText searchText;
    TextView title;
    String gender = null;
    String clothingType = null;
    JsonArrayRequest request;
    RequestQueue requestQueue;
    List<BrandModel> brandList = new ArrayList<>();
    List<BrandModel> brandList2 = new ArrayList<>();
    ArrayList<String> brandNames = new ArrayList<>();
    ArrayList<String> brandNames2 = new ArrayList<>();
    Spinner brands;
    String genderChoice;
    int brandSelection;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_categoreyfragment, container, false);
        card = v.findViewById(R.id.card7);
        title = v.findViewById(R.id.bookmarksTitle);
        card.setBackgroundResource(R.drawable.corner);
        men = v.findViewById(R.id.categoriesMen);
        women = v.findViewById(R.id.categoriesWomen);
        search = v.findViewById(R.id.searchBookmarks);
        searchText = v.findViewById(R.id.searchEditText);
        children = v.findViewById(R.id.categoriesChildren);
        headers = v.findViewById(R.id.CategoriesItems);
        categories = v.findViewById(R.id.CategoriesCategories);
        showtext = v.findViewById(R.id.searchCategoryText);
        brands = v.findViewById(R.id.Brands);

        // Responsible for showing the Search bar at the top (Animation)
        final int[] flag = {0};
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchText.getVisibility() == View.INVISIBLE) {
                    title.animate().alpha(0f).setDuration(300).start();
                    searchText.setVisibility(View.VISIBLE);
                    searchText.animate().alpha(1f).setDuration(500).start();
                    flag[0] = 1;
                    searchText.setText(null);
                } else if (flag[0] == 1) {
                    title.animate().alpha(1f).setDuration(500).start();
                    searchText.animate().alpha(0f).setDuration(300).start();
                    searchText.setVisibility(View.INVISIBLE);
                    searchText.setText(null);
                }

            }
        });

        men.setText("MEN");
        women.setText("WOMEN");
        children.setText("CHILDREN");
        items.add("Top");
        items.add("Pants");
        items.add("Shoes");


        // Responsible for Search for only Men, no further filters
        men.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                women.setTextColor(Color.GRAY);
                children.setTextColor(Color.GRAY);
                men.setTextColor(Color.BLACK);
                genderChoice = "1";

                searchitemsAdapter searchItemsAdapter = new searchitemsAdapter(items, itemList, headers, categories, men);
                headers.setAdapter(searchItemsAdapter);

                String url = "http://clothesshopapi2.azurewebsites.net/api/Product/MainCategory?mainCategoryId=" + genderChoice + "&CategoryId=&brandsId=";

                request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<ChildItem> list = new ArrayList<>();
                        JSONObject jsonObject = null;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                jsonObject = response.getJSONObject(i);
                                int mainproduct = jsonObject.getInt("mainProductId");
                                String productname = jsonObject.getString("productName");
                                int productprice = jsonObject.getInt("productPrice");
                                int percentage = jsonObject.getInt("ProductOfferPercentage");
                                String imagemodel = jsonObject.getString("imgName");
                                String myImg = "http://clothesshopapi2.azurewebsites.net/img/products/" + imagemodel;
                                String imgbrands = jsonObject.getString("ImgBrands");
                                String myImg2 = "http://clothesshopapi2.azurewebsites.net/img/products/" + imgbrands;
                                int brandid = jsonObject.getInt("brandsId");
                                list.add(new ChildItem(mainproduct, percentage, brandid, productname, myImg, myImg2, productprice));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(list);
                        categories.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
                        categories.setAdapter(parentItemAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(request);

            }
        });

        // Responsible for Search for only Women, no further filters
        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                men.setTextColor(Color.GRAY);
                children.setTextColor(Color.GRAY);
                women.setTextColor(Color.BLACK);
                genderChoice = "2";

                searchitemsAdapter searchItemsAdapter = new searchitemsAdapter(items, itemList, headers, categories, women);
                headers.setAdapter(searchItemsAdapter);

                List<ChildItem> list = new ArrayList<>();

                String url = "http://clothesshopapi2.azurewebsites.net/api/Product/MainCategory?mainCategoryId=" + genderChoice + "&CategoryId=&brandsId=";

                JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                jsonObject = response.getJSONObject(i);
                                int mainproduct = jsonObject.getInt("mainProductId");
                                String productname = jsonObject.getString("productName");
                                int productprice = jsonObject.getInt("productPrice");
                                int percentage = jsonObject.getInt("ProductOfferPercentage");
                                String imagemodel = jsonObject.getString("imgName");
                                String myImg = "http://clothesshopapi2.azurewebsites.net/img/products/" + imagemodel;
                                String imgbrands = jsonObject.getString("ImgBrands");
                                String myImg2 = "http://clothesshopapi2.azurewebsites.net/img/products/" + imgbrands;
                                int brandid = jsonObject.getInt("brandsId");
                                list.add(new ChildItem(mainproduct, percentage, brandid, productname, myImg, myImg2, productprice));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(list);
                        categories.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
                        categories.setAdapter(parentItemAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Failure");

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(request);

            }
        });

        // Responsible for Search for only Children, no further filters
        children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                men.setTextColor(Color.GRAY);
                women.setTextColor(Color.GRAY);
                children.setTextColor(Color.BLACK);
                genderChoice = "3";


                searchitemsAdapter searchItemsAdapter = new searchitemsAdapter(items, itemList, headers, categories, children);
                headers.setAdapter(searchItemsAdapter);

                List<ChildItem> list = new ArrayList<>();

                String url = "http://clothesshopapi2.azurewebsites.net/api/Product/MainCategory?mainCategoryId=" + genderChoice + "&CategoryId=&brandsId=";

                JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                jsonObject = response.getJSONObject(i);
                                int mainproduct = jsonObject.getInt("mainProductId");
                                String productname = jsonObject.getString("productName");
                                int productprice = jsonObject.getInt("productPrice");
                                int percentage = jsonObject.getInt("ProductOfferPercentage");
                                String imagemodel = jsonObject.getString("imgName");
                                String myImg = "http://clothesshopapi2.azurewebsites.net/img/products/" + imagemodel;
                                String imgbrands = jsonObject.getString("ImgBrands");
                                String myImg2 = "http://clothesshopapi2.azurewebsites.net/img/products/" + imgbrands;
                                int brandid = jsonObject.getInt("brandsId");
                                list.add(new ChildItem(mainproduct, percentage, brandid, productname, myImg, myImg2, productprice));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(list);
                        categories.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
                        categories.setAdapter(parentItemAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Failure");

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(request);

            }
        });

        // Brands Filter Processes starts here
        {
            brandList.clear();
            brandNames.clear();
            brandList.add(0, new BrandModel(null, "ALL"));
            brandNames.add(brandList.get(0).brandName);
            String urlBrand = "http://clothesshopapi2.azurewebsites.net/api/Brands/list";
            JsonArrayRequest requestBrand = new JsonArrayRequest(urlBrand, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    JSONObject object = null;

                    final int[] k = {1};

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            object = response.getJSONObject(i);
                            int brandId = object.getInt("Id");
                            String brandName = object.getString("brandName");
                            brandList.add(k[0], new BrandModel(brandId, brandName));

                            brandNames.add(k[0], brandList.get(k[0]).brandName);
                            k[0]++;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, brandNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    brands.setAdapter(adapter);


                    brands.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                String url = "http://clothesshopapi2.azurewebsites.net/api/Product/MainCategory?mainCategoryId=" + genderChoice + "&CategoryId=&brandsId=" + brandList.get(0).ID;
                                JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        List<ChildItem> list = new ArrayList<>();
                                        JSONObject jsonObject = null;
                                        for (int i = 0; i < response.length(); i++) {
                                            try {
                                                jsonObject = response.getJSONObject(i);
                                                int mainproduct = jsonObject.getInt("mainProductId");
                                                String productname = jsonObject.getString("productName");
                                                int productprice = jsonObject.getInt("productPrice");
                                                int percentage = jsonObject.getInt("ProductOfferPercentage");
                                                String imagemodel = jsonObject.getString("imgName");
                                                String myImg = "http://clothesshopapi2.azurewebsites.net/img/products/" + imagemodel;
                                                String imgbrands = jsonObject.getString("ImgBrands");
                                                String myImg2 = "http://clothesshopapi2.azurewebsites.net/img/products/" + imgbrands;
                                                int brandid = jsonObject.getInt("brandsId");
                                                list.add(new ChildItem(mainproduct, percentage, brandid, productname, myImg, myImg2, productprice));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(list);
                                        categories.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
                                        categories.setAdapter(parentItemAdapter);

                                        searchText.addTextChangedListener(new TextWatcher() {
                                            @Override
                                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                            }

                                            @Override
                                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                String text = searchText.getText().toString();
                                                String finalText;
                                                List<ChildItem> listTemp = new ArrayList<>();
                                                for (int i = 0; i < list.size(); i++) {
                                                    if (list.get(i).getProductname().startsWith(text)) {

                                                        finalText = list.get(i).getProductname();
                                                        listTemp.add(new ChildItem(list.get(i).getMainid(), list.get(i).getPercentage(), list.get(i).getBrandid(), finalText, list.get(i).getImagemodel(), list.get(i).getLogobrand(), list.get(i).getPrice()));
                                                    }

                                                }
                                                categories.setAdapter(null);
                                                ParentItemAdapter parentItemAdapter = new ParentItemAdapter(listTemp);
                                                categories.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
                                                categories.setAdapter(parentItemAdapter);

                                            }

                                            @Override
                                            public void afterTextChanged(Editable s) {

                                            }
                                        });

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                requestQueue.add(request);

                                searchText.setText(null);

                            } else {
                                String url = "http://clothesshopapi2.azurewebsites.net/api/Product/MainCategory?mainCategoryId=" + genderChoice + "&CategoryId=&brandsId=" + brandList.get(position).ID;
                                JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        List<ChildItem> list = new ArrayList<>();
                                        JSONObject jsonObject = null;
                                        for (int i = 0; i < response.length(); i++) {
                                            try {
                                                jsonObject = response.getJSONObject(i);
                                                int mainproduct = jsonObject.getInt("mainProductId");
                                                String productname = jsonObject.getString("productName");
                                                int productprice = jsonObject.getInt("productPrice");
                                                int percentage = jsonObject.getInt("ProductOfferPercentage");
                                                String imagemodel = jsonObject.getString("imgName");
                                                String myImg = "http://clothesshopapi2.azurewebsites.net/img/prodcuts/" + imagemodel;
                                                String imgbrands = jsonObject.getString("ImgBrands");
                                                String myImg2 = "http://clothesshopapi2.azurewebsites.net/img/prodcuts/" + imgbrands;
                                                int brandid = jsonObject.getInt("brandsId");
                                                list.add(new ChildItem(mainproduct, percentage, brandid, productname, myImg, myImg2, productprice));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(list);
                                        categories.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
                                        categories.setAdapter(parentItemAdapter);

                                        searchText.addTextChangedListener(new TextWatcher() {
                                            @Override
                                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                            }

                                            @Override
                                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                String text = searchText.getText().toString();
                                                String finalText;
                                                List<ChildItem> listTemp = new ArrayList<>();
                                                for (int i = 0; i < list.size(); i++) {
                                                    if (list.get(i).getProductname().startsWith(text)) {

                                                        finalText = list.get(i).getProductname();
                                                        listTemp.add(new ChildItem(list.get(i).getMainid(), list.get(i).getPercentage(), list.get(i).getBrandid(), finalText, list.get(i).getImagemodel(), list.get(i).getLogobrand(), list.get(i).getPrice()));
                                                    }

                                                }
                                                categories.setAdapter(null);
                                                ParentItemAdapter parentItemAdapter = new ParentItemAdapter(listTemp);
                                                categories.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
                                                categories.setAdapter(parentItemAdapter);

                                            }

                                            @Override
                                            public void afterTextChanged(Editable s) {

                                            }
                                        });

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                requestQueue.add(request);

                                searchText.setText(null);

                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            RequestQueue requestQueueBrand = Volley.newRequestQueue(getContext());
            requestQueueBrand.add(requestBrand);


        }

        // Block responsible for connection between home and categories.
        {
            String brandName;
            int position;
            int indicator;

            Bundle bundle = this.getArguments();
            if (bundle != null) {
                brandName = (String) bundle.get("name");
                position = (int) bundle.get("id");
                System.out.println(brandName + "<---- Coming from bundle\n" + position + "<---- Coming from bundle");
                indicator = (int) bundle.get("from home");
                if (indicator == 1) {
                    System.out.println("Navigated from home");
                    brandList2.add(0, new BrandModel(null, "ALL"));
                    brandNames2.add(brandList2.get(0).brandName);
                    String urlBrand2 = "http://clothesshopapi2.azurewebsites.net/api/Brands/list";
                    JsonArrayRequest requestBrand2 = new JsonArrayRequest(urlBrand2, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            JSONObject object = null;

                            final int[] k = {1};

                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    object = response.getJSONObject(i);
                                    int brandId = object.getInt("Id");
                                    String brandName = object.getString("brandName");
                                    brandList2.add(k[0], new BrandModel(brandId, brandName));

                                    brandNames2.add(k[0], brandList2.get(k[0]).brandName);
                                    k[0]++;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            brands.setSelection(indicator);

//                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, brandNames2);
//                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//                            brands.setAdapter(adapter);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    })
                    {
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
                    RequestQueue requestQueue1 = Volley.newRequestQueue(getContext());
                    requestQueue1.add(requestBrand2);



                } else if ( indicator == 2){
                    System.out.println("bundle is empty");
                }
            }



        }

        // Block Responsible for showing ALL Products on startup + Cache
        {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
            headers.setLayoutManager(layoutManager);

            searchitemsAdapter searchItemsAdapter = new searchitemsAdapter(items, itemList, headers, categories, men);
            headers.setAdapter(searchItemsAdapter);


            String url = "http://clothesshopapi2.azurewebsites.net/api/Product/MainCategory?mainCategoryId=&CategoryId=&brandsId=";

            request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    itemList.clear();
                    JSONObject jsonObject = null;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            int mainproduct = jsonObject.getInt("mainProductId");
                            String productname = jsonObject.getString("productName");
                            int productprice = jsonObject.getInt("productPrice");
                            int percentage = jsonObject.getInt("ProductOfferPercentage");
                            String imagemodel = jsonObject.getString("imgName");
                            String myImg = "http://clothesshopapi2.azurewebsites.net/img/products/" + imagemodel;
                            String imgbrands = jsonObject.getString("ImgBrands");
                            String myImg2 = "http://clothesshopapi2.azurewebsites.net/img/products/" + imgbrands;
                            int brandid = jsonObject.getInt("brandsId");
                            itemList.add(new ChildItem(mainproduct, percentage, brandid, productname, myImg, myImg2, productprice));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    ParentItemAdapter parentItemAdapter = new ParentItemAdapter(itemList);
                    categories.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
                    categories.setAdapter(parentItemAdapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
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

            requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(request);
        }


        return v;

    }

    public class searchitemsAdapter extends RecyclerView.Adapter<searchitemsAdapter.ViewHolder> {
        List<String> items;
        List<ChildItem> list;
        RecyclerView headers;
        RecyclerView category;
        TextView textView;
        RequestQueue requestQueue;
        JsonArrayRequest request;


        public searchitemsAdapter(List<String> items, List<ChildItem> list, RecyclerView headers, RecyclerView category, TextView textView) {
            this.items = items;
            this.list = list;
            this.headers = headers;
            this.category = category;
            this.textView = textView;
        }

        @NonNull
        @Override
        public searchitemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_items, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull searchitemsAdapter.ViewHolder holder, int position) {
            holder.searchItem.setText(items.get(position).toString());
            List<ChildItem> test2 = new ArrayList<>();

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    test2.clear();
                    list.clear();
                    if (textView.getText().toString().equals("MEN")) {
                        gender = "1";
                        System.out.println(gender);
                        if (items.get(position).equals("Top")) {
                            showtext.setText("Top");

                            clothingType = "2";
                            System.out.println(clothingType);
                            int brandChoice = categoreyfragment.this.brands.getSelectedItemPosition();
                            getCategoryResponse(gender,clothingType,String.valueOf(categoreyfragment.this.brandList.get(brandChoice).ID),request,requestQueue,category,test2,textView);
                        } else if (items.get(position).equals("Pants")) {
                            showtext.setText("Pants");

                            clothingType = "4";
                            System.out.println(clothingType);
                            int brandChoice = categoreyfragment.this.brands.getSelectedItemPosition();
                            getCategoryResponse(gender,clothingType,String.valueOf(categoreyfragment.this.brandList.get(brandChoice).ID),request,requestQueue,category,test2,textView);
                        } else if (items.get(position).equals("Shoes")) {
                            showtext.setText("Shoes");

                            clothingType = "1";
                            System.out.println(clothingType);
                            int brandChoice = categoreyfragment.this.brands.getSelectedItemPosition();
                            getCategoryResponse(gender,clothingType,String.valueOf(categoreyfragment.this.brandList.get(brandChoice).ID),request,requestQueue,category,test2,textView);
                        }

                    }

                    else if (textView.getText().toString().equals("WOMEN")) {
                        gender = "2";
                        System.out.println(gender);

                        if (items.get(position).equals("Top")) {
                            showtext.setText("Top");

                            clothingType = "9";
                            System.out.println(clothingType);
                            int brandChoice = categoreyfragment.this.brands.getSelectedItemPosition();
                            getCategoryResponse(gender,clothingType,String.valueOf(categoreyfragment.this.brandList.get(brandChoice).ID),request,requestQueue,category,test2,textView);
//                            System.out.println(categoreyfragment.this.brands.getItemAtPosition(0).toString());


                        } else if (items.get(position).equals("Pants")) {
                            showtext.setText("Pants");

                            clothingType = "12";
                            System.out.println(clothingType);
                            int brandChoice = categoreyfragment.this.brands.getSelectedItemPosition();
                            getCategoryResponse(gender,clothingType,String.valueOf(categoreyfragment.this.brandList.get(brandChoice).ID),request,requestQueue,category,test2,textView);
                        } else if (items.get(position).equals("Shoes")) {
                            showtext.setText("Shoes");

                            clothingType = "6";
                            System.out.println(clothingType);
                            int brandChoice = categoreyfragment.this.brands.getSelectedItemPosition();
                            getCategoryResponse(gender,clothingType,String.valueOf(categoreyfragment.this.brandList.get(brandChoice).ID),request,requestQueue,category,test2,textView);
                        }
                    }

                    else if (textView.getText().toString().equals("CHILDREN")) {
                        if (items.get(position).equals("Top")) {
                            showtext.setText("Top");

                        }
                        else if (items.get(position).equals("Pants")) {
                            showtext.setText("Pants");

                        }
                        else if (items.get(position).equals("Shoes")) {
                            showtext.setText("Shoes");

                        }
                    }


                }
            });


        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView searchItem;
            CardView cardView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                searchItem = itemView.findViewById(R.id.searchItemText);
                cardView = itemView.findViewById(R.id.cardback);
            }
        }
    }


    // Function Responsible for finding the appropriate response according to the filter used.
    public void getCategoryResponse (String gender, String clothingType,String brandName,JsonArrayRequest request, RequestQueue requestQueue, RecyclerView category, List<ChildItem> test2, TextView textView) {

        String url="http://clothesshopapi2.azurewebsites.net/api/Product/MainCategory?mainCategoryId="+gender+"&CategoryId="+clothingType+"&brandsId="+brandName;
        request=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;
                for(int i=0;i<response.length();i++){
                    try {
                        jsonObject=response.getJSONObject(i);
                        int mainproduct=jsonObject.getInt("mainProductId");
                        String productname=jsonObject.getString("productName");
                        int productprice=jsonObject.getInt("productPrice");
                        int percentage=jsonObject.getInt("ProductOfferPercentage");
                        String imagemodel=jsonObject.getString("imgName");
                        String myImg = "http://clothesshopapi2.azurewebsites.net/img/products/"+imagemodel;
                        String imgbrands=jsonObject.getString("ImgBrands");
                        String myImg2 = "http://clothesshopapi2.azurewebsites.net/img/products/"+imgbrands;
                        int brandid=jsonObject.getInt("brandsId");
                        itemList.add(new ChildItem(mainproduct,percentage,brandid,productname,myImg,myImg2,productprice));
                    }catch (JSONException e){e.printStackTrace();}

                }
                searchitemsAdapter searchItemsAdapter = new searchitemsAdapter(items, itemList, headers, categories, textView);
                headers.setAdapter(searchItemsAdapter);

                ParentItemAdapter parentItemAdapter = new ParentItemAdapter(itemList);
                category.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
                category.setAdapter(parentItemAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

        for (int i = 0; i < itemList.size(); i++) {
            test2.add(itemList.get(i));
        }

    }


}