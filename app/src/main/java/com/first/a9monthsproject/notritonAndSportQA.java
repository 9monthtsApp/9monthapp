package com.first.a9monthsproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class notritonAndSportQA extends AppCompatActivity {

    private Button returnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notriton_and_sport_q);

        returnButton=(Button)findViewById(R.id.goback);


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQApage();
            }
        });

        //move to home page when we push on the logo

    }

    //use fun to move on to main QA page
    public void openQApage() {
        Intent in = new Intent(this, questionsAnswers.class);
        startActivity(in);
    }

    //use fun to move on to Homepage page
    public void openHomePage() {
        Intent in = new Intent(this, homePage.class);
        startActivity(in);
    }

}
