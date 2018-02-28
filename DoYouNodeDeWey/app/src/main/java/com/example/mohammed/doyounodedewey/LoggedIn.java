package com.example.mohammed.doyounodedewey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class LoggedIn extends AppCompatActivity {

    public Button logoutBut;
    public Button shelterBut;

    public void init() {
        logoutBut = findViewById(R.id.logoutBut);
        logoutBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(LoggedIn.this, LoginActivity.class);
                startActivity(toy);
            }
            });
        shelterBut = findViewById(R.id.shelterBut);
        shelterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(LoggedIn.this,
                        ShelterInfoMainScreenActivity.class);
                startActivity(toy);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        init();
    }
}
