package com.elmoledmol.www;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.marlonlom.utilities.timeago.TimeAgo;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.ocpsoft.prettytime.PrettyTime;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class notificationadapter extends RecyclerView.Adapter<notificationadapter.mh> {
    Context context;
    List<String> list = new ArrayList<>();

    public notificationadapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public notificationadapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notificationscard, parent, false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notificationadapter.mh holder, int position) {
        holder.message.setText(list.get(position));

        holder.time.setText("just now");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class mh extends RecyclerView.ViewHolder {
        TextView message, time;

        public mh(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.messagenotification);
            time = itemView.findViewById(R.id.time);

        }
    }
}
