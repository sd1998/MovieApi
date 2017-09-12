package com.example.shashvatkedia.movieapi;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Movie_info extends AppCompatActivity {

    public static ExtendedMovieInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        Intent i = getIntent();
        Bundle bun = i.getExtras();
        String url = bun.getString("Query_URL");
        Movie_Info_Tasker tasker = new Movie_Info_Tasker();
        tasker.execute(url);
        String murl = "http://image.tmdb.org/t/p/w185" + info.getPath();
        ImageView movie_poster_view = (ImageView) findViewById(R.id.movie_poster_view);
        TextView movie_name_view = (TextView) findViewById(R.id.movie_name_view);
        TextView movie_desc_view = (TextView) findViewById(R.id.movie_desc_view);
        TextView age_view = (TextView) findViewById(R.id.age_view);
        TextView rating_view = (TextView) findViewById(R.id.rating_view);
        TextView genre1_view = (TextView) findViewById(R.id.genre_1);
        TextView genre2_view = (TextView) findViewById(R.id.genre_2);
        TextView genre3_view = (TextView) findViewById(R.id.genre_3);
        TextView releasedate_view = (TextView) findViewById(R.id.release_date_view);
        TextView runtime_view = (TextView) findViewById(R.id.runtime_view);
        Picasso.with(getApplicationContext()).load(murl).into(movie_poster_view);
        movie_name_view.setText(info.getName());
        age_view.setText(info.getAge());
        rating_view.setText(info.getRating() + "/10");
        genre1_view.setText(info.getGenre(0));
        genre2_view.setText(info.getGenre(1));
        genre3_view.setText(info.getGenre(2));
        Date date = new Date(info.getDate());
        DateFormat format = new SimpleDateFormat(info.getDate());
        releasedate_view.setText(format.format(date));
        String runtime = "";
        int time = info.getTime(), hrs = 0, min = 0;
        hrs = time / 60;
        time = time - hrs * 60;
        min = time;
        runtime = hrs + " Hours and" + min + " Minutes";
        runtime_view.setText(runtime);
        movie_desc_view.setText(info.getDesc());
    }
}
