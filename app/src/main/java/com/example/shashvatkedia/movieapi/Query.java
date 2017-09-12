package com.example.shashvatkedia.movieapi;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.os.Build.VERSION_CODES.M;
import static com.example.shashvatkedia.movieapi.MainActivity.movie;
import static java.lang.System.in;

/**
 * Created by Shashvat Kedia on 08-09-2017.
 */

public class Query {

    private Query(){

    }

    public static ArrayList<MovieInfo> fetchData(String murl){
        URL url=createUrl(murl);
        String response = null;
        try{
            response=makerequest(url);
        }
        catch(IOException e){
            Log.e("#","Error IOException");
        }
        ArrayList<MovieInfo> info=extractjson(response);
        return info;
    }

    public static ExtendedMovieInfo findMovieInfo(String murl){
        URL url=createUrl(murl);
        String response=null;
        try{
            response=makerequest(url);
        }
        catch(IOException e){
            Log.e("#","IOException");
        }
        ExtendedMovieInfo info=extractMovieInfo(response);
        return info;
    }

    public static ExtendedMovieInfo extractMovieInfo(String response){
        if(TextUtils.isEmpty(response)){
            return null;
        }
        ExtendedMovieInfo info=null;
        try{
            JSONObject obj=new JSONObject(response);
            boolean age=obj.getBoolean("adult");
            String movie_age="",movie_name,movie_desc,movie_release,movie_poster;
            String[] movie_genres={"","",""};
            int length=0,movie_id,movie_runtime;
            double movie_rating;
            if(age){
                movie_age="A";
            }
            else{
                movie_age="UA";
            }
            JSONArray genre=(JSONArray) obj.getJSONArray("genres");
            if(genre.length()>=3){
                length=3;
            }else{
                length=genre.length();
            }
            for(int i=0;i<=length-1;i++){
                JSONObject array_obj=(JSONObject) genre.getJSONObject(i);
                movie_genres[i]=array_obj.getString("name");
            }
            movie_id=obj.getInt("id");
            movie_name=obj.getString("original_title");
            movie_desc=obj.getString("overview");
            movie_rating=obj.getDouble("vote_average");
            movie_runtime=obj.getInt("runtime");
            movie_release=obj.getString("release_date");
            movie_poster=obj.getString("poster_path");
            info=new ExtendedMovieInfo(movie_name,movie_desc,movie_id,movie_runtime,movie_release,movie_genres[0],movie_genres[1],movie_genres[2],movie_age,movie_rating,movie_poster);
        }
        catch(JSONException e){
            Log.e("#","JSONException");
        }
        return info;
    }

    public static Integer findId(String murl){
        URL url=createUrl(murl);
        String response=null;
        try{
            response=makerequest(url);
        }
        catch(IOException e) {
            Log.e("#", "IOException");
        }
        Integer id=extractId(response);
        return id;
    }

    public static Integer extractId(String response){
        Integer id=null;
        if(TextUtils.isEmpty(response)){
            return null;
        }
        try{
            JSONObject obj=new JSONObject(response);
            JSONArray arr=(JSONArray) obj.getJSONArray("results");
            JSONObject info=(JSONObject) arr.getJSONObject(0);
            id=info.getInt("id");
        }
        catch(JSONException e){
            Log.e("#","JSONException");
        }
        return id;
    }

    public static String makerequest(URL url) throws IOException{
        String json="";
        if(url == null){
            return json;
        }
        HttpURLConnection connection = null;
        InputStream input = null;
        try{
            connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Accept", "application/json");
            connection.connect();
            if(connection.getResponseCode()==200){
                input=connection.getInputStream();
                json=read(input);
            }
            else{
                Log.e("#","Error Code"+connection.getResponseCode());
            }
        }
        catch(IOException e){
            Log.e("#","Error IOException");
        }
        finally{
            if(connection!=null){
                connection.disconnect();
            }
            if(input!=null){
                input.close();
            }
        }
        return json;
    }

    public static String read(InputStream input) throws IOException{
        StringBuilder build=new StringBuilder();
        if(input!=null){
            InputStreamReader inputread = new InputStreamReader(input, Charset.forName("UTF-8"));
            BufferedReader buffer=new BufferedReader(inputread);
            String line=buffer.readLine();
            while(line != null){
                build.append(line);
                line=buffer.readLine();
            }
        }
        return build.toString();
    }

    public static ArrayList<MovieInfo> extractjson(String response){
        if(TextUtils.isEmpty(response)){
            return null;
        }
        ArrayList<MovieInfo> info=new ArrayList<>();
        try{
            JSONObject obj=new JSONObject(response);
            JSONArray mov=(JSONArray) obj.getJSONArray("results");
            for(int i=0;i<=mov.length()-1;i++){
                JSONObject curr=mov.getJSONObject(i);
                String name=curr.getString("original_title");
                String path=curr.getString("poster_path");
                long id=curr.getLong("id");
                MovieInfo movie=new MovieInfo(name,path,id);
                info.add(movie);
            }
        }
        catch(JSONException e){
            Log.e("#","Error JSONException"+e);
        }
        return info;
    }

    public static URL createUrl(String murl){
        URL url=null;
        try{
            url=new URL(murl);
        }
        catch(MalformedURLException e){
            Log.e("#","Malformed Error");
        }
        return url;
    }
}
