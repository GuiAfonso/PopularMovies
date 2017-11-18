package com.example.guilhermino.popularmovies.utilities;

import android.content.Context;

import com.example.guilhermino.popularmovies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by guilhermino on 09/07/17.
 */

public class OpenMovieJsonUtils {

    public static Movie[] getSimpleMovieStringsFromJson(Context context, String movieJsonStr)
            throws JSONException {

        final String OM_RESULTS = "results";

        final String OM_ORIGINAL_TITLE = "original_title";
        final String OM_POSTER_IMAGE = "poster_path";
        final String OM_SYNOPSIS = "overview";
        final String OM_USER_RATING = "vote_average";
        final String OM_RELEASE_DATE = "release_date";

        final String OM_STATUS_CODE = "status_code";

        Movie[] parsedMovieData;

        JSONObject movieJson = new JSONObject(movieJsonStr);

        if (movieJson.has(OM_STATUS_CODE)) {
            return null;
        }

        JSONArray movieArray = movieJson.getJSONArray(OM_RESULTS);

        parsedMovieData = new Movie[movieArray.length()];

        for (int i = 0; i < movieArray.length(); i++) {
            JSONObject movieObject = movieArray.getJSONObject(i);

            Movie movie = new Movie();
            movie.setTitle(movieObject.getString(OM_ORIGINAL_TITLE));
            movie.setPoster(movieObject.getString(OM_POSTER_IMAGE));
            movie.setSynopsis(movieObject.getString(OM_SYNOPSIS));
            movie.setRating(movieObject.getString(OM_USER_RATING));
            movie.setDate(movieObject.getString(OM_RELEASE_DATE));

            parsedMovieData[i] = movie;

        }

        return parsedMovieData;
    }
}
