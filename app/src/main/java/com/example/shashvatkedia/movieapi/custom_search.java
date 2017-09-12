package com.example.shashvatkedia.movieapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class custom_search extends AppCompatActivity {
    public String query_movie="https://api.themoviedb.org/3/search/movie?api_key="+MainActivity.API_KEY+"&query=";
    public String check_query_movie=query_movie;
    public static Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_search);
        final EditText name=(EditText) findViewById(R.id.movie_name);
        final Button submit=(Button) findViewById(R.id.search);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movie_name=name.getText().toString();
                String query_name="";
                int prev=0;
                for(int i=0;i<=movie_name.length()-1;i++){
                    if(movie_name.charAt(i)==' '){
                        query_name=query_name+movie_name.substring(prev,i).trim()+"+";
                        prev=i+1;
                    }
                }
                if(query_name.equals(" ")){
                    query_name=movie_name;
                }
                query_movie=query_movie+query_name;
                query_movie.trim();
                if(!query_movie.equals(check_query_movie)){
                    Query_Tasker task=new Query_Tasker();
                    task.execute(query_movie);
                }
            }
        });
    }
}
