package com.example.shashvatkedia.movieapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class trailer extends AppCompatActivity {

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
        ListView view=(ListView) findViewById(R.id.trailer_list);
        simple_adapter adapt=new simple_adapter(this,values);
        view.setAdapter(adapt);
    }
}
