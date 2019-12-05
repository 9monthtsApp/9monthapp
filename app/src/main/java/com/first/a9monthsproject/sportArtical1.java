package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sportArtical1 extends AppCompatActivity {

    private Button returnButton;
    private Button returnMainArtical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_artical1);

        returnButton =(Button) findViewById(R.id.gobackToArticlesNut);
        returnMainArtical = (Button) findViewById(R.id.returnMainArticles);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSportArticalPage();
            }
        });

        returnMainArtical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainArticlesPage();
            }
        });
    }

    //use fun to move on to nutriton And Diet Articals page
    public void openSportArticalPage() {
        Intent in = new Intent(this, sportsArticles.class);
        startActivity(in);

    }


    //use fun to move on to main Articals page
    public void openMainArticlesPage() {
        Intent in = new Intent(this, articles.class);
        startActivity(in);

    }
}
