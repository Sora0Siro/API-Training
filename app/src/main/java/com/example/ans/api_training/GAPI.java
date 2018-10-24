package com.example.ans.api_training;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GAPI
{
    @GET("/movie/popular")
    Call<Movie> getMovies( @Query("api_key") String api_key);
    //@Query("language") String language,
    //@Query("page") int page,
    //@Query("region") String region
}