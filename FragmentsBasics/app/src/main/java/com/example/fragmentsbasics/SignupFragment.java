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

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    EditText nameEdt, emailEdt, passEdt;


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

        nameEdt = view.findViewById(R.id.nameEdt);

        Button signupBtn = view.findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSignIn()) {
                    // go to the onboarding route
                    Bundle bundle = new Bundle();
                    bundle.putString("name", getName());

                    Navigation.findNavController(v).navigate(R.id.action_signupFragment_to_onBoardingFragment, bundle);
                }
            }
        });
    }

    private boolean checkSignIn() {
        // TODO: check the login

        return true;
    }

    private String getName() {
        return nameEdt.getText().toString();
    }
}
