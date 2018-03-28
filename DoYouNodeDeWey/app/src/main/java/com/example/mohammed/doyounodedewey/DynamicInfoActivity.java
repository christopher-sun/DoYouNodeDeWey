package com.example.mohammed.doyounodedewey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DynamicInfoActivity extends AppCompatActivity {
    TextView name;
    TextView capacity;
    TextView restrictions;
    TextView longitude;
    TextView latitude;
    TextView address;
    TextView specialNote;
    TextView phoneNumber;
    Button claimABed;
    Shelter shelter;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = getIntent().getParcelableExtra("USER");
        shelter = getIntent().getParcelableExtra("SHELTER");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_info);

        //Linking the textView fields to the textViews in the activity
        name = findViewById(R.id.textView1);
        capacity = findViewById(R.id.textView2);
        restrictions = findViewById(R.id.textView3);
        longitude = findViewById(R.id.textView4);
        latitude = findViewById(R.id.textView5);
        address = findViewById(R.id.textView6);
        specialNote = findViewById(R.id.textView7);
        phoneNumber = findViewById(R.id.textView8);
        claimABed = findViewById(R.id.claimABed);

        claimABed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getClaimedSpace() == null) {
                    Intent toy = new Intent(DynamicInfoActivity.this, ReportPopupActivity.class);
                    toy.putExtra("USER", user);
                    toy.putExtra("SHELTER", shelter);
                    startActivity(toy);
                } else if (user.getClaimedSpace().equals(name)){
                    //todo
                } else {
                    //todo
                }
            }
        });


        //Setting the text to be displayed
        name.setText("Name: " + this.getIntent().getExtras().getString("Name:"));
        capacity.setText("Capacity: " + this.getIntent().getExtras().getString("Capacity:"));
        restrictions.setText("Restrictions: " + this.getIntent().getExtras().getString("Restrictions:"));
        longitude.setText("Longitude: " + this.getIntent().getExtras().getString("Longitude:"));
        latitude.setText("Latitude: " + this.getIntent().getExtras().getString("Latitude:"));
        address.setText("Address: " + this.getIntent().getExtras().getString("Address:"));
        specialNote.setText("Special Notes: " + this.getIntent().getExtras().getString("Special Notes:"));
        phoneNumber.setText("Phone Number: " + this.getIntent().getExtras().getString("Phone Number:"));

    }

}
