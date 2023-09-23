package com.example.learningproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreKeeperFragment extends Fragment {
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";
    ImageView imgT1Minus, imgT2Minus, imgT1Plus, imgT2Plus;
    private int Score1, Score2;
    private TextView scoreText1, scoreText2;
    private Button resetScore;
    private SharedPreferences sharedPreferences;
    private String sharedPrefFile = BuildConfig.APPLICATION_ID + ".fileName";

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
        resetScore = view.findViewById(R.id.score_reset);
        sharedPreferences = requireActivity()
                .getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);

        Score1 = sharedPreferences.getInt("STATE_SCORE_1", 0);
        Score2 = sharedPreferences.getInt("STATE_SCORE_2", 0);
        scoreText1.setText(String.valueOf(Score1));
        scoreText2.setText(String.valueOf(Score2));

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

        resetScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();
                scoreText1.setText(String.valueOf(0));
                scoreText2.setText(String.valueOf(0));
                Score2=0;
                Score1=0;
            }
        });


        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("STATE_SCORE_1", Score1);
        editor.putInt("STATE_SCORE_2", Score2);
        editor.apply();
    }
}