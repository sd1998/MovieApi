package com.example.shashvatkedia.movieapi;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static android.R.attr.format;

public class Movie_info extends AppCompatActivity {

    public static ExtendedMovieInfo info=null;
    public ImageView movie_poster_view;
    public TextView movie_name_view ;
    public TextView movie_desc_view;
    public TextView age_view ;
    public TextView rating_view ;
    public TextView genre1_view ;
    public TextView genre2_view ;
    public TextView genre3_view;
    public TextView releasedate_view;
    public TextView runtime_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        movie_poster_view = (ImageView) findViewById(R.id.movie_poster_view);
        movie_name_view = (TextView) findViewById(R.id.movie_name_view);
        movie_desc_view = (TextView) findViewById(R.id.movie_desc_view);
        genre2_view = (TextView) findViewById(R.id.genre_2);
        runtime_view = (TextView) findViewById(R.id.movie_runtime_view);
        genre1_view = (TextView) findViewById(R.id.genre_1);
        rating_view = (TextView) findViewById(R.id.rating_view);
        releasedate_view = (TextView) findViewById(R.id.date_view);
        age_view = (TextView) findViewById(R.id.age_view);
        String murl = "http://image.tmdb.org/t/p/w185" + info.getPath();
        Picasso.with(getApplicationContext()).load(murl).into(movie_poster_view);
        movie_name_view.setText(info.getName());
        age_view.setText(info.getAge());
        rating_view.setText(info.getRating() + "/10");
        genre1_view.setText(info.getGenre(0));
        genre2_view.setText(info.getGenre(1));
        releasedate_view.setText(info.getDate());
        String runtime = "";
        int time = info.getTime(), hrs = 0, min = 0;
        hrs = time / 60;
        time = time - hrs * 60;
        min = time;
        runtime = hrs + " Hours and " + min + " Minutes";
        runtime_view.setText(runtime);
        movie_desc_view.setText(info.getDesc());
        TextView credits=(TextView) findViewById(R.id.credits_view);
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://api.themoviedb.org/3/movie/"+info.getId()+"/credits?api_key="+MainActivity.API_KEY;
                Credits_Tasker task=new Credits_Tasker(Movie_info.this);
                task.execute(url);
            }
        });
    }
}
