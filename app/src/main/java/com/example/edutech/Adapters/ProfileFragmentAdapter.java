package com.example.edutech.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.edutech.Fragments.PerformanceFragment;
import com.example.edutech.Fragments.ResumeFragment;


public class ProfileFragmentAdapter extends FragmentPagerAdapter {
    public ProfileFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ResumeFragment resumeFragment = new ResumeFragment();
                return resumeFragment;
            case 1:
                PerformanceFragment performanceFragment = new PerformanceFragment();
                return performanceFragment;

            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    //title  for fragments
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Resume";
            case 1:
                return "Performance";
            default:
                return null;
        }
    }
}
