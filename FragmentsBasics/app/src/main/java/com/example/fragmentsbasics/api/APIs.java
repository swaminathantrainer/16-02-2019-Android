package com.example.fragmentsbasics.api;

import android.util.Log;

import com.example.fragmentsbasics.api.services.QuizService;
import com.google.gson.JsonElement;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIs {
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    private QuizService quizService;

    public APIs() {
        okHttpClient = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://sample-quiz-application.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        quizService = retrofit.create(QuizService.class);
    }

    public void getAllQuizzes() {
//        try {
//            Response<JsonElement> response = quizService.getQuizList().execute();
//            Log.i("NETWORKING_LEARNING", response.body().toString());
//        } catch (IOException e) {
//            Log.e("NETWORKING_LEARNING", e.getLocalizedMessage());
//        }

        quizService.getQuizList().enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    Log.i("NETWORKING_LEARNING", response.body().toString());
                } else {
                    Log.e("NETWORKING_LEARNING", "Error occurred");
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("NETWORKING_LEARNING", t.getLocalizedMessage());
            }
        });
    }
}
