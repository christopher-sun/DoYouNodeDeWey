package com.example.mohammed.doyounodedewey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    int shelterIndex;
    int userIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userIndex = getIntent().getExtras().getInt("USER INDEX");
        user = UserList.getInstance().getUserList().get(userIndex);
        shelterIndex = getIntent().getExtras().getInt("SHELTER INDEX");
        shelter = ShelterList.getInstance().getShelterList().get(shelterIndex);
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


        //Setting the text to be displayed
        name.setText("Name: " + this.getIntent().getExtras().getString("Name:"));
        capacity.setText("Current Capacity: " + this.getIntent().getExtras().getString("Capacity:"));
        restrictions.setText("Restrictions: " + this.getIntent().getExtras().getString("Restrictions:"));
        longitude.setText("Longitude: " + this.getIntent().getExtras().getString("Longitude:"));
        latitude.setText("Latitude: " + this.getIntent().getExtras().getString("Latitude:"));
        address.setText("Address: " + this.getIntent().getExtras().getString("Address:"));
        specialNote.setText("Special Notes: " + this.getIntent().getExtras().getString("Special Notes:"));
        phoneNumber.setText("Phone Number: " + this.getIntent().getExtras().getString("Phone Number:"));

        claimABed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getClaimedSpace() == null) {
                    Intent toy = new Intent(DynamicInfoActivity.this, ReportPopupActivity.class);
                    toy.putExtra("USER INDEX", userIndex);
                    toy.putExtra("SHELTER INDEX", shelterIndex);
                    startActivity(toy);
                } else if (user.getClaimedSpace().getName().equals(name.getText().toString())){
                    Log.i("AAA", user.getClaimedSpace().getName());
                    shelter.vacate(user.getNumClaimed());
                    user.release();
                    Intent intent = getIntent();
                    intent.putExtra("USER INDEX", userIndex);
                    intent.putExtra("SHELTER INDEX", shelterIndex);
                    finish();
                    startActivity(intent);
                } else {
                    Log.i("AAA", user.getClaimedSpace().getName());
                    Log.i("AA", name.getText().toString());
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        userIndex = getIntent().getExtras().getInt("USER INDEX");
        user = UserList.getInstance().getUserList().get(userIndex);
        shelterIndex = getIntent().getExtras().getInt("SHELTER INDEX");
        shelter = ShelterList.getInstance().getShelterList().get(shelterIndex);
        //Log.i("AAAAA", shelter.getCapacity());
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


        //Setting the text to be displayed
        name.setText("Name: " + this.getIntent().getExtras().getString("Name:"));
        capacity.setText("Current Capacity: " + shelter.getCapacity());
        restrictions.setText("Restrictions: " + this.getIntent().getExtras().getString("Restrictions:"));
        longitude.setText("Longitude: " + this.getIntent().getExtras().getString("Longitude:"));
        latitude.setText("Latitude: " + this.getIntent().getExtras().getString("Latitude:"));
        address.setText("Address: " + this.getIntent().getExtras().getString("Address:"));
        specialNote.setText("Special Notes: " + this.getIntent().getExtras().getString("Special Notes:"));
        phoneNumber.setText("Phone Number: " + this.getIntent().getExtras().getString("Phone Number:"));

        claimABed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getClaimedShelterIndex() == -1) {
                    Intent toy = new Intent(DynamicInfoActivity.this, ReportPopupActivity.class);
                    toy.putExtra("USER INDEX", userIndex);
                    toy.putExtra("SHELTER INDEX", shelterIndex);
                    startActivity(toy);
                } else if (user.getClaimedShelterIndex() == shelterIndex){
                    Log.i("BB", user.getClaimedSpace().getName());
                    Log.i("BBB", name.getText().toString());
                    Log.i("BBBB", shelter.getCapacity());
                    shelter.vacate(user.getNumClaimed());
                    Log.i("BBBBB", shelter.getCapacity());
                    Log.i("BBBBBB", String.valueOf(user.getClaimedShelterIndex()));
                    user.release();
                    Intent intent = getIntent();
                    intent.putExtra("USER INDEX", userIndex);
                    intent.putExtra("SHELTER INDEX", shelterIndex);
                    finish();
                    startActivity(intent);
                } else {
                    Log.i("AAA", user.getClaimedSpace().getName());
                    Log.i("AA", name.getText().toString());
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(DynamicInfoActivity.this, ShelterInfoMainScreenActivity.class);
        //intent.putExtra("SHELTER INDEX", shelterIndex);
        intent.putExtra("USER INDEX", userIndex);

        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userListRef = database.getReference("UserList");
        DatabaseReference shelterListRef = database.getReference("ShelterList");

        userListRef.setValue(UserList.getInstance().getUserList());
        shelterListRef.setValue(ShelterList.getInstance().getShelterList());

    }

}
