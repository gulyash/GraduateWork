package com.example.gulnara.graduatework.results;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.gulnara.graduatework.R;
import com.example.gulnara.graduatework.model.Dish;
import com.example.gulnara.graduatework.model.User;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {
    ArrayList<User> users;
    ArrayList<Dish> dishes;
    ArrayList<ArrayList<Integer>> selectedDishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Итоги");
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        users = intent.getParcelableArrayListExtra("users");
        dishes = intent.getParcelableArrayListExtra("dishes");

        selectedDishes = new ArrayList<>();
        for (int j=0; j< users.size(); j++) {
            ArrayList<Integer> extra = intent.getIntegerArrayListExtra("selected by user " + j);
            selectedDishes.add(extra);
        }

        ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.expandable_list_view);
        ResultsAdapter resultsAdapter = new ResultsAdapter(users, dishes, selectedDishes);
        expandableListView.setAdapter(resultsAdapter);
    }
}
