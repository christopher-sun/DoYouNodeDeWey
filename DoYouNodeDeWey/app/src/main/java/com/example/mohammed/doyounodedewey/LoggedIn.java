package com.example.mohammed.doyounodedewey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

public class LoggedIn extends AppCompatActivity {

    public Button logoutBut;
    public Button shelterBut;
    public Button searchBut;
    public User user;
    public int userIndex;

    public void init() {
        userIndex = getIntent().getExtras().getInt("USER INDEX");
        user = UserList.getInstance().getUserList().get(userIndex);
        logoutBut = findViewById(R.id.logoutBut);
        logoutBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(LoggedIn.this, LoginActivity.class);
                startActivity(toy);
            }
            });
        shelterBut = findViewById(R.id.shelterBut);
        shelterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(LoggedIn.this,
                        ShelterInfoMainScreenActivity.class);
                toy.putExtra("USER INDEX", userIndex);
                startActivity(toy);
            }
        });
        searchBut = findViewById(R.id.searchBut);
        searchBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(LoggedIn.this,
                        SearchActivity.class);
                toy.putExtra("USER INDEX", userIndex);
                startActivity(toy);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readShelterData();
        setContentView(R.layout.activity_logged_in);
        init();
    }

    private void readShelterData() {
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
                    temp[2] = "0";
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
                ShelterList.getInstance().addToShelterList(newShelter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
