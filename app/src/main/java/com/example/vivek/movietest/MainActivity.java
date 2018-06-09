package com.example.vivek.movietest;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.example.vivek.movietest.Adapter.MovieAdapter;
import com.example.vivek.movietest.Models.MovieList;
import com.example.vivek.movietest.Models.Result;
import com.example.vivek.movietest.Networking.MovieAPI;
import com.example.vivek.movietest.Networking.MovieClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Result> movielist=new ArrayList<>();
    MovieAdapter movieAdapter;
    MovieList movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView();
        fetchData();
    }

    private void intView() {
        recyclerView = findViewById(R.id.recycle_list);
        movieAdapter = new MovieAdapter(this, movielist);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();


    }


    private void fetchData() {
//        MovieClient client = new MovieClient();
        MovieAPI api = MovieClient.getMovieClient().create(MovieAPI.class);

        Call<Result> call =api.getPopularMovies(String.valueOf(R.string.API_KEY));
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body() != null) {

                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });



    }
}
