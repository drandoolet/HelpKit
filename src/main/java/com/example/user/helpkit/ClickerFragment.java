package com.example.user.helpkit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ClickerFragment extends Fragment {
    private int currentCount;
    private TextView currentCountTextView, save1TV, save2TV, save3TV, save4TV;
    private Button clickButton;
    private ImageButton save1Button, save2Button, save3Button, save4Button;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clicker, container, false);

        currentCountTextView = view.findViewById(R.id.mainCounterTextView);
        save1TV = view.findViewById(R.id.save1TextView);
        save2TV = view.findViewById(R.id.save2TextView);
        save3TV = view.findViewById(R.id.save3TextView);
        save4TV = view.findViewById(R.id.save4TextView);

        clickButton = view.findViewById(R.id.clickerButton);
        save1Button = view.findViewById(R.id.save1Button);
        save2Button = view.findViewById(R.id.save2Button);
        save3Button = view.findViewById(R.id.save3Button);
        save4Button = view.findViewById(R.id.save4Button);

        clickButton.setOnClickListener(clickerListener);

        ImageButton[] buttons = {save1Button, save2Button, save3Button, save4Button};
        for (ImageButton button : buttons) button.setOnClickListener(saveListener);

        currentCount = 0;

        return view;
    }

    private View.OnClickListener clickerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            currentCount++;
            currentCountTextView.setText(Integer.toString(currentCount));
        }
    };

    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.save1Button: save1TV.setText(Integer.toString(currentCount)); break;
                case R.id.save2Button: save2TV.setText(Integer.toString(currentCount)); break;
                case R.id.save3Button: save3TV.setText(Integer.toString(currentCount)); break;
                case R.id.save4Button: save4TV.setText(Integer.toString(currentCount)); break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
