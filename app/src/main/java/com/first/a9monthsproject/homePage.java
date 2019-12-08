package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class homePage extends AppCompatActivity {

    private Button personalAreaButton;
    private Button myTestsButton;
    private Button recommButton;
    private Button pregnancyCalButton;
    private Button qaButton;
    private Button articlesButton;
    private Button logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        personalAreaButton = (Button) findViewById(R.id.personal_area);
        myTestsButton = (Button) findViewById(R.id.testResults);
        recommButton = (Button) findViewById(R.id.recommendations);
        pregnancyCalButton = (Button) findViewById(R.id.calender);
        qaButton = (Button) findViewById(R.id.QA);
        articlesButton = (Button) findViewById(R.id.article);
        logOutButton = (Button) findViewById(R.id.log_out);

        //handle the personalAreaButton
        personalAreaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPersonalAreaPage();
            }
        });

        //handle the myTestsButton
        myTestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentestsPage();
            }
        });

        //handle the recommButton
        recommButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openrecommendationPage();
            }
        });

        //handle the pregnancyCalButton
        pregnancyCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencalenderPage();
            }
        });

        //handle the qaButton
        qaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQAPage();
            }
        });

        //handle the articlesButton
        articlesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openArticlesPage();
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutFun();
            }
        });
    }

    //use fun to move on to personal area page
    public void openPersonalAreaPage() {
        Intent in = new Intent(this, personalArea.class);
        startActivity(in);
    }

    //use fun to move on to tests page
    public void opentestsPage() {
        Intent in = new Intent(this, tests.class);
        startActivity(in);
    }

    //use fun to move on to recommendation page
    public void openrecommendationPage() {
        Intent in = new Intent(this, recommendation.class);
        startActivity(in);
    }

    //use fun to move on to calender page
    public void opencalenderPage() {
        Intent in = new Intent(this, pregnancyCalender.class);
        startActivity(in);
    }

    //use fun to move on to qa page
    public void openQAPage() {
        Intent in = new Intent(this, questionsAnswers.class);
        startActivity(in);
    }

    //use fun to move on to articles page
    public void openArticlesPage() {
        Intent in = new Intent(this, articles.class);
        startActivity(in);
    }

    //use fun to logout
    public void logoutFun() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }


}



