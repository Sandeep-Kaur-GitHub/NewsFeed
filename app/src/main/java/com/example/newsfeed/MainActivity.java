package com.example.newsfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<NewsData> array_list= new ArrayList<NewsData>();
        array_list.add(new NewsData("sports title","World news","http"));
        NewsDataAdapter news_data_adapter= new NewsDataAdapter(this,array_list);
        ListView listView= findViewById(R.id.list_view);
        listView.setAdapter(news_data_adapter);
    }
}