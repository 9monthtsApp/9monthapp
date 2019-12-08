package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class questionsAnswers extends AppCompatActivity {

    private Button nutritionButton;
    private Button VaccomationButton;
    private Button breastButton;
    private Button rightsButton;
    private Button birthButton;
    private Button babyAndAfterButton;
    private ImageButton returnButton;
    private ImageButton logoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_answers);

        nutritionButton =(Button) findViewById(R.id.nutrition);
        VaccomationButton=(Button) findViewById(R.id.VaccinesAndTests);
        rightsButton = (Button) findViewById(R.id.Pregnantrights);
        birthButton =(Button) findViewById(R.id.birth);
        babyAndAfterButton =(Button) findViewById(R.id.afterBirth);
        breastButton = (Button) findViewById(R.id.breastFeeding);
        returnButton = (ImageButton) findViewById(R.id.go_back);
        logoButton =(ImageButton) findViewById(R.id.Image_Logo);


        //handle nutrition button -> move to nutrition and sport QA
        nutritionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNutriationQA();
            }
        });

        VaccomationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVaccineAndTestQAPage();
            }
        });

        //handle breast button -> move to breastfeeding QA
        breastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBreastfeddingQA();
            }
        });

        rightsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPregnantRightsPage();
            }
        });

        //handle return button
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
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


    //use fun to move on to home page
    public void openHomePage() {
        Intent in = new Intent(this, homePage.class);
        startActivity(in);
    }

    //use fun to move on to breastfeeding QA page
    public void openNutriationQA() {
        Intent in = new Intent(this, notritonAndSportQA.class);
        startActivity(in);
    }

    //use fun to move to nutrition and sport QA page
    public void openBreastfeddingQA() {
        Intent in = new Intent(this, breastfeedingQA.class);
        startActivity(in);
    }

    //use fun to move to pregnant_rights QA page
    public void openPregnantRightsPage() {
        Intent in = new Intent(this, pregnant_rights_QA.class);
        startActivity(in);
    }

    //use fun to move to vaccines and tests QA page
    public void openVaccineAndTestQAPage() {
        Intent in = new Intent(this, vaccineAndTestingQA.class);
        startActivity(in);
    }


}


