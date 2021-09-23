package com.elmoledmol.www;

import android.content.Context;
import android.content.Intent;
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

public class newsadapter extends RecyclerView.Adapter<newsadapter.mh> {
    List<newsinheret> list;
    Context context;

    public newsadapter(List<newsinheret> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public newsadapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.new_card,parent,false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newsadapter.mh holder, int position) {
        holder.product.setText(list.get(position).getProduct());
        holder.price.setText(String.valueOf(list.get(position).getPrice()));
        holder.hiddenprice.setText(list.get(position).getHiddentext());

//        if(list.get(position).getHiddentext().isEmpty()){
//            holder.linearLayout.setVisibility(View.GONE);
//            holder.offer.setVisibility(View.GONE);
//        }
//        else{
//            holder.linearLayout.setVisibility(View.VISIBLE);
//            holder.offer.setVisibility(View.VISIBLE);
//        }
        float x = list.get(position).getPrice() * (100-list.get(position).percentage)/100;
        if (list.get(position).getPercentage() == 0) {
            holder.linearLayout.setVisibility(View.GONE);
            holder.offer.setVisibility(View.GONE);
            holder.price.setText("EGP "+list.get(position).getPrice());

        } else {
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.offer.setVisibility(View.VISIBLE);
            holder.percent.setText(list.get(position).getPercentage()+"%");
            holder.price.setText("EGP "+x);
            holder.hiddenprice.setText("EGP "+list.get(position).getPrice());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                context.startActivity(new Intent(context,productInformation.class));
                Intent intent = new Intent(context,productInformation.class);
                intent.putExtra("ProductName", list.get(position).getProduct());
                intent.putExtra("rate", list.get(position).getRate());
                intent.putExtra("price",x);
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
    public class mh extends RecyclerView.ViewHolder{
        TextView product,price,hiddenprice,percent;
        ImageView imagemodel,logo,offer;
        LinearLayout linearLayout;
        public mh(@NonNull View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.linearLayout);
            product=itemView.findViewById(R.id.textView);
            price=itemView.findViewById(R.id.textView13);
            imagemodel=itemView.findViewById(R.id.imageView8);
            hiddenprice=itemView.findViewById(R.id.textView14);
            offer=itemView.findViewById(R.id.imageView12);
            logo=imagemodel.findViewById(R.id.imageView);
            percent = itemView.findViewById(R.id.percentNew);
        }
    }
}
