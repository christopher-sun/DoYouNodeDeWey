package com.example.mohammed.doyounodedewey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by derek on 03/28/18.
 */

public class ReportPopupActivity extends AppCompatActivity {

    private static String[] numBeds = {"1","2","3","4","5","6","7","8","9","10"};
    Spinner beds;
    Button confirm;
    private User user;
    private Shelter entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = getIntent().getParcelableExtra("USER");
        entry = getIntent().getParcelableExtra("SHELTER");
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
                int numBeds = Integer.parseInt((String) beds.getSelectedItem());
                user.claim(entry, numBeds);
                entry.occupy(numBeds);
                Intent intent = new Intent(ReportPopupActivity.this, DynamicInfoActivity.class);
                intent.putExtra("SHELTER", entry);
                intent.putExtra("Name:", entry.getName());
                intent.putExtra("Capacity:", entry.getCapacity());
                intent.putExtra("Restrictions:", entry.getRestrictions());
                intent.putExtra("Longitude:", "" + entry.getLongitude());
                intent.putExtra("Latitude:", "" + entry.getLatitude());
                intent.putExtra("Address:", entry.getAddress());
                intent.putExtra("Special Notes:", entry.getSpecialNotes());
                intent.putExtra("Phone Number:", entry.getPhoneNumber());
                startActivity(intent);
            }
        });
    }
}
