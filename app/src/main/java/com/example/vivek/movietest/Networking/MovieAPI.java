package com.example.vivek.movietest.Networking;


import com.example.vivek.movietest.Models.MovieList;
import com.example.vivek.movietest.Models.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MovieAPI {

    //getting the Popular Movie
    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String api_key);

    //getting the Top rated Movies
    @GET("movie/top_rated")
    Call<Result> getTopRatedMovies(@Query("api_key") String api_key);

}
