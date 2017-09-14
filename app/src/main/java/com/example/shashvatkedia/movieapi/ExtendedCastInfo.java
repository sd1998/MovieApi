package com.example.shashvatkedia.movieapi;

import static android.R.attr.rating;

/**
 * Created by Shashvat Kedia on 14-09-2017.
 */

public class ExtendedCastInfo {
    public String name;
    public String birthday;
    public String desc;
    public String poster_path;
    public String place_ob;
    public String d_day;

    public ExtendedCastInfo(String nm,String bday,String des,String path,String place,String day){
        name=nm;
        birthday=bday;
        desc=des;
        poster_path=path;
        d_day=day;
        place_ob=place;
    }

    public String getName(){
        return name;
    }

    public String getBirthday(){
        return birthday;
    }

    public String getDesc(){
        return desc;
    }

    public String getPath(){
        return poster_path;
    }

    public String getDday(){
        return d_day;
    }

    public String getPlace(){
        return place_ob;
    }

}
