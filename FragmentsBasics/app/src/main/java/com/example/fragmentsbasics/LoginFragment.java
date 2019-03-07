package com.example.fragmentsbasics;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText emailEdt = view.findViewById(R.id.emailEdt);
        EditText passEdt = view.findViewById(R.id.passwordEdt);

        Button login = view.findViewById(R.id.loginBtn);

        TextView signupText = view.findViewById(R.id.signupText);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: do checks

                if (checkLogin()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("name", getName());

                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_onBoardingFragment, bundle);
                }

            }
        });

        signupText.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_signupFragment, null));
    }

    private boolean checkLogin() {
        // TODO: check the login

        return true;
    }

    private String getName() {
        return "Swami";
    }
}
