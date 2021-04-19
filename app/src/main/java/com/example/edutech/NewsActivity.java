package com.example.edutech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.edutech.Adapters.NewsAdapter;
import com.example.edutech.Adapters.TopNewsAdapter;
import com.example.edutech.Models.TopNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    //hooks
    RecyclerView tops_news_rv,news_rv;

    //adapters
    TopNewsAdapter topNewsAdapter;
    NewsAdapter newsAdapter;

    //lists
    ArrayList<TopNews> top_news_list;

    //database ref
    DatabaseReference topNewRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.light_grey));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // getWindow().setNavigationBarColor(getResources().getColor(android.R.color.transparent));
        setContentView(R.layout.activity_news);


        //database refs
        topNewRef = FirebaseDatabase.getInstance().getReference().child("TopNewsRef");

        //setting all news
        news_rv = findViewById(R.id.news_list);

        //setting tops news slider
        tops_news_rv = findViewById(R.id.tops_news_list);
        topNewRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    top_news_list = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()){
                        top_news_list.add(ds.getValue(TopNews.class));
                    }
                    topNewsAdapter = new TopNewsAdapter(top_news_list);
                    tops_news_rv.setAdapter(topNewsAdapter);
                    newsAdapter = new NewsAdapter(top_news_list);
                    news_rv.setAdapter(newsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        //bottom navigation
         SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_arrow_back_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_chat_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_dashboard_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_home_24));
        spaceNavigationView.setCentreButtonColor(getResources().getColor(R.color.darkpurple));
        spaceNavigationView.setActiveCentreButtonIconColor(getResources().getColor(R.color.darkpurple));
        spaceNavigationView.setInActiveCentreButtonIconColor(getResources().getColor(R.color.white));
        spaceNavigationView.setActiveSpaceItemColor(ContextCompat.getColor(this, R.color.black));
        //spaceNavigationView.changeCurrentItem(3);---for selecting current item
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {

            }
            @Override
            public void onItemClick(int itemIndex, String itemName) {
                if (itemIndex==3){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    overridePendingTransition(0,0);
                }
                else if (itemIndex==1){
                    startActivity(new Intent(getApplicationContext(),ChatActivity.class));
                    overridePendingTransition(0,0);
                }
                else if (itemIndex == 2){
                    startActivity(new Intent(getApplicationContext(),ActivitiesActivity.class));
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