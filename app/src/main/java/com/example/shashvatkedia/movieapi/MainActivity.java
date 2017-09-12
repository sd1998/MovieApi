package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.AdapterView;
import android.widget.GridView;
import android.support.v4.app.LoaderManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    public static String API_KEY=""; //Enter your api key
    private static String popular_url="https://api.themoviedb.org/3/movie/popular?api_key="+API_KEY+"&language=en-US";
    private static String top_rated_url="https://api.themoviedb.org/3/movie/top_rated?api_key="+API_KEY+"&language=en-US";
    private static String upcoming_url="https://api.themoviedb.org/3/movie/upcoming?api_key="+API_KEY+"&language=en-US";
    public static String url="";
    public  static ArrayList<MovieInfo> movie=new ArrayList<MovieInfo>();
    public static CustomGridAdapter adapt;
    public static GridView grid;
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
        grid=(GridView) findViewById(R.id.namegrid);
        adapt=new CustomGridAdapter(this,movie);
        grid.setAdapter(adapt);
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
                temp=4;
                break;
        }
        setAdapt(temp);
        if(id != R.id.search) {
            frag = new GeneralFragment();
        }
        if(frag != null){
            FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
            trans.replace(R.id.content_frame,frag);
            trans.commit();
        }
        if(temp==4){
            Intent i=new Intent(MainActivity.this,custom_search.class);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void setAdapt(int temp){
        if(temp!=4){
            if(temp==1){
              url=popular_url;
            }
            else if(temp==2){
                url=top_rated_url;
            }
            else {
                url = upcoming_url;
            }
            Tasker task=new Tasker(MainActivity.this);
            task.execute(url);
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        defineView(id);
        return true;
    }
}
