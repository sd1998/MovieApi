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

import static android.os.Build.VERSION_CODES.M;
import static java.lang.System.in;

/**
 * Created by Shashvat Kedia on 08-09-2017.
 */

public class Query {

    private Query(){

    }

    public static List<MovieInfo> fetchData(String murl){
        URL url=createUrl(murl);
        String response = null;
        try{
            response=makerequest(url);
        }
        catch(IOException e){
            Log.e("#","Error IOException");
        }
        List<MovieInfo> info=extractjson(response);
        return info;
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

    public static List<MovieInfo> extractjson(String response){
        if(TextUtils.isEmpty(response)){
            return null;
        }
        List<MovieInfo> info=new ArrayList<>();
        long length=0;
        try{
            JSONObject obj=new JSONObject(response);
            JSONArray mov=obj.getJSONArray("results");
            if(mov.length()>=50){
                length=50;
            }
            else{
                length=50-mov.length();
            }
            for(int i=0;i<=length-1;i++){
                JSONObject curr=mov.getJSONObject(i);
                String name=curr.getString("title");
                String path=curr.getString("poster_path");
                long id=curr.getLong("id");
                MovieInfo movie=new MovieInfo(name,path,id);
                info.add(movie);
            }
        }
        catch(JSONException e){
            Log.e("#","Error JSONException");
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
