package com.darshangroups.ganeshdarshan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.darshangroups.ganeshdarshan.Categories.GaneshaPreparation.GaneshaPreparationActivity;
import com.darshangroups.ganeshdarshan.Categories.HomeGanesha.HomeGaneshaActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.SarvajanikGanapatiActivity;
import com.darshangroups.ganeshdarshan.Categories.UploadImage.UploadImageActivity;

public class HomeActivity extends AppCompatActivity {

    CardView c1, c2, c3, c4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(HomeActivity.this, SarvajanikGanapatiActivity.class);
                startActivity(Sarvajanik);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Home = new Intent(HomeActivity.this, HomeGaneshaActivity.class);
                startActivity(Home);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Preparation = new Intent(HomeActivity.this, GaneshaPreparationActivity.class);
                startActivity(Preparation);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent upload = new Intent(HomeActivity.this, UploadImageActivity.class);
                startActivity(upload);
            }
        });
    }
}
