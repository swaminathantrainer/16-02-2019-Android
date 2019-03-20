package com.example.fragmentsbasics.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.fragmentsbasics.R;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

public class AuthActivity extends AppCompatActivity {
    NavController navController;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("com.example.fragmentsbasics.prefs", MODE_PRIVATE);

        setContentView(R.layout.auth_activity);

        navController = Navigation.findNavController(this, R.id.auth_nav_host_fragment);
        setupActionBarWithNavController(this, navController);

        if (isSessionAvailable()) {
            openMainActivity();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    private boolean isSessionAvailable() {
        return !sharedPreferences.getString("mobileNumber", "").isEmpty();
    }


    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
