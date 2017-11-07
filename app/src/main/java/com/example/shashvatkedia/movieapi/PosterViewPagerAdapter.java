package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shashvatkedia on 08/11/17.
 */

public class PosterViewPagerAdapter extends PagerAdapter {
    public Context context;
    public ArrayList<String> posterPaths;
    private LayoutInflater layoutInflater;

    public PosterViewPagerAdapter(Context con, ArrayList<String> paths) {
        context = con;
        posterPaths = paths;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return posterPaths.size();
    }

    @Override
    public boolean isViewFromObject(View view,Object object){
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position){
        View itemView = layoutInflater.inflate(R.layout.custom_view_pager_item,container,false);
        ImageView customViewPagerImageView = (ImageView) itemView.findViewById(R.id.customViewPagerImageView);
        String imageUrl = "http://image.tmdb.org/t/p/w185" + posterPaths.get(position);
        Picasso.with(context).load(imageUrl).into(customViewPagerImageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((LinearLayout) object);
    }
}
