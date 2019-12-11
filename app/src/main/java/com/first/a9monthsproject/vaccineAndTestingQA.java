package com.first.a9monthsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class vaccineAndTestingQA extends AppCompatActivity {

    private Button returnButton;
   private ImageButton logoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_and_testing_q);

        returnButton= (Button) findViewById(R.id.goback);
       logoButton=(ImageButton) findViewById(R.id.Image_Logo);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQApage();
            }
        });

        //move to home page when we push on the logo
       logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });
    }

    //use fun to move on to main QA page
    public void openQApage() {
        Intent in = new Intent(this, questionsAnswers.class);
        startActivity(in);
    }

    //use fun to move on to home page
    public void openHomePage() {
        Intent in = new Intent(this, homePage.class);
        startActivity(in);
    }

    public static class postImageBlog {
        public String title;
        public String image;
        public String timestamp;
        public String userid;

    public postImageBlog(String title1, String image1 , String time1, String userid1){
        this.title =title1;
        this.image=image1;
        this.timestamp =time1;
        this.userid =userid1;
    }

    public String getTitle(){
        return title;
        }


        public String getTimestamp(){
            return timestamp;
        }


        public String getImage(){
            return image;
        }

        public String getUserid(){
             return userid;
        }

        public void setTitle(String temp){
            title =temp;
        }

        public void setImage(String temp){
            image =temp;
        }

        public void setTimestamp(String temp){
            timestamp =temp;
        }

        public void setUserid(String temp){
            userid =temp;
        }

    }
}
