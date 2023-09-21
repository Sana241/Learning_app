package com.example.learningproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CartFragment extends Fragment {
    private EditText bookInput;
    private TextView titleText,authorText;
    private Button searchButton;
      public CartFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart, container, false);
        bookInput = view.findViewById(R.id.bookInput);
       titleText = view.findViewById(R.id.titleText);
        authorText = view.findViewById(R.id.authorText);
        searchButton=view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String queryString=bookInput.getText().toString();
                new BookFetchAsyncTask(titleText,authorText).execute(queryString);
            }
        });

    return view;
      }
}