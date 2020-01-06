package com.first.a9monthsproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

public class watch_tests_results extends AppCompatActivity {

    private TextView title;

    private ListView list;
    private Button returnBtn;
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
        setContentView(R.layout.activity_watch_tests_results);


        title = (TextView) findViewById(R.id.bloodTestsResult);
        list = (ListView) findViewById(R.id.listViewId);
        returnBtn =(Button) findViewById(R.id.returnButton);


        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(watch_tests_results.this ,android.R.layout.simple_list_item_1,arrayList);
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
                arrayList.add(value);
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

    }


    private void openTestsPage() {
        Intent in = new Intent(this , tests.class);
        startActivity(in);
    }
}
