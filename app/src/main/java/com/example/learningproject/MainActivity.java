package com.example.learningproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private CustomBroadcastReceiver receiver =
            new CustomBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar launch_toolbar = findViewById(R.id.launch_activity_toolbar);
        setSupportActionBar(launch_toolbar);

        drawerLayout = findViewById(R.id.nav_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, launch_toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).
                    replace(R.id.main_fragments_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        this.registerReceiver(receiver,intentFilter);

    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(receiver);
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).
                        replace(R.id.main_fragments_container, new HomeFragment()).commit();
                break;
            case R.id.nav_scoreKeeper:
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).
                        replace(R.id.main_fragments_container, new ScoreKeeperFragment()).commit();
                break;
            case R.id.nav_search_book:
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).
                        replace(R.id.main_fragments_container, new BookSearchFragment()).commit();
                break;
            case R.id.nav_others:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}