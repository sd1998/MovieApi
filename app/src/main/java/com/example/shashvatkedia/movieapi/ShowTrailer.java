package com.example.shashvatkedia.movieapi;

import android.content.Intent;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import es.dmoral.toasty.Toasty;

public class ShowTrailer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youtubePlayerView;
    private static final int RECOVERY_REQUEST = 1;
    private YoutubePlaybackEventListener youtubePlaybackEventListener;
    private YoutubePlayerStateChangeListener youtubePlayerStateChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_trailer);
        youtubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlayer);
        youtubePlaybackEventListener = new YoutubePlaybackEventListener();
        youtubePlayerStateChangeListener = new YoutubePlayerStateChangeListener();
        youtubePlayerView.initialize(MainActivity.YOUTUBE_API_KEY,this);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youtubePlayer, boolean wasRestored) {
        youtubePlayer.setPlaybackEventListener(youtubePlaybackEventListener);
        youtubePlayer.setPlayerStateChangeListener(youtubePlayerStateChangeListener);
        if(!wasRestored){
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            String key = bundle.getString("youtubeKey");
            youtubePlayer.cueVideo(key);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,RECOVERY_REQUEST).show();
        }
        else{
            String error = String.format("Error initializing YouTube player: ",youTubeInitializationResult.toString());
            Toasty.error(this,error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode == RECOVERY_REQUEST){
            getYouTubePlayerProvider().initialize(MainActivity.YOUTUBE_API_KEY, this);
        }
    }

    protected YouTubePlayer.Provider  getYouTubePlayerProvider(){
        return youtubePlayerView;
    }

    private final class YoutubePlaybackEventListener implements YouTubePlayer.PlaybackEventListener{
        @Override
        public void onPlaying(){
            Toasty.info(getApplicationContext(),"Trailer now playing",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onStopped() {
        }

        @Override
        public void onBuffering(boolean b) {
        }

        @Override
        public void onSeekTo(int i) {
        }
    }

    private final class YoutubePlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener{

        @Override
        public void onLoading() {
        }

        @Override
        public void onLoaded(String s) {
        }

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onVideoStarted() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            Toasty.error(getApplicationContext(),errorReason.toString(),Toast.LENGTH_SHORT).show();
            Log.e("#Error ShowTrailer.java",errorReason.toString());
        }
    }
}
