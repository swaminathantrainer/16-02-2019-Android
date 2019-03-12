package com.example.fragmentsbasics.viewmodels;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.fragmentsbasics.model.Quiz;
import com.example.fragmentsbasics.repositories.DataRepository;

public class QuizViewModel extends ViewModel {
    private int currentQuiz = 0;
    private int score = 0;
    private MediatorLiveData<Quiz[]> quizzesLiveData = new MediatorLiveData<>();

    public QuizViewModel() {
        DataRepository dataRepository = new DataRepository();

        quizzesLiveData.addSource(dataRepository.getListOfQuizzes(), new Observer<Quiz[]>() {
            @Override
            public void onChanged(@Nullable Quiz[] q) {
                quizzesLiveData.setValue(q);
            }
        });
    }

    public int getCurrentQuiz() {
        return currentQuiz;
    }

    public void setCurrentQuiz(int currentQuiz) {
        this.currentQuiz = currentQuiz;
    }

    public MutableLiveData<Quiz[]> getQuizData() {
        return quizzesLiveData;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
