package com.a40333.bharrin4.lab2_bharrin4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by bchaudhr on 4/3/2017.
 */

public class ImageViewActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");

        ImageView imageView = (ImageView) findViewById(R.id.image_view);
    }
}
