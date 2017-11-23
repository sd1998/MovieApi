package com.example.shashvatkedia.movieapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManagerNonConfig;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import es.dmoral.toasty.Toasty;

import static android.R.attr.format;
import static android.R.attr.fragment;
import static android.util.Log.e;

public class Movie_info extends AppCompatActivity {
    public static CustomGridAdapter adapter;
    public static ExtendedMovieInfo info=null;
    public ImageView movie_poster_view;
    public TextView movie_name_view ;
    public TextView movie_desc_view;
    public TextView age_view ;
    public TextView rating_view ;
    public TextView genre1_view ;
    public TextView genre2_view ;
    public TextView genre3_view;
    public TextView releasedate_view;
    public TextView runtime_view;
    public TextView reviews;
    public ListView reviewsListView;
    public static ArrayList<MovieInfo> similar_info=new ArrayList<MovieInfo>();
    public static ArrayList<video_values> videos=new ArrayList<video_values>();
    public static ArrayList<MovieInfo> moviesWithSimilarGenre = null;
    public static ArrayList<Reviews>  reviewsList = null;
    public static ArrayList<MovieInfo> recommendedMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        movie_poster_view = (ImageView) findViewById(R.id.movie_poster_view);
        movie_name_view = (TextView) findViewById(R.id.movie_name_view);
        movie_desc_view = (TextView) findViewById(R.id.movie_desc_view);
        genre2_view = (TextView) findViewById(R.id.genre_2);
        runtime_view = (TextView) findViewById(R.id.movie_runtime_view);
        genre1_view = (TextView) findViewById(R.id.genre_1);
        rating_view = (TextView) findViewById(R.id.rating_view);
        releasedate_view = (TextView) findViewById(R.id.date_view);
        age_view = (TextView) findViewById(R.id.age_view);
        reviews = (TextView) findViewById(R.id.reviewsTextView);
        reviewsListView = (ListView) findViewById(R.id.reviewsList);
        String murl = "http://image.tmdb.org/t/p/w185" + info.getPath();
        Picasso.with(getApplicationContext()).load(murl).into(movie_poster_view);
        movie_name_view.setText(info.getName());
        age_view.setText(info.getAge());
        rating_view.setText(info.getRating() + "/10");
        genre1_view.setText(info.getGenre(0));
        genre2_view.setText(info.getGenre(1));
        genre1_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateGenreList(info.getGenre(0));
            }
        });
        genre2_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateGenreList(info.getGenre(1));
            }
        });
        releasedate_view.setText(info.getDate());
        String runtime = "";
        int time = info.getTime(), hrs = 0, min = 0;
        hrs = time / 60;
        time = time - hrs * 60;
        min = time;
        runtime = hrs + " Hours and " + min + " Minutes";
        runtime_view.setText(runtime);
        movie_desc_view.setText(info.getDesc());
        TextView credits=(TextView) findViewById(R.id.credits_view);
        String reviewsUrl = "https://api.themoviedb.org/3/movie/"+Integer.toString(info.getId())+"/reviews?api_key="+MainActivity.API_KEY+"&language=en-US";
        ReviewsTasker reviewsTasker = new ReviewsTasker();
        reviewsTasker.execute(reviewsUrl);
        String postersUrl = "https://api.themoviedb.org/3/movie/"+Integer.toString(info.getId())+"/images?api_key="+MainActivity.API_KEY+"&language=en-US";
        Posters posters = new Posters(Movie_info.this);
        posters.execute(postersUrl);
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://api.themoviedb.org/3/movie/"+info.getId()+"/credits?api_key="+MainActivity.API_KEY;
                Credits_Tasker task=new Credits_Tasker(Movie_info.this);
                task.execute(url);
            }
        });
        TextView similar_movie=(TextView) findViewById(R.id.similar_movies_view);
        similar_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://api.themoviedb.org/3/movie/"+info.getId()+"/similar?api_key="+MainActivity.API_KEY+"&language=en-US&page=1";
                Similar_Tasker task=new Similar_Tasker(Movie_info.this);
                task.execute(url);
            }
        });
        TextView trailer=(TextView) findViewById(R.id.view_Trailer);
        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String url="https://api.themoviedb.org/3/movie/"+info.getId()+"/videos?api_key="+MainActivity.API_KEY+"&language=en-US";
                video_tasker task=new video_tasker(Movie_info.this);
                task.execute(url);
            }
        });
        final TextView recommendedMovie = (TextView) findViewById(R.id.recommendedMovieTextView);
        recommendedMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.themoviedb.org/3/movie/"+info.getId()+"/recommendations?api_key="+MainActivity.API_KEY+"&language=en-US";
                RecommendedMovieTasker recommendedMovieTasker = new RecommendedMovieTasker();
                recommendedMovieTasker.execute(url);
            }
        });
    }
    public void generateGenreList(String genreToBeFound){
        String genreUrl = "https://api.themoviedb.org/3/genre/movie/list?api_key="+MainActivity.API_KEY+"&language=en-US";
        GenreListTasker genreTasker = new GenreListTasker(genreToBeFound);
        genreTasker.execute(genreUrl);
    }

    public class GenreListTasker extends AsyncTask<String,Void,ArrayList<GenreObject>> {
        String genreToBeFound = "";
        public GenreListTasker(String genre){
            super();
            genreToBeFound = genre;
        }
        @Override
        protected ArrayList<GenreObject> doInBackground(String... urls){
            if(urls.length < 1 || urls[0] == null){
                return null;
            }
            ArrayList<GenreObject> genreList = Query.extractAvailableGenreList(urls[0]);
            return genreList;
        }

        @Override
        protected void onPostExecute(ArrayList<GenreObject> genreList){
            if(genreList != null){
                for(int i = 0;i <= genreList.size()-1;i++){
                    if(genreList.get(i).getGenreName().equalsIgnoreCase(genreToBeFound)){
                        GenreObject genreMatched = genreList.get(i);
                        String url = "https://api.themoviedb.org/3/genre/"+genreMatched.getGenreId()+"/movies?api_key="+MainActivity.API_KEY+"&language=en-US&sort_by=created_at.asc";
                        e("#",url);
                        FindSameGenreMovieTasker sameGenreMovieTasker = new FindSameGenreMovieTasker();
                        sameGenreMovieTasker.execute(url);
                     return;
                    }
                }
                Toasty.error(getApplicationContext(),"Movies of this genre cannot be found", Toast.LENGTH_SHORT).show();
            }
            else{
                Log.e("#","Error Genre Empty");
            }
        }
    }

    public class FindSameGenreMovieTasker extends AsyncTask<String,Void,ArrayList<MovieInfo>>{
    @Override
        protected ArrayList<MovieInfo> doInBackground(String... urls){
        if(urls.length < 1 || urls[0] == null){
            return null;
        }
        ArrayList<MovieInfo> similarGenreMovie = Query.fetchData(urls[0]);
        return similarGenreMovie;
    }

    @Override
        protected void onPostExecute(ArrayList<MovieInfo> similarGenreMovie){
        if(similarGenreMovie != null){
            moviesWithSimilarGenre = similarGenreMovie;
            Intent intent = new Intent(getApplicationContext(),SimilarGenreMovie.class);
            startActivity(intent);
        }
            Toasty.error(getApplicationContext(),"No Movies of the genre found",Toast.LENGTH_SHORT).show();
    }
    }

    public class ReviewsTasker extends AsyncTask<String,Void,ArrayList<Reviews>>{
        @Override
        protected ArrayList<Reviews> doInBackground(String... urls){
            if(urls.length < 1 || urls[0] == null){
                return null;
            }
            ArrayList<Reviews> reviewsList = Query.extractReviewsList(urls[0]);
            return reviewsList;
        }

        @Override
        protected void onPostExecute(ArrayList<Reviews> reviewList){
            if(reviewList != null){
                reviewsList = reviewList;
            }
            else{
                Toasty.error(getApplicationContext(),"This movie has no reviews",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class Posters extends AsyncTask<String,Void,ArrayList<String>>{
        Context con;

    public Posters(Context context){
        super();
        con = context;
    }

    @Override
        protected ArrayList<String> doInBackground(String... urls){
        if(urls.length < 1 || urls[0] == null){
            return null;
        }
        ArrayList<String> posterPaths = Query.extraxtPosterPaths(urls[0]);
        return posterPaths;
    }

    @Override
        protected void onPostExecute(ArrayList<String> posterPaths){
        if(posterPaths != null || !posterPaths.isEmpty()){
            PosterViewPagerAdapter adapter = new PosterViewPagerAdapter(getApplicationContext(),posterPaths);
            ViewPager posterViewPager = (ViewPager) findViewById(R.id.posterViewPager);
            posterViewPager.setVisibility(View.VISIBLE);
            posterViewPager.setAdapter(adapter);
        }
        else{
            ViewPager posterViewPager  = (ViewPager) findViewById(R.id.posterViewPager);
            posterViewPager.setVisibility(View.GONE);
        }
    }

    }

    public class RecommendedMovieTasker extends AsyncTask<String,Void,ArrayList<MovieInfo>>{
    @Override
        protected ArrayList<MovieInfo> doInBackground(String... urls){
        if(urls.length < 1 || urls[0] == null){
            return null;
        }
        ArrayList<MovieInfo> recommendedMovies = Query.fetchData(urls[0]);
        return recommendedMovies;
    }

    @Override
        protected void onPostExecute(ArrayList<MovieInfo> recommendedMovies){
        if(recommendedMovies != null || !recommendedMovies.isEmpty()) {
            recommendedMovie = recommendedMovies;
            LinearLayout fragmentLinearLayout = (LinearLayout) findViewById(R.id.fragmentLinearLayout);
            fragmentLinearLayout.setVisibility(View.VISIBLE);
            CustomFragment fragment = new CustomFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.recommendedMovieFragment, fragment);
            fragmentTransaction.commit();
        }
    }
    }

    public static class CustomFragment extends Fragment{

        public CustomFragment(){
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
            View convertView = inflater.inflate(R.layout.custom_fragment_resource,container,false);
            ListView recommendedMovieListView = (ListView) convertView.findViewById(R.id.reommendedMovieListView);
            searchAdapter adapter = new searchAdapter(getActivity(),recommendedMovie);
            recommendedMovieListView.setAdapter(adapter);
            return convertView;
        }
    }
    
    public static class GenreObject{
        private String genreName;
        private int genreId;
        public GenreObject(String name,int id){
            genreName = name;
            genreId = id;
        }
        public int getGenreId(){
            return genreId;
        }
        public String getGenreName(){
            return genreName;
        }
    }
}
