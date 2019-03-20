package com.example.fragmentsbasics.api;

import android.util.Log;

import com.example.fragmentsbasics.api.services.HighScoresService;
import com.example.fragmentsbasics.api.services.QuizService;
import com.example.fragmentsbasics.model.HighScore;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIs {

    // interface
    public interface APIResponseInterface {
        void onResponse(Object jsonElement);

        void onError(String msg);
    }

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    private QuizService quizService;
    private HighScoresService highScoresService;

    public APIs() {
        okHttpClient = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://sample-quiz-application.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        quizService = retrofit.create(QuizService.class);
        highScoresService = retrofit.create(HighScoresService.class);
    }

    public void updateHighScore(String number, int score, final APIResponseInterface apiResponseInterface) {
        HighScore highScore = new HighScore();
        highScore.setDate(new Date().toString());
        highScore.setScore(score);

        highScoresService.updateHighScoreForNumber(number, highScore).enqueue(new Callback<HighScore>() {
            @Override
            public void onResponse(Call<HighScore> call, Response<HighScore> response) {
                if (response.isSuccessful()) {
                    apiResponseInterface.onResponse(response.body());
                } else {
                    apiResponseInterface.onError("Error occurred");
                }
            }

            @Override
            public void onFailure(Call<HighScore> call, Throwable t) {
                apiResponseInterface.onError(t.getLocalizedMessage());
            }
        });
    }

    public void getHighScore(String number, final APIResponseInterface apiResponseInterface) {
        highScoresService.getHighScoreForNumber(number).enqueue(new Callback<HighScore>() {
            @Override
            public void onResponse(Call<HighScore> call, Response<HighScore> response) {
                if (response.isSuccessful()) {
                    apiResponseInterface.onResponse(response.body());
                } else {
                    apiResponseInterface.onError("Error occurred");
                }
            }

            @Override
            public void onFailure(Call<HighScore> call, Throwable t) {
                apiResponseInterface.onError(t.getLocalizedMessage());
            }
        });
    }

    public void getAllQuizzes(final APIResponseInterface apiResponseInterface) {
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

                    // invoke
                    apiResponseInterface.onResponse(response.body());

                } else {
                    Log.e("NETWORKING_LEARNING", "Error occurred");
                    apiResponseInterface.onError("Error occurred");
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("NETWORKING_LEARNING", t.getLocalizedMessage());
                apiResponseInterface.onError(t.getLocalizedMessage());
            }
        });
    }
}
