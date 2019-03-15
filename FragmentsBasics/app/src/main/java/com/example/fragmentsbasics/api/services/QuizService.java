package com.example.fragmentsbasics.api.services;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuizService {
    @GET("/quiz.json")
    public Call<JsonElement> getQuizList();
}
