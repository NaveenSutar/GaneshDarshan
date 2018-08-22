package com.darshangroups.ganeshdarshan.Categories.PreparationGanesha.Details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    /*@Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_image_container, container, false);

        ivCimg_path = rootView.findViewById(R.id.image);
        tvCshared_by = rootView.findViewById(R.id.sharedby);
        tvCplace_name = rootView.findViewById(R.id.placename);
        tvCtitle = rootView.findViewById(R.id.ctitle);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String cimg_path = bundle.getString("cimg_path", null);
            String cshared_by = bundle.getString("cshared_by", null);
            String cplace_name = bundle.getString("cplace_name", null);
            String ctitle = bundle.getString("ctitle", null);


            Glide.with(getActivity()).load(cimg_path).placeholder(R.drawable.pray).error(R.drawable.pray).into(ivCimg_path);
            tvCtitle.setText(ctitle);
            tvCshared_by.setText("Shared By : " + cshared_by);
            tvCplace_name.setText("From : " + cplace_name);
        }
        return rootView;
    }*/
}

