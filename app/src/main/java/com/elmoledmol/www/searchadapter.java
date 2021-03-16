package com.elmoledmol.www;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class searchadapter extends RecyclerView.Adapter<searchadapter.mh> {
    List<newsinheret> list;
    Context context;

    public searchadapter(List<newsinheret> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public searchadapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_card, parent, false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchadapter.mh holder, int position) {
        holder.brand.setText(list.get(position).getProduct());
        Picasso.get().load(list.get(position).getLogo()).into(holder.logo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=new Bundle();
                bundle.putInt("id", list.get(position).getBrandid());
                bundle.putString("name",list.get(position).getProduct());
                bundle.putInt("from home",1);
                Navigation.findNavController(v).navigate(R.id.categoriesFragment,bundle);
            }
        });
        System.out.println(list.get(position).getProduct());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class mh extends RecyclerView.ViewHolder {
        TextView brand;
        ImageView logo;

        public mh(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.imageView11);
            brand = itemView.findViewById(R.id.textView18);
        }
    }
}
