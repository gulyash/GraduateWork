package com.example.gulnara.graduatework.results;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gulnara.graduatework.R;
import com.example.gulnara.graduatework.model.User;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {
    ArrayList<User> users;
    ArrayList<User> dishes;
    ArrayList<ArrayList<Integer>> selectedDishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        users = intent.getParcelableArrayListExtra("users");
        dishes = intent.getParcelableArrayListExtra("dishes");

        selectedDishes = new ArrayList<>();
        for (int j=0; j< users.size(); j++) {
            ArrayList<Integer> extra = intent.getIntegerArrayListExtra("selected by user " + j);
            selectedDishes.add(extra);
        }


    }
}
