package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

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

public class CustomGridAdapter extends BaseAdapter{
    public static String query_url1="https://api.themoviedb.org/3/movie/";
    public static String query_url2="?api_key=";
    public static String query_url3="&language=en-US";
    Context con;
    ArrayList<MovieInfo> mov;
    public CustomGridAdapter(Context mainActivity,ArrayList<MovieInfo> info){
        con=mainActivity;
        mov=info;
    }
    @Override
    public int getCount(){
        return mov.size();
    }
    @Override
    public MovieInfo getItem(int position){
        return mov.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    public class ViewHolder{
        TextView movie_name;
        ImageView movie_poster;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        ViewHolder holder=new ViewHolder();
        View row;
        row=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid_component,null);
        holder.movie_name=(TextView) row.findViewById(R.id.movie_name);
        holder.movie_poster=(ImageView) row.findViewById(R.id.poster_view);
        holder.movie_name.setText(getItem(position).getName());
        String url="http://image.tmdb.org/t/p/w185"+getItem(position).getPath();
        Picasso.with(con).load(url).into(holder.movie_poster);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url=query_url1+getItem(position).getId()+query_url2+MainActivity.API_KEY+query_url3;
                Movie_Info_Tasker task=new Movie_Info_Tasker(con);
                task.execute(url);
            }
        });
        return row;
    }


}
