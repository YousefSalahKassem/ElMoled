package com.elmoledmol.www;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class topadapter extends RecyclerView.Adapter<topadapter.mh> {
    List<newsinheret>list;
    Context context;
    public topadapter(List<newsinheret> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.top_card,parent,false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mh holder, int position) {
     Picasso.get().load(list.get(position).getImagemodel()).into(holder.imagemodel);
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

        ImageView imagemodel;

        public mh(@NonNull View itemView) {
            super(itemView);

            imagemodel=itemView.findViewById(R.id.topcart);


        }
    }
}
