package com.example.shashvatkedia.movieapi;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static android.os.Build.VERSION_CODES.M;

public class Cast_data extends AppCompatActivity {

    public static ExtendedCastInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_data);
        TextView cast_name=(TextView) findViewById(R.id.cast_name_view);
        TextView cast_dob=(TextView) findViewById(R.id.dob_view);
        TextView cast_dod=(TextView) findViewById(R.id.dod_view);
        TextView cast_pob=(TextView) findViewById(R.id.pob_view);
        TextView cast_desc=(TextView) findViewById(R.id.cast_desc_view);
        ImageView cast_poster=(ImageView) findViewById(R.id.cast_poster);
        String url = "http://image.tmdb.org/t/p/w185"+info.getPath();
        Picasso.with(getApplicationContext()).load(url).into(cast_poster);
        cast_name.setText(info.getName());
        cast_dob.setText(info.getBirthday());
        cast_dod.setText(info.getDday());
        cast_pob.setText(info.getPlace());
        cast_desc.setText(info.getDesc());
    }
}
