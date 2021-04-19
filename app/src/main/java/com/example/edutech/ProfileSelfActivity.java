package com.example.edutech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.edutech.Adapters.ProfileFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class ProfileSelfActivity extends AppCompatActivity {

    //hooks
    TabLayout tabLayout;
    ViewPager viewPager;

    //adapters
    ProfileFragmentAdapter profileFragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_self);

        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        profileFragmentAdapter = new ProfileFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(profileFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}