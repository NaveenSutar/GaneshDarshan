package com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.Angol.AngolActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.AutoNagar.AutoNagarActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.Bogarves.BogarvesActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.Camp.CampActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.Chennamma.ChennammaActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.FortRoad.FortRoadActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.Hindalga.HindalgaActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.Honaga.HonagaActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.Kangrali.KangraliActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.Khanapur.KhanapurActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.NehruNagar.NehruNagarActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.Shahapur.ShahapurActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.Tilakwadi.TilakwadiActivity;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.Vadagaon.VadagaonActivity;
import com.darshangroups.ganeshdarshan.R;

public class SarvajanikGanapatiActivity extends AppCompatActivity {
    CardView a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarvajanik);

        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);
        a6 = findViewById(R.id.a6);
        a7 = findViewById(R.id.a7);
        a8 = findViewById(R.id.a8);
        a9 = findViewById(R.id.a9);
        a10 = findViewById(R.id.a10);
        a11 = findViewById(R.id.a11);
        a12 = findViewById(R.id.a12);
        a13 = findViewById(R.id.a13);
        a14 = findViewById(R.id.a14);

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, AngolActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, AutoNagarActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, BogarvesActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, CampActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, ChennammaActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, FortRoadActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, HindalgaActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, HonagaActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, KangraliActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, KhanapurActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, NehruNagarActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, ShahapurActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, TilakwadiActivity.class);
                startActivity(Sarvajanik);
            }
        });

        a14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sarvajanik = new Intent(SarvajanikGanapatiActivity.this, VadagaonActivity.class);
                startActivity(Sarvajanik);
            }
        });

    }
}