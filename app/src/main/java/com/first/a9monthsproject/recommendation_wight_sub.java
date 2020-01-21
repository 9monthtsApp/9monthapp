package com.first.a9monthsproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class recommendation_wight_sub extends AppCompatActivity {

    List<String> keyList;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    private ListView list;
    private TextView check2;
    private TextView check4;
    private TextView check5;
    private Button subBtn;


    //firebase
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation_wight_sub);


        list = (ListView) findViewById(R.id.listViewId2);
        check2 = findViewById(R.id.check3);
        check4 = findViewById(R.id.check4);
        check5 = findViewById(R.id.check5);
        subBtn = findViewById(R.id.subW);


        arrayList = new ArrayList<String>();
        keyList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(recommendation_wight_sub.this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);

        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();


        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Recommendations").child("Blood").child("WBC").child("feedback").child("High").child("2");


        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(String.class);
                arrayList.add(value); // include the scan values
                keyList.add(dataSnapshot.getKey()); //include the id of each tests
                arrayAdapter.notifyDataSetChanged();
                int count = list.getAdapter().getCount();
                //check the amount of feedback for each recommendation
                if (count != 0) {
                    check2.append(" " + "1");
                }

                String toastText = check2.getText().toString();

                String[] result = toastText.split(" ");
                int cnt = result.length; // the number of feedback
                // the recommendation is helpfull
                if (cnt >= 4) { // 10 users
                    check4.setText("!!!!");
                    calc();

                }


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


    }


    public void func(String s ){
        Intent in = new Intent( this,updateWeight_num1_sub.class);
        in.putExtra("key", s);
        startActivity(in);
    }

    public void calc (){


        mDatabaseReference = mDatabase.getReference();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Recommendations").child("Blood").child("WBC").child("Weight").child("High").child("1");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String userId = dataSnapshot.getValue(String.class);
                subBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        func(userId);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}