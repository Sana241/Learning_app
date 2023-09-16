package com.example.learningproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.LinkedList;

public class homeFragment extends Fragment {

    public final LinkedList<String> linkedList = new LinkedList<>();
    Button fragmentButton;
    RecyclerView recyclerView;

    public homeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,
                container, false);
        for (int i = 1; i <= 20; i++) {
            linkedList.add("Recycle item " + i);
        }
        fragmentButton = view.findViewById(R.id.home_fragment_btn);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerAdapter recyclerAdapter=new recyclerAdapter(getActivity(),linkedList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), secondActivity.class));
            }
        });

        return view;
    }
}