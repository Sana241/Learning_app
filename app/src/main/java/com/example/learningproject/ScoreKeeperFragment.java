package com.example.learningproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreKeeperFragment extends Fragment {
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";
    ImageView imgT1Minus, imgT2Minus, imgT1Plus, imgT2Plus;
    private int Score1;
    private int Score2;
    private TextView scoreText1;
    private TextView scoreText2;

    public ScoreKeeperFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scorekeeper, container, false);
        scoreText1 = view.findViewById(R.id.score_1);
        scoreText2 = view.findViewById(R.id.score_2);
        imgT1Minus = view.findViewById(R.id.decreaseTeam1);
        imgT2Minus = view.findViewById(R.id.decreaseTeam2);
        imgT1Plus = view.findViewById(R.id.increaseTeam1);
        imgT2Plus = view.findViewById(R.id.increaseTeam2);
        imgT1Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Score1++;
                scoreText1.setText(String.valueOf(Score1));
            }
        });
        imgT2Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Score2++;
                scoreText2.setText(String.valueOf(Score2));
            }
        });
        imgT1Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Score1--;
                scoreText1.setText(String.valueOf(Score1));
            }
        });
        imgT2Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Score2--;
                scoreText2.setText(String.valueOf(Score2));
            }
        });
        if (savedInstanceState != null) {
            scoreText1.setText(String.valueOf(
                    savedInstanceState.getInt(STATE_SCORE_1)));
            scoreText2.setText(String.valueOf(
                    savedInstanceState.getInt(STATE_SCORE_2)));
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(STATE_SCORE_1, Score1);
        outState.putInt(STATE_SCORE_2, Score2);
        super.onSaveInstanceState(outState);
    }
}