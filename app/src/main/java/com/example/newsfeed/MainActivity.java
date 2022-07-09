package com.example.newsfeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<NewsData>> {
    private static final int LOADER_ID = 1;
    private String theGuardianUrl = "https://content.guardianapis.com/search";
    NewsDataAdapter news_data_adapter;
    ListView listView;
    private TextView mEmptyStateTextView;
    View v;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmptyStateTextView = findViewById(R.id.empty_view);
        v = findViewById(R.id.progress);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);
            news_data_adapter = new NewsDataAdapter(this, new ArrayList<NewsData>());
            listView = findViewById(R.id.list_view);
            listView.setAdapter(news_data_adapter);

        } else {
            v.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.noInternet);
        }
        if (listView != null) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    NewsData currentData = news_data_adapter.getItem(i);
                    Uri earthquakeUri = Uri.parse(currentData.getUrl());
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                    if (websiteIntent.resolveActivity(getPackageManager()) != null) {

                        startActivity(websiteIntent);
                    }
                    Toast.makeText(getApplicationContext(),
                                    "Click ListItem Number " + i, Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
    }

    @Override
    public Loader<ArrayList<NewsData>> onCreateLoader(int id, @Nullable Bundle args) {
        Uri baseUri = Uri.parse(theGuardianUrl);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q", "technology");
        uriBuilder.appendQueryParameter("api-key", "08a79839-6aa5-4a47-80dc-2c2afb89865b");
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        return new NewsDataLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<NewsData>> loader, ArrayList<NewsData> data) {
        v.setVisibility(View.GONE);
        news_data_adapter.clear();
        if (data != null && !data.isEmpty()) {
            news_data_adapter.addAll(data);
        }else {
            mEmptyStateTextView.setText(R.string.no_news);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<NewsData>> loader) {
        news_data_adapter.clear();
    }
}