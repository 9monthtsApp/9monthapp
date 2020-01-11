package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class choose_scan_or_manul_urine extends AppCompatActivity {

    private Button scanButton;
    private Button manualButton;
    private ImageButton logoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_scan_or_manul_urine);

        scanButton =(Button) findViewById(R.id.scan);
        manualButton =(Button) findViewById(R.id.manual);
        logoButton= findViewById(R.id.Image_Logo);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScanResultUrine();

            }
        });

        logoButton.setOnClickListener(new View.OnClickListener() {
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

    private void openScanResultUrine() {
        Intent in = new Intent(this, scanResults_urine.class);
        startActivity(in);
    }
}
