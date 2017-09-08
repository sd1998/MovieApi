package com.example.shashvatkedia.movieapi;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Shashvat Kedia on 08-09-2017.
 */

public class Tasker extends AsyncTaskLoader<List<MovieInfo>>{
    String murl;

    public Tasker(Context con,String url){
        super(con);
        murl=url;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }

    @Override
    public List<MovieInfo> loadInBackground(){
        if(murl == null){
            return null;
        }
        List<MovieInfo> Info=Query.fetchData(murl);
        return Info;
    }


}
