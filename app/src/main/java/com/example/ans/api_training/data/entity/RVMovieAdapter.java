package com.example.ans.api_training.data.entity;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ans.api_training.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class RVMovieAdapter extends RecyclerView.Adapter<RVMovieAdapter.ViewHolder>
{
    //поле для хранения списка фильмов
    private List<MovieResults>  movieResults;

    //конструктор для присвоения списка фильмов полю этого класса
    public RVMovieAdapter(List<MovieResults> movieResults)
    {
        this.movieResults = movieResults;
    }
    //===========================================================

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        //поля отображения информации
        ImageView moviePoster;
        TextView movieTitle;
        TextView overview;
        TextView releaseDate;
        TextView popularity;
        //===========================

        //связывание элементов и объектов
        public ViewHolder(@NonNull View view)
        {
            super(view);

            moviePoster = view.findViewById(R.id.flag);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            overview = view.findViewById(R.id.overview);
            releaseDate = view.findViewById(R.id.releaseDate);
            popularity = view.findViewById(R.id.popularity);
        }
        //===============================

    }

    @NonNull
    @Override
    public RVMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position)
    {
        //выделить место = откуда(context)
        LayoutInflater inflater =  LayoutInflater.from(viewGroup.getContext());
        //создать вью (берем(movie_list.xml), добавляем в(viewGroup), не привязываем к руту)
        View movieView = inflater.inflate(R.layout.movie_list,viewGroup,false);
        //создать держатель с(movieView)
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;

        //or
        //return new ViewHolder(inflater.inflate(R.layout.movie_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index)
    {
        //получаем один фильм из списка фильмов
        MovieResults movieResult = movieResults.get(index);

        //присваиваем полям из item значения из movieResult из Holder'a
        ImageView moviePoster = viewHolder.moviePoster;
        TextView movieTitle = viewHolder.movieTitle;
        TextView overview = viewHolder.overview;
        TextView releaseDate = viewHolder.releaseDate;
        TextView popularity = viewHolder.popularity;

        Resources.getSystem().getString(R.string.IMG_SIZE);
        Picasso.get().load("https://image.tmdb.org/t/p/" +"w500/" + movieResult.getPoster_path()).into(moviePoster);

        movieTitle.setText(movieResult.getTitle());
        overview.setText(movieResult.getOverview());
        releaseDate.setText(movieResult.getRelease_date());
        popularity.setText(String.valueOf(movieResult.getPopularity()));
    }

    @Override
    public int getItemCount()
    {
        return movieResults.size();
    }
}
