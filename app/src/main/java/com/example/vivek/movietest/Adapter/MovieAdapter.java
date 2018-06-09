package com.example.vivek.movietest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vivek.movietest.Models.Movie;
import com.example.vivek.movietest.Models.MovieResponse;
import com.example.vivek.movietest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnails;
        public TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            thumbnails = itemView.findViewById(R.id.movie_image);
            title = itemView.findViewById(R.id.movie_name);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(movies.get(position).getTitle());
        Picasso.get().load(movies.get(position).getPosterPath()).into(holder.thumbnails);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}
