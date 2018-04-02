package com.example.mohammed.doyounodedewey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by derek on 03/28/18.
 */

public class UserInstance {
    private User currentUser = null;
    private static UserInstance mInstance;
    private static User emptyUser = new User("", null, false);

    public static UserInstance getInstance() {
        if(mInstance == null)
            mInstance = new UserInstance();

        return mInstance;
    }

    private UserInstance() {
        currentUser = emptyUser;
    }

    // retrieve array from anywhere
    public User getCurrentUser() {
        return this.currentUser;
    }

    public void updateCurrentUser(User user) {
        currentUser = user;
    }

    public void clearCurrentUser() {
        currentUser = emptyUser;
    }
}
