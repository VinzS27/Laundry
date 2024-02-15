package com.example.laundryvae;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.laundryvae.fragment.HomeF;
import com.example.laundryvae.fragment.Map_F;
import com.example.laundryvae.fragment.Balance_F;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadFragment(new HomeF());
    }

    private void initView(){
        BottomNavigationView navigation=findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.nav_home:
                fragment = new HomeF();
                break;
            case R.id.nav_map:
                fragment = new Map_F();
                break;
            case R.id.nav_balance:
                fragment = new Balance_F();
                break;
        }
        return loadFragment(fragment);
    }
    private boolean loadFragment(Fragment fragment) {
        if(fragment!=null){
            final int commit = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
        }
        return false;
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        if(drawerLayout.isDrawerOpen((GravityCompat.START))){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else super.onBackPressed();
    }
}