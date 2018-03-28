package com.example.mohammed.doyounodedewey;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;


public class RegisterActivity extends AppCompatActivity {

    private EditText mPasswordView;
    private EditText mUsernameView;
    private EditText mNameView;

    private UserRegisterTask mAuthTask = null;

    /**
     * Instance of FirebaseAuth
     */
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    private boolean toReturn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        File userpass = new File("userpass.txt");
//        if (!userpass.exists()) {
//            try {
//                userpass.createNewFile(); //creates file if not already made
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        //Initialize FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

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
                attemptRegister();
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
                    attemptRegister();
                }
        });
    }

    /**
     * Attempts to register the account specified by the input form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual registration attempt is made.
     */
    private void attemptRegister() {
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
            mAuthTask = new UserRegisterTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isUsernameValid(String username) {
        //TODO: Replace this with your own logic
        return username.length() > 3;
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

        UserRegisterTask(String email, String password) {
            mUsername = email;
            mPassword = password;
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

            //working one before
//            LoginActivity.getDummyCredentials().add(mUsername+":"+mPassword);

//            try {
//                BufferedWriter out = new BufferedWriter(new FileWriter("userpass.txt"));
//                out.write(mUsername+":"+mPassword);  //Replace with the string
//                //you are trying to write
//                out.close();
//            }
//            catch (IOException e)
//            {
//                System.out.println("Exception ");
//
//            }

//            try (PrintWriter out = new PrintWriter("userpass.txt")) {
//                out.println(mUsername+":"+mPassword);
//            } catch (FileNotFoundException e) {
//                System.out.println("Bruh");
//            }

//            FileUtils.writeStringToFile(new File("test.txt"), "Hello File");

//            URL path = LoginActivity.class.getResource("userpass.txt");
//            try (BufferedWriter out = new BufferedWriter(
//                    new FileWriter(path.getFile()))) {
//                out.write(mUsername+":"+mPassword);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

//            URL path = LoginActivity.class.getResource("userpass.txt");
//                File f = new File(path.getFile());
//                FileReader fileReader = new FileReader(f);
//                BufferedReader bufferedReader = new BufferedReader(fileReader);
////            FileReader fileReader = new FileReader(new File("userpass.txt"));
////            BufferedReader bufferedReader = new BufferedReader(fileReader);
////            StringBuffer stringBuffer = new StringBuffer();

//        writeToFile(mUsername+":"+mPassword, getApplicationContext());

//            File path = getApplicationContext().getFilesDir();
//            File file = new File(path, "userpass.txt");
//            FileOutputStream stream = null;
//            try {
//                stream = new FileOutputStream(file);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            try {
//                String stuff = mUsername;
//                stream.write(stuff.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    stream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

//            createAccount(mUsername, mPassword);
            mAuth.createUserWithEmailAndPassword(mUsername, mPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
//                                updateUI(user);
                                toReturn = true;
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
//                                updateUI(null);
                                toReturn = false;
                            }

                            // ...
                        }
                    });



//            return true;
            return toReturn;
        }

//        protected void createAccount(String email, String password) {
//            mAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with the signed-in user's information
//                                Log.d(TAG, "createUserWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
////                                updateUI(user);
//                            } else {
//                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
////                                updateUI(null);
//                            }
//
//                            // ...
//                        }
//                    });
//        }

//        private void writeToFile(String data, Context context) {
//            try {
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("userpass.txt", Context.MODE_PRIVATE));
//                outputStreamWriter.write(data);
//                outputStreamWriter.close();
//            }
//            catch (IOException e) {
//                Log.e("Exception", "File write failed: " + e.toString());
//            }
//        }

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
