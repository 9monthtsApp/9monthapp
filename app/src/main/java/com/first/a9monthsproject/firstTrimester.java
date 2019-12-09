package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class firstTrimester extends AppCompatActivity {

    private Button returnMainTests;
    private Button returnTestslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_trimester);

        returnMainTests = (Button) findViewById(R.id.returnMainArticles);
        returnTestslist =(Button) findViewById(R.id.testList);

        returnTestslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfoOfTests();
            }
        });

        returnMainTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opemTestsPage();
            }
        });
    }


    //open the main tests page
    private void opemTestsPage() {
        Intent in = new Intent(this, tests.class);
        startActivity(in);
    }

    //open the list of all trimestrs
    private void openInfoOfTests() {
        Intent in = new Intent(this, test_InfoPage.class);
        startActivity(in);
    }
}
