package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Shashvat Kedia on 15-09-2017.
 */

public class simple_adapter extends ArrayAdapter<video_values>{
    public static String youtube_url="https://www.youtube.com/watch?v=";
    Context con;

    public simple_adapter(Context c,ArrayList<video_values> value){
        super(c,0,value);
        con=c;
    }

    @Override
    public View getView(int position,View convetView, ViewGroup parent){
        if(convetView==null){
            convetView= LayoutInflater.from(getContext()).inflate(R.layout.video_item,parent,false);
        }
        final video_values val=getItem(position);
        TextView trailer=(TextView) convetView.findViewById(R.id.trailer_layout_view);
        ImageView thumbnail=(ImageView) convetView.findViewById(R.id.youtubeThumbnail);
        String thumbnailUrl = "http://img.youtube.com/vi/\" + " + val.getKey() + " +\"/0.jpg";
        Picasso.with(con).load(thumbnailUrl).into(thumbnail);
        trailer.setText(val.getType());
        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(con,ShowTrailer.class);
                intent.putExtra("youtubeKey",val.getKey());
                con.startActivity(intent);
            }
        });
        return convetView;
    }
}