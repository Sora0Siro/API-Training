package com.example.ans.api_training;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<Movie>
{
    static final String BASE_URL = "https://api.themoviedb.org/3/";
    static final String api_key = "0b3363d9492a7c969ab01d7b1cbc1029";

    public void start()
    {
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GAPI gAPI = retrofit.create(GAPI.class);

        Call<Movie> call = gAPI.getMovies(api_key);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Movie> call, Response<Movie> response)
    {
        if(response.isSuccessful())
        {
            Movie changesList = response.body();
            System.out.println("MY ANSWER IS HERE ");
            System.out.println("Page number: " + changesList.getPage()
                      + "\n" + "Total pages: " + changesList.getTotal_pages());
        }
        else
        {
            System.out.println("BIG ERROR " + response.message());
        }
    }

    @Override
    public void onFailure(Call<Movie> call, Throwable t)
    {
        System.out.println("SOME PRINTED SHIT:->>>> ");t.printStackTrace();
    }
}