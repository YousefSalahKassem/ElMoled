package com.elmoledmol.www;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class showmyaddressadapter extends RecyclerView.Adapter<showmyaddressadapter.mh> {
    Context context;
    List<adressinheret> list;

    public showmyaddressadapter(Context context, List<adressinheret> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public showmyaddressadapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.myadressescard,parent,false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull showmyaddressadapter.mh holder, int position) {
        holder.type.setText(list.get(position).getAddresstype());
        holder.details.setText(list.get(position).getDetails());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("mydetails", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("details", list.get(position).getDetails());
                editor.apply();
                Intent intent = new Intent(context,ordercheckout.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.itemView.getContext().startActivity(intent);
            }
        });
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
