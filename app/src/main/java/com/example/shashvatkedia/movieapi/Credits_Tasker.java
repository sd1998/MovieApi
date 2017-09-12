package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Shashvat Kedia on 12-09-2017.
 */

public class Credits_Tasker extends AsyncTask<String,Void,ArrayList<CastInfo>> {
    Context con;
    public Credits_Tasker(Context c){
        super();
        con=c;
    }
    @Override
    protected ArrayList<CastInfo> doInBackground(String... urls){
        if(urls.length<1 || urls[0]==null){
            return null;
        }
        ArrayList<CastInfo> cast=Query.findCast(urls[0]);
        return cast;
    }

    @Override
    protected  void onPostExecute(ArrayList<CastInfo> cast){
        Cast_display.cast=cast;
        Intent i=new Intent(con,Cast_display.class);
        con.startActivity(i);
    }
}
