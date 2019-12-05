package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class articles extends AppCompatActivity {

    private Button nutritionButton;
    private Button sportButton;
    private ImageButton returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        nutritionButton= (Button)findViewById(R.id.nutrition);
        sportButton= (Button)findViewById(R.id.sport);
        returnButton = (ImageButton) findViewById(R.id.go_back);

        // handle the nutrition Button - move to nutrition articles
        nutritionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNutritionDietArticles();
            }
        });

        // handle the sport Button - move to sport articles
        sportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSportArticles();
            }
        });

        // handle the return Button - move to home page
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });
    }


    //use fun to move on to notrition articles page
    public void openNutritionDietArticles() {
        Intent in = new Intent(this, nutritonAndDietArticals.class);
        startActivity(in);
    }

    //use fun to move on to sport articles page
    public void openSportArticles() {
        Intent in = new Intent(this, sportsArticles.class);
        startActivity(in);
    }

    //use fun to move on to sport articles page
    public void openHomePage() {
        Intent in = new Intent(this, homePage.class);
        startActivity(in);
    }
}
