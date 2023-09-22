package com.example.learningproject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
int noTabs;
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager,int noTabs) {
        super(fragmentManager);
        this.noTabs = noTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ScoreKeeperFragment();
            case 2:
                return new BookSearchFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noTabs;
    }
}
