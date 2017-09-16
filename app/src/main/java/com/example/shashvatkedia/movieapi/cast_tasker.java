package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * Created by Shashvat Kedia on 14-09-2017.
 */

public class cast_tasker extends AsyncTask<String,Void,ExtendedCastInfo>{
    Context con;
    public cast_tasker(Context c){
        super();
        con=c;
    }

    @Override
    protected ExtendedCastInfo doInBackground(String... urls){
        if(urls.length<1 || urls[0]==null){
            return null;
        }
        ExtendedCastInfo info=Query.extractCastInfo(urls[0]);
        return info;
    }

    @Override
    protected void onPostExecute(ExtendedCastInfo info){
        Cast_data.info=info;
        if(info!=null) {
            Intent i = new Intent(con, Cast_data.class);
            con.startActivity(i);
        }
        else{
            Toasty.error(con,"No such information found", Toast.LENGTH_LONG,true).show();
        }
    }
}
