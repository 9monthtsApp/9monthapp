package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class decodingResults_urine extends AppCompatActivity {

    private ImageButton logoButton;
    private TextView title;
    private ListView list;
    private Button returnBtn;
    List<String> keyList;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    //check test value
    private TextView checkNitrite;
    private TextView checkLeucocytes;
    private TextView checkPh;
    public TextView checkProtein;
    private TextView checkGlu;
    private TextView checkKetones;
    private TextView checkUroblinogen;
    private TextView checkBilirubin;
    private TextView checkEry;



    private TextView Nitrite_recommendation;
    private TextView Leucocytes_recommendation;
    private TextView ph_recommendation;
    private TextView Protein_recommendation;
    private TextView glu_recommendation;
    private TextView Ketones_recommendation;
    private TextView Uroblinogen_recommendation;
    private TextView Bilirubin_recommendation;
    private TextView Ery_recommendation;




    //firebase
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoding_results_urine);

        logoButton = findViewById(R.id.Image_Logo);

        //list of tests result
        arrayList = new ArrayList<String>();
        keyList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(decodingResults_urine.this ,android.R.layout.simple_list_item_1,arrayList);
        list.setAdapter(arrayAdapter);

        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();


        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();
        //FirebaseUser users = myFirebaseRef.child("users");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("MUsers").child(userId).child("Blood_tests_result");

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
}
