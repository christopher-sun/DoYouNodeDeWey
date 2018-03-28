package com.example.mohammed.doyounodedewey;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    public Button searchAgain;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = getIntent().getParcelableExtra("USER");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        searchAgain = findViewById(R.id.searchAgain);
        searchAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(SearchResultActivity.this, SearchActivity.class);
                toy.putExtra("USER", user);
                startActivity(toy);
            }
        });

        searchResultsView = findViewById(R.id.searchResultsList);
        ArrayAdapter<Shelter> shelterAdapter = new ArrayAdapter<Shelter>(this,
                android.R.layout.simple_list_item_1, readShelterData(
                        getIntent().getExtras().getString("QUERY"),
                        getIntent().getExtras().getBoolean("FEMALE"),
                        getIntent().getExtras().getBoolean("MALE"),
                        getIntent().getExtras().getBoolean("FAMILIES"),
                        getIntent().getExtras().getBoolean("CHILDREN"),
                        getIntent().getExtras().getBoolean("YOUNGADULTS"),
                        getIntent().getExtras().getBoolean("ANYONE")
        ));
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
                intent.putExtra("USER", user);

                startActivity(intent);
            }
        });
    }

    private List<Shelter> readShelterData(String query, boolean female, boolean male, boolean families, boolean children, boolean youngAdults, boolean anyone) {
        List<Shelter> shelterList = new ArrayList<>();
        InputStream is = getResources().openRawResource(R.raw.shelter_data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        try {
            String line = reader.readLine();
            while( (line = reader.readLine()) != null) {
                boolean add = true;

                String[] temp = line.split(",");

                String name = temp[1].replace('%', ',');
                if (query != null && query.length() != 0) {
                    if (!name.contains(query)) {
                        add = false;
                    }
                }
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
                if (female && !restrictions.contains("Women")) {
                    add = false;
                }
                if (male && !restrictions.contains("Men")) {
                    add = false;
                }
                if (families && !restrictions.contains("Families w/")) {
                    add = false;
                }
                if (children && !restrictions.contains("Children")) {
                    add = false;
                }
                if (youngAdults && !restrictions.contains("Young adults")) {
                    add = false;
                }
                if (anyone && !restrictions.contains("Anyone")) {
                    add = false;
                }

                if (add) {
                    Shelter newShelter = new Shelter(name, capacity, restrictions, longitude, latitude,
                            address, specialNote, phoneNumber);
                    shelterList.add(newShelter);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shelterList;
    }
}