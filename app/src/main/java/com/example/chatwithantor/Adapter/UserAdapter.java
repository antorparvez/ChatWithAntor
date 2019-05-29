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
import com.example.chatwithantor.R;
import com.example.chatwithantor.POJO.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private List <User> mUsers;

    public UserAdapter(Context mContext, List<User> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item_view, viewGroup,false);

         return  new UserAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final User user = mUsers.get(i);
        viewHolder.username_user.setText(user.getUsername());

        if(user.getImageURL().equals("default")){
            viewHolder.profile_image_user.setImageResource(R.mipmap.ic_launcher);
        }

        else {
            Glide.with(mContext).load(user.getImageURL()).into(viewHolder.profile_image_user);
        }


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{

        public TextView username_user;
        public ImageView profile_image_user;

        public  ViewHolder (View itemView){
            super(itemView);

            username_user = itemView.findViewById(R.id.username_user);
            profile_image_user = itemView.findViewById(R.id.profile_image_user);
        }

    }
}
