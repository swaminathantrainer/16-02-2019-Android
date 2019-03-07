package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    EditText emaiEdt, passwordEdt;
    TextView errorText;
    boolean errorState;
    String errorMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        TextView signUpTextView = findViewById(R.id.signupText);

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpPage();
            }
        });

        errorText = findViewById(R.id.errorText);

        Button loginButton = findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetError();
                 checkFields();
            }
        });

        emaiEdt = findViewById(R.id.emailEdt);
        passwordEdt = findViewById(R.id.passwordEdt);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        errorState = savedInstanceState.getBoolean("errorState");
        errorMessage = savedInstanceState.getString("errorMessage");

        if (errorState) {
            setError(errorMessage);
        } else {
            resetError();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("errorState", errorState);
        outState.putString("errorMessage", errorMessage);
    }

    private boolean checkFields() {
        String email = emaiEdt.getText().toString();
        String pass = passwordEdt.getText().toString();

        if (email.isEmpty() || pass.isEmpty()) {
            setError("Please enter valid data");
            return false;
        }

        return true;
    }

    private void setError(String errorMsg) {
        errorMessage = errorMsg;
        errorState = true;
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(errorMsg);
    }

    private void resetError() {
        errorText.setVisibility(View.INVISIBLE);
        errorText.setText("");
        errorMessage = "";
        errorState = false;
    }

    private void openSignUpPage() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}
