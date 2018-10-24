package com.example.ans.api_training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import retrofit2.http.GET;
import retrofit2.http.Query;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity
{
    String request = "https://api.themoviedb.org/3/movie/550?api_key=0b3363d9492a7c969ab01d7b1cbc1029";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controller controller = new Controller();
        controller.start();
    }
}