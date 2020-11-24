package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class projectDetailDisplay extends AppCompatActivity {
    private RatingBar ratingBar;
    private Button submitRatingBtn;
    private TextView currentRatingTv;
    private String currRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail_display);
        ratingBar=(RatingBar)findViewById(R.id.rating_bar);
        submitRatingBtn=(Button)findViewById(R.id.submit_rating_btn);
        currentRatingTv=(TextView)findViewById(R.id.current_rating_TV);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("finalprojectpcs-9").child("Projects");

        // Read from the database
        //Currently only accessing 2021  ->  PID-2021-1
        myRef.child("2021").child("PID-2021-1").child("Rating").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                Toast.makeText(projectDetailDisplay.this,dataSnapshot.toString(),Toast.LENGTH_LONG);
                if(dataSnapshot.hasChild("CurrentValue")) {
                    currRating = dataSnapshot.child("CurrentValue").getValue().toString();
                    currentRatingTv.setText(currRating);
                }
                else
                {

                    currentRatingTv.setText(String.valueOf(dataSnapshot.getChildrenCount()));
                }

                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(projectDetailDisplay.this,"Not retrieved",Toast.LENGTH_LONG);
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

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