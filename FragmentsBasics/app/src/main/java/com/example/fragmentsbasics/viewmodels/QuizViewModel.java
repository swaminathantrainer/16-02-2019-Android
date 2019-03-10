package com.example.fragmentsbasics.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.fragmentsbasics.model.Quiz;
import com.example.fragmentsbasics.repositories.DataRepository;

public class QuizViewModel extends ViewModel {
    private int currentQuiz = 0;
    private MutableLiveData<Quiz[]> quizzesLiveData = new MutableLiveData<>();
    private Quiz[] quizzes;

    public int getCurrentQuiz() {
        return currentQuiz;
    }

    public void setCurrentQuiz(int currentQuiz) {
        this.currentQuiz = currentQuiz;
    }

    public MutableLiveData<Quiz[]> getQuizData(DataRepository dataRepository) {
        if (quizzes == null) {
            quizzes = dataRepository.getListOfQuizzes();
        }

        quizzesLiveData.setValue(quizzes);

        return quizzesLiveData;
    }
}
