package com.example.edutech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.edutech.Adapters.HomeImagesAdapter;
import com.example.edutech.Models.HomeImages;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    //hooks
    BottomNavigationView bottomNavigationView ;
    CircleImageView profile_btn;
    ViewPager2 viewPagerImageSlider;

    //adapters
    HomeImagesAdapter homeImagesAdapter;

    //handler for view pager
    Handler sliderhandler = new Handler();

    //database ref
    DatabaseReference home_images_ref;

    //lists
    ArrayList<HomeImages> imagelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.light_grey));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
       // getWindow().setNavigationBarColor(getResources().getColor(android.R.color.transparent));
        setContentView(R.layout.activity_home);

        //database refs
        home_images_ref = FirebaseDatabase.getInstance().getReference().child("HomeImagesRef");

        //intent to profile
        profile_btn = findViewById(R.id.profile_btn);
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProfileSelfActivity.class));
            }
        });

        //image slider
       /* viewPagerImageSlider = findViewById(R.id.viewPagerImageSlider);
        home_images_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    imagelist = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()){
                        imagelist.add(ds.getValue(HomeImages.class));
                    }
                    homeImagesAdapter = new HomeImagesAdapter(imagelist,viewPagerImageSlider);
                    viewPagerImageSlider.setAdapter(homeImagesAdapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        viewPagerImageSlider.setClipToPadding(false);
        viewPagerImageSlider.setClipChildren(false);
        viewPagerImageSlider.setOffscreenPageLimit(3);
        viewPagerImageSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(80));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r*0.15f);
        });
        viewPagerImageSlider.setPageTransformer(compositePageTransformer);

        viewPagerImageSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderhandler.removeCallbacks(sliderRunnable);
                sliderhandler.postDelayed(sliderRunnable,4000);
            }
        });*/




       /* bottomNavigationView = findViewById(R.id.home_bn);
        bottomNavigationView.setSelectedItemId(R.id.nv_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nv_home:
                        return true;
                    case R.id.nv_activities:
                        Toast.makeText(getApplicationContext(),"Activity Page Not Ready",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nv_news:
                        Toast.makeText(getApplicationContext(),"News Page Not Ready",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nv_chat:
                        startActivity(new Intent(getApplicationContext(),ChatActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }

        });*/

        SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_arrow_back_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_feed_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_dashboard_24));
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
  /*  private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPagerImageSlider.setCurrentItem(viewPagerImageSlider.getCurrentItem()+1);
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        sliderhandler.postDelayed(sliderRunnable,4000);

    }
    @Override
    protected void onPause() {
        super.onPause();
        sliderhandler.removeCallbacks(sliderRunnable);
    }*/
}