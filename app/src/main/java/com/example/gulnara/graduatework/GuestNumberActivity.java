package com.example.gulnara.graduatework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import com.example.gulnara.graduatework.pickPhoto.PickPhotoActivity;

public class GuestNumberActivity extends AppCompatActivity {
    NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_number);

        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(2);
        numberPicker.setMaxValue(12);
        numberPicker.setWrapSelectorWheel(false);
    }


    public void onAcceptGuestNumberClick(View view) {
        Intent intent = new Intent(this, PickPhotoActivity.class);
        intent.putExtra("guest num", numberPicker.getValue());
        startActivity(intent);
    }
}
