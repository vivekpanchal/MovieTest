package com.example.vivek.movietest;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;


import com.example.vivek.movietest.Adapter.MovieAdapter;
import com.example.vivek.movietest.Models.Movie;
import com.example.vivek.movietest.Models.MovieResponse;
import com.example.vivek.movietest.Networking.ApiClient;
import com.example.vivek.movietest.Networking.ApiInterface;


import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycle_list)
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;

    Toolbar toolbar;
    ProgressBar progressBar;

    private final static String API_KEY = "d411ae8547a5999d5d617464c27bced9";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView();


        loadPopularMovies();
    }

    private void intView() {
        toolbar = findViewById(R.id.my_toolbar);

        setSupportActionBar(toolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_sort_black_24dp);
        toolbar.setOverflowIcon(drawable);
        ButterKnife.bind(this);


        progressBar = findViewById(R.id.pb);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            recyclerView.setAdapter(movieAdapter);
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setAdapter(movieAdapter);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void fetchData(Call<MovieResponse> call) {

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null) {
                    try {
                        assert response.body() != null;
                        List<Movie> movies = response.body().getResults();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(new MovieAdapter(MainActivity.this, movies));
                    } catch (NullPointerException e) {
                        Log.d(TAG, "onResponse: null pointer aarha h ");
                    }
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });
    }

    public void loadPopularMovies() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiInterface.getPopularMovies(API_KEY);
        progressBar.setVisibility(View.VISIBLE);
        fetchData(call);
    }

    public void loadTopRatedMovies() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiInterface.getTopRatedMovies(API_KEY);
        progressBar.setVisibility(View.VISIBLE);
        fetchData(call);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.top_rated:
                loadTopRatedMovies();
                return true;
            case R.id.popular:
                loadPopularMovies();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

