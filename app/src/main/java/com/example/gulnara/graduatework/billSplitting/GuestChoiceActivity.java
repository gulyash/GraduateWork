package com.example.gulnara.graduatework.billSplitting;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gulnara.graduatework.model.Dish;
import com.example.gulnara.graduatework.R;
import com.example.gulnara.graduatework.model.User;
import com.example.gulnara.graduatework.results.ResultsActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class GuestChoiceActivity extends AppCompatActivity {
    ArrayList<User> users;
    ArrayList<Dish> bill;
    ArrayList<Dish> dishes;
    GuestChoiceAdapter gcAdapter;
    ArrayList<ArrayList<Boolean>> checked;
    int guestNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_choice);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Разделите чек");
        setSupportActionBar(myToolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            guestNum = extras.getInt("guest num", 2);
            bill = extras.getParcelableArrayList("bill");
        }

        initDishes(bill);
        initUsers();
        initChecked();

        ListView listView = (ListView) findViewById(R.id.list);
        gcAdapter = new GuestChoiceAdapter(this, dishes, users, checked);
        listView.setAdapter(gcAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bill_editor_menu, menu);
        return true;
    }

    public void onNextButtonClick(MenuItem item) {
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putParcelableArrayListExtra("dishes", dishes);
        intent.putParcelableArrayListExtra("users", users);
        putCheckedExtras(intent);
        startActivity(intent);
    }

    private void initDishes(ArrayList<Dish> bill){
        dishes = new ArrayList<>();
        for (Dish dish : bill) {
            for (int i=0; i<dish.quantity; i++) {
                dishes.add(new Dish(dish.name + (i==0 ? " ":i), dish.price, 1));
            }
        }
    }

    private void initUsers(){
        users = new ArrayList<>();
        for (int i=0; i < guestNum; i++) {
            users.add(new User(i));
        }
    }

    private void initChecked() {
        checked = new ArrayList<>();
        for (int i=0; i<dishes.size(); i++) {
            checked.add(new ArrayList<Boolean>());
            for (int j=0; j<users.size(); j++) {
                checked.get(i).add(false);
            }
        }
    }

    private void putCheckedExtras(Intent intent) {
        for (int j=0; j< users.size(); j++) {
            ArrayList<Integer> selectedDishNumbers = new ArrayList<>();
            for (int i=0; i<dishes.size(); i++) {
                if (checked.get(i).get(j)) {
                    selectedDishNumbers.add(i);
                }
            }
            intent.putExtra("selected by user " + j, selectedDishNumbers);
        }
    }
}
