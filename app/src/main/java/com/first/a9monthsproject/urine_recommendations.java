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

        //recommendation for low acidity in urine
        if (testType.equals("lowPH")) {

            titleText.setText("Low ph in urine: ");
            recommendation.setText("1. If the acidic urine indicates diabetes - make an appointment with the attending physician.\n" +
                    "2. In diarrhea and vomiting condition a lot of water should be consumed.\n" +
                    "If it is a bacterial diarrhea accompanied by dehydration and heat, it is recommended to treat it with antibiotics\n");
        }

        //recommendation for high acidity in urine
        if (testType.equals("highPH")) {

            titleText.setText("high ph in urine: ");
            recommendation.setText("1. If the cause is multiple vomiting - make sure you drink multiple times and ask your doctor for the Pramin medication to help treat nausea without side effects.\n" +
                    "2. If there is a urinary tract infection - please make an appointment with your attending physician for a prescription for antibiotics.\n");
        }


        //recommendation for positive Proteine
        if (testType.equals("ProteinePos")) {

            titleText.setText("Positive value of proteine: ");
            recommendation.setText("An amount of more than 0.3 grams of protein in the urine during 24 hours (together with high blood pressure) usually indicates a condition of preeclampsia - consult the physician urgently and perform additional examinations for the mother and fetus.");
        }

        //recommendation for positive glucose
        if (testType.equals("GlucosePos")) {

            titleText.setText("Positive value of glucose: ");
            recommendation.setText("High blood sugar levels may indicate latent diabetes, so the recommendation is to talk to the attending physician and coordinate sugar loading treatments");
        }

        //recommendation for positive Ketones
        if (testType.equals("KetonesPos")) {

            titleText.setText("Positive value of Ketones: ");
            recommendation.setText("A positive result of urinary ketones can indicate: diabetes,\n" +
                    "high body heat, fasting or multiple vomiting during high heat pregnancy.\n" +
                    "1. If you have recently fasted or you have high body heat, these values will most often return to be normal at the next check.\n" +
                    "2. In case of vomiting - it is advised not to consume fried foods and drink a lot. You can also get a prescription for paramine from your doctor.\n" +
                    "3. If you have diabetes - make an appointment with your doctor for appropriate consultation.\n");
        }

        //recommendation for abnormal Uroblinogen
        if (testType.equals("UroblinogenPos")) {

            titleText.setText("Abnormal value of Uroblinogen: ");
            recommendation.setText("If the test is different from normal the possible causes are antibiotic consumption or liver disease.\n" +
                    "It is advisable to repeat the urine test to ascertain the cause of the high value and continue monitoring with a female doctor.\n");
        }

        //recommendation for positive Bilirubin
        if (testType.equals("BilirubinPos")) {

            titleText.setText("Positive value of Bilirubin: ");
            recommendation.setText("The presence of bilirubin in the urine may indicate the development of liver disease and therefore must make an appointment with the attending physician for further consultation.");
        }

        //recommendation for positive EryThrocytes
        if (testType.equals("EryThrocytesPos")) {

            titleText.setText("Positive value of Ery Throcytes: ");
            recommendation.setText("Higher values than the norm may indicate urinary tract infection, so the recommended treatment is:\n" +
                    "1. Drinking cranberry juice.\n" +
                    "3. Drinking a spoonful of apple cider vinegar mixed with water.\n" +
                    "4. Taking probiotic pills for women.\n" +
                    "5. Please make an appointment with your attending physician for a prescription for antibiotics.\n");
        }
    }






    private void openHomepage() {
        Intent in = new Intent(this, homePage.class);
        startActivity(in);
    }
}
