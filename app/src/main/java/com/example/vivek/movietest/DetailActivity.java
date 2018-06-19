package com.example.vivek.movietest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    TextView movie_title, movie_rating, movie_release_date, movie_overview;
    ImageView movieFullimg, moviePosterImg;
    Toolbar tb;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        movie_title = findViewById(R.id.movie_title);
        movie_rating = findViewById(R.id.movie_rating);
        movie_release_date = findViewById(R.id.movie_release_date);
        movie_overview = findViewById(R.id.movie_overview);
        movieFullimg = findViewById(R.id.full_img);
        moviePosterImg = findViewById(R.id.posterImg);
        tb=findViewById(R.id.detail_toolbar);
        tb.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(tb);
        tb.setTitle(" ");


        movie_title.setSelected(true);
        Intent intent = getIntent();
        if (intent.hasExtra("original_title")) {
            String title = Objects.requireNonNull(getIntent().getExtras()).getString("original_title");
            String overview = getIntent().getExtras().getString("overview");
            String moviepath = getIntent().getExtras().getString("poster_path");
            String rating = getIntent().getExtras().getString("vote_average");
            String relase_date = getIntent().getExtras().getString("release_date");
            String full_img=getIntent().getExtras().getString("full_img");

            Picasso.get().load(moviepath).placeholder(R.drawable.ic_error).into(moviePosterImg);
            Picasso.get().load(full_img).placeholder(R.drawable.ic_error).fit().into(movieFullimg);
            movie_title.setText(title);
            movie_rating.setText(rating);
            movie_release_date.setText(relase_date);
            movie_overview.setText(overview);

        } else {
            Toast.makeText(DetailActivity.this, "No API data fetched", Toast.LENGTH_SHORT).show();
        }


    }

}
