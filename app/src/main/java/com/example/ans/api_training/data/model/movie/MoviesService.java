package com.example.ans.api_training.data.model.movie;

import com.example.ans.api_training.data.entity.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesService
{
    @GET("movie/popular")
    Call<Movie> getMovies(
            @Query("api_key") String api_key);

    @GET("search/movie")
    Call<Movie> searchMovie(
            @Query("api_key") String api_key,
            @Query("query") String query);
    //@Query("language") String language,
    //@Query("page") int page,
    //@Query("region") String region
    // нет сделал ее потому что если один пакет сделать то он сольется
    // вместе в дата точка модел будет херня
}