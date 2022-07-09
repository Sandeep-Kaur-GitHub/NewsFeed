package com.example.newsfeed;

public class NewsData {
    public String mTitle;
    public String mSection;
    public String mUrl;
    public String mDate;
    public String mAuthor;

    public NewsData(String title, String Section, String Url, String date, String author) {
        mTitle = title;
        mSection = Section;
        mUrl = Url;
        mDate = date;
        mAuthor=author;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getDate() {
        return mDate;
    }
    public String getAuthor(){
        return mAuthor;
    }
}
