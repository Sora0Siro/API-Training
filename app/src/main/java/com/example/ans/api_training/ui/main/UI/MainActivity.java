package com.example.ans.api_training.ui.main.UI;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ans.api_training.data.entity.Movie;
import com.example.ans.api_training.R;
import com.example.ans.api_training.data.entity.MovieAdapter;
import com.example.ans.api_training.data.entity.MovieErMsgHandler;
import com.example.ans.api_training.data.entity.MovieResults;
import com.example.ans.api_training.data.entity.RVMovieAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieContract,MovieErMsgHandler
    // только запросы в контроллер который все решает и возвращает сюда нужные обработанные данные
{
    //private TextView textView;
    //private Movie movie;
    //ListView moviesList;
    private List<MovieResults> movieResults;
    private EditText editText;
    //
    RVMovieAdapter movieAdapter;
    RecyclerView rvMovies;
    //=
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        hideToolbar();
        setContentView(R.layout.activity_main3);

        onCrt();
    }

    public void onCrt()
    {
        editText = findViewById(R.id.searchField);

        MoviePresenter moviePresenter = new MoviePresenter(this,this);

        //if(checkConnection())
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
    public void showMovies(List<MovieResults> movieResults)
    {
        this.movieResults = movieResults;
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
        //есть список фильмов
        //movieResults
        rvMovies = findViewById(R.id.myRecyclerView);
        movieAdapter = new RVMovieAdapter(movieResults);
        rvMovies.setAdapter(movieAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

    }

    protected boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager)getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {

            return true;

        } else {

            return false;

        }

    }

    public boolean checkConnection() {

        if (isOnline()) {
            return true;

        } else {

            Toast.makeText(MainActivity.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();

            return false;
        }
    }

    @Override
    public void failureMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
