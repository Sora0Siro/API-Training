package com.example.ans.api_training;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Movie
{
    @SerializedName("page")
    int page;

    @SerializedName("total_results")
    int total_results;

    @SerializedName("total_pages")
    int total_pages;

    @SerializedName("results")
    List<Results> results;

    public List<Results> getResults()
    {
        return results;
    }

    public void setResults(List<Results> results) {
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