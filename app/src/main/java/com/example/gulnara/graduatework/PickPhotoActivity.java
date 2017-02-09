package com.example.gulnara.graduatework;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.R.attr.bitmap;

public class PickPhotoActivity extends AppCompatActivity {

    private static final int PICK_PHOTO=1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_photo);

        imageView = (ImageView)findViewById(R.id.imageView);

        Button galleryButton = (Button) findViewById(R.id.galleryButton);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO);


                Intent intent = new Intent();
// Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO);
                //imageView.setImageBitmap(bitmap);


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == PICK_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null) {
//
//            Uri uri = data.getData();
//
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                // Log.d(TAG, String.valueOf(bitmap));
//
//                //imageView.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else Toast.makeText(this, "something's wrong", Toast.LENGTH_LONG).show();
        if (requestCode == PICK_PHOTO && resultCode == Activity.RESULT_OK) {

            Uri selectedImageUri = data.getData();


            Toast.makeText(this, selectedImageUri.toString(), Toast.LENGTH_LONG).show();
            imageView.setImageURI(selectedImageUri);
        }

    }

}
