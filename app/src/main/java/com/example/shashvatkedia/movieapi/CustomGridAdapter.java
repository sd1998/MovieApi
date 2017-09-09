package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.id;
import static android.R.attr.name;

/**
 * Created by Shashvat Kedia on 08-09-2017.
 */

public class CustomGridAdapter extends BaseAdapter {
    Context c;
    ArrayList<MovieInfo> movie=new ArrayList<MovieInfo>();
    public CustomGridAdapter(Context con, ArrayList<MovieInfo> info){
        c=con;
        movie=info;
    }
    @Override
    public int getCount(){
        return movie.size();
    }
    @Override
    public Object getItem(int position){
        return null;
    }
    @Override
    public long getItemId(int position){
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View compo;
        LayoutInflater inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            compo=new View(c);
            compo=inflater.inflate(R.layout.custom_grid_component,null);
        }
        else{
            compo=(View) convertView;
        }
        TextView name=(TextView) convertView.findViewById(R.id.movie_name);
        ImageView poster=(ImageView) convertView.findViewById(R.id.movie_pic);
        name.setText(movie.get(position).getName());
        return compo;
    }
}
