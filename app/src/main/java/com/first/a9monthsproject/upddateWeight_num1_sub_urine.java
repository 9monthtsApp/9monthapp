package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class upddateWeight_num1_sub_urine extends AppCompatActivity {

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
        setContentView(R.layout.activity_upddate_weight_num1_sub_urine);


        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Recommendations").child("Urine").child("Glucose").child("Weight").child("1");


        Intent i = getIntent();
        String testType = i.getStringExtra("key");
        num = Integer.valueOf(testType);
        if (num >= 20) {
            Integer m = num - 2;
            mDatabaseReference.setValue(m.toString());
        }
        else{

            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Recommendations").child("Blood").child("WBC").child("The_recommendation").child("High").child("1");
            mDatabaseReference.removeValue();
            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Recommendations").child("Urine").child("Glucose").child("Weight").child("1");
            mDatabaseReference.setValue("0");


        }


        // Automatic start of a new activity => move to home page
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                Intent i3 = new Intent(upddateWeight_num1_sub_urine.this, homePage.class);
                startActivity(i3);
            }
        }, _splashTime);
    }


}
