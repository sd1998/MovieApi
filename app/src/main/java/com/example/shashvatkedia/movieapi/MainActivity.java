package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.content.Loader;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, android.app.LoaderManager.LoaderCallbacks<Object> {
    private static String API_KEY=""; //Enter your api key
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        defineView(R.id.Popular);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void defineView(int id){
        int temp=1;
        Fragment frag=null;
        switch(id){
            case R.id.Popular :
                temp=1;
                break;
            case R.id.TopRated :
                temp=2;
                break;
            case R.id.Upcoming :
                temp=3;
                break;
            case R.id.search :
                frag = new searchFragment();
                temp=4;
                break;
        }
        if(id != R.id.search) {
            frag = new GeneralFragment();
        }
        if(frag != null){
            FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
            trans.replace(R.id.content_frame,frag);
            trans.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        setAdapt(temp);
    }

    public void setAdapt(int temp){
        if(temp!=4){
            GridView grid=(GridView) findViewById(R.id.data_grid);
            ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo net=cm.getActiveNetworkInfo();
            if(net != null && net.isConnected()){
                android.app.LoaderManager loaderManager=getLoaderManager();
                loaderManager.initLoader(1,null,this);

            }
             else{
                Log.e("#","Error No Internet Connection");
            }
            CustomGridAdapter adapt=new CustomGridAdapter(MainActivity.this);
            grid.setAdapter(adapt);
        }
    }
    
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        defineView(id);
        return true;
    }

    @Override
    public Loader<List<MovieInfo>> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object o) {

    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {

    }
}
