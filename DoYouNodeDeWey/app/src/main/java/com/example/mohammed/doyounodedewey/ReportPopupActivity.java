package com.example.mohammed.doyounodedewey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by derek on 03/28/18.
 */

public class ReportPopupActivity extends AppCompatActivity {

    private static String[] numBeds = {"1","2","3","4","5","6","7","8","9","10"};
    Spinner beds;
    Button confirm;
    private User user;
    private Shelter entry;
    private int shelterIndex;
    private int userIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userIndex = getIntent().getExtras().getInt("USER INDEX");
        user = UserList.getInstance().getUserList().get(userIndex);
        shelterIndex = getIntent().getExtras().getInt("SHELTER INDEX");
        entry = ShelterList.getInstance().getShelterList().get(shelterIndex);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_report_popup);

        beds = findViewById(R.id.beds);
        confirm = findViewById(R.id.confirmClaim);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, numBeds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        beds.setAdapter(adapter);


            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.parseInt((String) beds.getSelectedItem()) <= Integer.parseInt(entry.getCapacity())) {
                        int numBeds = Integer.parseInt((String) beds.getSelectedItem());
                        user.claim(entry, numBeds, shelterIndex);
                        entry.occupy(numBeds);
                        Intent intent = new Intent(ReportPopupActivity.this, DynamicInfoActivity.class);
                        intent.putExtra("SHELTER INDEX", shelterIndex);
                        intent.putExtra("Name:", entry.getName());
                        intent.putExtra("Capacity:", entry.getCapacity());
                        intent.putExtra("Restrictions:", entry.getRestrictions());
                        intent.putExtra("Longitude:", "" + entry.getLongitude());
                        intent.putExtra("Latitude:", "" + entry.getLatitude());
                        intent.putExtra("Address:", entry.getAddress());
                        intent.putExtra("Special Notes:", entry.getSpecialNotes());
                        intent.putExtra("Phone Number:", entry.getPhoneNumber());
                        intent.putExtra("USER INDEX", userIndex);
                        startActivity(intent);
                    }
                }
            });

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