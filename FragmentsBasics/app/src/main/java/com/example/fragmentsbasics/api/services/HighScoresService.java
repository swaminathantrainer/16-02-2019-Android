package com.example.fragmentsbasics.api.services;

import com.example.fragmentsbasics.model.HighScore;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface HighScoresService {
    @GET("/highScores/{number}.json")
    Call<HighScore> getHighScoreForNumber(@Path("number") String userNumber);

    @PATCH("/highScores/{number}.json")
    Call<HighScore> updateHighScoreForNumber(@Path("number") String userNumber, @Body HighScore highScore);
}
