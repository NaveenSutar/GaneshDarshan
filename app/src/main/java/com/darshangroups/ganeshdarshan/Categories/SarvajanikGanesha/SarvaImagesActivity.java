package com.darshangroups.ganeshdarshan.Categories.SarvajanikGanesha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.darshangroups.ganeshdarshan.R;

public class SarvaImagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_g_images);

        TextView txtProduct = (TextView) findViewById(R.id.area_label);

        Intent i = getIntent();
        // getting attached intent data
        String area = i.getStringExtra("area");
        // displaying selected product name
        txtProduct.setText(area);
    }
}