package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class triedTrimesterTests extends AppCompatActivity {

    private Button returnMainTests;
    private Button returnTestslist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tried_trimester_tests);

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
