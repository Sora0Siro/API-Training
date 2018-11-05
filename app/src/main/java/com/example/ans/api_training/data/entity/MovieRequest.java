package com.example.ans.api_training.data.entity;

import com.example.ans.api_training.data.model.movie.MoviesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRequest
{
    private static MovieRequest instance = null;
    private Retrofit retrofit;
    private Gson gson;

    private MoviesService moviesService;

    static final String BASE_URL = "https://api.themoviedb.org/3/";

    public MovieRequest()
    {
        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        moviesService = retrofit.create(MoviesService.class);
    }

    public static MovieRequest getInstance()
    {
        if(instance == null)
        {
            instance = new MovieRequest();
        }
        return instance;
    }

    public MoviesService getMoviesService()
    {
        return moviesService;
    }
}

