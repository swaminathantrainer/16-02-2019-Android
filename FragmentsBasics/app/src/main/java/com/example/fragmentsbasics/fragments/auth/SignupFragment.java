package com.example.fragmentsbasics.fragments.auth;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fragmentsbasics.R;

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    EditText nameEdt, emailEdt, passEdt, mobileNumberEdt;
    private SharedPreferences sharedPreferences;


    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("com.example.fragmentsbasics.prefs", Context.MODE_PRIVATE);

        nameEdt = view.findViewById(R.id.nameEdt);
        mobileNumberEdt = view.findViewById(R.id.mobileNumberEdt);

        Button signupBtn = view.findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSignIn()) {
                    // go to the onboarding route
                    Bundle bundle = new Bundle();
                    bundle.putString("name", getName());
                    bundle.putString("number", getNumber());

                    saveNumber(getNumber());

                    Navigation.findNavController(v).navigate(R.id.action_signupFragment_to_onBoardingFragment, bundle);
                }
            }
        });
    }

    private boolean checkSignIn() {
        // TODO: check the login

        return !getNumber().isEmpty();
    }

    private String getName() {
        return nameEdt.getText().toString();
    }

    private String getNumber() {
        return mobileNumberEdt.getText().toString();
    }

    private void saveNumber(String number) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mobileNumber", number);
        editor.apply();
    }
}
