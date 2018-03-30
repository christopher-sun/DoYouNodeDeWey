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
    public int userIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userIndex = getIntent().getExtras().getInt("USER INDEX");
        user = UserList.getInstance().getUserList().get(userIndex);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        searchAgain = findViewById(R.id.searchAgain);
        searchAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(SearchResultActivity.this, SearchActivity.class);
                toy.putExtra("USER INDEX", userIndex);
                startActivity(toy);
            }
        });

        searchResultsView = findViewById(R.id.searchResultsList);
        ArrayAdapter<Shelter> shelterAdapter = new ArrayAdapter<Shelter>(this,
                android.R.layout.simple_list_item_1, filter(
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
                intent.putExtra("USER INDEX", userIndex);

                startActivity(intent);
            }
        });
    }

    private List<Shelter> filter(String query, boolean female, boolean male, boolean families, boolean children, boolean youngAdults, boolean anyone) {
        List<Shelter> shelterList = new ArrayList<>();
        for (Shelter shelter : ShelterList.getInstance().getShelterList()) {
            boolean add = true;
            if (female && !shelter.getRestrictions().contains("Women")) {
                add = false;
            }
            if (male && !shelter.getRestrictions().contains("Men")) {
                add = false;
            }
            if (families && !shelter.getRestrictions().contains("Families /w")) {
                add = false;
            }
            if (children && !shelter.getRestrictions().contains("Children")) {
                add = false;
            }
            if (youngAdults && !shelter.getRestrictions().contains("Young adults")) {
                add = false;
            }
            if (anyone && !shelter.getRestrictions().contains("Anyone")) {
                add = false;
            }
            if (add) {
                shelterList.add(shelter);
            }
        }
        return shelterList;
    }
}