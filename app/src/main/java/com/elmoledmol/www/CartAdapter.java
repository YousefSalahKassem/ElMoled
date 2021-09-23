package com.elmoledmol.www;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.mh> {

    List<cartinheret> list2;
    List<cartinheret> listTemp;
    int Quantity;
    Context context;
    float basePrice;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    public CartAdapter(List<cartinheret> list2, Context context) {
        this.list2 = list2;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new mh(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.mh holder, int position) {
            Picasso.get().load(list2.get(position).getImages()).resize(90,90).into(holder.image);
        holder.quantity.setText(String.valueOf(list2.get(position).getQunatity()));
        holder.cost.setText(String.valueOf(list2.get(position).getPrice()));
        holder.name.setText(String.valueOf(list2.get(position).name));
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list2.get(position).Qunatity++;
                holder.quantity.setText(String.valueOf(list2.get(position).getQunatity()));
                holder.cost.setText(String.valueOf(list2.get(position).getPrice()));

                saveData(list2);

            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.quantity.getText().toString().equals("0") || holder.quantity.getText().toString().equals("1")) {

                } else {

                    list2.get(position).Qunatity--;
                    holder.quantity.setText(String.valueOf(list2.get(position).getQunatity()));
                    holder.cost.setText(String.valueOf(list2.get(position).getPrice()));

                    saveData(list2);
                }
            }
        });





    }


    @Override
    public int getItemCount() {
        return list2.size();
    }

    class mh extends RecyclerView.ViewHolder {
        ImageView image, add, remove;
        CardView card;
        TextView quantity, cost, name;

        public mh(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.modelCart);
            card = itemView.findViewById(R.id.cardCart);
            card.setBackgroundResource(R.drawable.cornerblack);
            add = itemView.findViewById(R.id.plusMore);
            remove = itemView.findViewById(R.id.minusMore);
            quantity = itemView.findViewById(R.id.quantity);
            cost = itemView.findViewById(R.id.costCard);
            name = itemView.findViewById(R.id.textCart);
        }
    }


    private List<cartinheret> loadData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferences2", 0);
        List<cartinheret> list;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("product","");
        Type type = new TypeToken<ArrayList<com.elmoledmol.www.cartinheret>>() {
        }.getType();
        list = gson.fromJson(json, type);

        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    private void saveData(List<cartinheret> list) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferences2", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("product", json);
        editor.apply();
    }
}
