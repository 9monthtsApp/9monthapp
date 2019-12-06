package com.first.a9monthsproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class vaccinationArticle1 extends AppCompatActivity {
    private Button returnButton;
    private Button returnMainArtical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_article1);
        returnButton =(Button) findViewById(R.id.gobackToArticlesNut);
        returnMainArtical = (Button) findViewById(R.id.returnMainArticles);



        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVaccinationsPage();
            }
        });

        returnMainArtical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainArticlesPage();
            }
        });
    }

    //use fun to move on to vaccinations page
    public void openVaccinationsPage() {
        Intent in = new Intent(this, vaccinationsArticles.class);
        startActivity(in);

    }


    //use fun to move on to main Articals page
    public void openMainArticlesPage() {
        Intent in = new Intent(this, articles.class);
        startActivity(in);

    }
}
