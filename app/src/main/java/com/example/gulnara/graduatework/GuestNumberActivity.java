package com.example.gulnara.graduatework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class GuestNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_number);

        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(2);
        numberPicker.setMaxValue(20);
        numberPicker.setWrapSelectorWheel(false);
    }


    public void onAcceptGuestNumberClick(View view) {
        Intent intent = new Intent(this, PickPhotoActivity.class);
        startActivity(intent);
    }
}
