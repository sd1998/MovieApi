package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Shashvat Kedia on 12-09-2017.
 */

public class cast_adapter extends ArrayAdapter<CastInfo> {
    Context con;
    public cast_adapter(Context c, ArrayList<CastInfo> info){
        super(c,0,info);
        con=c;
    }
    public static class ViewHolder{
        TextView cast_info;
        ImageView poster_cast;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        ViewHolder holder;
        View view;
        if(convertView==null){
            holder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.cast_row,parent,false);
            holder.cast_info=(TextView) convertView.findViewById(R.id.character_info);
            holder.poster_cast=(ImageView) convertView.findViewById(R.id.poster_cast_view);
            view=convertView;
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder) convertView.getTag();
            view=convertView;
        }
        final CastInfo info=getItem(position);
        holder.cast_info.setText(info.getCharacter()+" : "+info.getName());
        String url = "http://image.tmdb.org/t/p/w185"+info.getPath();
        Picasso.with(getContext()).load(url).into(holder.poster_cast);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://api.themoviedb.org/3/person/"+info.getId()+"?api_key="+MainActivity.API_KEY+"&language=en-US";
                cast_tasker task=new cast_tasker(con);
                task.execute(url);
            }
        });
        return convertView;
    }
}
