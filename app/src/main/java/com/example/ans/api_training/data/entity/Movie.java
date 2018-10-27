package com.example.ans.api_training.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie
{
    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int total_results;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("results")
    private  List<MovieResults> results;

    Movie()
    {
        this.page = 0;
        this.total_results = 0;
        this.total_pages = 0;
        this.results = null;
    }

    Movie(Movie movie2)
    {
        this.page = movie2.getPage();
        this.total_results = movie2.getTotal_results();
        this.total_pages = movie2.getTotal_pages();
        this.results = movie2.getResults();
    }

    public List<MovieResults> getResults()
    {
        return results;
    }

    public void setResults(List<MovieResults> results)
    {
        this.results = results;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getPage()
    {
        return this.page;
    }

    public void setTotal_results(int total_results)
    {
        this.total_results = total_results;
    }

    public int getTotal_results()
    {
        return this.total_results;
    }

    public void setTotal_pages(int total_pages)
    {
        this.total_pages = total_pages;
    }

    public int getTotal_pages()
    {
        return this.total_pages;
    }
}