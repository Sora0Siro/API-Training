package com.example.ans.api_training.ui.main.UI;

import com.example.ans.api_training.data.entity.Movie;
import com.example.ans.api_training.data.entity.MovieResults;

import java.util.List;

public interface MovieContract {
    void showMovies(List<MovieResults> movieResults); // здксь просто описываем метод с нужнвм нам параметром
}
