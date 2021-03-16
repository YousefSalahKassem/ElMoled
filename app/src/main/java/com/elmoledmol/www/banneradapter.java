package com.elmoledmol.www;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class banneradapter extends RecyclerView.Adapter<banneradapter.mh> {
    List<String> list;
    Context context;

    public banneradapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public banneradapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banneritems, parent, false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull banneradapter.mh holder, int position) {
        Picasso.get().load(list.get(position)).resize(holder.itemView.getContext().getDisplay().getWidth(), (holder.itemView.getContext().getDisplay().getHeight() / 2) - 100).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, showimage.class);
                intent.putExtra("image", list.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class mh extends RecyclerView.ViewHolder {
        ImageView imageView;

        public mh(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagebanner);
        }
    }

    public Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

}
