package com.example.fragmentsbasics.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentsbasics.fragments.quiz.QuizFragment;
import com.example.fragmentsbasics.R;
import com.example.fragmentsbasics.model.Quiz;
import com.example.fragmentsbasics.repositories.DataRepository;
import com.example.fragmentsbasics.viewmodels.QuizViewModel;

public class MainActivity extends AppCompatActivity {
    TextView pageNumberText;
    int currentQuiz = 0;
    private Quiz[] quizzes;
    private QuizViewModel quizViewModel;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataRepository = new DataRepository();

        Button nextBtn = findViewById(R.id.nextBtn);
        Button prevBtn = findViewById(R.id.prevBtn);

        pageNumberText = findViewById(R.id.pageNumber);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuiz();
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevQuiz();
            }
        });

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        currentQuiz = quizViewModel.getCurrentQuiz();

        quizViewModel.getQuizData(dataRepository).observe(this, new Observer<Quiz[]>() {
            @Override
            public void onChanged(@Nullable Quiz[] q) {
                quizzes = q;
                updateQuizUI();
            }
        });
    }

    private void nextQuiz() {
        currentQuiz += 1;
        if (currentQuiz > quizCount() - 1) {
            currentQuiz = quizCount() - 1;
        }

        updateQuizUI();
    }

    private void prevQuiz() {
        currentQuiz -= 1;
        if (currentQuiz < 0) {
            currentQuiz = 0;
        }

        updateQuizUI();
    }

    private void updateQuizUI() {
        quizViewModel.setCurrentQuiz(currentQuiz);

        Quiz quiz = quizzes[currentQuiz];

        createPage(quiz);
        updatePageCount();
    }

    private void createPage(Quiz quiz) {
        // 1. Get the Fragment Manager
        // 2. Create a Transaction
        // 3. Commit the Transaction

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Create a Fragment
        QuizFragment quizFragment = QuizFragment.getInstance(quiz);
        // Add to the Transaction
        transaction.replace(R.id.quizContainer, quizFragment, "" + quiz.getId());
        // here

        transaction.commit();
    }

    private void updatePageCount() {
        String pageCountTxt = "" + (currentQuiz + 1) + "/" + quizCount();
        pageNumberText.setText(pageCountTxt);
    }

    private int quizCount() {
        return quizzes.length;
    }
}
