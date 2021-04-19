package com.example.edutech;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.edutech.Adapters.ConversationAdapter;
import com.example.edutech.Models.Conversation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.edutech.R.color.light_grey;

public class ConversationActivity extends AppCompatActivity {

    //hooks
    RecyclerView chatlist;
    CircleImageView profile_image;
    TextView username;

    //adapters
    ConversationAdapter conversationAdapter;

    //lists
    ArrayList<Conversation> list;

    //Intent strings
    String user_name,profile_image_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
       // getWindow().setNavigationBarColor(getResources().getColor(android.R.color.transparent));

        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.light_grey, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.light_grey));
        }*/

        setContentView(R.layout.activity_conversation);

        //Intent
        user_name = getIntent().getStringExtra("user_name");
        profile_image_url = getIntent().getStringExtra("profile_image_url");


        username = findViewById(R.id.username);
        username.setText(user_name);
        profile_image = findViewById(R.id.profile_image);
        Picasso.get().load(profile_image_url).into(profile_image);

        chatlist = findViewById(R.id.chatlist);
        //dummy
        list = new ArrayList<>();
        list.add(new Conversation("Hello there !","user",""));
        list.add(new Conversation("Hie","chat_user",""));
        list.add(new Conversation("Howz the app ?","user",""));
        list.add(new Conversation("Its Great I really loved it ..","chat_user",""));
        list.add(new Conversation("Ohh ! m really glad to hear that","user",""));
        list.add(new Conversation("Yeah!","chat_user",""));

        conversationAdapter = new ConversationAdapter(list);
        chatlist.setAdapter(conversationAdapter);

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.grey_gradiant);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }
}