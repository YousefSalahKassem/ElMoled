package com.elmoledmol.www;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class bookmarksadapter extends RecyclerView.Adapter<bookmarksadapter.mh> {
    Context context;
    List<featuredinheret> list;

    public bookmarksadapter(Context context, List<featuredinheret> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public bookmarksadapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favoritecard, parent, false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bookmarksadapter.mh holder, int position) {
        holder.product.setText(list.get(position).getProduct());
        holder.product.setText(list.get(position).getPrice());
        holder.product.setText(list.get(position).getHiddenprice());
        Picasso.get().load(list.get(position).getImagemodel()).into(holder.imageView);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle("Warning!")
                        .setMessage("Do you really want to delete this product from bookmarks?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                list.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton(android.R.string.no,null)
                        .setIcon(R.drawable.logo_splash)
                        .show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class mh extends RecyclerView.ViewHolder {
        TextView product, price, hiddenprice;
        ImageView imageView;

        public mh(@NonNull View itemView) {
            super(itemView);
            product = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.textView13);
            hiddenprice = itemView.findViewById(R.id.textView14);
            imageView = itemView.findViewById(R.id.imageView8);

        }
    }
}
