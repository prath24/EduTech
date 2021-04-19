package com.example.edutech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.edutech.Adapters.MessagesAdapter;
import com.example.edutech.Models.Messages;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class ChatActivity extends AppCompatActivity {

    //hooks
    BottomNavigationView bottomNavigationView ;
    RecyclerView message_list;

    //adapters
    MessagesAdapter messagesAdapter;

    //Database ref
    DatabaseReference tempref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStatusBarGradiant(this);
        getWindow().setStatusBarColor(getResources().getColor(R.color.light_grey));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
       // getWindow().setNavigationBarColor(getResources().getColor(android.R.color.transparent));
        setContentView(R.layout.activity_chat);

        //database refs
        tempref = FirebaseDatabase.getInstance().getReference().child("Tempref");


        message_list = findViewById(R.id.message_list);
        //firebase recycler options
        FirebaseRecyclerOptions<Messages> options =
                new FirebaseRecyclerOptions.Builder<Messages>()
                        .setQuery(tempref,Messages.class)
                        .build();
        messagesAdapter = new MessagesAdapter(options);
        message_list.setAdapter(messagesAdapter);


        //bottom navigation
       /* bottomNavigationView = findViewById(R.id.chat_bn);
        bottomNavigationView.setSelectedItemId(R.id.nv_chat);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nv_home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nv_activities:
                        Toast.makeText(getApplicationContext(),"Activity Page Not Ready",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nv_news:
                        Toast.makeText(getApplicationContext(),"News Page Not Ready",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nv_chat:
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
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_home_24));
        spaceNavigationView.setCentreButtonColor(getResources().getColor(R.color.darkpurple));
        spaceNavigationView.setActiveCentreButtonIconColor(getResources().getColor(R.color.darkpurple));
        spaceNavigationView.setInActiveCentreButtonIconColor(getResources().getColor(R.color.white));
        spaceNavigationView.setActiveSpaceItemColor(ContextCompat.getColor(this, R.color.black));

        //spaceNavigationView.changeCurrentItem(3);

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
    @Override
    protected void onStart() {
        super.onStart();
        messagesAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        messagesAdapter.stopListening();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.app_gradient);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }
}