package com.example.learningproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class secondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TEXT_STATE = "froyo_text";
    Spinner spinner;
    private TextView textView, dountDescTextView, froyoDescTextView;
    private Button buttonChangeColor, buttonFragments;
    private String[] colorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Toolbar mainToolbar = findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(mainToolbar);

        // Enable the "up" navigation button (back arrow)
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        froyoDescTextView = findViewById(R.id.froyo_desc);
        buttonFragments = findViewById(R.id.fragments);

        //Spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                secondActivity.this, R.array.spinner_item,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        }
        textView = findViewById(R.id.change_color_txt);
        if (savedInstanceState != null) {
            textView.setTextColor(savedInstanceState.getInt("color"));
            froyoDescTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }

        //Change Color of text with button
        buttonChangeColor = findViewById(R.id.color_button);
        buttonChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                String colorName = colorArray[random.nextInt(20)];
                int colorRes = getResources().getIdentifier(colorName,
                        "color", getApplicationContext().getPackageName());
                int colorID = ContextCompat.getColor(secondActivity.this, colorRes);
                textView.setTextColor(colorID);


                froyoDescTextView.setText(R.string.napping_text);
                new AsyncTaskClass(froyoDescTextView).execute();
            }
        });

        dountDescTextView = findViewById(R.id.dount_desc);
        registerForContextMenu(dountDescTextView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.select:
                getToast("you choose selection.");
                return true;
            case R.id.edit:
                getToast("You choose edit.");
                return true;
            case R.id.delete:
                getToast("You choose to delete the text.");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void getToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("color", textView.getCurrentTextColor());
        outState.putString(TEXT_STATE,froyoDescTextView.getText().toString());
    }

    public void getDount(View view) {
        getToast("You cliked Dount Image");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedText = adapterView.getSelectedItem().toString();
        if ((!selectedText.equals(getString(R.string.spinner_prompt)))) {
            getToast("Select: " + selectedText);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void launchFragments(View view) {
        startActivity(new Intent(secondActivity.this, tabActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
                Toast.makeText(this, "cliked Favorites", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(this, "We are running Cafe", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }
}