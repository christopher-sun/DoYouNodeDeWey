package com.example.mohammed.doyounodedewey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by derek on 03/06/18.
 */

public class SearchActivity extends AppCompatActivity {

    public EditText query;
    public Button search;
    public CheckBox female;
    public CheckBox male;
    public CheckBox families;
    public CheckBox children;
    public CheckBox youngAdults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        query = findViewById(R.id.query);
        search = findViewById(R.id.search);
        female = findViewById(R.id.female);
        male = findViewById(R.id.male);
        families = findViewById(R.id.families);
        children = findViewById(R.id.children);
        youngAdults = findViewById(R.id.youngAdults);
    }
}
