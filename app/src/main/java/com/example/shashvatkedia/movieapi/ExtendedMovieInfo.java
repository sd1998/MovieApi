package com.example.shashvatkedia.movieapi;

import static com.example.shashvatkedia.movieapi.MainActivity.movie;

/**
 * Created by Shashvat Kedia on 12-09-2017.
 */

public class ExtendedMovieInfo {
    public String movie_name;
    public String movie_desc;
    public int movie_id;
    public int movie_runtime;
    public String movie_release;
    public String[] movie_genre=new String[3];
    public String movie_age;
    public double movie_rating;
    public String movie_poster_path;

    public ExtendedMovieInfo(String name,String desc,int id,int runtime,String release,String genre1,String genre2,String genre3,String age,double rating,String poster){
        movie_name=name;
        movie_desc=desc;
        movie_id=id;
        movie_runtime=runtime;
        movie_release=release;
        movie_genre[0]=genre1;
        movie_genre[1]=genre2;
        movie_genre[2]=genre3;
        movie_age=age;
        movie_rating=rating;
        movie_poster_path=poster;
    }

    public String getName(){
        return movie_name;
    }

    public String getDesc(){
        return movie_desc;
    }

    public int getId(){
        return movie_id;
    }

    public int getTime(){
        return movie_runtime;
    }

    public String getDate(){
        return movie_release;
    }

    public String getGenre(int i){
        if(i<=2){
            return movie_genre[i];
        }
        return null;
    }

    public String getAge(){
        return movie_age;
    }

    public double getRating(){
        return movie_rating;
    }

    public String getPath(){
        return movie_poster_path;
    }
}
