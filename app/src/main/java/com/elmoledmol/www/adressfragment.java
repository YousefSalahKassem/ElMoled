package com.elmoledmol.www;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class adressfragment extends Fragment {

    RecyclerView recyclerView;
    CardView cardView, add;
    List<adressinheret> list = new ArrayList<>();
    addressadapter addressadapter;
    ImageView back;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adressfragment, container, false);
        recyclerView = view.findViewById(R.id.address);
        cardView = view.findViewById(R.id.card3);
        back = view.findViewById(R.id.backMyAddresses);
        cardView.setBackgroundResource(R.drawable.corner);
        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size()<3){
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment2,new AddAddressFragment()).commit();
                }
                else{
                    Toast toast=new Toast(getContext());
                    toast.setGravity(Gravity.BOTTOM,0,0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    View view1=LayoutInflater.from(getActivity()).inflate(R.layout.customtoastunavailable,container,false);
                    toast.setView(view1);
                    toast.show();
                }

            }
        });
        list.add(new adressinheret("WORK ADDRESS", "EL-hourya . No.630, \n" +
                "12345 - Alexandria/Egypt"));
        list.add(new adressinheret("WORK ADDRESS", "EL-hourya . No.630, \n" +
                "12345 - Alexandria/Egypt"));
        list.add(new adressinheret("WORK ADDRESS", "EL-hourya . No.630, \n" +
                "12345 - Alexandria/Egypt"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addressadapter = new addressadapter(getActivity(), list);
        recyclerView.setAdapter(addressadapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2,new settingfragment()).commit();
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

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    adressinheret deletedRecord = new adressinheret(list.get(position).addresstype,list.get(position).details);
                    list.remove(position);
                    addressadapter.notifyItemRemoved(position);
                    Snackbar.make(recyclerView,"Item removed successfully",Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    list.add(position,deletedRecord);
                                    addressadapter.notifyItemInserted(position);

                                }
                            }).show();
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

}