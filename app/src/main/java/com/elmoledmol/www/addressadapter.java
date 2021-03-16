package com.elmoledmol.www;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class addressadapter extends RecyclerView.Adapter<addressadapter.mh> {
Context context;
List<adressinheret> list;

    public addressadapter(Context context, List<adressinheret> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public addressadapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.myadressescard,parent,false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull addressadapter.mh holder, int position) {
holder.type.setText(list.get(position).getAddresstype());
holder.details.setText(list.get(position).getDetails());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    class mh extends RecyclerView.ViewHolder{
        TextView type,details;
        public mh(@NonNull View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.typeaddress);
            details=itemView.findViewById(R.id.details);
        }
    }
}
