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
    private TextView checkWBC;
    private TextView checkRBC;
    private ListView list;
    private Button returnBtn;
    List<String> keyList;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

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
        checkWBC =(TextView) findViewById(R.id.WBC);
        checkRBC =(TextView) findViewById(R.id.RBC) ;

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

               checkValuesWBC(result);
               checkValuesRBC(result);






            }// end of onItemClick
        }); //end of setOnItemClickListener




    }
    private void checkValuesWBC( String[] str){
        if (Double.valueOf(str[3]) < 14.00) {
            checkWBC.setText("WBC-this test examines a number of white blood cells - your WBC test values ​​are under the normal range!");

        } else if (Double.valueOf(str[3]) > 16.00) {
            checkWBC.setText("WBC-this test examines a number of white blood cells - your WBC test values are above the normal range ! ");

        } else{
            checkWBC.setText("WBC-this test examines a number of white blood cells - your WBC test values are at the normal range "); }
    }

    private void checkValuesRBC( String[] str){

        if (Double.valueOf(str[5]) < 3.00) {
            checkRBC.setText("RBC-this test examines a number of red blood cells - your RBC test values ​​are under the normal range!");

        } else if (Double.valueOf(str[5]) > 5.5) {
            checkRBC.setText("RBC-this test examines a number of red blood cells - your RBC test values are above the normal range ! ");

        } else{
            checkRBC.setText("RBC-this test examines a number of red blood cells - your RBC test values are at the normal range "); }
    }

    private void openTestsPage() {
        Intent in = new Intent(this , tests.class);
        startActivity(in);
    }

}
