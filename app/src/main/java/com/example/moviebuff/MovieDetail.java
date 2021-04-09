package com.example.moviebuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class MovieDetail extends AppCompatActivity {
    private ImageView movieImageView;
    private TextView movieTitleTextView;
    private TextView descriptionTextView;
    private TextView releaseDateTextView;
    private TextView ratingTextView;
    private TextView reviewsTextView;

    private String title;
    private String imageUrl;
    private String description;
    private String releaseDate;
    private String rating;
    private String reviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        //initializing views
        movieImageView =findViewById(R.id.movieImageView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        movieTitleTextView = findViewById(R.id.movieTitleTextView);
        releaseDateTextView = findViewById(R.id.releaseDateTextView);
        ratingTextView = findViewById(R.id.ratingTextView);
        reviewsTextView = findViewById(R.id.reviewsTextView);

        // getting rawData from mainActivity using intent.
        Intent intent = getIntent();
        String data = intent.getStringExtra("Data");
        descriptionTextView.setText(data);

        //extracting necessary data from rawData using String manipulation (regex is used).
        extractData(data);

        //setting all the views of MoviesDetail Activity
        setViews();

    }

    public void extractData(String data){
        String[] splitArray = data.split("@");
        title = splitArray[0];
        imageUrl = splitArray[1];
        description = splitArray[2];
        releaseDate = splitArray[3];
        rating = splitArray[4];
        reviews = splitArray[5];
        Log.i("imageUrl",imageUrl);



    }

    public void setViews(){
        movieTitleTextView.setText(title);
        descriptionTextView.setText(description);
        releaseDateTextView.setText(releaseDate);
        ratingTextView.setText(rating +" out of 10");
        reviewsTextView.setText(reviews +" people");


        Glide.with(MovieDetail.this)
                .load(imageUrl)
                .into(movieImageView);




    }

}