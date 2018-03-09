package com.example.mohammed.doyounodedewey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    public CheckBox anyone;

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
        anyone = findViewById(R.id.anyone);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(SearchActivity.this, SearchResultActivity.class);
                toy.putExtra("QUERY", query.getText().toString());
                toy.putExtra("FEMALE", female.isChecked());
                toy.putExtra("MALE", male.isChecked());
                toy.putExtra("FAMILIES", families.isChecked());
                toy.putExtra("CHILDREN", children.isChecked());
                toy.putExtra("YOUNDADULTS", youngAdults.isChecked());
                toy.putExtra("ANYONE", anyone.isChecked());
                startActivity(toy);
            }
        });
    }
}
