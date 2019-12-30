package com.first.a9monthsproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class testLog extends AppCompatActivity {

    private ListView testsList;
    private Button addButton;
    private Button removeButton;
    private Button showMyTestList;
    private EditText testName;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    //for save the test list to database
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "MainActivity";
    public String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_log);

        //xml
        addButton= (Button) findViewById(R.id.addtest);
        testName = (EditText) findViewById(R.id.inputText);
        testsList = (ListView) findViewById(R.id.listView);
        showMyTestList = (Button) findViewById(R.id.showList);
        removeButton = (Button) findViewById(R.id.removeTest);

        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();



        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //list
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(testLog.this ,android.R.layout.simple_list_item_1,arrayList);
        testsList.setAdapter(arrayAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = testName.getText().toString();
                arrayList.add(string);
                mDatabaseReference.child("MUsers").child(user.getUid()).child("Tests_List").push().setValue(string);
                arrayAdapter.notifyDataSetChanged();


            }
        });








        showMyTestList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyTestList();
            }
        });



    }

    private void openMyTestList() {

        Intent in = new Intent(this, MyTestList.class);
        startActivity(in);
    }



}
