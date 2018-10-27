package com.example.ans.api_training.ui.main;


import com.example.ans.api_training.data.entity.Movie;
import com.example.ans.api_training.data.model.movie.MoviesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviePresenter { // этот класс отвечает за обработку запросов - логику -
    // сюда надо запихивать всю логику
    static final String BASE_URL = "https://api.themoviedb.org/3/";
    static final String BASE_IMG_URL = "somewhat url for images";
    static final String api_key = "0b3363d9492a7c969ab01d7b1cbc1029";

    private MovieContract movieContract;

    public MoviePresenter(MovieContract movieContract) {
        this.movieContract = movieContract;
    }

    public void start()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MoviesService moviesService = retrofit.create(MoviesService.class);

        Call<Movie> call = moviesService.getMovies(api_key);
        call.enqueue(new Callback<Movie>()
        {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response)
            {
                movieContract.showMovies(response.body());
                // здесь передаем данные через интерфейс а в активити как раз их обрабатываем
                // они там появятся в том методе (@Override)
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t)
            {
               t.printStackTrace();
            }
        });
    }
}