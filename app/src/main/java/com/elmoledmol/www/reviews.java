package com.elmoledmol.www;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class reviews extends Fragment {
RecyclerView recyclerView;
List<reviewsinheret> list=new ArrayList<>();
reviewsadapter reviewsadapter;
    private RequestQueue requestQueue ;
    private JsonArrayRequest request ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_reviews, container, false);

    String url = "http://hwayadesigns-001-site3.itempurl.com/api/Review/list?MinProductId=" + ((productInformation) getActivity()).mainId;

    request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

            JSONObject jsonObject = null;

            for (int i = 0; i < response.length(); i++) {


                try {
                    jsonObject = response.getJSONObject(i);
                    String creatorName = jsonObject.getString("userName");
                    String review = jsonObject.getString("review");
                    int rate = jsonObject.getInt("rate");
                    list.add(new reviewsinheret(creatorName, "", review, rate));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            recyclerView = view.findViewById(R.id.myreviews);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            reviewsadapter = new reviewsadapter(getContext(), list);
            recyclerView.setAdapter(reviewsadapter);
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

        return view;
    }
}