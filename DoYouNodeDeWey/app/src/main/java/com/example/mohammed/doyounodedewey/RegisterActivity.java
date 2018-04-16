package com.example.mohammed.doyounodedewey;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText mPasswordView;
    private EditText mUsernameView;
    private EditText mNameView;

    private UserRegisterTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mPasswordView = (EditText) findViewById(R.id.registerPassword);
        mUsernameView = (EditText) findViewById(R.id.registerUsername);
        mNameView = (EditText) findViewById(R.id.registerName);

        //Cancel Button
        Button mRegisterCancelButton = (Button) findViewById(R.id.registerCancelButton);
        mRegisterCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        //Register User Button
        Button mRegisterUserButton = (Button) findViewById(R.id.registerUserButton);
        mRegisterUserButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//            }
            @Override
            public void onClick(View view) {
                attemptRegister(false);
            }
        });

        //Register Admin Button
        Button mRegisterAdminButton = (Button) findViewById(R.id.registerAdminButton);
        mRegisterAdminButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//            }
                @Override
                public void onClick(View view) {
                    attemptRegister(true);
                }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userListRef = database.getReference("UserList");
        DatabaseReference shelterListRef = database.getReference("ShelterList");

        userListRef.setValue(UserList.getInstance().getUserList());
        shelterListRef.setValue(ShelterList.getInstance().getShelterList());

    }

    /**
     * Attempts to register the account specified by the input form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual registration attempt is made.
     */
    private void attemptRegister(boolean admin) {
        if (mAuthTask != null) {
            return;
        }

//         Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!isUsernameValid(email)) {
            mUsernameView.setError(getString(R.string.error_invalid_email));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
//            showProgress(true);
            mAuthTask = new UserRegisterTask(email, password, admin);
            mAuthTask.execute((Void) null);
        }
    }

    public boolean isUsernameValid(String username) {
//        if (email.contains("@") && email.contains(".")) {
//            return email.length() > 5;
//        } else {
//            return false;
//        }
        if (Character.isUpperCase(username.charAt(0))) {
            return username.length() > 5;
        } else {
            return false;
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 3;
    }

    /**
     * Represents an asynchronous registration task used to authenticate
     * the user.
     */
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUsername;
        private final String mPassword;
        private final boolean admin;

        UserRegisterTask(String email, String password, boolean isAdmin) {
            mUsername = email;
            mPassword = password;
            admin = isAdmin;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(0);
            } catch (InterruptedException e) {
                return false;
            }

//            for (String credential : LoginActivity.getDummyCredentials()) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mUsername)) {
//                    // Account exists, return true if the password matches.
//                    return !pieces[1].equals(mPassword);
//                } else {
//                    LoginActivity.getDummyCredentials().add(mUsername+":"+mPassword);
//                }
//            }

            UserList.getInstance().addToUserList( new User(mUsername, mPassword, admin));
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
//            showProgress(false);

            if (success) {
                Intent toy = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(toy);
                //finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
//            showProgress(false);
        }

//        void logInPage() {
//
//        }
    }
}
