package com.example.user.helpkit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class MainFragment extends Fragment {
    private ImageButton fragment1Button, fragment2Button;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        fragment1Button = view.findViewById(R.id.fragment1ImageButton);
        fragment2Button = view.findViewById(R.id.fragment2ImageButton);

        ImageButton[] buttons = {fragment1Button, fragment2Button};
        for (ImageButton button : buttons) button.setOnClickListener(buttonListener);

        return view;
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Fragment fragment = null;
            switch (view.getId()) {
                case R.id.fragment1ImageButton: fragment = new RandomizerFragment(); break;
                case R.id.fragment2ImageButton: fragment = new ClickerFragment(); break;
            }
            if (fragment != null) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment, "initial"); //TODO add tags
                transaction.commit();
            }
        }
    };
}
