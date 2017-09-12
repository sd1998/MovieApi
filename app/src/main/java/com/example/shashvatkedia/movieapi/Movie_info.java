package com.example.shashvatkedia.movieapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Movie_info extends AppCompatActivity {

    public static ExtendedMovieInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        Intent i=getIntent();
        Bundle bun=i.getExtras();
        String url=bun.getString("Query_URL");
        Movie_Info_Tasker tasker=new Movie_Info_Tasker();
        tasker.execute(url);

    }
}
