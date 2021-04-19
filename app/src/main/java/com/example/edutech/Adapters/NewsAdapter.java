package com.example.edutech.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edutech.Models.TopNews;
import com.example.edutech.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{

    ArrayList<TopNews> list;

    public NewsAdapter(ArrayList<TopNews> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_model,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (position != list.size()){
            TopNews topNews = list.get(position);
            holder.news_headline.setText(topNews.getNews_headline());
            holder.date.setText(topNews.getDate());
            Picasso.get().load(topNews.getNews_image()).into(holder.news_image);
        }

        if (position >= (list.size()-1) ){
            holder.bottom_line.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView news_image;
        TextView news_headline,date;
        LinearLayout bottom_line;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            news_image = itemView.findViewById(R.id.news_image);
            news_headline = itemView.findViewById(R.id.news_headline);
            date = itemView.findViewById(R.id.date);
            bottom_line = itemView.findViewById(R.id.bottom_line);
        }
    }
}
