package com.example.chatwithantor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chatwithantor.MessageActivity;
import com.example.chatwithantor.POJO.Chat;
import com.example.chatwithantor.R;
import com.example.chatwithantor.POJO.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

private Context mContext;
private List<Chat> mChat;
private String image_url;
public  static  final int MSG_TYEP_SENDER=0;
public  static  final int MSG_TYEP_RECEIVER=1;
FirebaseUser firebaseUser;


public MessageAdapter(Context mContext, List<Chat> mChat, String image_url) {
        this.mContext = mContext;
        this.mChat = mChat;
        this.image_url = image_url;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    if (i == MSG_TYEP_SENDER){
        View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_sender, viewGroup, false);

        return new MessageAdapter.ViewHolder(view);
    }
    else {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_reciever, viewGroup, false);

        return new MessageAdapter.ViewHolder(view);
    }
}

@Override
public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


    Chat chat = mChat.get(i);
    viewHolder.show_msg.setText(chat.getMessage());

    if (image_url.equals("default")){
        viewHolder.profile_image_reciever.setImageResource(R.mipmap.ic_launcher);
    }else{
        Glide.with(mContext).load(image_url).into(viewHolder.profile_image_reciever);
    }

        }

@Override
public int getItemCount() {
        return mChat.size();
        }

public class  ViewHolder extends  RecyclerView.ViewHolder{

    public TextView show_msg;
    public CircleImageView profile_image_reciever;

    public  ViewHolder (View itemView){
        super(itemView);

        show_msg = itemView.findViewById(R.id.show_msg);
        profile_image_reciever = itemView.findViewById(R.id.pro_pic);
    }
}

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(firebaseUser.getUid())){
            return  MSG_TYEP_SENDER;
        }else {
            return MSG_TYEP_RECEIVER;
        }
    }
}
