package com.example.gulnara.graduatework.results;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.gulnara.graduatework.R;
import com.example.gulnara.graduatework.model.Dish;
import com.example.gulnara.graduatework.model.User;

import java.util.ArrayList;

/**
 * Created by gulnara on 4/30/17.
 */

public class ResultsAdapter extends BaseExpandableListAdapter {
    ArrayList<User> users;
    ArrayList<Dish> dishes;
    ArrayList<ArrayList<Integer>> selectedDishes;

    public ResultsAdapter(ArrayList<User> u, ArrayList<Dish> d, ArrayList<ArrayList<Integer>> s) {
        users = u;
        dishes = d;
        selectedDishes = s;
    }

    @Override
    public int getGroupCount() {
        return users.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return selectedDishes.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return selectedDishes.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return selectedDishes.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        if (view==null) {
            Context context = viewGroup.getContext();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.result_group_view, null);
        }

        TextView userText = (TextView)view.findViewById(R.id.user_name);
        userText.setText(users.get(i).name);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        if (view==null) {
            Context context = viewGroup.getContext();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.result_child_view, null);
        }

        TextView userText = (TextView)view.findViewById(R.id.dish_name);
        int dishIndex = selectedDishes.get(i).get(i1);
        userText.setText(dishes.get(dishIndex).name);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
