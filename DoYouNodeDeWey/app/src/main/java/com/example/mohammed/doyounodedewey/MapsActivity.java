package com.example.mohammed.doyounodedewey;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.List;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //readShelterData();

        //ArrayList<Shelter> shelterList = ShelterList.getInstance().getShelterList();

        List<Shelter> shelterList = SearchResultActivity.filteredList;

        if (shelterList.size() == 0) {
            Toast.makeText(this, "List of Shelters Empty", Toast.LENGTH_SHORT).show();
        } else {
            for (Shelter shelter : shelterList) {
                LatLng currLocation = new LatLng(shelter.getLatitude(), shelter.getLongitude());
                Marker currMarker = mMap.addMarker(new MarkerOptions().position(currLocation).title(shelter.getName()).snippet("Phone Number: " + shelter.getPhoneNumber()));
                currMarker.setTag(shelter.getUniqueKey());

                mMap.moveCamera(CameraUpdateFactory.newLatLng(currLocation));
            }
        }


    }

//    private void readShelterData() {
//        InputStream is = getResources().openRawResource(R.raw.shelter_data);
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(is, Charset.forName("UTF-8"))
//        );
//
//        try {
//            String line = reader.readLine();
//            while( (line = reader.readLine()) != null) {
//                String[] temp = line.split(",");
//
//                String name = temp[1].replace('%', ',');
//                if (temp[2].length() == 0) {
//                    temp[2] = "0";
//                }
//                String maxCapacity = temp[2].replace('%', ',');
//                String capacity = temp[3].replace('%', ',');
//                String restrictions = temp[4].replace('%', ',');
//                double longitude = Double.parseDouble(temp[5]);
//                double latitude = Double.parseDouble(temp[6]);
//                String address = temp[7].replace('%', ',');
//                String specialNote = temp[8].replace('%', ',');
//                String phoneNumber = temp[9].replace('%', ',');
//
//                Shelter newShelter = new Shelter(name, maxCapacity, capacity, restrictions, longitude, latitude,
//                        address, specialNote, phoneNumber);
//                ShelterList.getInstance().addToShelterList(newShelter);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
