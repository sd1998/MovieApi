package com.example.shashvatkedia.movieapi;

/**
 * Created by Shashvat Kedia on 12-09-2017.
 */

public class CastInfo {
    public String character;
    public String name;
    public String poster_path;

    public CastInfo(String ch,String nm,String path){
        character=ch;
         name=nm;
        poster_path=path;
    }

    public String getCharacter(){
        return character;
    }

    public String getName(){
        return  name;
    }

    public String getPath(){
        return poster_path;
    }
}
