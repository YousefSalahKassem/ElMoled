package com.elmoledmol.www;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class reviewsadapter extends RecyclerView.Adapter<reviewsadapter.mh> {
Context context;
List<reviewsinheret>list;

    public reviewsadapter(Context context, List<reviewsinheret> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public reviewsadapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.reviewscard,parent,false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull reviewsadapter.mh holder, int position) {
holder.name.setText(list.get(position).getName());
holder.comment.setText(list.get(position).getComment());
holder.date.setText(list.get(position).getDate());
holder.ratingBar.setClickable(false);
holder.ratingBar.setRating((float)list.get(position).getRate());
holder.ratingBar.setFocusable(false);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
public class  mh extends RecyclerView.ViewHolder{
        TextView name,comment,date;
        RatingBar ratingBar;
    public mh(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        comment=itemView.findViewById(R.id.comment);
        date=itemView.findViewById(R.id.date);
        ratingBar=itemView.findViewById(R.id.reviewraiting);
    }
}
}
