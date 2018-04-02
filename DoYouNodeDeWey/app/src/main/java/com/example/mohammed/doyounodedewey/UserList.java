package com.example.mohammed.doyounodedewey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by derek on 03/28/18.
 */

public class UserList {
    private ArrayList<User> userList = null;
    private static UserList mInstance;

    public static UserList getInstance() {
        if(mInstance == null)
            mInstance = new UserList();

        return mInstance;
    }

    public static UserList getInstance(Map<String, String> users, Map<String, String> pass, Map<String, Boolean> admin, Map<String, Boolean> locked, Map<String, Integer> numClaimed, Map<String, Integer> shelterIndex) {
        if(mInstance == null)
            mInstance = new UserList(users, pass, admin, locked, numClaimed, shelterIndex);

        return mInstance;
    }

    private UserList() {
        userList = new ArrayList<User>();
    }

    private UserList(Map<String, String> users, Map<String, String> pass, Map<String, Boolean> admin, Map<String, Boolean> locked, Map<String, Integer> numClaimed, Map<String, Integer> shelterIndex) {
        for (int i = 0; i < users.size(); i++) {
            String userKey = "user" + i;
            String passKey = "pass" + i;
            String adminKey = "admin" + i;
            String lockedKey = "locked" + i;
            String numClaimedKey = "numClaimed" + i;
            String shelterIndexKey = "shelterIndex" + i;

            userList.add(new User(users.get(userKey), pass.get(passKey), admin.get(adminKey), numClaimed.get(numClaimedKey), shelterIndex.get(shelterIndexKey)));
        }
    }
    // retrieve array from anywhere
    public ArrayList<User> getUserList() {
        return this.userList;
    }
    //Add element to array
    public void addToUserList(User user) {
        userList.add(user);
    }
}
