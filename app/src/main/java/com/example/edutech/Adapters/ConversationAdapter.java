package com.example.edutech.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edutech.Models.Conversation;
import com.example.edutech.R;

import java.util.ArrayList;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.MyViewHolder>{

    ArrayList<Conversation> list ;

    public ConversationAdapter(ArrayList<Conversation> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_model,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Conversation conversation = list.get(position);
        if (conversation.getType().equals("user")){
            holder.chat_user_message.setVisibility(View.GONE);
            holder.user_message.setVisibility(View.VISIBLE);
            holder.user_message.setText(conversation.getMessage());
        }
        else if (conversation.getType().equals("chat_user")){
            holder.user_message.setVisibility(View.GONE);
            holder.chat_user_message.setVisibility(View.VISIBLE);
            holder.chat_user_message.setText(conversation.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView chat_user_message,user_message;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            chat_user_message = itemView.findViewById(R.id.chat_user_message);
            user_message = itemView.findViewById(R.id.user_message);
        }
    }
}
