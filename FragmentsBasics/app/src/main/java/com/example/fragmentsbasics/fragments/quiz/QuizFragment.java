package com.example.fragmentsbasics.fragments.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.fragmentsbasics.R;
import com.example.fragmentsbasics.model.Quiz;

public class QuizFragment extends Fragment {
    private TextView titleTxt;
    private RecyclerView recyclerView;
    private OptionsAdapter optionsAdapter;
    private Quiz quiz;
    private QuizRowData quizRowData;

    public static QuizFragment getInstance(Quiz quiz) {
        QuizFragment quizFragment = new QuizFragment();

        Bundle bundle = new Bundle();

        bundle.putParcelable("quiz", quiz);

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

        quiz = getArguments().getParcelable("quiz");

        titleTxt = view.findViewById(R.id.titleTxt);
        recyclerView = view.findViewById(R.id.optionsRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        quizRowData = new QuizRowData(quiz);

        optionsAdapter = new OptionsAdapter();

        recyclerView.setAdapter(optionsAdapter);

        setTitle(quiz.getQuestionText());
    }

    public boolean isQuestionProperlyAnswered() {
        String actualAnswer = quiz.getCorrectAnswer();
        String givenAnswer = quizRowData.getSelectedOption();

        return actualAnswer.equals(givenAnswer);
    }

    public void setTitle(String title) {
        titleTxt.setText(title);
    }

    private class OptionsAdapter extends RecyclerView.Adapter<OptionViewHolder> {
        @NonNull
        @Override
        public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View row = LayoutInflater.from(getContext()).inflate(R.layout.option_row, viewGroup, false);
            OptionViewHolder optionViewHolder = new OptionViewHolder(row);
            return optionViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull OptionViewHolder optionViewHolder, int i) {
            final String option = quizRowData.getQuiz().getOptions()[i];
            optionViewHolder.setOptionText(option);

            optionViewHolder.getRadioButton().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        quizRowData.setSelectedOption(option);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return quizRowData.getQuiz().getOptions().length;
        }
    }

    private class OptionViewHolder extends RecyclerView.ViewHolder {
        private RadioButton radioButton;
        private TextView optionsText;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);

            radioButton = itemView.findViewById(R.id.optionRadioButton);
            optionsText = itemView.findViewById(R.id.optionsText);
        }

        public void setOptionText(String option) {
            optionsText.setText(option);
        }

        public RadioButton getRadioButton() {
            return radioButton;
        }
    }

    private class QuizRowData {
        private Quiz quiz;
        String selectedOption;

        public QuizRowData(Quiz quiz, String selectedOption) {
            this.quiz = quiz;
            this.selectedOption = selectedOption;
        }

        public QuizRowData(Quiz quiz) {
            this.quiz = quiz;
            this.selectedOption = null;
        }

        public Quiz getQuiz() {
            return quiz;
        }

        public void setQuiz(Quiz quiz) {
            this.quiz = quiz;
        }

        public String getSelectedOption() {
            return selectedOption;
        }

        public void setSelectedOption(String selectedOption) {
            this.selectedOption = selectedOption;
        }
    }
}
