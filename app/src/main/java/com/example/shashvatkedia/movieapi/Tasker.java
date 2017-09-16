package com.example.shashvatkedia.movieapi;

import android.content.AsyncTaskLoader;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static android.os.Build.VERSION_CODES.M;
import static com.example.shashvatkedia.movieapi.MainActivity.adapt;
import static com.example.shashvatkedia.movieapi.MainActivity.movie;

/**
 * Created by Shashvat Kedia on 08-09-2017.
 */

public class Tasker extends AsyncTask<String,Void,ArrayList<MovieInfo>> {

    Context c;
    public Tasker(Context con){
        super();
        c=con;
    }

    @Override
    protected ArrayList<MovieInfo> doInBackground(String... urls) {
        if (urls.length < 1 || urls[0] == null) {
            return null;
        }
        MainActivity.movie.clear();
        ArrayList<MovieInfo> info = Query.fetchData(urls[0]);
        return info;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieInfo> info){
        super.onPostExecute(info);
        if(info!=null) {
            MainActivity.movie = info;
            MainActivity.adapt=new CustomGridAdapter(c,info);
            MainActivity.grid.setAdapter(adapt);
        }
        else{
            Toasty.error(c,"No such information found", Toast.LENGTH_LONG,true).show();
        }
    }
}

