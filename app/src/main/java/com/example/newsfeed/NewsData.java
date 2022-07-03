package com.example.newsfeed;

public class NewsData {
    public String mTitle;
    public String mSection;
    public String mUrl;

    public NewsData(String title, String Section, String Url) {
        mTitle = title;
        mSection = Section;
        mUrl = Url;
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


}
