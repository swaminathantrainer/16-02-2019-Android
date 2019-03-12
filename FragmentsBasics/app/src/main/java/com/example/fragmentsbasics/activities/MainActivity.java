package com.example.fragmentsbasics.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentsbasics.fragments.quiz.QuizFragment;
import com.example.fragmentsbasics.R;
import com.example.fragmentsbasics.model.Quiz;
import com.example.fragmentsbasics.repositories.DataRepository;
import com.example.fragmentsbasics.viewmodels.QuizViewModel;

public class MainActivity extends AppCompatActivity {
    private final int CORRECT_ANSWERS_POINTS = 10;
    private final int WRONG_ANSWER_POINTS = 5;

    TextView pageNumberText, scoreTxt, highScoreTxt;
    int currentQuiz = 0;
    private Quiz[] quizzes;
    private QuizViewModel quizViewModel;
    private DataRepository dataRepository;
    private int score;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.example.fragmentsbasics.prefs", MODE_PRIVATE);

        dataRepository = new DataRepository();

        Button nextBtn = findViewById(R.id.nextBtn);
        Button prevBtn = findViewById(R.id.prevBtn);
        prevBtn.setVisibility(View.INVISIBLE);

        pageNumberText = findViewById(R.id.pageNumber);
        scoreTxt = findViewById(R.id.scoreTxt);

        highScoreTxt = findViewById(R.id.highScore);
        highScoreTxt.setText("HIGH SCORE: " + getHighScore());

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

        score = quizViewModel.getScore();
        updateScore(score);

        quizViewModel.getQuizData().observe(this, new Observer<Quiz[]>() {
            @Override
            public void onChanged(@Nullable Quiz[] q) {
                quizzes = q;
                updateQuizUI();
            }
        });
    }

    private void nextQuiz() {
        currentQuiz += 1;

        checkAnswer();

        if (currentQuiz > quizCount() - 1) {
            // quiz over
            Toast.makeText(this, "Quiz is over", Toast.LENGTH_SHORT).show();
            saveHighScore(score);
            return;
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
        transaction.replace(R.id.quizContainer, quizFragment, "QUIZ_FRAGMENT");
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

    private QuizFragment getCurrentFragment() {
        String fragmentTag = "QUIZ_FRAGMENT";
        QuizFragment quizFragment = (QuizFragment) getSupportFragmentManager().findFragmentByTag(fragmentTag);
        return quizFragment;
    }

    private void checkAnswer() {
        QuizFragment quizFragment = getCurrentFragment();

        if (quizFragment == null) {
            return;
        }

        boolean result = quizFragment.isQuestionProperlyAnswered();

        if (result) {
            // increment score
            score += CORRECT_ANSWERS_POINTS;
        } else {
            // decrement score
            score -= WRONG_ANSWER_POINTS;
        }

        quizViewModel.setScore(score);
        updateScore(score);
    }

    private void updateScore(int score) {
        scoreTxt.setText("" + score);
    }

    private void saveHighScore(int score) {
        int existingScore = getHighScore();
        if (score > existingScore) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.apply();
        }
    }

    private int getHighScore() {
        int score = sharedPreferences.getInt("HIGH_SCORE", 0);
        return score;
    }

}
