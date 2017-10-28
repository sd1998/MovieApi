package com.example.shashvatkedia.movieapi;

/**
 * Created by shashvatkedia on 28/10/17.
 */

public class Reviews {
    private String reviewAuthor;
    private String review;
    private String reviewId;
    private String reviewUrl;

    public Reviews(String name,String content,String id,String url){
        reviewAuthor = name;
        review = content;
        reviewId = id;
        reviewUrl = url;
    }

    public String getReviewAuthor(){
        return reviewAuthor;
    }

    public String getReviewId(){
        return reviewId;
    }

    public String getReview(){
        return review;
    }

    public String getReviewUrl(){
        return reviewUrl;
    }
}

