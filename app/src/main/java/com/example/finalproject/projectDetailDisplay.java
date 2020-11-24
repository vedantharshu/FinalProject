package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class projectDetailDisplay extends AppCompatActivity {
    private RatingBar ratingBar;
    private Button submitRatingBtn;
    private TextView currentRatingTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail_display);
        ratingBar=(RatingBar)findViewById(R.id.rating_bar);
        submitRatingBtn=(Button)findViewById(R.id.submit_rating_btn);
        currentRatingTv=(TextView)findViewById(R.id.current_rating_TV);
        submitRatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double rating=Double.parseDouble(String.valueOf(ratingBar.getRating()));
                //Check if already rated then proceed.
                Toast.makeText(projectDetailDisplay.this, String.valueOf(rating), Toast.LENGTH_LONG).show();
            }
        });
    }
}