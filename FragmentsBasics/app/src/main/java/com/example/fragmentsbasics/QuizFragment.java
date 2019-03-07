package com.example.fragmentsbasics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuizFragment extends Fragment {
    private TextView titleTxt;

    public static QuizFragment getInstance(String title) {
        QuizFragment quizFragment = new QuizFragment();

        Bundle bundle = new Bundle();

        bundle.putString("title", title);

        quizFragment.setArguments(bundle);

        return quizFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quiz_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title = getArguments().getString("title");

        titleTxt = view.findViewById(R.id.titleTxt);

        setTitle(title);
    }

    public void setTitle(String title) {
        titleTxt.setText(title);
    }
}
