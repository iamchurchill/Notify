package com.samsoft.notify.activity;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.samsoft.notify.R;
import com.samsoft.notify.fragment.HomeFrag;
import com.samsoft.notify.fragment.StatFrag;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle actionBarDrawerToggle;
    int item_selected;
    private DrawerLayout drawerLayout;
    private long pressed = 0;

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        }else{
            Toast toast = Toast.makeText(this, "press again to exit", Toast.LENGTH_LONG);
            if (System.currentTimeMillis() - pressed > 5000) {
                toast.show();
                pressed = System.currentTimeMillis();
            } else {
                toast.cancel();
                super.onBackPressed();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.app_drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.actionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){

            }
        }*/
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        item_selected = savedInstanceState == null ? R.id.home : savedInstanceState.getInt("selected");
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        item_selected = item.getItemId();
        switch (item_selected){
            case R.id.home:
                onMenuSelected(new HomeFrag());
                break;
            case R.id.stats:
                onMenuSelected(new StatFrag());
                break;
            case R.id.about:
                runActivity(AboutActivity.class);
                break;
            case R.id.setting:
                runActivity(SettingActivity.class);
                break;
            case R.id.exit:
                System.exit(0);
                break;
        }
        drawerLayout.closeDrawers();
        return true;
    }

    public void runActivity(Class cls){
        startActivity(new Intent(getBaseContext(), cls));
    }

    public void onMenuSelected(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.app_content, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;
            case R.id.help:
                runActivity(HelpFeedbackActivity.class);
                return true;
            case  R.id.setting:
                runActivity(SettingActivity.class);
                return true;
            case R.id.about:
                runActivity(AboutActivity.class);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("selected", item_selected);
    }
}
