package com.example.fragmentsbasics.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HighScore {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("score")
    @Expose
    private Integer score;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
