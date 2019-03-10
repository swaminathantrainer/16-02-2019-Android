package com.example.fragmentsbasics.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.fragmentsbasics.model.Quiz;

public class DataRepository {
    private final int DUMMY_QUIZ_SIZE = 6;

    private final String DUMMY_QUIZ_QUESTIONS[] = new String[]{
            "The brain of any computer system is",
            "The binary system uses powers of",
            "A computer program that converts assembly language to machine language is",
            "What is the maximum address range supported in a 32-bit operating system",
            "1 Byte equals how many Bits?",
            "This is a sample question"
    };

    private final String[][] DUMMY_OPTIONS = new String[][]{
            {"CPU", "ALU", "REGISTERS", "GPU"},
            {"4", "8", "2", "16"},
            {"Compiler", "Transpiler", "Interpreter", "Assembler"},
            {"4294967296", "65536", "2147483648"},
            {"1", "4", "6", "8"},
            {"AA", "BB"}
    };

    private final int[] DUMMY_ANSWERS = new int[]{0, 2, 3, 0, 3, 0};

    public Quiz[] getListOfQuizzes() {
        return generateDummyQuizData();
    }

    private Quiz[] generateDummyQuizData() {
        Quiz[] quizzes = new Quiz[DUMMY_QUIZ_SIZE];

        for (int i = 0; i < DUMMY_QUIZ_SIZE; i++) {
            Quiz quiz = new Quiz(
                    i,
                    DUMMY_QUIZ_QUESTIONS[i],
                    DUMMY_OPTIONS[i],
                    DUMMY_OPTIONS[i][DUMMY_ANSWERS[i]]
            );

            // populate quizzes array
            quizzes[i] = quiz;
        }

        return quizzes;
    }
}
