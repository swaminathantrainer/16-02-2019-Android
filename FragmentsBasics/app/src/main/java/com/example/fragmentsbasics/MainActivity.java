package com.example.fragmentsbasics;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String[] QUIZ_TITLE = new String[]{"QUIZ PAGE 1", "QUIZ PAGE 2", "QUIZ PAGE 3", "QUIZ PAGE 4", "QUIZ PAGE 5", "QUIZ PAGE 6"};
    private final int QUIZ_COUNT = QUIZ_TITLE.length;
    TextView pageNumberText;
    int currentPage = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        nextQuiz();
    }

    private void nextQuiz() {
        currentPage += 1;
        if (currentPage > QUIZ_COUNT - 1) {
            currentPage = QUIZ_COUNT - 1;
        }

        String title = QUIZ_TITLE[currentPage];

        createPage(title);
        updatePageCount();
    }

    private void prevQuiz() {
        currentPage -= 1;
        if (currentPage < 0) {
            currentPage = 0;
        }

        String title = QUIZ_TITLE[currentPage];

        createPage(title);
        updatePageCount();
    }

    private void createPage(String title) {
        // 1. Get the Fragment Manager
        // 2. Create a Transaction
        // 3. Commit the Transaction

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Create a Fragment
        QuizFragment quizFragment = QuizFragment.getInstance(title);
        // Add to the Transaction
        transaction.replace(R.id.quizContainer, quizFragment, title);
        // here

        transaction.commit();
    }

    private void updatePageCount() {
        String pageCountTxt = "" + (currentPage + 1) + "/" + QUIZ_COUNT;
        pageNumberText.setText(pageCountTxt);
    }
}
