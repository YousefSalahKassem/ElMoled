package com.elmoledmol.www;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class shopfragment extends Fragment {

    TextView men, women,children,showtext;
    RecyclerView headers, categories;
    List<String> items = new ArrayList<>();
    CardView cardView;
    List<ChildItem> itemList = new ArrayList<>();
    ImageView search;
    EditText searchText;
    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shopfragment, container, false);

        men = v.findViewById(R.id.categoriesMen);
        women = v.findViewById(R.id.categoriesWomen);
        children=v.findViewById(R.id.categoriesChildren);
        showtext=v.findViewById(R.id.searchCategoryText);
        categories=v.findViewById(R.id.searchCategories);
        cardView = v.findViewById(R.id.card3);
        search = v.findViewById(R.id.searchBookmarks);
        searchText = v.findViewById(R.id.searchEditText);
        title = v.findViewById(R.id.bookmarksTitle);
        cardView.setBackgroundResource(R.drawable.corner);
        headers = v.findViewById(R.id.searchItems);
        final int[] flag = {0};
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchText.getVisibility() == View.INVISIBLE) {
                    title.animate().alpha(0f).setDuration(300).start();
                    searchText.setVisibility(View.VISIBLE);
                    searchText.animate().alpha(1f).setDuration(500).start();
                    flag[0] =1;
                } else if (flag[0]==1) {
                    title.animate().alpha(1f).setDuration(500).start();
                    searchText.animate().alpha(0f).setDuration(300).start();
                    searchText.setVisibility(View.INVISIBLE);
                }

            }
        });
        men.setText("MEN");
        women.setText("WOMEN");
        children.setText("CHILDREN");
        items.add("Top");
        items.add("Pants");
        items.add("Shoes");
        men.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                women.setTextColor(Color.GRAY);
                children.setTextColor(Color.GRAY);
                men.setTextColor(Color.BLACK);

              searchitemsAdapter searchItemsAdapter = new searchitemsAdapter(items, itemList, headers, categories, men);
                headers.setAdapter(searchItemsAdapter);

            }
        });

        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                men.setTextColor(Color.GRAY);
                children.setTextColor(Color.GRAY);
                women.setTextColor(Color.BLACK);

             searchitemsAdapter searchItemsAdapter = new searchitemsAdapter(items, itemList, headers, categories, women);
                headers.setAdapter(searchItemsAdapter);

            }
        });

        children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                men.setTextColor(Color.GRAY);
                women.setTextColor(Color.GRAY);
                children.setTextColor(Color.BLACK);


            searchitemsAdapter searchItemsAdapter = new searchitemsAdapter(items, itemList, headers, categories, children);
                headers.setAdapter(searchItemsAdapter);

            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        headers.setLayoutManager(layoutManager);

     searchitemsAdapter searchItemsAdapter = new searchitemsAdapter(items, itemList, headers, categories, men);
        headers.setAdapter(searchItemsAdapter);



        itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
        itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "2%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
        itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
        categories.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(itemList);
        categories.setAdapter(parentItemAdapter);


        return v;
    }

    public class searchitemsAdapter extends RecyclerView.Adapter<searchitemsAdapter.ViewHolder> {
        List<String> items;
        List<ChildItem> list;
        RecyclerView headers;
        RecyclerView category;
        TextView textView;

        public searchitemsAdapter(List<String> items, List<ChildItem> list, RecyclerView headers, RecyclerView category, TextView textView) {
            this.items = items;
            this.list = list;
            this.headers = headers;
            this.category = category;
            this.textView = textView;
        }

        @NonNull
        @Override
        public searchitemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_items, parent, false);
            return new searchitemsAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull searchitemsAdapter.ViewHolder holder, int position) {
            holder.searchItem.setText(items.get(position).toString());
            List<ChildItem> test2 = new ArrayList<>();
            holder.searchItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    test2.clear();
                    itemList.clear();
                    if (textView.getText().toString().equals("MEN")) {
                        if (items.get(position).equals("Top")) {
                            showtext.setText("Top");
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "2%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            for (int i = 0; i < itemList.size(); i++) {
                                test2.add(itemList.get(i));
                            }
                        } else if (items.get(position).equals("Pants")) {
                            showtext.setText("Pants");
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "2%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            for (int i = 0; i < itemList.size(); i++) {
                                test2.add(itemList.get(i));
                            }
                        } else if (items.get(position).equals("Shoes")) {
                            showtext.setText("Shoes");

                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "2%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));

                            for (int i = 0; i < itemList.size(); i++) {
                                test2.add(itemList.get(i));
                            }
                        }
                    } else if (textView.getText().toString().equals("WOMEN")) {
                        if (items.get(position).equals("Top")) {
                            showtext.setText("Top");
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "2%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            for (int i = 0; i < itemList.size(); i++) {
                                test2.add(itemList.get(i));
                            }
                        } else if (items.get(position).equals("Pants")) {
                            showtext.setText("Pants");

                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "2%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            for (int i = 0; i < itemList.size(); i++) {
                                test2.add(itemList.get(i));
                            }
                        } else if (items.get(position).equals("Shoes")) {
                            showtext.setText("Shoes");

                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "2%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));

                            for (int i = 0; i < itemList.size(); i++) {
                                test2.add(itemList.get(i));
                            }
                        }
                    } else if (textView.getText().toString().equals("CHILDREN")) {
                        if (items.get(position).equals("Top")) {
                            showtext.setText("Top");
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "2%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            for (int i = 0; i < itemList.size(); i++) {
                                test2.add(itemList.get(i));
                            }
                        } else if (items.get(position).equals("Pants")) {
                            showtext.setText("Pants");
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "2%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            for (int i = 0; i < itemList.size(); i++) {
                                test2.add(itemList.get(i));
                            }
                        } else if (items.get(position).equals("Shoes")) {
                            showtext.setText("Shoes");
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "2%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));
                            itemList.add(new ChildItem(R.drawable.brand1, R.drawable.model1, "1%", "Pink Jacket", "EGP 699.00", "EGP 999.00"));

                            for (int i = 0; i < itemList.size(); i++) {
                                test2.add(itemList.get(i));
                            }
                        }
                    }
                    ParentItemAdapter parentItemAdapter = new ParentItemAdapter(test2);
                    category.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
                    category.setAdapter(parentItemAdapter);

              searchitemsAdapter searchItemsAdapter = new searchitemsAdapter(items, itemList, headers, categories, textView);
                    headers.setAdapter(searchItemsAdapter);

                }
            });

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView searchItem;
            CardView cardView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                searchItem = itemView.findViewById(R.id.searchItemText);
                cardView = itemView.findViewById(R.id.cardback);
            }
        }
    }


}