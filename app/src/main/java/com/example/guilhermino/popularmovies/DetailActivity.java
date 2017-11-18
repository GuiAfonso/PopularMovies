package com.example.guilhermino.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView mTitleText;
    private ImageView mPosterImage;
    private TextView mSynopsisText;
    private TextView mRatingText;
    private TextView mDateText;

    final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    final String IMAGE_SIZE = "w500/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTitleText = (TextView) findViewById(R.id.tv_movie_title);
        mPosterImage = (ImageView) findViewById(R.id.iv_movie_image);
        mSynopsisText = (TextView) findViewById(R.id.tv_movie_synopsis);
        mRatingText = (TextView) findViewById(R.id.tv_movie_rating);
        mDateText = (TextView) findViewById(R.id.tv_movie_date);


        Intent intent = getIntent();


        if (intent.hasExtra("title")) {

            String title = intent.getStringExtra("title");
            String poster = intent.getStringExtra("poster");
            String synopsis = intent.getStringExtra("synopsis");
            String rating = intent.getStringExtra("rating");
            String date = intent.getStringExtra("date");

            String image_url = IMAGE_BASE_URL + IMAGE_SIZE + poster;

            Picasso.with(getBaseContext())
                    .load(image_url)
                    .into(mPosterImage);

            mTitleText.setText(title);
            mSynopsisText.setText(synopsis);
            mRatingText.setText(rating);
            mDateText.setText(date);
        }
    }
}
