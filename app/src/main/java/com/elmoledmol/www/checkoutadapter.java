package com.elmoledmol.www;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class checkoutadapter extends RecyclerView.Adapter<checkoutadapter.mh> {
Context context;
List<cartinheret> list;

    public checkoutadapter(Context context, List<cartinheret> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public checkoutadapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.detailscard,parent,false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull checkoutadapter.mh holder, int position) {
holder.product.setText(list.get(position).getName());
Picasso.get().load(list.get(position).getImages()).into(holder.imageView);
holder.quantity.setText("x"+String.valueOf(list.get(position).getQunatity()));
holder.price.setText("EGP"+String.valueOf(list.get(position).getPrice()));

//        Picasso.get().load(list.get(position).getImages()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class mh extends RecyclerView.ViewHolder{
        TextView product,price,quantity;
        ImageView imageView;
        public mh(@NonNull View itemView) {
            super(itemView);
            product=itemView.findViewById(R.id.product);
            price=itemView.findViewById(R.id.productprice);
            imageView=itemView.findViewById(R.id.photo);
            quantity=itemView.findViewById(R.id.quantitycheckout);
        }
    }
}
