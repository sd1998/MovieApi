package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import static android.R.attr.id;
import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.shashvatkedia.movieapi.MainActivity.movie;

/**
 * Created by Shashvat Kedia on 08-09-2017.
 */

public class CustomGridAdapter extends ArrayAdapter<MovieInfo>{
    public CustomGridAdapter(Context con, ArrayList<MovieInfo> info){
        super(con,0,info);
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.custom_grid_component,parent,false);
        }
        MovieInfo mov=getItem(position);
        TextView name=(TextView) convertView.findViewById(R.id.movie_name);
        name.setText(mov.getName());
        ImageView poster=(ImageView) convertView.findViewById(R.id.poster_view);
        String url="http://image.tmdb.org/t/p/w185"+mov.getPath();
        Picasso.with(getContext()).load(url).into(poster);
        return convertView;
    }

}
