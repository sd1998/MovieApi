package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

import static com.example.shashvatkedia.movieapi.MainActivity.url;

/**
 * Created by Shashvat Kedia on 11-09-2017.
 */

public class Query_Tasker extends AsyncTask<String,Void,ArrayList<MovieInfo>>{
    Context con;
    public Query_Tasker(Context c){
        super();
        con=c;
    }

    @Override
    protected ArrayList<MovieInfo> doInBackground(String... urls){
        if(urls.length<1 || urls[0]==null){
            return null;
        }
        ArrayList<MovieInfo> info=Query.fetchData(urls[0]);
        return info;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieInfo> info){
        Search_Results.info=info;
        custom_search.anim.hide();
        custom_search.anim.setVisibility(View.GONE);
        if(info!=null){
            Intent i=new Intent(con,Search_Results.class);
            con.startActivity(i);
        }
        else{
            Toasty.error(con,"No such information found", Toast.LENGTH_LONG,true).show();
        }
    }
}
