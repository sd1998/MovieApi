package com.example.shashvatkedia.movieapi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

public class custom_search extends AppCompatActivity {
    public String query_movie="https://api.themoviedb.org/3/search/movie?api_key="+MainActivity.API_KEY+"&query=";
    public String check_query_movie=query_movie;
    public static Integer id;
    public static com.wang.avi.AVLoadingIndicatorView anim;
    private RadioButton advancedSearch;
    private int frag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_search);
        final EditText name=(EditText) findViewById(R.id.movie_name);
        final Button submit=(Button) findViewById(R.id.search);
        advancedSearch = (RadioButton) findViewById(R.id.advanced_search_radio_button);
        advancedSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    advancedSearch.setVisibility(View.GONE);
                    AdvancedSearch fragment = new AdvancedSearch();
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(R.id.advanced_search_fragment,fragment);
                    transaction.commit();
                    frag++;
                }
            }
        });
        anim=findViewById(R.id.progress_anim);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movie_name = name.getText().toString();
                String query_name = "";
                int prev = 0;
                for (int i = 0; i <= movie_name.length() - 1; i++) {
                    if (movie_name.charAt(i) == ' ') {
                        query_name = query_name + movie_name.substring(prev, i).trim() + "+";
                        prev = i + 1;
                    }
                }
                if (query_name.equals("")) {
                    query_name = movie_name;
                }
                if (frag == 0) {
                    query_movie = query_movie + query_name;
                    query_movie = query_movie.trim();
                    if (!query_movie.equals(check_query_movie)) {
                        anim.setVisibility(View.VISIBLE);
                        anim.show();
                        Query_Tasker task = new Query_Tasker(custom_search.this);
                        task.execute(query_movie);
                    }
                }
                else{
                    // Execute custom search
                    frag = 0;
                }
            }
        });
    }
}
