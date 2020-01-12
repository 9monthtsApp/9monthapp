package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class urine_recommendations extends AppCompatActivity {

    private TextView titleText;
    private TextView recommendation;
    private ImageButton logoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urine_recommendations);

        titleText = (TextView) findViewById(R.id.checkTestsType);
        recommendation = (TextView) findViewById(R.id.theRecommendation);
        logoBtn = findViewById(R.id.Image_Logo);

        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        Intent i = getIntent();
        String testType = i.getStringExtra("key");

        //recommendation for positive NitritePos
        if (testType.equals("NitritePos")) {

            titleText.setText("Positive nitritePos: ");
            recommendation.setText("The presence of nitrites and white blood cells in the urine often indicate urinary tract infection.\n" +
                    "The recommendations are:\n" +
                    "1. Drinking water with brewed parsley.\n" +
                    "2. Eating of cranberries without sugar or driniking cranberry juice.\n" +
                    "3. Drinking a spoonful of apple cider vinegar mixed with water.\n" +
                    "4. Taking probiotic pills for women.\n" +
                    "5. In a more serious case requiring antibiotic medication - make an appointment with your doctor and ask for antibiotics to help reduce inflammation.\n");
        }

        //recommendation for positive Leucocytes
        if (testType.equals("LeucocytesPos")) {

            titleText.setText("Positive Leucocytes: ");
            recommendation.setText("A high value of white blood cells in the urine may indicate urinary tract infection.\n" +
                    "Recommendations to relieve pain:\n" +
                    "1. Drinking cranberry juice.\n" +
                    "2. Drinking a spoonful of apple cider vinegar mixed with water.\n" +
                    "3. Taking probiotic pills for women.\n" +
                    "Recommendations for treating infection -\n" +
                    "Please make an appointment with your attending physician for a prescription for antibiotics.\n");
        }


    }



    private void openHomepage() {
        Intent in = new Intent(this, homePage.class);
        startActivity(in);
    }
}
