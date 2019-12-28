package com.first.a9monthsproject;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.first.a9monthsproject.GraphicUtils.GraphicOverlay;
import com.first.a9monthsproject.GraphicUtils.TextGraphic;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import java.util.List;

import static com.google.firebase.ml.vision.text.FirebaseVisionText.Element;
import static com.google.firebase.ml.vision.text.FirebaseVisionText.Line;

public class scanResultPage extends AppCompatActivity {
    private CameraView mCameraView;
    private Button mCameraButton;
    public GraphicOverlay mGraphicOverlay;
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result_page);
        mCameraView=(CameraView)findViewById(R.id.camView);
        mCameraButton=(Button)findViewById(R.id.butt);
        mGraphicOverlay= (GraphicOverlay)findViewById(R.id.graphicOverlay);
        textView=(TextView)findViewById(R.id.textView1);

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
            List<Line> lines = blocks.get(i).getLines();
            for (int j = 0; j < lines.size(); j++) {
                List<Element> elements = lines.get(j).getElements();
                for (int k = 0; k < elements.size(); k++) {

                    GraphicOverlay.Graphic textGraphic = new TextGraphic(mGraphicOverlay, elements.get(k));
                    textView.append(" " +elements.get(k).getText());
                    mGraphicOverlay.add(textGraphic);

                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mCameraView.start();
    }


    @Override
    public void onPause() {
        mCameraView.stop();
        super.onPause();
    }


}
