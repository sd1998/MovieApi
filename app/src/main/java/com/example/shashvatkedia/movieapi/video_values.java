package com.example.shashvatkedia.movieapi;

/**
 * Created by Shashvat Kedia on 14-09-2017.
 */

public class video_values {
    public String key;
    public String type;
    public String site;

    public video_values(String video_key,String video_type,String video_site){
        key=video_key;
        type=video_type;
        site=video_site;
    }

    public String getKey(){
        return key;
    }

    public String getType(){
        return type;
    }

    public String getSite(){
        return site;
    }

}
