package com.example.rrifafauzikomara.coursethree;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by R Rifa Fauzi Komara on 10/02/2018.
 */

public class ItemObject {

    @SerializedName("results")
    List<Results> results;

    public class Results {
        @SerializedName("poster_path")
        public String poster_path;

        @SerializedName("original_title")
        public String original_title;

        @SerializedName("overview")
        public String overview;

        @SerializedName("release_date")
        public String release_date;

        @SerializedName("backdrop_path")
        public String backdrop_path;

        @SerializedName("vote_average")
        public String vote_average;

        @SerializedName("id")
        public String id;
    }
}
