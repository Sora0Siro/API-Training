package com.example.ans.api_training.ui.main.UI;

import com.example.ans.api_training.data.entity.Movie;
import com.example.ans.api_training.data.entity.MovieErMsgHandler;
import com.example.ans.api_training.data.entity.MovieRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter
{ // этот класс отвечает за обработку запросов - логику -
    // сюда надо запихивать всю логику
    static final String BASE_URL = "https://api.themoviedb.org/3/";
    static final String BASE_IMG_URL = "https://image.tmdb.org/t/p/";
    static final String api_key = "0b3363d9492a7c969ab01d7b1cbc1029";
    static final String size = "w500/";
    static final String chckInMsg = "Check internet connection";

    private MovieContract movieContract;
    private MovieErMsgHandler movieErMsgHandler;

    public MoviePresenter(MovieContract movieContract,MovieErMsgHandler movieErMsgHandler)
    {
        this.movieContract = movieContract;
        this.movieErMsgHandler = movieErMsgHandler;
    }

    public void start(String query)
    {
        ////////////////////////////////////////////////////////////////////
        if(query.isEmpty())
        {
            //показ популярных фильмов
            Call<Movie> call = MovieRequest.getInstance().getMoviesService().getMovies(api_key);
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response)
                {
                    movieContract.showMovies(response.body().getResults());
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t)
                {
                    movieErMsgHandler.failureMessage(chckInMsg);
                    t.printStackTrace();
                }
            });
        }

        else
        {
            Call<Movie> call = MovieRequest.getInstance().getMoviesService() .searchMovie(api_key,query);
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response)
                {
                    movieContract.showMovies(response.body().getResults());
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t)
                {
                    movieErMsgHandler.failureMessage(chckInMsg);
                    t.printStackTrace();
                }
            });
        }
        ///////////////////////////////////////////////////////////////////
    }
}