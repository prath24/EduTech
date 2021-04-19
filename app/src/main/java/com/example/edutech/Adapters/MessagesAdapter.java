package com.example.edutech.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edutech.ConversationActivity;
import com.example.edutech.Models.Messages;
import com.example.edutech.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesAdapter extends FirebaseRecyclerAdapter<Messages, MessagesAdapter.MyViewHolder> {

    public MessagesAdapter(@NonNull FirebaseRecyclerOptions<Messages> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Messages model) {
        holder.user_name.setText(model.getUser_name());
        holder.last_message.setText(model.getLast_message());
        Picasso.get().load(model.getProfile_image()).into(holder.profile_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ConversationActivity.class);
                intent.putExtra("user_name",model.getUser_name());
                intent.putExtra("profile_image_url",model.getProfile_image());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_model,parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profile_image;
        TextView user_name,last_message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image = itemView.findViewById(R.id.profile_image);
            user_name = itemView.findViewById(R.id.user_name);
            last_message = itemView.findViewById(R.id.last_message);
        }

    }
}
