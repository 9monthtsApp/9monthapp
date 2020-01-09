package com.first.a9monthsproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class decodingResults extends AppCompatActivity {

    private TextView title;
    private ListView list;
    private Button returnBtn;
    List<String> keyList;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

//check test value
    private TextView checkWBC;
    private TextView checkRBC;
    private TextView checkGlucose;
    public TextView checkHemoglobin;
    private TextView checkHematocrit;
    private TextView checkMCV;
    private TextView checkMCH;
    private TextView checkMCHC;
    private TextView checkRDW;
    private TextView checkPlatelets;
    public TextView checkMPV;


    //firebase
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoding_results);

        title = (TextView) findViewById(R.id.bloodTestsResult);
        list = (ListView) findViewById(R.id.listViewId);
        returnBtn =(Button) findViewById(R.id.returnButton);

        //decoding per tests
       checkGlucose =(TextView) findViewById(R.id.Glucose);
        checkWBC =(TextView) findViewById(R.id.WBC);
        checkRBC =(TextView) findViewById(R.id.RBC);
        checkHemoglobin =(TextView) findViewById(R.id.Hemoglobin);

        checkHematocrit =(TextView) findViewById(R.id.Hematocrit);
        checkMCV =(TextView) findViewById(R.id.MCV);
        checkMCH = (TextView) findViewById(R.id.MCH);
        checkMCHC = (TextView) findViewById(R.id.MCHC);
        checkRDW = (TextView) findViewById(R.id.RDW);
        checkPlatelets = (TextView) findViewById(R.id.Platelets);
        checkMPV = (TextView) findViewById(R.id.MPV);



        arrayList = new ArrayList<String>();
        keyList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(decodingResults.this ,android.R.layout.simple_list_item_1,arrayList);
        list.setAdapter(arrayAdapter);

        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();


        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();
        //FirebaseUser users = myFirebaseRef.child("users");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("MUsers").child(userId).child("Tests_result");


        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(String.class);
                arrayList.add(value); // include the scan values
                keyList.add(dataSnapshot.getKey()); //include the id of each tests
                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTestsPage();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String resultValue = arrayAdapter.getItem(position);
                String[] result = resultValue.split(" ");

                 checkValuesGlucose(result);
                checkValuesWBC(result);
                checkValuesRBC(result);
                checkValuesHemoglobin(result);

                checkValuesHematocrit(result);
                checkValuesMCV(result);
                checkValuesMCH(result);
                checkValuesMCHC(result);
                checkValuesRDW(result);
                checkValuesPlatelets(result);
                checkValuesMPV(result);





            }// end of onItemClick
        }); //end of setOnItemClickListener




    }

    private void checkValuesGlucose( String[] str){

        if (Double.valueOf(str[13]) < 65.00) {
            checkGlucose.setText("Glucose-this test examines a number of red blood cells -  the amount of sugar in your blood below the proper range!");

        } else if (Double.valueOf(str[13]) > 126.0) {
            checkGlucose.setText("Glucose-this test examines the amount of sugar in the blood - the amount of sugar in your blood above the proper range! ");

        } else{
            checkGlucose.setText("Glucose-this test examines a number of red blood cells -  the amount of sugar in your blood are at the proper range! "); }
    }

    private void checkValuesWBC( String[] str){
        if (Double.valueOf(str[14]) < 4.5) {
            checkWBC.setText("WBC-this test examines a number of white blood cells - your WBC values ​​are below the normal range!");

        } else if (Double.valueOf(str[14]) > 11.00) {
            checkWBC.setText("WBC-this test examines a number of white blood cells - your WBC values are above the normal range ! ");

        } else{
            checkWBC.setText("WBC-this test examines a number of white blood cells - your WBC values are at the normal range. "); }
    }

    private void checkValuesRBC( String[] str){

        if (Double.valueOf(str[15]) < 3.5) {
            checkRBC.setText("RBC-this test examines a number of red blood cells - your RBC values ​​are below the normal range!");

        } else if (Double.valueOf(str[15]) > 5.5) {
            checkRBC.setText("RBC-this test examines a number of red blood cells - your RBC values are above the normal range ! ");

        } else{
            checkRBC.setText("RBC-this test examines a number of red blood cells - your RBC values are at the normal range."); }
    }

    private void checkValuesHemoglobin( String[] str){

        if (Double.valueOf(str[16]) < 11.0) {
            checkHemoglobin.setText("Hemoglobin- this test examines the blood hemoglobin level - your hemoglobin values ​​are below the normal range!");

        } else if (Double.valueOf(str[16]) > 16.0) {
            checkHemoglobin.setText("Hemoglobin- this test examines the blood hemoglobin level - your hemoglobin values ​​are above the normal range! ");

        } else{
            checkHemoglobin.setText("Hemoglobin- this test examines the blood hemoglobin level - your hemoglobin values ​​are at the normal range"); }
    }

    private void checkValuesHematocrit( String[] str){

        if (Double.valueOf(str[17]) < 36.0) {
            checkHematocrit.setText("Hematocrit- this test counting the percentage of red blood cell volume - The percentage of red blood cell volume is low !");

        } else if (Double.valueOf(str[17]) > 46.0) {
            checkHematocrit.setText("Hematocrit- this test counting the percentage of red blood cell volume - The percentage of red blood cell volume is high !");

        } else{
            checkHematocrit.setText("Hematocrit- this test counting the percentage of red blood cell volume - The percentage of red blood cell volume is at the normal range."); }
    }

    private void checkValuesMCV( String[] str){

        if (Double.valueOf(str[18]) < 79.0) {
            checkMCV.setText("MCV- The test counts the volume of red blood cells - your volume of red blood cells ​​are below the normal range!");

        } else if (Double.valueOf(str[18]) > 97.0) {
            checkMCV.setText("MCV- The test counts the volume of red blood cells- your volume of red blood cells are above the normal range! ");

        } else{
            checkMCV.setText("MCV- The test counts the volume of red blood cells - your volume of red blood cells ​​are at the normal range"); }
    }

    private void checkValuesMCH( String[] str){

        if (Double.valueOf(str[19]) < 27.0) {
            checkMCH.setText("MCH- The test checking the amount and concentration of hemoglobin in the blood cells - the concentration of hemoglobin in your blood cells are low!");

        } else if (Double.valueOf(str[19]) > 34.0) {
            checkMCH.setText("MCH- The test checking the amount and concentration of hemoglobin in the blood cells - the concentration of hemoglobin in your blood cells are high! ");

        } else{
            checkMCH.setText("MCH- The test checking the amount and concentration of hemoglobin in the blood cells - the concentration of hemoglobin in your blood cells are at the normal range."); }
    }

    private void checkValuesMCHC( String[] str){

        if (Double.valueOf(str[20]) < 31.0) {
            checkMCHC.setText("MCHC- The test reflects the amount of hemoglobin relative to the cell size for each red blood cell - The amount of hemoglobin relative to the cell size is lower than the normal range!");

        } else if (Double.valueOf(str[20]) > 36.0) {
            checkMCHC.setText("MCHC- The test reflects the amount of hemoglobin relative to the cell size for each red blood cell - The amount of hemoglobin relative to the cell size is higher than the normal range! ");

        } else{
            checkMCHC.setText("MCHC- The test reflects the amount of hemoglobin relative to the cell size for each red blood cell - The amount of hemoglobin relative to the cell size is at the normal range"); }
    }

    private void checkValuesRDW( String[] str){

        if (Double.valueOf(str[21]) < 11.5) {
            checkRDW.setText("RDW- Testing the variability of red blood cell volumes - the variability of red blood cell volumes is below the normal range!");

        } else if (Double.valueOf(str[21]) > 15.0) {
            checkRDW.setText("RDW- Testing the variability of red blood cell volumes - the variability of red blood cell volumes is above the normal range! ");

        } else{
            checkRDW.setText("RDW- Testing the variability of red blood cell volumes - the variability of red blood cell volumes is at the normal range"); }
    }

    private void checkValuesPlatelets( String[] str){

        if (Double.valueOf(str[22]) < 150.0) {
            checkPlatelets.setText("Platelets- Count the amount of platelets in the blood - The amount of platelets in your blood is low!");

        } else if (Double.valueOf(str[22]) > 450.0) {
            checkPlatelets.setText("Platelets- Count the amount of platelets in the blood - The amount of platelets in your blood is high!");

        } else{
            checkPlatelets.setText("Platelets- Count the amount of platelets in the blood - The amount of platelets in your blood are at the normal range"); }
    }

    private void checkValuesMPV( String[] str) {

        if (Double.valueOf(str[23]) < 8.5) {
            checkMPV.setText("MPV- The test measures an average platelet volume - The average blood platelet volume in your blood is low!");

        } else if (Double.valueOf(str[23]) > 12.9) {
            checkMPV.setText("MPV- The test measures an average platelet volume - The average blood platelet volume in your blood is high!");

        } else {
            checkMPV.setText("MPV- The test measures an average platelet volume - The average blood platelet volume in your blood is at the normal range");
        }

    }




    private void openTestsPage() {
        Intent in = new Intent(this , tests.class);
        startActivity(in);
    }

}
