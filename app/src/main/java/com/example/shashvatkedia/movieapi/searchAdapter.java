package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.shashvatkedia.movieapi.MainActivity.url;

/**
 * Created by Shashvat Kedia on 16-09-2017.
 */

public class searchAdapter extends ArrayAdapter<MovieInfo> {
    Context con;
    public searchAdapter(Context c, ArrayList<MovieInfo> info){
        super(c,0,info);
        con=c;
    }

    public static class ViewHolder{
        TextView search_cast_info;
        ImageView search_poster_cast;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        ViewHolder holder;
        View view;
        if(convertView==null){
            holder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.custom_list_component,parent,false);
            holder.search_cast_info=(TextView) convertView.findViewById(R.id.search_movie_name);
            holder.search_poster_cast=(ImageView) convertView.findViewById(R.id.search_movie_poster);
            view=convertView;
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder) convertView.getTag();
            view=convertView;
        }
        final MovieInfo info=getItem(position);
        holder.search_cast_info.setText(info.getName());
        String url= "http://image.tmdb.org/t/p/w185"+info.getPath();
        Picasso.with(getContext()).load(url).into(holder.search_poster_cast);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url=CustomGridAdapter.query_url1+info.getId()+CustomGridAdapter.query_url2+MainActivity.API_KEY+CustomGridAdapter.query_url3;
                Movie_Info_Tasker tasker=new Movie_Info_Tasker(con);
                tasker.execute(url);
            }
        });
        return convertView;
    }
}
