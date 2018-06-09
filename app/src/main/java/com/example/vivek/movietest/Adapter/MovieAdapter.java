package com.example.vivek.movietest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vivek.movietest.Models.MovieList;
import com.example.vivek.movietest.Models.Result;
import com.example.vivek.movietest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Context context;
    private List<Result> movielist;

    public MovieAdapter(Context context, List<Result> movielist) {
        this.context = context;
        this.movielist = movielist;
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
        holder.title.setText(movielist.get(position).getOriginalTitle());
        Picasso.get().load(movielist.get(position).getPosterPath()).into(holder.thumbnails);
    }

    @Override
    public int getItemCount() {
        return movielist.size();
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
}
