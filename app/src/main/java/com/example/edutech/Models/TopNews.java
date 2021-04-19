package com.example.edutech.Models;

public class TopNews {
    String news_image,news_headline,date;

    public TopNews() {
    }

    public TopNews(String news_image, String news_headline, String date) {
        this.news_image = news_image;
        this.news_headline = news_headline;
        this.date = date;
    }

    public String getNews_image() {
        return news_image;
    }

    public void setNews_image(String news_image) {
        this.news_image = news_image;
    }

    public String getNews_headline() {
        return news_headline;
    }

    public void setNews_headline(String news_headline) {
        this.news_headline = news_headline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
