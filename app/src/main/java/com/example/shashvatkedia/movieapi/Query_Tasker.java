package com.example.shashvatkedia.movieapi;

import android.os.AsyncTask;

/**
 * Created by Shashvat Kedia on 11-09-2017.
 */

public class Query_Tasker extends AsyncTask<String,Void,Integer>{
    @Override
    protected Integer doInBackground(String... urls){
        if(urls.length<1 || urls[0]==null){
            return null;
        }
        Integer id=Query.findId(urls[0]);
        return id;
    }

    @Override
    protected void onPostExecute(Integer id){
        if(id!=null){
            custom_search.id=id;
        }
    }
}
