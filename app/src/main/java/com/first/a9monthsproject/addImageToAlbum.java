package com.first.a9monthsproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

public class addImageToAlbum extends AppCompatActivity {
    private ImageButton uploadImageButton;
    private EditText addTitle;
    private Button addImageButton;
  //  private ImageView image;
    private DatabaseReference mPostDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Uri mImageUri; //path to image -> use link to where to find out image
    private static final int GALLERY_CODE =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image_to_album);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mPostDatabase = FirebaseDatabase.getInstance().getReference().child("MBlog");
        uploadImageButton = (ImageButton)findViewById(R.id.enterPic);
        addTitle = (EditText) findViewById(R.id.entertitle);
        addImageButton = (Button) findViewById(R.id.addImage);
     //   image=(ImageView) findViewById(R.id.image_view);

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //posting the Image to database
                startPostImage();
            }
        });

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("Image/*");
                startActivityForResult(galleryIntent,GALLERY_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);




     /*   if ((requestCode == GALLERY_CODE) && (requestCode == RESULT_OK)){
            mImageUri=data.getData();
            uploadImageButton.setImageURI(mImageUri);

        }*/
    }

    private void startPostImage() {

        String titleVal = addTitle.getText().toString().trim();

        if (!TextUtils.isEmpty(titleVal)){
            //start the uploading..
        PostImageBlog postblog = new PostImageBlog("title", "image", "time", "userid");
        mPostDatabase.setValue(postblog).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "item addes", Toast.LENGTH_LONG).show();
            }
        });



        }
    }
}
