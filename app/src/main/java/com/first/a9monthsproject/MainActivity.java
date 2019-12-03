package com.first.a9monthsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("message");
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "MainActivity";
    private EditText email;
    private EditText pass;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.emailAd);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.logButton);
        register = (Button) findViewById(R.id.registration);

        mAuth = FirebaseAuth.getInstance();
        myRef.setValue("Hello, timor!!");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // take care to user login
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    //user singed in
                    Log.d(TAG, "singed in");
                    // Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_LONG).show();

                } else {
                    //user singed out
                    Log.d(TAG, "singed out");
                    //  Toast.makeText(MainActivity.this, "No", Toast.LENGTH_LONG).show();
                }


            }
        };
        // take care to login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmpEmail = email.getText().toString();
                String tmpPass = pass.getText().toString();

                ///check if the input is not empty
                if (!tmpEmail.equals("") && !tmpPass.equals("")) {
                    mAuth.signInWithEmailAndPassword(tmpEmail, tmpPass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_LONG).show();
                                //  Log.d(TAG,"success");
                                Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_LONG).show();
                                openHomePage();

                            } else {
                                //Toast.makeText(MainActivity.this, "singed in!!", Toast.LENGTH_LONG).show();
                                // Log.w(TAG,"failed", task.getException());

                                Toast.makeText(MainActivity.this, "Incorrect contact details ", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistrationPage();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListener);
        // updateUI(currentUser);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);

        }
    }

    // use the fun to move on to home page
    public void openHomePage() {
        Intent in = new Intent(this, homePage.class);
        startActivity(in);
    }

    //use fun to move on to registration page
    public void openRegistrationPage() {
        Intent in = new Intent(this, registeration.class);
        startActivity(in);
    }
}
