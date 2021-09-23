package com.elmoledmol.www;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class seealltopadapter extends RecyclerView.Adapter<seealltopadapter.mh> {
    List<newsinheret> list;
    Context context;

    public seealltopadapter(List<newsinheret> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public seealltopadapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.top_card,parent,false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull seealltopadapter.mh holder, int position) {
        Picasso.get().load(list.get(position).getImagemodel()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), productInformation.class);
                intent.putExtra("productName", list.get(position).getProduct());
                intent.putExtra("rate", list.get(position).getRate());
                intent.putExtra("price", list.get(position).getPrice() * (100 - list.get(position).percentage) / 100);
                intent.putExtra("productID",list.get(position).getId());
                intent.putExtra("mainProductId",list.get(position).getMainid());
                intent.putExtra("brandsId",list.get(position).getBrandid());
                intent.putExtra("image",list.get(position).getLogo());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class mh extends RecyclerView.ViewHolder{
        ImageView imageView;
        public mh(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.topcart);
        }
    }
}
