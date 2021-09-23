package com.elmoledmol.www;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class orderhistoryFragment extends Fragment {
    RecyclerView recyclerView;
    List<orderhistoryinheret> list = new ArrayList<>();
    orderhistoryadapter orderhistoryadapter;
    CardView cardView;
    ImageView back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orderhistory, container, false);
        recyclerView = view.findViewById(R.id.orderhistory);
        cardView = view.findViewById(R.id.card3);
        back = view.findViewById(R.id.backOrderHistory);
        cardView.setBackgroundResource(R.drawable.corner);

        list = loadData();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        orderhistoryadapter = new orderhistoryadapter(getContext(), list);
        recyclerView.setAdapter(orderhistoryadapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2,new settingfragment()).commit();
            }
        });

        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,new settingfragment()).commitAllowingStateLoss();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            int lastPosition = list.size() - 1;

            switch (direction) {
                case  ItemTouchHelper.LEFT:
                    orderhistoryinheret deletedRecord = new orderhistoryinheret(list.get(position).placed,list.get(position).amount,list.get(position).status,list.get(position).ordernumber);
                    if (position == lastPosition) {
                        list.remove(lastPosition);
                        orderhistoryadapter.notifyItemRemoved(lastPosition);
                        orderhistoryadapter.notifyDataSetChanged();

                        saveData(list);

                        Snackbar.make(recyclerView,"Item deleted successfully",Snackbar.LENGTH_LONG)
                                .setAction("UNDO", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        list.add(position,deletedRecord);
                                        orderhistoryadapter.notifyItemInserted(position);
                                        orderhistoryadapter.notifyDataSetChanged();
                                    }
                                }).show();
                    } else {
                        list.remove(position);
                        orderhistoryadapter.notifyItemRemoved(position);

                        saveData(list);

                        Snackbar.make(recyclerView,"Item removed successfully",Snackbar.LENGTH_LONG)
                                .setAction("UNDO", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        list.add(position,deletedRecord);
                                        orderhistoryadapter.notifyItemInserted(position);
                                        saveData(list);
                                    }
                                }).show();
                    }

                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(getContext(),c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(0xFFDA1E1E)
                    .addSwipeLeftActionIcon(R.drawable.delete)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    private void saveData(List<orderhistoryinheret> list) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("History",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("history", json);
        editor.apply();
    }
    private List<orderhistoryinheret> loadData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("History",0);;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("history",null);
        Type type = new TypeToken<ArrayList<orderhistoryinheret>>() {}.getType();
        list = gson.fromJson(json,type);

        if (list == null) {
            list = new ArrayList<>();
        }

        return list;
    }
}