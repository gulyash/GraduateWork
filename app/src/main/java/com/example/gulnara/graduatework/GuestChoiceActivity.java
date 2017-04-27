package com.example.gulnara.graduatework;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gulnara.graduatework.billEditor.BillEditorAdapter;
import com.example.gulnara.graduatework.billEditor.BillItemDialogFragment;
import com.example.gulnara.graduatework.billEditor.BillParser;

import java.util.ArrayList;

public class GuestChoiceActivity extends AppCompatActivity {
    ArrayList<User> users;
    ArrayList<Dish> dishes;
    GuestChoiceAdapter gcAdapter;
    int guestNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_choice);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            guestNum = extras.getInt("guest num", 2);

            ArrayList<Dish> bill = extras.getParcelableArrayList("bill");
            dishes = new ArrayList<>();
            for (Dish dish : bill) {
                for (int i=0; i<dish.quantity; i++) {
                    dishes.add(new Dish(dish.name + (i==0 ? "":i), dish.price, 1));
                }
            }
        }

        users = new ArrayList<>();
        for (int i=0; i < guestNum; i++) {
            users.add(new User(i));
        }

        ListView listView = (ListView) findViewById(R.id.list);
        gcAdapter = new GuestChoiceAdapter(this, dishes, users);
        listView.setAdapter(gcAdapter);

    }
}
