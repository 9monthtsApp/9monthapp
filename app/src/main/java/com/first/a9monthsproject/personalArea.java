package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class personalArea extends AppCompatActivity {

    private Button presonalButton;
    private Button myRecommendationButton;
    private Button picButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);

        presonalButton = (Button) findViewById(R.id.WatchPersonalInfo);
        myRecommendationButton=(Button) findViewById(R.id.personal_area);
        picButton = (Button) findViewById(R.id.photoAlbum);

        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbumPage();
            }
        });


        
    }

    private void openAlbumPage() {
        Intent in = new Intent(this, hoosingOptionImageAlbum.class);
        startActivity(in);
    }


}
