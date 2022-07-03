package com.example.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NewsDataAdapter extends ArrayAdapter<NewsData> {
    public NewsDataAdapter(@NonNull Context context, ArrayList<NewsData> resource) {
        super(context,0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NewsData news_data= getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_recycler_view,parent,false);
        }
        TextView textview_title= (TextView) convertView.findViewById(R.id.textview_title);
        textview_title.setText(news_data.getTitle());
        TextView textview_section=(TextView) convertView.findViewById(R.id.textview_section);
        textview_section.setText(news_data.getSection());
        return convertView;
    }
}
