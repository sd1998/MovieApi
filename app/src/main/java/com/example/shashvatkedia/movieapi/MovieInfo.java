package com.example.shashvatkedia.movieapi;

/**
 * Created by Shashvat Kedia on 08-09-2017.
 */

public class MovieInfo {
    public String name;
    public String image_path;
    long id;
    public MovieInfo(String nm,String path,long info){
        name=nm;
        image_path=path;
        id=info;
    }
    public String getName(){
        return name;
    }
    public String getPath(){
        return image_path;
    }
    public long getId(){
        return id;
    }
}
