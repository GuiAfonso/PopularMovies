package com.example.guilhermino.popularmovies.utilities;

/**
 * Created by guilhermino on 09/07/17.
 */

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private final static String POPULAR_THEMOVIESDB_URL =
            "https://api.themoviedb.org/3/movie/popular";

    private final static String TOP_RATED_THEMOVIESDB_URL =
            "https://api.themoviedb.org/3/movie/top_rated";

    final static String PARAM_API = "api_key";
    final static String api_key = "8804c6e52d5ff65c663f4cac29c24b64";

    public static URL buildUrl(Boolean popularMovieQuery) {

        Uri builtUri;
        if (popularMovieQuery) {
            builtUri = Uri.parse(POPULAR_THEMOVIESDB_URL).buildUpon()
                    .appendQueryParameter(PARAM_API, api_key)
                    .build();
        } else {
            builtUri = Uri.parse(TOP_RATED_THEMOVIESDB_URL).buildUpon()
                    .appendQueryParameter(PARAM_API, api_key)
                    .build();
        }

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}