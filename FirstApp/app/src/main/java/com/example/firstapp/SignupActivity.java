package com.example.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    EditText emailEdt, passEdt, confirmEdt;
    TextView errorText;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        emailEdt = findViewById(R.id.emailEdt);
        passEdt = findViewById(R.id.passwordEdt);
        confirmEdt = findViewById(R.id.confirmPasswordEdt);
        signUpButton = findViewById(R.id.signUpButton);
        errorText = findViewById(R.id.errorText);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdt.getText().toString();
                String pass = passEdt.getText().toString();
                String confirmPass = confirmEdt.getText().toString();

                if (validateEmail(email) && validatePassword(pass) && validateConfirmPassword(pass, confirmPass)) {
                    resetError();
                    Toast.makeText(SignupActivity.this, "Success!!!", Toast.LENGTH_SHORT).show();
                } else {
                    setError("Provide valid credentials");
                }
            }
        });

        Log.i("Lifecycle", "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("Lifecycle", "onStart()");
    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.i("Lifecycle", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("Lifecycle", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("Lifecycle", "onStop()");
    }

    private boolean validateEmail(String email) {
        return checkRegex("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$", email);
    }

    private boolean validatePassword(String pass) {
        return checkRegex("^[a-zA-Z]\\w{3,14}$", pass);
    }

    private boolean validateConfirmPassword(String actualPass, String confirmPassword) {
        return actualPass.equals(confirmPassword);
    }

    private void setError(String errorMsg) {
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(errorMsg);
    }

    private void resetError() {
        errorText.setVisibility(View.INVISIBLE);
        errorText.setText("");
    }

    private boolean checkRegex(String pattern, String input) {
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);
        // Now create matcher object.
        return r.matcher(input).matches();
    }

}
