package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shashvatkedia on 28/10/17.
 */

public class ReviesListViewAdapter extends ArrayAdapter<Reviews> {
    Context con;
    public ReviesListViewAdapter(Context context, ArrayList<Reviews> reviewsList){
        super(context,0,reviewsList);
        con = context;
    }
    public static class ViewHolder{
        TextView authorView;
        TextView contentVIew;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        ViewHolder viewHolder;
        View view;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.reviewslistcustomcomponent,parent,false);
            viewHolder.authorView = (TextView) convertView.findViewById(R.id.authorTextView);
            viewHolder.contentVIew = (TextView) convertView.findViewById(R.id.contentTextView);
            view = convertView;
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
            view = convertView;
        }
        final Reviews review = getItem(position);
        viewHolder.contentVIew.setText(review.getReview());
        viewHolder.authorView.setText(review.getReviewAuthor());
        return convertView;
    }
}
