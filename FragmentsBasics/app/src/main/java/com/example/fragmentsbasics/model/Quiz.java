package com.example.fragmentsbasics.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Quiz implements Parcelable {
    private int id;
    private String questionText;
    private String[] options;
    private String correctAnswer;

    public Quiz(int id, String questionText, String[] options, String correctAnswer) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    protected Quiz(Parcel in) {
        id = in.readInt();
        questionText = in.readString();
        options = in.createStringArray();
        correctAnswer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(questionText);
        dest.writeStringArray(options);
        dest.writeString(correctAnswer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
