package com.example.vivek.movietest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vivek.movietest.DetailActivity;
import com.example.vivek.movietest.Models.Movie;
import com.example.vivek.movietest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Context context;
    private List<Movie> movies;


    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_image)
        ImageView thumbnails;
        @BindView(R.id.movie_card)
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Picasso.get().load(movies.get(position).getPosterPath()).into(holder.thumbnails);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("original_title", movies.get(position).getOriginalTitle());
                intent.putExtra("poster_path", movies.get(position).getPosterPath());
                intent.putExtra("overview", movies.get(position).getOverview());
                intent.putExtra("vote_average", Double.toString(movies.get(position).getVoteAverage()));
                intent.putExtra("release_date", movies.get(position).getReleaseDate());
                intent.putExtra("full_img", movies.get(position).getBackdropPath());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}
