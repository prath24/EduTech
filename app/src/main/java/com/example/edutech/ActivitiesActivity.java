package com.example.edutech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.edutech.Adapters.WeeklyActivitiesAdapter;
import com.example.edutech.Models.WeeklyActivity;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;

public class ActivitiesActivity extends AppCompatActivity {

    //hooks
    RecyclerView weekly_activities_rv;
    RelativeLayout dactivity1;

    //adapters
    WeeklyActivitiesAdapter weeklyActivitiesAdapter;

    //lists
    ArrayList<WeeklyActivity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);


        weekly_activities_rv = findViewById(R.id.weekly_activities_rv);
        dactivity1 = findViewById(R.id.dactivity1);
        dactivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActivityDetailsActivity.class));
            }
        });

        //dummy data
        list = new ArrayList<>();
        list.add(new WeeklyActivity("Practise DataStructure & Algorithm","Remaining"));
        list.add(new WeeklyActivity("Study Data Science and Machine Learning","Remaining"));
        list.add(new WeeklyActivity("Solve Trignometery problems","Completed"));
        list.add(new WeeklyActivity("Complete OS Assignment","Remaining"));
        list.add(new WeeklyActivity("Submit Mini Project with documentation","Completed"));

        weeklyActivitiesAdapter = new WeeklyActivitiesAdapter(list);
        weekly_activities_rv.setAdapter(weeklyActivitiesAdapter);

        //bottom navigation
        SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_arrow_back_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_feed_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_home_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_chat_24));
        spaceNavigationView.setCentreButtonColor(getResources().getColor(R.color.darkpurple));
        spaceNavigationView.setActiveCentreButtonIconColor(getResources().getColor(R.color.darkpurple));
        spaceNavigationView.setInActiveCentreButtonIconColor(getResources().getColor(R.color.white));
        spaceNavigationView.setActiveSpaceItemColor(ContextCompat.getColor(this, R.color.black));

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {

            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                if (itemIndex==3) {
                    startActivity(new Intent(getApplicationContext(), ChatActivity.class));
                    overridePendingTransition(0, 0);
                }
                else if (itemIndex==1){
                    startActivity(new Intent(getApplicationContext(),NewsActivity.class));
                    overridePendingTransition(0,0);
                }
                else if (itemIndex == 2){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    overridePendingTransition(0,0);
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(getApplicationContext(), itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
    }
}