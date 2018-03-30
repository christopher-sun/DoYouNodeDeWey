package com.example.mohammed.doyounodedewey;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ShelterInfoMainScreenActivity extends AppCompatActivity {

    public ListView shelterListView;
    final Context context = this;
    public User user;
    public int userIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userIndex = getIntent().getExtras().getInt("USER INDEX");
        user = UserList.getInstance().getUserList().get(userIndex);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_info_main_screen);
        shelterListView = findViewById(R.id.shelterListView);
        ArrayAdapter<Shelter> shelterAdapter = new ArrayAdapter<Shelter>(this,
                android.R.layout.simple_list_item_1, ShelterList.getInstance().getShelterList());
        shelterListView.setAdapter(shelterAdapter);
        shelterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Shelter entry = (Shelter) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(context, DynamicInfoActivity.class);
                intent.putExtra("SHELTER INDEX", i);
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
        });
    }
}
