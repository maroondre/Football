package com.devdre.football.Connections;

import com.devdre.football.ListModels.ListPicture;
import com.devdre.football.ListModels.ListScore;
import com.devdre.football.ListModels.ListSoccer;
import com.devdre.football.ListModels.ListVideo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/";
    String BASE_URLPOD = "https://www.scorebat.com/video-api/";
    String BASE_URlSCORE = "http://livescore-api.com/api-client/scores/";
    //String where = "strAlternate LIKE '"+ +"' ";

    @GET("search_all_teams.php?l=English%20Premier%20League")
    Call<ListSoccer> getSoccer();

    @GET("searchteams.php?")
    Call<ListPicture> getPics(@Query("t") String name);

    @GET("v1")
    Call<List<ListVideo>> getVideo();

    @GET("live.json?")
    Call<ListScore> getScore(@Query("key") String key, @Query("secret") String secret);
}
