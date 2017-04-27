package com.example.gulnara.graduatework;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by gulnara on 4/25/17.
 */

public class UserListRecyclerViewAdapter extends RecyclerView.Adapter<UserListRecyclerViewAdapter.UserViewHolder> {
    ArrayList<User> users;
    int chosenUserPosition;

    public UserListRecyclerViewAdapter(Context context, ArrayList<User> u){
        users = u;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        UserListRecyclerViewAdapter.UserViewHolder vh = new UserViewHolder(itemView, new UserListRecyclerViewAdapter.UserViewHolder.IUserViewHolderListener() {
            @Override
            public void onItemClick(View caller, int position) {
                chosenUserPosition = position;
                notifyDataSetChanged();
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.name.setText(user.name);
        if (position==chosenUserPosition){
            holder.pic.setImageResource(R.drawable.ic_active_user);
        }
        else{
            holder.pic.setImageResource(R.drawable.ic_inactive_user);
        }
        int colorToUse = holder.ta.getResourceId(position, R.color.colorPrimary);
        DrawableCompat.setTint(holder.pic.getDrawable(), ContextCompat.getColor(holder.pic.getContext(), colorToUse));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        ImageView pic;
        TypedArray ta;
        IUserViewHolderListener mListener;
        public int clickedPosition;

        public interface IUserViewHolderListener {
            public void onItemClick(View caller, int position);
        }

        public UserViewHolder(View itemView, IUserViewHolderListener listener) {
            super(itemView);
            mListener = listener;
            name = (TextView)itemView.findViewById(R.id.user_name);
            pic = (ImageView)itemView.findViewById(R.id.user_pic);
            ta = itemView.getContext().getResources().obtainTypedArray(R.array.usercolors);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickedPosition = this.getPosition();
            mListener.onItemClick(view, clickedPosition);
        }
    }



}
