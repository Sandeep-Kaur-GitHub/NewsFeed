package com.example.newsfeed;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class NewsDataLoader extends AsyncTaskLoader<ArrayList<NewsData>> {
    private String mUrl;

    public NewsDataLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<NewsData> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        URL url = Utils.createUrl(mUrl);
        String jsonResponse = "";
        try {
            jsonResponse = Utils.makeHttpRequest(url);
        } catch (IOException e) {

        }
        ArrayList<NewsData> data_array_list = Utils.extractDataFromJson(jsonResponse);
        return data_array_list;

    }
}
