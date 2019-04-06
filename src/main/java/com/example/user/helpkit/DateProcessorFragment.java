package com.example.user.helpkit;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateProcessorFragment extends Fragment {
    private TextView resultTextView;
    private EditText editText1, editText2;
    private ImageButton button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date, container, false);

        resultTextView = view.findViewById(R.id.resultTextView);
        editText1 = view.findViewById(R.id.editText);
        editText2 = view.findViewById(R.id.editText2);
        button = view.findViewById(R.id.dateProcessButton);

        editText1.setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        button.setOnClickListener(dateProcessButtonListener);

        return view;
    }

    private View.OnClickListener dateProcessButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GregorianCalendar date1 = DateProcessor.convertStringToCalendar(editText1.getText().toString());
            String editText2String = editText2.getText().toString();
            String text = "";

            if (editText2String.contains(".")) {
                text = Integer.toString(DateProcessor.daysFrom(date1,
                        DateProcessor.convertStringToCalendar(editText2String)));
            } else {
                GregorianCalendar date2 = DateProcessor.when(date1.getTime(),
                        Integer.parseInt(editText2String));

                text = new SimpleDateFormat("dd.MM.yyyy").format(date2.getTime());
            }

            resultTextView.setText(text);
        }
    };

    private static class DateProcessor {
        private DateProcessor() {}

        public static int daysFrom(Calendar calendar) { // returns number of days from today
            return (int) (Math.abs(new GregorianCalendar().getTime().getTime()
                    - calendar.getTime().getTime()) / 86400000);
        }

        public static int daysFrom(Calendar calendar1, Calendar calendar2) {
            long timePassed = Math.abs(calendar2.getTime().getTime() - calendar1.getTime().getTime());

            return (int) (timePassed / 86400000);
        }

        public static GregorianCalendar when(Date date, int days) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, days);

            return calendar;
        }

        public static GregorianCalendar convertStringToCalendar(String s) {
            if (s.length() != 10) {
                throw new IllegalArgumentException();
            } else {
                int day = Integer.parseInt(s.substring(0, 2));
                int month = Integer.parseInt(s.substring(3, 5))-1;
                int year = Integer.parseInt(s.substring(6, 10));

                return new GregorianCalendar(year, month, day);
            }
        }
    }
}
