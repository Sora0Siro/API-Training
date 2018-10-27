package com.example.ans.api_training.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ans.api_training.data.entity.Movie;
import com.example.ans.api_training.R;
import com.example.ans.api_training.data.entity.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieContract
    // здесь реализовуем этот интерфейс
    // в активити вообще не долнжо быть кода связанного с логикой
    // только запросы в контроллер который все решает и возвращает сюда нужные обработанные данные
{
    private TextView textView;
    private Movie movie;
    String request = "https://api.themoviedb.org/3/movie/550?api_key=0b3363d9492a7c969ab01d7b1cbc1029";
    //+
    ListView moviesList;
    //=
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MoviePresenter moviePresenter = new MoviePresenter(this);
        moviePresenter.start();
    }

    @Override
    public void showMovies(Movie movie)
    {
        this.movie = movie;
        setupList();
    }

    private void setupList()
    {
        moviesList = findViewById(R.id.moviesList);
        MovieAdapter movieAdapter = new MovieAdapter(this,R.layout.movie_list,this.movie.getResults());
        moviesList.setAdapter(movieAdapter);
    }
}