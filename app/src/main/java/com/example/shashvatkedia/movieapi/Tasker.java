package com.example.shashvatkedia.movieapi;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shashvat Kedia on 08-09-2017.
 */

public class Tasker extends AsyncTask<String,Void,ArrayList<MovieInfo>> {
    @Override
    protected ArrayList<MovieInfo> doInBackground(String... urls) {
        if (urls.length < 1 || urls[0] == null) {
            return null;
        }
        ArrayList<MovieInfo> info = Query.fetchData(urls[0]);
        return info;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieInfo> info){
        if(info!=null) {
            MainActivity.movie.clear();
            MainActivity.movie = info;
        }
    }
}

