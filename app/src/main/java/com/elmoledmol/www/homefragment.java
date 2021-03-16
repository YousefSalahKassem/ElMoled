package com.elmoledmol.www;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class homefragment extends Fragment {
    TextView see1, see2, see3, see4, newa, tops, feature, brand;
    RecyclerView news, top, featured, searchby;
    ArrayList<newsinheret> list;
    ArrayList<newsinheret> list2;
    ArrayList<newsinheret> list3;
    ArrayList<newsinheret> list4;
    ArrayList<newsinheret> list5;
    ArrayList<newsinheret> list6;
    newsadapter newsadapter;
    CardView layout, card2, card3;
    topadapter topadapter;
    searchadapter searchadapter;
    featuredadapter featuredadapter;
    String topitems="http://clothesshopapi2.azurewebsites.net/api/Product/List?state=3&BrandId=0";
    String newitems="http://clothesshopapi2.azurewebsites.net/api/Product/List?state=1&BrandId=0";
    String featuredUrl = "http://clothesshopapi2.azurewebsites.net/api/Product/List?state=2&BrandId=0"; // URL to obtain featured Items.
    String brands = "http://clothesshopapi2.azurewebsites.net/api/Brands/list"; // URL to obtain available brands.
    private RequestQueue requestQueue, requestQueue2,requestQueue3,requestQueue4;
    private JsonArrayRequest request, request2,request3,request4;

    public homefragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_homefragment, container, false);
        calling(v);

        // Headers for home categories.
        newa.setText("New Arrivals");
        tops.setText("Top deals");
        feature.setText("Featured");
        brand.setText("Search by");

        list = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();
        list5 = new ArrayList<>();
        list6 = new ArrayList<>();


        // Request responsible for news available.
        {
            request3 = new JsonArrayRequest(newitems, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    JSONObject jsonObject = null;

                    list5.clear();
                    for (int i = 0; i < response.length(); i++) {


                        try {
                            jsonObject = response.getJSONObject(i);
                            String creatorName = jsonObject.getString("productName");
                            int id = jsonObject.getInt("productId");
                            int mainid = jsonObject.getInt("mainProductId");
                            int price = jsonObject.getInt("productPrice");
                            int brandid = jsonObject.getInt("brandsId");
                            int rate = jsonObject.getInt("averageRate");
                            String image="http://clothesshopapi2.azurewebsites.net/img/products/"+jsonObject.getString("imgName");

                            String logo=jsonObject.getString("ImgBrands");
                            int percentage = jsonObject.getInt("ProductOfferPercentage");
                            System.out.println(id);
                            list5.add(new newsinheret(creatorName, price, "", image, logo, mainid, id, brandid, percentage, rate));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    news.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    featuredadapter = new featuredadapter(list5, getContext());
                    news.setAdapter(featuredadapter);
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

            requestQueue3 = Volley.newRequestQueue(getContext());
            requestQueue3.add(request3);
        }


        // Request responsible for top available.
        {
            request4 = new JsonArrayRequest(topitems, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    JSONObject jsonObject = null;

                    list6.clear();
                    for (int i = 0; i < response.length(); i++) {


                        try {
                            jsonObject = response.getJSONObject(i);
                            String creatorName = jsonObject.getString("productName");
                            int id = jsonObject.getInt("productId");
                            int mainid = jsonObject.getInt("mainProductId");
                            int price = jsonObject.getInt("productPrice");
                            int brandid = jsonObject.getInt("brandsId");
                            String image="http://clothesshopapi2.azurewebsites.net/img/products/"+jsonObject.getString("imgName");
                            String logo=jsonObject.getString("ImgBrands");
                            System.out.println(id);
                            list6.add(new newsinheret(creatorName, price, "", image, logo, mainid, id, brandid, 0, 0));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    top.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    topadapter = new topadapter(list6, getContext());
                    top.setAdapter(topadapter);
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

            requestQueue4 = Volley.newRequestQueue(getContext());
            requestQueue4.add(request4);
        }


        // Request responsible for Featured Items.
        {
            request = new JsonArrayRequest(featuredUrl, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    JSONObject jsonObject = null;

                    list3.clear();
                    for (int i = 0; i < response.length(); i++) {


                        try {
                            jsonObject = response.getJSONObject(i);
                            String creatorName = jsonObject.getString("productName");
                            int id = jsonObject.getInt("productId");
                            int mainid = jsonObject.getInt("mainProductId");
                            int price = jsonObject.getInt("productPrice");
                            int brandid = jsonObject.getInt("brandsId");
                            int rate = jsonObject.getInt("averageRate");
                            String image="http://clothesshopapi2.azurewebsites.net/img/products/"+jsonObject.getString("imgName");

                            String logo=jsonObject.getString("ImgBrands");
                            int percentage = jsonObject.getInt("ProductOfferPercentage");
                            System.out.println(id);
                            list3.add(new newsinheret(creatorName, price, "", image, logo, mainid, id, brandid, percentage, rate));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    featured.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    featuredadapter = new featuredadapter(list3, getContext());
                    featured.setAdapter(featuredadapter);
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



        // Request responsible for Brands available.
        {
            request2 = new JsonArrayRequest(brands, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    JSONObject jsonObject = null;
                    list4.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            String brand = jsonObject.getString("brandName");
                            String imagebrand = "http://clothesshopapi2.azurewebsites.net/img/products/"+jsonObject.getString("imgName");
                            int brandid = jsonObject.getInt("Id");
                            list4.add(new newsinheret(brand, imagebrand, brandid));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    searchby.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    searchadapter = new searchadapter(list4, getContext());
                    searchby.setAdapter(searchadapter);
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
            requestQueue2 = Volley.newRequestQueue(getContext());
            requestQueue2.add(request2);
        }




        news.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        newsadapter = new newsadapter(list, getContext());
        news.setAdapter(newsadapter);

        top.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        topadapter = new topadapter(list2, getContext());
        top.setAdapter(topadapter);


        // "Button" responsible for navigation to On Sale.
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2, new onsale()).commit();
            }
        });

        // "Button" responsible for navigation to Below EGP 200.
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2, new belowthan200()).commit();
            }
        });

        // "Button" responsible for navigating to wishlist.
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2, new bookmarksfragment()).commit();
            }
        });

        // See all new Items.
        see1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), seeAllActivity.class);
                intent.putExtra("text", newa.getText().toString());
                intent.putParcelableArrayListExtra("list", list5);
                startActivity(intent);
            }
        });

        // See all top Deals.
        see2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), sellalltop.class);
                intent.putParcelableArrayListExtra("list", list6);
                intent.putExtra("text", tops.getText().toString());
                startActivity(intent);
            }
        });

        // See all featured Items.
        see3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), seeAllActivity.class);
                intent.putParcelableArrayListExtra("list", list3);
                intent.putExtra("text", feature.getText().toString());
                startActivity(intent);
            }
        });

        // See all brands available.
        see4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), seeAllSearch.class);
                intent.putParcelableArrayListExtra("list", list4);
                intent.putExtra("text", brand.getText().toString());
                startActivity(intent);
            }
        });

        return v;
    }


    private void calling(View v) {
        news = v.findViewById(R.id.recyclenew);
        top = v.findViewById(R.id.recycletop);
        searchby = v.findViewById(R.id.recyclesearch);
        featured = v.findViewById(R.id.featured);
        see1 = v.findViewById(R.id.seeAllNew);
        see2 = v.findViewById(R.id.seeAllTop);
        see3 = v.findViewById(R.id.seeAllFeatured);
        see4 = v.findViewById(R.id.seeAllSearchBy);
        card2 = v.findViewById(R.id.below);
        card3 = v.findViewById(R.id.foryou);
        layout = v.findViewById(R.id.onsale5);
        newa = v.findViewById(R.id.news);
        tops = v.findViewById(R.id.topd);
        feature = v.findViewById(R.id.feature);
        brand = v.findViewById(R.id.brand);
    }

}