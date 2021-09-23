package com.elmoledmol.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class featuredadapter extends RecyclerView.Adapter<featuredadapter.mh> {
    List<newsinheret> list;
    Context context;

    public featuredadapter(List<newsinheret> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.featured_card, parent, false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mh holder, int position) {
        Picasso.get().load(list.get(position).getImagemodel()).into(holder.imagemodel);
        holder.product.setText(list.get(position).getProduct());
        holder.price.setText("EGP " + String.valueOf(list.get(position).getPrice()));
        float x = list.get(position).getPrice() * (100 - list.get(position).percentage) / 100;
        if (list.get(position).getPercentage() == 0) {
            holder.linearLayout.setVisibility(View.GONE);
            holder.offer.setVisibility(View.GONE);
            holder.price.setText("EGP " + String.valueOf(list.get(position).getPrice()));
        } else {
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.offer.setVisibility(View.VISIBLE);
            holder.percent.setText(String.valueOf(list.get(position).getPercentage() + "%"));
            holder.price.setText("EGP " + String.valueOf(x));
            holder.hiddenprice.setText("EGP " + String.valueOf(list.get(position).getPrice()));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, productInformation.class);
                intent.putExtra("productName", list.get(position).getProduct());
                intent.putExtra("rate", list.get(position).getRate());
                intent.putExtra("price", x);
                intent.putExtra("productID",list.get(position).getId());
                intent.putExtra("mainProductId",list.get(position).getMainid());
                intent.putExtra("brandsId",list.get(position).getBrandid());
                intent.putExtra("image",list.get(position).getImagemodel());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class mh extends RecyclerView.ViewHolder {
        TextView product, price, hiddenprice, percent;
        ImageView imagemodel, offer;
        LinearLayout linearLayout;

        public mh(@NonNull View itemView) {
            super(itemView);
            percent = itemView.findViewById(R.id.mypercent);
            product = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.textView13);
            hiddenprice = itemView.findViewById(R.id.textView14);
            imagemodel = itemView.findViewById(R.id.imageView8);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            offer = itemView.findViewById(R.id.imageView12);
        }
    }
}
