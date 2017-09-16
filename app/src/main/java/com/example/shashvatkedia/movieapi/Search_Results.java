package com.example.shashvatkedia.movieapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Search_Results extends AppCompatActivity {
    public static ArrayList<MovieInfo> info=new ArrayList<MovieInfo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__results);
        ListView search=(ListView) findViewById(R.id.search_result_view);
        searchAdapter adapt=new searchAdapter(this,info);
        search.setAdapter(adapt);
    }
}
