package com.example.user.helpkit;

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
import android.widget.Toast;

import java.security.SecureRandom;

public class RandomizerFragment extends Fragment {
    private int boundFrom, boundTo;
    private SecureRandom random;
    private Button randomizeButton;
    private EditText boundFromEditText, boundToEditText;
    private TextView resultTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_randomizer, container, false);

        boundFromEditText = view.findViewById(R.id.boundFromEditText);
        boundToEditText = view.findViewById(R.id.boundToEditText);

        randomizeButton = view.findViewById(R.id.randomizeButton);
        randomizeButton.setOnClickListener(buttonListener);

        resultTextView = view.findViewById(R.id.resultTextView);

        random = new SecureRandom();

        return view;
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (boundFromEditText.getText().toString().equals("")) {
                if (boundToEditText.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "enter at least the upper bound", Toast.LENGTH_SHORT).show();
                } else {
                    boundTo = Integer.parseInt(boundToEditText.getText().toString());
                    resultTextView.setText(Integer.toString(1 + random.nextInt(boundTo)));
                }
            } else {
                boundFrom = Integer.parseInt(boundFromEditText.getText().toString());
                boundTo = Integer.parseInt(boundToEditText.getText().toString());

                if ((boundTo - boundFrom)<0) {
                    Toast.makeText(getContext(), "illegal bounds", Toast.LENGTH_SHORT).show();
                } else resultTextView.setText(Integer.toString(boundFrom + random.nextInt(boundTo - boundFrom+1)));
            }
        }
    };
}
