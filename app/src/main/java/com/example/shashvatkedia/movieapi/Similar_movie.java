package com.example.shashvatkedia.movieapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class Similar_movie extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_movie);
        GridView grid=(GridView) findViewById(R.id.similar_movie_grid);
        grid.setAdapter(Movie_info.adapter);
    }
}
