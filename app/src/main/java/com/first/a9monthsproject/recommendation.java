package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class recommendation extends AppCompatActivity {

    private Button urineRec;
    private Button bloodRec;
    private Button generalRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        urineRec = findViewById(R.id.urine);
        bloodRec = findViewById(R.id.blood);
        generalRec= findViewById(R.id.general);

        urineRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrineRecommendation();
            }
        });
    }

    private void openUrineRecommendation() {
        Intent in = new Intent( this,main_recommendation_urine.class);
        startActivity(in);
    }
}
