package com.elmoledmol.www;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class orderhistoryadapter extends RecyclerView.Adapter<orderhistoryadapter.mh> {
    Context context;
    List<orderhistoryinheret> list;

    public orderhistoryadapter(Context context, List<orderhistoryinheret> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public orderhistoryadapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.orderhistorycard,parent,false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderhistoryadapter.mh holder, int position) {
        holder.placed.setText(list.get(position).getPlaced());
        holder.amount.setText(list.get(position).getAmount());
        holder.status.setText(list.get(position).getStatus());
        holder.order.setText(list.get(position).getOrdernumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class mh extends RecyclerView.ViewHolder{
        TextView placed,amount,status,order;
        public mh(@NonNull View itemView) {
            super(itemView);
            order=itemView.findViewById(R.id.order);
            placed=itemView.findViewById(R.id.placedon);
            amount=itemView.findViewById(R.id.amount);
            status=itemView.findViewById(R.id.status);
        }
    }
}
