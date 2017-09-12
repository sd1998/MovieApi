package com.example.shashvatkedia.movieapi;

import android.os.AsyncTask;

/**
 * Created by Shashvat Kedia on 12-09-2017.
 */

public class Movie_Info_Tasker extends AsyncTask<String,Void,ExtendedMovieInfo> {
    @Override
    protected ExtendedMovieInfo doInBackground(String... urls){
        if(urls.length<1 || urls[0]==null){
            return null;
        }
        ExtendedMovieInfo info=Query.findMovieInfo(urls[0]);
        return info;
    }

    @Override
    protected void onPostExecute(ExtendedMovieInfo info){
       Movie_info.info=info;
    }
}
