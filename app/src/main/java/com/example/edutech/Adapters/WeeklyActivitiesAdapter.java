package com.example.edutech.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edutech.ActivityDetailsActivity;
import com.example.edutech.Models.WeeklyActivity;
import com.example.edutech.R;

import java.util.ArrayList;

public class WeeklyActivitiesAdapter extends RecyclerView.Adapter<WeeklyActivitiesAdapter.MyViewHolder>{

    ArrayList<WeeklyActivity> list;

    public WeeklyActivitiesAdapter(ArrayList<WeeklyActivity> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weekly_activities_model,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (position < list.size()){
            holder.activity_title.setText(list.get(position).getActivity_name());
            holder.status.setText(list.get(position).getStatus());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), ActivityDetailsActivity.class));
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView activity_title,status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            activity_title = itemView.findViewById(R.id.user_name);
            status = itemView.findViewById(R.id.last_message);
        }
    }
}
