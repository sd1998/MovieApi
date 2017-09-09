package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.id;
import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.shashvatkedia.movieapi.MainActivity.movie;

/**
 * Created by Shashvat Kedia on 08-09-2017.
 */

public class CustomGridAdapter extends RecyclerView.Adapter<CustomGridAdapter
        .ViewHolder> {
    private ArrayList<String> movie_name=new ArrayList<String>();
    private ArrayList<String> movie_image_source=new ArrayList<String>();
    private Context con;
    private ItemClickListener click;
    private LayoutInflater inflater;
    public CustomGridAdapter(Context context,ArrayList<String> names,ArrayList<String> image_source){
        this.con=context;
        this.movie_name=names;
        this.movie_image_source=image_source;
        this.inflater=LayoutInflater.from(con);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=inflater.inflate(R.layout.custom_grid_component,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        String name=movie_name.get(position);
        String path=movie_image_source.get(position);
        holder.nameView.setText(name);
    }
    @Override
    public int getItemCount(){
        return movie_name.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameView;
        public ImageView posterView;
        public ViewHolder(View itemView){
            super(itemView);
            nameView=(TextView) itemView.findViewById(R.id.movie_name);
            posterView=(ImageView) itemView.findViewById(R.id.movie_pic);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            if(click != null){
                click.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public String getItem(int id){
        return movie_name.get(id);
    }
    public void setClickListener(ItemClickListener itemClicksListener){
        this.click=itemClicksListener;
    }
    public interface ItemClickListener{
        void onItemClick(View view,int position);
    }
}
