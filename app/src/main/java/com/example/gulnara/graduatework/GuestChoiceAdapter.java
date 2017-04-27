package com.example.gulnara.graduatework;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gulnara on 4/25/17.
 */

public class GuestChoiceAdapter extends BaseAdapter{
    ArrayList<Dish> dishes;
    ArrayList<User> users;
    LayoutInflater layoutInflater;

    public GuestChoiceAdapter(Context context, ArrayList<Dish> d, ArrayList<User> u){
        dishes = d;
        users = u;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return dishes.size();
    }

    @Override
    public Object getItem(int i) {
        return dishes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.user_choice_item, viewGroup, false);
        }

        Dish dish = dishes.get(i);

        ((TextView) v.findViewById(R.id.dish_name)).setText(dish.name);
        ((TextView) v.findViewById(R.id.dish_price)).setText(dish.price + " р.");

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        UserListRecyclerViewAdapter mRvAdapter= new UserListRecyclerViewAdapter(v.getContext(), users);
        recyclerView.setAdapter(mRvAdapter);

        return v;
    }
}
