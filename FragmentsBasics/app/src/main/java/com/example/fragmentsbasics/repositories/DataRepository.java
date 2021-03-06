package com.example.fragmentsbasics.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.fragmentsbasics.api.APIs;
import com.example.fragmentsbasics.model.HighScore;
import com.example.fragmentsbasics.model.Quiz;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;
import java.util.Set;

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

    private MutableLiveData<Quiz[]> quizzesMutableLiveData;
    private MutableLiveData<HighScore> highScoreMutableLiveData;

    private APIs apIs;

    public DataRepository() {
        quizzesMutableLiveData = new MutableLiveData<>();
        highScoreMutableLiveData = new MutableLiveData<>();

        apIs = new APIs();
    }

    public MutableLiveData<HighScore> updateHighScore(String number, int score) {
        apIs.updateHighScore(number, score, new APIs.APIResponseInterface() {
            @Override
            public void onResponse(Object obj) {
                highScoreMutableLiveData.postValue((HighScore) obj);
            }

            @Override
            public void onError(String msg) {

            }
        });

        return highScoreMutableLiveData;
    }

    public MutableLiveData<HighScore> getHighScore(String number) {
        apIs.getHighScore(number, new APIs.APIResponseInterface() {
            @Override
            public void onResponse(Object obj) {
                highScoreMutableLiveData.postValue((HighScore) obj);
            }

            @Override
            public void onError(String msg) {

            }
        });

        return highScoreMutableLiveData;
    }

    public MutableLiveData<Quiz[]> getListOfQuizzes() {
        apIs.getAllQuizzes(new APIs.APIResponseInterface() {
            @Override
            public void onResponse(Object object) {
                JsonElement jsonElement = (JsonElement) object;
                JsonObject root = jsonElement.getAsJsonObject();

                Quiz[] quizzes = new Quiz[root.entrySet().size()];

                int index = 0;

                for (Map.Entry<String, JsonElement> element : root.entrySet()) {
                    String quizId = element.getKey();

                    JsonElement value = element.getValue();
                    JsonObject valueJsonObject = value.getAsJsonObject();

                    String questionText = valueJsonObject.get("question").getAsString();
                    JsonArray optionsJson = valueJsonObject.get("options").getAsJsonArray();

                    // populate the options array
                    String[] options = new String[optionsJson.size()];

                    for (int i = 0; i < options.length; i++) {
                        JsonElement optionElement = optionsJson.get(i);
                        String option = optionElement.getAsString();
                        options[i] = option;
                    }

                    int correctAnswerIndex = valueJsonObject.get("correctAnswer").getAsInt();

                    String correctAnswer = options[correctAnswerIndex];

                    Quiz quiz = new Quiz(quizId, questionText, options, correctAnswer);
                    quizzes[index] = quiz;
                    index++;
                }

                quizzesMutableLiveData.postValue(quizzes);

            }

            @Override
            public void onError(String msg) {

            }
        });

        return quizzesMutableLiveData;
    }

    private Quiz[] generateDummyQuizData() {
        Quiz[] quizzes = new Quiz[DUMMY_QUIZ_SIZE];

        for (int i = 0; i < DUMMY_QUIZ_SIZE; i++) {
            Quiz quiz = new Quiz(
                    "" + i,
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
