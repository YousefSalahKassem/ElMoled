package com.elmoledmol.www;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView menu, toCart;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    CardView cardView, cart;

    TextView textView, email, counter;
    AppBarConfiguration appBarConfiguration;

    List<cartinheret> list = new ArrayList<>();
    SharedPreferences sharedPreferences2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = findViewById(R.id.imageView4);
        counter = findViewById(R.id.cartCounter);
        cart = findViewById(R.id.cardViewCart);
        cardView = findViewById(R.id.card1);
        cardView.setBackgroundResource(R.drawable.corner);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        toCart = findViewById(R.id.toCartMain);
        navigationView.bringToFront();
        getSupportActionBar().hide();
        View view = navigationView.getHeaderView(0);
        email = view.findViewById(R.id.email3);
        SharedPreferences sharedPreferences = getSharedPreferences("checkbox", 0);
        String checkbox = sharedPreferences.getString("email", null);
        if(checkbox!=null) {
            email.setText(checkbox);
        }
        else {
            email.setText("guest123@gmail.com");
        }
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawerLayout, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(true);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController1 = Navigation.findNavController(this, R.id.fragment2);
        NavController navController = Navigation.findNavController(this, R.id.fragment2);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.categoriesFragment, R.id.settingsFragment).build();
        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, appBarConfiguration);
        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavigationView, navController1);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                drawerLayout.closeDrawers();

                int id = item.getItemId();

                switch (id) {
                    case R.id.notification:
                        navController.navigate(R.id.notificationFragment);
                        break;
                    case R.id.history:

                        navController.navigate(R.id.orderhistoryFragment);
                        break;
                    case R.id.myaddress:
                        navController.navigate(R.id.adressfragment2);
                        break;

                    case R.id.settings:
                        navController.navigate(R.id.settingsFragment);
                        break;
                    case R.id.logout:
                        SharedPreferences sharedPreferences3 = getSharedPreferences("preferences2", Context.MODE_PRIVATE);
                        sharedPreferences3.edit().clear().apply();
                        SharedPreferences sharedPreferences = getSharedPreferences("checkbox", 0);
                        sharedPreferences2 = getSharedPreferences("checkbox3", 0);
                        sharedPreferences2.edit().clear().putString("acesstoken", "no").apply();
                        finish();
                        startActivity(new Intent(MainActivity.this, SplashLoginActivity.class));
                        sharedPreferences.edit().clear().putString("remember", "false").apply();
                        break;
                    default:
                        break;
                }


                return true;
            }
        });

        toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("checkbox3", 0);
                SharedPreferences sharedPreferences1 = getSharedPreferences("checkbox",0);
                String checkbox = sharedPreferences.getString("acesstoken", null);
                String state = sharedPreferences1.getString("remember",null);
                if (state.equals("false")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setIcon(R.drawable.logo_splash).setTitle("You must sign in!").setMessage("You must have an account to order this item!").setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            startActivity(new Intent(MainActivity.this, SplashLoginActivity.class));
                        }
                    }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            builder.setCancelable(true);
                        }
                    }).show();

                } else if (state.equals("true")) {
                    Intent intent = new Intent(MainActivity.this, myCartActivity.class);
                    startActivity(intent);
                }

            }
        });
        Intent intent = getIntent();
        int indicator = intent.getIntExtra("from see all",0);
        if (indicator == 1) {
//            Bundle bundle=new Bundle();
//            bundle.putInt("id", list.get(position).getBrandid());
//            bundle.putString("name",list.get(position).getProduct());
//            bundle.putInt("from home",2);
            NavController navController2 = Navigation.findNavController(this,R.id.fragment2);
            navController2.navigate(R.id.categoriesFragment);
        }
        counter.setText(String.valueOf(loadData().size()));
    }

    @Override
    public void onBackPressed() {

    }

    private List<cartinheret> loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("preferences2", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("product", null);
        Type type = new TypeToken<ArrayList<cartinheret>>() {
        }.getType();
        list = gson.fromJson(json, type);

        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

}
