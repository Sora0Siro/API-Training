package com.example.ans.api_training.ui.main.UI;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements MovieContract,MovieErMsgHandler
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
