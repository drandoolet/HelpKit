package com.example.user.helpkit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;

public class RandomizerFragment extends Fragment {
    private int boundFrom, boundTo;
    private SecureRandom random;
    private Button randomizeButton;
    private ImageButton upImgButton, downImgButton;
    private EditText boundFromEditText, boundToEditText;
    private TextView resultTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_randomizer, container, false);

        boundFromEditText = view.findViewById(R.id.boundFromEditText);
        boundToEditText = view.findViewById(R.id.boundToEditText);

        randomizeButton = view.findViewById(R.id.randomizeButton);
        randomizeButton.setOnClickListener(buttonListener);
        upImgButton = view.findViewById(R.id.upRandomImgButton);
        downImgButton = view.findViewById(R.id.downRandomImgButton);
        upImgButton.setOnClickListener(arrowButtonListener);
        downImgButton.setOnClickListener(arrowButtonListener);

        resultTextView = view.findViewById(R.id.resultTextView);

        random = new SecureRandom();

        boundTo = 5;
        boundFrom = 1;
        boundToEditText.setText(Integer.toString(boundTo), TextView.BufferType.EDITABLE);
        boundFromEditText.setText(Integer.toString(boundFrom), TextView.BufferType.EDITABLE);

        boundToEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("") && !charSequence.toString().equals("-"))
                    boundTo = Integer.parseInt(charSequence.toString());
                else boundTo = 0;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        boundFromEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("") && !charSequence.toString().equals("-")) {
                    boundFrom = Integer.parseInt(charSequence.toString());
                }
                else {
                    boundFrom = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

    private View.OnClickListener arrowButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.upRandomImgButton: boundTo++; break;
                case R.id.downRandomImgButton: boundTo--; break;
            }
            boundToEditText.setText(Integer.toString(boundTo), TextView.BufferType.EDITABLE);
        }
    };
}
