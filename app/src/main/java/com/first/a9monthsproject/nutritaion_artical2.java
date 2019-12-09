package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class nutritaion_artical2 extends AppCompatActivity {

    private Button returnButton;
    private Button returnMainArtical;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritaion_artical2);

        returnButton =(Button) findViewById(R.id.testList);
        returnMainArtical = (Button) findViewById(R.id.returnMainArticles);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNutritonArticalPage();
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
    public void openNutritonArticalPage() {
        Intent in = new Intent(this, nutritonAndDietArticals.class);
        startActivity(in);

    }


    //use fun to move on to main Articals page
    public void openMainArticlesPage() {
        Intent in = new Intent(this, articles.class);
        startActivity(in);

    }
}
