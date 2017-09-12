package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

/**
 * Created by Shashvat Kedia on 11-09-2017.
 */

public class Query_Tasker extends AsyncTask<String,Void,Integer>{
    Context con;
    public Query_Tasker(Context c){
        super();
        con=c;
    }

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
            String url=CustomGridAdapter.query_url1+id+CustomGridAdapter.query_url2+MainActivity.API_KEY+CustomGridAdapter.query_url3;
            Movie_Info_Tasker tasker=new Movie_Info_Tasker(con);
            tasker.execute(url);
        }
    }
}
