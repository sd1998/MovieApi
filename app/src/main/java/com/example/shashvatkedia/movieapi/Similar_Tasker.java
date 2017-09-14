package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;

import static com.example.shashvatkedia.movieapi.MainActivity.url;

/**
 * Created by Shashvat Kedia on 14-09-2017.
 */

public class Similar_Tasker extends AsyncTask<String,Void,ArrayList<MovieInfo>>{
    Context con;

    public Similar_Tasker(Context c){
        super();
        con=c;
    }

     protected ArrayList<MovieInfo> doInBackground(String... urls){
         if(urls.length<1 || urls[0]==null){
             return null;
         }
         Movie_info.similar_info.clear();
         ArrayList<MovieInfo> info=Query.fetchData(urls[0]);
         return info;
     }

     protected void onPostExecute(ArrayList<MovieInfo> info){
         super.onPostExecute(info);
         if(info!=null){
             Movie_info.similar_info=info;
             Movie_info.adapter=new CustomGridAdapter(con,info);
             Intent i=new Intent(con,Similar_movie.class);
             con.startActivity(i);
         }
     }
}
