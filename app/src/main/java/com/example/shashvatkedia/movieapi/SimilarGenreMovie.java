package com.example.shashvatkedia.movieapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class SimilarGenreMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_genre_movie);
        GridView grid=(GridView) findViewById(R.id.similar_genre_movie_grid);
        CustomGridAdapter adapter = new CustomGridAdapter(getApplicationContext(),Movie_info.moviesWithSimilarGenre);
        grid.setAdapter(adapter);
    }
}
