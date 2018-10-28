package com.example.ans.api_training.data.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ans.api_training.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class MovieAdapter extends ArrayAdapter<MovieResults>
{
    private LayoutInflater inflater;
    private int layout;
    private List<MovieResults> movieResultsList;
    static final String BASE_IMG_URL = "https://image.tmdb.org/t/p/";
    static final String IMG_SIZE = "w500/";


    public MovieAdapter(Context context, int resource, List<MovieResults> movies)
    {
        super(context,resource,movies);
        this.movieResultsList = movies;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }


    public View getView(int position,View convertView, ViewGroup parent)
    {
        View view = inflater.inflate(this.layout, parent, false);

        ImageView moviePoster = view.findViewById(R.id.flag);
        TextView movieTitle = view.findViewById(R.id.movieTitle);
        TextView overview = view.findViewById(R.id.overview);
        TextView releaseDate = view.findViewById(R.id.releaseDate);
        TextView popularity = view.findViewById(R.id.popularity);

        MovieResults movieResults = movieResultsList.get(position);

        movieTitle.setText(movieTitle.getText()+movieResults.getTitle());
        overview.setText(overview.getText()+movieResults.getOverview());
        releaseDate.setText(releaseDate.getText()+movieResults.getRelease_date());
        popularity.setText(popularity.getText()+String.valueOf(movieResults.getPopularity()));
        Picasso.get().load(BASE_IMG_URL+IMG_SIZE+movieResults.getPoster_path()).into(moviePoster);

        return view;
    }
}
