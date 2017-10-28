package com.example.shashvatkedia.movieapi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import es.dmoral.toasty.Toasty;

public class Suggestions_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions_);
        TextView sugggestion_text_view=(TextView) findViewById(R.id.suggestions_text_view);
        final EditText suggestions=(EditText) findViewById(R.id.suggestions_data);
        suggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestions.setText("");
            }
        });
        android.support.design.widget.FloatingActionButton fab_email=(android.support.design.widget.FloatingActionButton) findViewById(R.id.fab_email);
        fab_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bugs=suggestions.getText().toString();
                if(TextUtils.isEmpty(bugs)){
                    Toasty.info(getApplicationContext(),"You have not entered any text", Toast.LENGTH_LONG,true).show();
                }
                else{
                    Uri uri= Uri.fromParts("mailto","shashvat51@gmail.com",null);
                    Intent email=new Intent(Intent.ACTION_SENDTO,uri);
                    email.putExtra(Intent.EXTRA_SUBJECT,"Bug Details");
                    email.putExtra(Intent.EXTRA_TEXT,bugs);
                    startActivity(Intent.createChooser(email,"Send email..."));
                }
            }
        });
    }
}
