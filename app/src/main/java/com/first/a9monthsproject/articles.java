package com.first.a9monthsproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class articles extends AppCompatActivity {

    private Button nutritionButton;
    private Button sportButton;
    private ImageButton returnButton;
    private ImageButton logoButton;
    private Button vaccinationsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        nutritionButton= (Button)findViewById(R.id.nutrition);
        sportButton= (Button)findViewById(R.id.sport);
        returnButton = (ImageButton) findViewById(R.id.go_back);
        vaccinationsButton = (Button) findViewById(R.id.vaccinations) ;
        logoButton = (ImageButton) findViewById(R.id.Image_Logo);

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

        // handle the vaccinations Button - move to vaccinations articles
        vaccinationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVaccinationsArticles();
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

    //use fun to move on to home page
    public void openHomePage() {
        Intent in = new Intent(this, homePage.class);
        startActivity(in);
    }

    //use fun to move on to vaccinations articles page
    public void openVaccinationsArticles() {
        Intent in = new Intent(this, vaccinationsArticles.class);
        startActivity(in);
    }


}
