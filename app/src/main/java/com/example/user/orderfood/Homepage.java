package com.example.user.orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.orderfood.FragmentApp.HienthiThucdonFragment;
import com.example.user.orderfood.FragmentApp.HienthibananFragment;

/**
 * Created by USER on 9/5/2016.
 */
public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView TenNV_Navi;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_homepage);
        Intent intent = getIntent();
        String tendn = intent.getStringExtra("tendn");
        drawerLayout = (DrawerLayout) findViewById(R.id.HomepageDrawer);
        navigationView = (NavigationView) findViewById(R.id.homepage_navi);
        toolbar = (Toolbar) findViewById(R.id.HomepageToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.Open, R.string.Close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        View view = navigationView.getHeaderView(0);
        TenNV_Navi = (TextView) view.findViewById(R.id.tvtennhanvien_navi);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        TenNV_Navi.setText(tendn);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.ittrangchu:
                fragmentTransaction = fragmentManager.beginTransaction();
                HienthibananFragment hienthibananFragment = new HienthibananFragment();
                fragmentTransaction.replace(R.id.content,hienthibananFragment);
                fragmentTransaction.commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.itthucdon:
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                HienthiThucdonFragment hienthiThucdonFragment = new HienthiThucdonFragment();
                fragmentTransaction.replace(R.id.content,hienthiThucdonFragment);
                fragmentTransaction.commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
        }
        return false;
    }
}
