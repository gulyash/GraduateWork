package com.example.gulnara.graduatework.billSplitting;

import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gulnara.graduatework.model.Dish;
import com.example.gulnara.graduatework.R;
import com.example.gulnara.graduatework.model.User;

import java.util.ArrayList;

/**
 * Created by gulnara on 4/25/17.
 */

public class UserListRecyclerViewAdapter extends RecyclerView.Adapter<UserListRecyclerViewAdapter.UserViewHolder> {
    ArrayList<User> users;
    ArrayList<ArrayList<Boolean>> checked;
    int dishNum;
    int count;
    Dish dish;

    public UserListRecyclerViewAdapter(ArrayList<User> u, Dish d, ArrayList<ArrayList<Boolean>> c, int dn) {
        users = u;
        dish = d;
        checked = c;
        dishNum = dn;

        count=0;
        for (Boolean b : checked.get(dishNum) ) {
            if (b) {
                count++;
            }
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        UserListRecyclerViewAdapter.UserViewHolder vh = new UserViewHolder(itemView, new UserListRecyclerViewAdapter.UserViewHolder.IUserViewHolderListener() {
            @Override
            public void onItemClick(View caller, int position) {
                recountSums(position);
                notifyDataSetChanged();
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.name.setText(user.name);
        if (checked.get(dishNum).get(position)){
            holder.pic.setImageResource(R.drawable.ic_checked);
        }
        else{
            holder.pic.setImageResource(R.drawable.ic_unchecked);
        }
        int colorToUse = holder.ta.getResourceId(position, R.color.colorPrimary);
        DrawableCompat.setTint(holder.pic.getDrawable(), ContextCompat.getColor(holder.pic.getContext(), colorToUse));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        ImageView pic;
        TypedArray ta;
        IUserViewHolderListener mListener;
        int clickedPosition;

        interface IUserViewHolderListener {
            public void onItemClick(View caller, int position);
        }

        UserViewHolder(View itemView, IUserViewHolderListener listener) {
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

    void recountSums(int userNum) {

        if (checked.get(dishNum).get(userNum)) {
            //вычитаем
            for (int j = 0; j < users.size(); j++) {
                if (checked.get(dishNum).get(j)) {
                    users.get(j).sum -= (dish.price) / count;
                }
            }
            count--;
            checked.get(dishNum).set(userNum, false);

            if (count!=0) {
                for (int j = 0; j < users.size(); j++) {
                    if (checked.get(dishNum).get(j)) {
                        users.get(j).sum += (dish.price) / count;
                    }
                }
            }
        }
        else {
            //складываем
            if (count!=0) {
                for (int j = 0; j < users.size(); j++) {
                    if (checked.get(dishNum).get(j)) {
                        users.get(j).sum -= (dish.price) / count;
                    }
                }
            }
            count++;
            checked.get(dishNum).set(userNum, true);

            for (int j = 0; j < users.size(); j++) {
                if (checked.get(dishNum).get(j)) {
                    users.get(j).sum += (dish.price) / count;
                }
            }
        }
    }
}
