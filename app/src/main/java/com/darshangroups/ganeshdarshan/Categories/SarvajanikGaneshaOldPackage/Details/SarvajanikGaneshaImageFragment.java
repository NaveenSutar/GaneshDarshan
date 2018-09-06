package com.darshangroups.ganeshdarshan.Categories.SarvajanikGaneshaOldPackage.Details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.darshangroups.ganeshdarshan.R;
import com.squareup.picasso.Picasso;

public class SarvajanikGaneshaImageFragment extends AppCompatActivity {
    ImageView ivCimg_path;
    TextView tvCshared_by, tvAddress, tvCtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_container);
        getInfo();
    }

    private void getInfo() {
        if (getIntent().hasExtra("cimg_path") && getIntent().hasExtra("cshared_by") && getIntent().hasExtra("caddress")) {
            String img = getIntent().getStringExtra("cimg_path");
            String shby = getIntent().getStringExtra("cshared_by");
            String address = getIntent().getStringExtra("caddress");
            String title = getIntent().getStringExtra("ctitle");
            setInfo(img, shby, address, title);
        }
    }

    private void setInfo(String img, String shby, String place, String title) {
        tvCshared_by = findViewById(R.id.sharedby);
        tvAddress = findViewById(R.id.placename);
        tvCtitle = findViewById(R.id.ctitle);
        ivCimg_path = findViewById(R.id.image);

        tvCshared_by.setText("Shared By : " + shby);
        tvAddress.setText("Address : " + place);
        tvCtitle.setText(title);
        Picasso.with(this).load(img).placeholder(R.drawable.img_err_two).error(R.drawable.img_err_two).into(ivCimg_path);
    }
}
