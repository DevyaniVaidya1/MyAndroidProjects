package com.example.masternewsapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Class which return and  and call artical  class, staus ,total result of news.
 */
public class NewsData {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults ")
    @Expose
    private String totalResults;
    @SerializedName("articles ")
    @Expose
    private List<Articles> articles = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

}