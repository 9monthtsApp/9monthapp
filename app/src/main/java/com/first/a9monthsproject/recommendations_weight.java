package com.first.a9monthsproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class recommendations_weight extends AppCompatActivity {

    List<String> keyList;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    private ListView list;
    private TextView check2;
    private TextView check4;
    private TextView check5;
    private Button addBtn;


    //firebase
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations_weight);

        list = (ListView) findViewById(R.id.listViewId2);
        check2 = findViewById(R.id.check3);
        check4 = findViewById(R.id.check4);
        check5 = findViewById(R.id.check5);
        addBtn = findViewById(R.id.addW);

        arrayList = new ArrayList<String>();
        keyList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(recommendations_weight.this ,android.R.layout.simple_list_item_1,arrayList);
        list.setAdapter(arrayAdapter);

        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        //positiveGlu

        Intent i = getIntent();
        String testType = i.getStringExtra("key");


        if ( testType.equals("highWBC")) {

            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Recommendations").child("Blood").child("WBC").child("feedback").child("High").child("1");

            mDatabaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String value = dataSnapshot.getValue(String.class);
                    arrayList.add(value); // include the scan values
                    keyList.add(dataSnapshot.getKey()); //include the id of each tests
                    arrayAdapter.notifyDataSetChanged();
                    int count = list.getAdapter().getCount();
                    ;
                    if (count != 0) {
                        check2.append(" " + "1");
                    }

                    String toastText = check2.getText().toString();

                    String[] result = toastText.split(" ");
                    int cnt = result.length;
                    // the recommendation is helpfull
                    if (cnt >= 4) {
                        check4.setText("!!!!");
                        clacHigh_WBC();

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

        }// end if highWBC

        if (testType.equals("positiveGlu")){

            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Recommendations").child("Urine").child("Glucose").child("feedback").child("1");

            mDatabaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String value = dataSnapshot.getValue(String.class);
                    arrayList.add(value); // include the scan values
                    keyList.add(dataSnapshot.getKey()); //include the id of each tests
                    arrayAdapter.notifyDataSetChanged();
                    int count = list.getAdapter().getCount();
                    ;
                    if (count != 0) {
                        check2.append(" " + "1");
                    }

                    String toastText = check2.getText().toString();

                    String[] result = toastText.split(" ");
                    int cnt = result.length;
                    // the recommendation is helpfull
                    if (cnt >= 1) {
                        check4.setText("!!!!");
                        clacGLU();

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


    }

    public void clacGLU(){

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Recommendations").child("Urine").child("Glucose").child("Weight").child("1");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String userId = dataSnapshot.getValue(String.class);
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        funcGLU_update(userId);
                    }
                });




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



    public void clacHigh_WBC(){

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

       mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Recommendations").child("Blood").child("WBC").child("Weight").child("High").child("1");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String userId = dataSnapshot.getValue(String.class);
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        funcWBC_update(userId);
                    }
                });




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void funcWBC_update(String s ){
        Intent in = new Intent( this,updateWeight_num2.class);
        in.putExtra("key", s);
        startActivity(in);
    }

    public void funcGLU_update(String s ){
        Intent in = new Intent( this,updateWeight_num2_urine.class);
        in.putExtra("key", s);
        startActivity(in);
    }

}


