package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Shashvat Kedia on 14-09-2017.
 */

public class video_tasker extends AsyncTask<String,Void,ArrayList<video_values>> {

    Context con;
    public video_tasker(Context c){
        super();
        con=c;
    }

    @Override
    protected ArrayList<video_values> doInBackground(String... urls){
        if(urls.length<1 || urls[0]==null){
            return null;
        }
        Movie_info.videos.clear();
        ArrayList<video_values> videos=Query.getVideos(urls[0]);
        return videos;
    }

    @Override
    protected void onPostExecute(ArrayList<video_values> videos){
        super.onPostExecute(videos);
        if(videos!=null){
            Movie_info.videos=videos;
            Intent i=new Intent(con,trailer.class);
            con.startActivity(i);
        }
    }
}
