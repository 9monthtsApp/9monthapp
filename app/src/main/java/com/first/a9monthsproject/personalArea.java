package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class personalArea extends AppCompatActivity {

    private Button presonalButton;
    private Button myRecommendationButton;
    private Button passButton;
    private ImageButton gobackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);

        presonalButton = (Button) findViewById(R.id.WatchPersonalInfo);
        myRecommendationButton=(Button) findViewById(R.id.personal_area);
        passButton = (Button) findViewById(R.id.Watchrecommendation);
        gobackButton = (ImageButton) findViewById(R.id.go_back);

        
    }
}
