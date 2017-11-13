package com.example.shashvatkedia.movieapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class trailer extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);
        ArrayList<video_values> values=new ArrayList<video_values>();
        for(int i=0;i<=Movie_info.videos.size()-1;i++){
            if(Movie_info.videos.get(i).getSite().equalsIgnoreCase("Youtube") && Movie_info.videos.get(i).getType().equalsIgnoreCase("Trailer")){
                values.add(Movie_info.videos.get(i));
            }
        }
        int count=1;
        for(video_values val: values) {
            if(val.getType().equalsIgnoreCase("Trailer")){
                val.type=val.type+count;
                count++;
            }
        }
        if(!values.isEmpty()) {
            TextView noTrailerView = (TextView) findViewById(R.id.noTrailerView);
            noTrailerView.setVisibility(View.GONE);
            ListView listView = (ListView) findViewById(R.id.trailerListView);
            listView.setVisibility(View.VISIBLE);
            simple_adapter adapter = new simple_adapter(this, values);
            listView.setAdapter(adapter);
        }
        else{
            Toasty.error(getApplicationContext(),"No such information found", Toast.LENGTH_LONG,true).show();
        }
    }
}
