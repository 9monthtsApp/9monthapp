package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestsResults extends AppCompatActivity {

    private Button bloodButton;
    private Button urineBuuton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_results);

        bloodButton =(Button) findViewById(R.id.bloodTest);
        urineBuuton =(Button) findViewById(R.id.urineTest);

        bloodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScanResultImage();
            }
        });
    }

    private void openScanResultImage() {
        Intent in = new Intent(this, scanResultPage.class);
        startActivity(in);
    }

}
