package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class chooseScanOrManual extends AppCompatActivity {

    private Button scanButton;
    private Button manualBuuton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_scan_or_manual);

        scanButton =(Button) findViewById(R.id.scan);
        manualBuuton =(Button) findViewById(R.id.manual);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScanResultImage();
            }
        });

        manualBuuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManualResultImage();
            }
        });
    }

    private void openScanResultImage() {
        Intent in = new Intent(this, scanResultPage.class);
        startActivity(in);
    }

    private void openManualResultImage() {
        Intent in = new Intent(this, Manual_blood_tests_result.class);
        startActivity(in);
    }
}
