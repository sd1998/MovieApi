package com.example.shashvatkedia.movieapi;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import io.reactivex.annotations.Nullable;

/**
 * Created by shashvatkedia on 17/12/17.
 */

public class AdvancedSearch extends Fragment {

    private Spinner genreSpinner;
    private Spinner ratingsSpinner;
    private Spinner orderbySpinner;

    public AdvancedSearch(){
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.advanced_search_layout,container,false);
        genreSpinner = (Spinner)view.findViewById(R.id.genre_spinner);
        ratingsSpinner = (Spinner) view.findViewById(R.id.ratings_spinner);
        orderbySpinner = (Spinner) view.findViewById(R.id.orderby_spinner);

        return view;
    }

    public Spinner getGenreSpinner(){
        return genreSpinner;
    }

    public Spinner getRatingsSpinner(){
        return ratingsSpinner;
    }

    public Spinner getOrderbySpinner(){
        return orderbySpinner;
    }

}
