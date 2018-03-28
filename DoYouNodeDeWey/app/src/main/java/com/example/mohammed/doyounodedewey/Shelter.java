package com.example.mohammed.doyounodedewey;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shawn Brisky on 2/26/2018.
 */

class Shelter implements Parcelable {
    private int uniqueKey;
    private String name;
    private String capacity;
    private String maxCapacity;
    private String restrictions;
    private double longitude;
    private double latitude;
    private String address;
    private String specialNotes;
    private String phoneNumber;

    public Shelter(String name, String capacity, String restrictions, double longitude,
                   double latitude, String address, String specialNotes, String phoneNumber) {
        this.name = name;
        this.capacity = capacity;
        this.maxCapacity = capacity;
        this.restrictions = restrictions;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.specialNotes = specialNotes;
        this.phoneNumber = phoneNumber;
    }

    public int getUniqueKey() {
        return uniqueKey;
    }

    public String getName() {
        return name;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void occupy(int beds) {
        capacity = String.valueOf(Integer.parseInt(capacity) - beds);
    }

    public void vacate(int beds) {
        capacity = String.valueOf(Integer.parseInt(capacity) + beds);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uniqueKey);
        dest.writeString(this.name);
        dest.writeString(this.capacity);
        dest.writeString(this.restrictions);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeString(this.address);
        dest.writeString(this.specialNotes);
        dest.writeString(this.phoneNumber);
    }

    protected Shelter(Parcel in) {
        this.uniqueKey = in.readInt();
        this.name = in.readString();
        this.capacity = in.readString();
        this.restrictions = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.address = in.readString();
        this.specialNotes = in.readString();
        this.phoneNumber = in.readString();
    }

    public static final Parcelable.Creator<Shelter> CREATOR = new Parcelable.Creator<Shelter>() {
        @Override
        public Shelter createFromParcel(Parcel source) {
            return new Shelter(source);
        }

        @Override
        public Shelter[] newArray(int size) {
            return new Shelter[size];
        }
    };
}
