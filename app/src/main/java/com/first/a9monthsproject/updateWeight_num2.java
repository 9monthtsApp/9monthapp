package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateWeight_num2 extends AppCompatActivity {
    private TextView check;
    private TextView temp;
    private Integer num;
    private ImageButton logoBtn;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;

    protected boolean _active = true;
    protected int _splashTime = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_update_weight_num2);


        logoBtn = findViewById(R.id.Image_Logo);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Recommendations").child("Blood").child("WBC").child("Weight").child("High").child("1");

        Intent i = getIntent();
      String testType = i.getStringExtra("key");
        num = Integer.valueOf(testType);
        if (num < 100 && num >=20) {
            Integer m = num + 2;

            mDatabaseReference.setValue(m.toString());
        }

        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });


       // Automatic start of a new activity => move to home page
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                Intent i3 = new Intent(updateWeight_num2.this, homePage.class);
                startActivity(i3);
            }
        }, _splashTime);



    }

    private void openHomePage() {
        Intent in = new Intent(this, homePage.class);
        startActivity(in);
    }


}