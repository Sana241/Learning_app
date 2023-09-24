package com.example.learningproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(android.R.id.content,
                        new AppSettingFragment())
                .commit();
    }

}

