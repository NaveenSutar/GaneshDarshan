package com.darshangroups.ganeshdarshan.Categories.PreparationGanesha.Details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.darshangroups.ganeshdarshan.R;

public class GaneshaPreparationImageFragment extends AppCompatActivity {

    ImageView ivCimg_path;
    TextView tvCshared_by, tvCplace_name, tvCtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_container);
        getInfo();
    }

    private void getInfo() {
        if (getIntent().hasExtra("cimg_path") && getIntent().hasExtra("cshared_by") && getIntent().hasExtra("cplace_name")) {
            String img = getIntent().getStringExtra("cimg_path");
            String shby = getIntent().getStringExtra("cshared_by");
            String place = getIntent().getStringExtra("cplace_name");
            String title = getIntent().getStringExtra("ctitle");
            setInfo(img, shby, place, title);
        }
    }

    private void setInfo(String img, String shby, String place, String title) {
        tvCshared_by = (TextView) findViewById(R.id.sharedby);
        tvCplace_name = (TextView) findViewById(R.id.placename);
        tvCtitle = (TextView) findViewById(R.id.ctitle);
        ivCimg_path = (ImageView) findViewById(R.id.image);

        tvCshared_by.setText("Shared By : " + shby);
        tvCplace_name.setText("From : " + place);
        tvCtitle.setText(title);
        Glide.with(this).load(img).placeholder(R.drawable.pray).error(R.drawable.pray).into(ivCimg_path);
    }
}