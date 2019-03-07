package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<User> userArrayList = new ArrayList<>();
    private ContactRecyclerViewAdapter contactRecyclerViewAdapter;
    private Random randomNumber = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateDummyData();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        contactRecyclerViewAdapter = new ContactRecyclerViewAdapter();

        recyclerView.setAdapter(contactRecyclerViewAdapter);
    }

    private class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRowViewHolder> {

        @NonNull
        @Override
        public ContactRowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
            ContactRowViewHolder rowViewHolder = new ContactRowViewHolder(row);
            return rowViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ContactRowViewHolder contactRowViewHolder, int position) {
            User user = userArrayList.get(position);
            contactRowViewHolder.populateRow(user.getName(), user.getPhoneNumber());
        }

        @Override
        public int getItemCount() {
            return userArrayList.size();
        }
    }

    private class ContactRowViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTxt, numberTxt;

        public ContactRowViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.nameText);
            numberTxt = itemView.findViewById(R.id.mobileNumberText);
        }

        public void populateRow(String name, String phoneNumber) {
            nameTxt.setText(name);
            numberTxt.setText(phoneNumber);
        }
    }

    private void generateDummyData() {
        for (int i = 0; i < 10000; i++) {
            User user = new User(generateRandomName(), generateRandomPhoneNumber());
            userArrayList.add(user);
        }
    }

    private String generateRandomPhoneNumber() {
        int[] numbers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        String phoneNumber = "";

        for (int i = 0; i < 10; i++) {
            int index = randomNumber.nextInt(10);
            int number = numbers[index];
            phoneNumber += number;
        }

        return phoneNumber;
    }

    private String generateRandomName() {
        char[] letters = new char[]{'A', 'B', 'C', 'D', 'E'};
        String name = "";

        for (int i = 0; i < (5 + randomNumber.nextInt(15)); i++) {
            int index = randomNumber.nextInt(5);
            char l = letters[index];
            name += l;
        }

        return name;
    }
}
