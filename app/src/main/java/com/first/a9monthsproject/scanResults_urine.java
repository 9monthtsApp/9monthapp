package com.first.a9monthsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.first.a9monthsproject.GraphicUtils.GraphicOverlay;
import com.first.a9monthsproject.GraphicUtils.TextGraphic;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class scanResults_urine extends AppCompatActivity {

    private CameraView mCameraView;
    private Button mCameraButton;
    private Button decoding;
    public GraphicOverlay mGraphicOverlay;
    public TextView textView;

    private ArrayList<String> arrayListString;
    private ArrayList<Double> arrayListDoule;
    private HashMap<String, Double> hmapResults;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_results_urine);


        mCameraView = (CameraView) findViewById(R.id.camView);
        mCameraButton = (Button) findViewById(R.id.IdentifyButton);
        mGraphicOverlay = (GraphicOverlay) findViewById(R.id.graphicOverlay);
        textView = (TextView) findViewById(R.id.textView1);
        decoding = (Button) findViewById(R.id.Decipher);


        //arrays
        arrayListString = new ArrayList<String>();
        arrayListDoule = new ArrayList<Double>();
        hmapResults = new HashMap<String, Double>();

        //firebase
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        mCameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {

                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, mCameraView.getWidth(), mCameraView.getHeight(), false);
                mCameraView.stop();

                runTextRecognition(bitmap);

            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });


        mCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGraphicOverlay.clear();
                mCameraView.start();
                mCameraView.captureImage();

            }
        });
    }

    private void runTextRecognition(Bitmap bitmap) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
        detector.processImage(image)
                .addOnSuccessListener(
                        new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText texts) {

                                processTextRecognitionResult(texts);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                e.printStackTrace();
                            }
                        });
    }

    private void processTextRecognitionResult(FirebaseVisionText texts) {
        List<FirebaseVisionText.TextBlock> blocks = texts.getTextBlocks();
        if (blocks.size() == 0) {
            Log.d("TAG", "No text found");
            return;
        }
        mGraphicOverlay.clear();


        for (int i = 0; i < blocks.size(); i++) {
            Log.d("TAG", String.valueOf(i));
            List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();
            for (int j = 0; j < lines.size(); j++) {
                List<FirebaseVisionText.Element> elements = lines.get(j).getElements();
                for (int k = 0; k < elements.size(); k++) {

                    GraphicOverlay.Graphic textGraphic = new TextGraphic(mGraphicOverlay, elements.get(k));

                    textView.append(" " + elements.get(k).getText());
                    mGraphicOverlay.add(textGraphic);
                }
            }
        }

        String toastText = textView.getText().toString();

        //take the string item and split them
        //Each cell in the array contains one value that we read - either a test name or its result
        String[] result = toastText.split(" , \\s*,\\s*");

        final FirebaseUser user = mAuth.getCurrentUser();

        for (String a : result) {

            //adding the values ​​we read into firebase
            databaseReference.child("MUsers").child(user.getUid()).child("Tests_result_urine").push().setValue(a);

        }//end loop on result



        decoding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDecodingPage();
            }
        });


    }

    private void openDecodingPage() {
        Intent in = new Intent(this, decodingResults_urine.class);
        startActivity(in);
    }

    public boolean stringContainsNumber( String s )
    {
        return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }



    @Override
    public void onResume () {
        super.onResume();
        mCameraView.start();
    }


    @Override
    public void onPause () {
        mCameraView.stop();
        super.onPause();
    }

}
