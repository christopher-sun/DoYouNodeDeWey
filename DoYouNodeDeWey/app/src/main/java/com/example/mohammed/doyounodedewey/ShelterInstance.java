package com.example.mohammed.doyounodedewey;

import java.util.ArrayList;

/**
 * Created by derek on 03/28/18.
 */

public class ShelterInstance {
    private Shelter currentShelter = null;
    private static ShelterInstance mInstance;
    private static Shelter emptyShelter = new Shelter(null, null, null, null, 0, 0, null, null, null);

    public static ShelterInstance getInstance() {
        if(mInstance == null)
            mInstance = new ShelterInstance();

        return mInstance;
    }

    private ShelterInstance() {
        currentShelter = emptyShelter;
    }
    // retrieve array from anywhere
    public Shelter getCurrentShelter() {
        return this.currentShelter;
    }
    //Add element to array
    //public void addToShelterList(Shelter shelter) {
        //shelterList.add(shelter);
    //}

    public void clearCurrentShelter() {
        currentShelter = emptyShelter;
    }

    public void updateCurrentShelter(Shelter shelter) {
        currentShelter = shelter;
    }
}
