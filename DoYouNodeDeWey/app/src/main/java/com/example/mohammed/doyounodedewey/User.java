package com.example.mohammed.doyounodedewey;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by derek on 03/27/18.
 */

public class User implements Parcelable {
    private String username;
    private String password;
    private final boolean admin;
    private boolean locked;
    private String claimedSpace;
    private int numClaimed;

    public User(String user, String pass, boolean clickedAdmin) {
        username = user;
        password = pass;
        locked = false;
        claimedSpace = "";
        numClaimed = 0;
        admin = clickedAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getClaimedSpace() {
        return claimedSpace;
    }

    public int getNumClaimed() {
        return numClaimed;
    }

    public void setUsername(String user) {
        username = user;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }

    public void claim(String shelter, int beds) {
        claimedSpace = shelter;
        numClaimed = beds;
    }

    public void release() {
        claimedSpace = "";
        numClaimed = 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeByte(this.admin ? (byte) 1 : (byte) 0);
        dest.writeByte(this.locked ? (byte) 1 : (byte) 0);
        dest.writeString(this.claimedSpace);
        dest.writeInt(this.numClaimed);
    }

    protected User(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.admin = in.readByte() != 0;
        this.locked = in.readByte() != 0;
        this.claimedSpace = in.readString();
        this.numClaimed = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}