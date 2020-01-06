package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

import java.util.List;

public class center extends AppCompatActivity {

    private Button returnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);

        returnButton = (Button) findViewById(R.id.returnBtn);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });


    }

    private void openHomePage() {
        Intent in = new Intent(this, homePage.class);
        startActivity(in);
    }
}
