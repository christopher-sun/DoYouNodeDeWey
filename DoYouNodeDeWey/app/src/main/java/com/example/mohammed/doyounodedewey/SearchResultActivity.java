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

public class SearchResultActivity extends AppCompatActivity {

    public ListView searchResultsView;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        searchResultsView = findViewById(R.id.searchResultsList);
        ArrayAdapter<Shelter> shelterAdapter = new ArrayAdapter<Shelter>(this,
                android.R.layout.simple_list_item_1, readShelterData());
        searchResultsView.setAdapter(shelterAdapter);
        searchResultsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Shelter entry = (Shelter) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(context, DynamicInfoActivity.class);
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

    private List<Shelter> readShelterData() {
        List<Shelter> shelterList = new ArrayList<>();
        InputStream is = getResources().openRawResource(R.raw.shelter_data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        try {
            String line = reader.readLine();
            while( (line = reader.readLine()) != null) {
                String[] temp = line.split(",");

                String name = temp[1].replace('%', ',');
                if (temp[2].length() == 0) {
                    temp[2] = "Unknown";
                }
                String capacity = temp[2].replace('%', ',');
                String restrictions = temp[3].replace('%', ',');
                double longitude = Double.parseDouble(temp[4]);
                double latitude = Double.parseDouble(temp[5]);
                String address = temp[6].replace('%', ',');
                String specialNote = temp[7].replace('%', ',');
                String phoneNumber = temp[8].replace('%', ',');

                Shelter newShelter = new Shelter(name, capacity, restrictions, longitude, latitude,
                        address, specialNote, phoneNumber);
                shelterList.add(newShelter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shelterList;
    }
}