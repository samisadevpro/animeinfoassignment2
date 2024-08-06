package com.example.animeapi;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class JikanAPI {

    public static JsonObject searchAnime(String query) throws Exception {
        // Encode the query to handle spaces and special characters
        String encodedQuery = URLEncoder.encode(query, "UTF-8");
        // Construct the URL with the encoded query
        String urlString = "https://api.jikan.moe/v4/anime?q=" + encodedQuery;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP GET Request Failed with Error code : " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parse the response to a JsonObject
        return JsonParser.parseString(response.toString()).getAsJsonObject();
    }
}
