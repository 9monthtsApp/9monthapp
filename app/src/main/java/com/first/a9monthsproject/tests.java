package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class tests extends AppCompatActivity {

    private Button testLogButton;
    private Button infoBuuton;
    private ImageButton logoButton;
    private Button testResultsBuuton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        testLogButton =(Button) findViewById(R.id.Testlog);
        infoBuuton =(Button) findViewById(R.id.Information);
        testResultsBuuton =(Button) findViewById(R.id.Testsresults);
        logoButton = (ImageButton) findViewById(R.id.Image_Logo);

        infoBuuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfoTestsPage();
            }
        });

        //move to home page when we push on the logo
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });

    }

    //use fun to move on to information about tests page
    public void openInfoTestsPage() {
        Intent in = new Intent(this, test_InfoPage.class);
        startActivity(in);
    }

    public void openHomePage() {
        Intent in = new Intent(this, homePage.class);
        startActivity(in);
    }
}
