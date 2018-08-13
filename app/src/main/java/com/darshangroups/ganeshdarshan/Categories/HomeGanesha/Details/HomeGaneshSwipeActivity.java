package com.darshangroups.ganeshdarshan.Categories.HomeGanesha.Details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.darshangroups.ganeshdarshan.Categories.HomeGanesha.Adapter.ScreenSlidePagerAdapter;
import com.darshangroups.ganeshdarshan.Data.GaneshaData;
import com.darshangroups.ganeshdarshan.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Vector;

public class HomeGaneshSwipeActivity extends FragmentActivity {

    private ViewPager mPager;
    private List<Fragment> fragmentList = new Vector<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_g_images);

        Intent intent = getIntent();
        String mJsonResponse = intent.getStringExtra("data");

        loadFragmentData(mJsonResponse);
        setViewpager();
    }

    private void loadFragmentData(String mJsonResponse) {
        List<GaneshaData> items = new Gson().fromJson(mJsonResponse, new TypeToken<List<GaneshaData>>() {}.getType());

        for (GaneshaData data : items) {
            Bundle bundle = new Bundle();
            bundle.putString("cimg_path", data.getcimg_path());

            HomeGaneshImageFragment imageFragment = new HomeGaneshImageFragment();
            imageFragment.setArguments(bundle);
            fragmentList.add(imageFragment);
        }
    }

    private void setViewpager() {
        mPager = findViewById(R.id.pager);
        PagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),fragmentList);
        mPager.setAdapter(mPagerAdapter);
    }

}