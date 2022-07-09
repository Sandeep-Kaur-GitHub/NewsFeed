package com.example.newsfeed;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Utils {
    public static URL createUrl(String dataUrl) {
        URL url = null;
        try {
            url = new URL(dataUrl);
        } catch (MalformedURLException e) {
            Log.e("LOG_TAG", "Error with creating URL", e);
            return null;
        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);

        } catch (IOException e) {

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;

    }

    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    public static ArrayList<NewsData> extractDataFromJson(String json) {

        ArrayList<NewsData> array_list = new ArrayList<NewsData>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject first_object = jsonObject.getJSONObject("response");
            JSONArray results = first_object.getJSONArray("results");
            for (int i = 0; i <= results.length(); i++) {
                JSONObject array_object = results.getJSONObject(i);
                JSONArray arr=array_object.getJSONArray("tags");
                JSONObject obj=arr.getJSONObject(0);
                String json_author=obj.getString("webTitle");
                String jTitle = array_object.getString("webTitle");
                String jSection = array_object.getString("sectionName");
                String jUrl = array_object.getString("webUrl");
                String jDate = array_object.getString("webPublicationDate");
                String[] splitDate = jDate.split("T");
                String separatedDate = splitDate[0];
                NewsData newsData = new NewsData(jTitle, jSection, jUrl, separatedDate,json_author);
                array_list.add(newsData);
            }
        } catch (Exception e) {

        }
        return array_list;
    }
}
