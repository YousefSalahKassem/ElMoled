package com.elmoledmol.www;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class notificationFragment extends Fragment {

    CardView cardView;
    RecyclerView recyclerView;
    notificationadapter notificationadapter;
    List<NotificationModel> list = new ArrayList<>();
    JsonArrayRequest request;
    RequestQueue requestQueue;
    String url = "http://hwayadesigns-001-site3.itempurl.com/api/Notification/NotificationList";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notification, container, false);

        cardView = v.findViewById(R.id.card2);
        cardView.setBackgroundResource(R.drawable.corner);

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("checkbox3", 0);
        ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        recyclerView = v.findViewById(R.id.notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        {
            request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.setCancelable(true);
                        mProgressDialog.dismiss();
                    } else {
                        mProgressDialog.setCancelable(false);
                    }

                    JSONObject jsonObject = null;
                    list.clear();

                    for (int i=0;i<response.length();i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            String body = jsonObject.getString("Body");
                            String time = jsonObject.getString("Date");
                            list.add(new NotificationModel(body,time));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    notificationadapter = new notificationadapter(getContext(), list);
                    recyclerView.setAdapter(notificationadapter);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer "+sharedPreferences2.getString("acesstoken",null));
                    return headers;
                }
            };
        }
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

        return v;

    }
}