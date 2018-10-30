package com.example.ans.api_training.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
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
    //+
    ListView moviesList;
    //+
    private EditText editText;
    //=
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        hideToolbar();
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.searchField);

        MoviePresenter moviePresenter = new MoviePresenter(this);
        moviePresenter.start("");

        editText.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,  int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                moviePresenter.start(s.toString());
            }
        });
    }

    @Override
    public void showMovies(Movie movie)
    {
        this.movie = movie;
        setupList();
    }

    public void hideToolbar()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    private void setupList()
    {
        moviesList = null;
        moviesList = findViewById(R.id.moviesList);
        MovieAdapter movieAdapter = new MovieAdapter(this,R.layout.movie_list,this.movie.getResults());
        moviesList.setAdapter(movieAdapter);
    }
}
