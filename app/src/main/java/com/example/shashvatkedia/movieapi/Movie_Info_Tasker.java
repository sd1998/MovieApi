package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.squareup.picasso.Picasso;


import es.dmoral.toasty.Toasty;

import static com.example.shashvatkedia.movieapi.Movie_info.info;
import static com.example.shashvatkedia.movieapi.R.id.age_view;
import static com.example.shashvatkedia.movieapi.R.id.movie_name_view;
import static com.example.shashvatkedia.movieapi.R.id.movie_poster_view;
import static com.example.shashvatkedia.movieapi.R.id.rating_view;

/**
 * Created by Shashvat Kedia on 12-09-2017.
 */

public class Movie_Info_Tasker extends AsyncTask<String,Void,ExtendedMovieInfo> {
    Context c;
    public Movie_Info_Tasker(Context con){
        super();
        c=con;
    }

    @Override
    protected ExtendedMovieInfo doInBackground(String... urls){
        if(urls.length<1 || urls[0]==null){
            return null;
        }
        ExtendedMovieInfo info=Query.findMovieInfo(urls[0]);
        return info;
    }

    @Override
    protected void onPostExecute(ExtendedMovieInfo data){
        Movie_info.info=data;
        if(data!=null) {
            Intent i = new Intent(c, Movie_info.class);
            c.startActivity(i);
        }
        else{
            Toasty.error(c,"No such information found", Toast.LENGTH_LONG,true).show();
        }
    }
}
