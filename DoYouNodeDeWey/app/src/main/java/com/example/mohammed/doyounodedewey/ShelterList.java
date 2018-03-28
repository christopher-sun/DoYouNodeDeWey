package com.example.mohammed.doyounodedewey;

import java.util.ArrayList;

/**
 * Created by derek on 03/28/18.
 */

public class ShelterList {
    private ArrayList<Shelter> shelterList = null;
    private static ShelterList mInstance;

    public static ShelterList getInstance() {
        if(mInstance == null)
            mInstance = new ShelterList();

        return mInstance;
    }

    private ShelterList() {
        shelterList = new ArrayList<Shelter>();
    }
    // retrieve array from anywhere
    public ArrayList<Shelter> getShelterList() {
        return this.shelterList;
    }
    //Add element to array
    public void addToShelterList(Shelter shelter) {
        shelterList.add(shelter);
    }
}
