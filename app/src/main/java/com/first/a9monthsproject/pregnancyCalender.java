package com.first.a9monthsproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;


public class pregnancyCalender extends AppCompatActivity {
    private Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnancy_calender);

        Date firstWeek = new Date(2019, 11, 3);
        Calendar a = Calendar.getInstance();

        a.setTime(firstWeek);

        Calendar b = Calendar.getInstance();

        int weekOne = a.get(Calendar.WEEK_OF_YEAR);
        Toast.makeText(pregnancyCalender.this, "week" + weekOne , Toast.LENGTH_LONG).show();


}
}

