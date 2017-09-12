package com.example.shashvatkedia.movieapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class Cast_display extends AppCompatActivity {

    public static ArrayList<CastInfo> cast=new ArrayList<CastInfo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_display);
        cast_adapter adapt=new cast_adapter(this,cast);
        ListView cast_view=(ListView) findViewById(R.id.cast_view);
        cast_view.setAdapter(adapt);
    }
}
