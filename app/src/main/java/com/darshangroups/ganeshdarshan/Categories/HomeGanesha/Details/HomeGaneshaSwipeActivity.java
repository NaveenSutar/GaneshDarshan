package com.darshangroups.ganeshdarshan.Categories.HomeGanesha.Details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.darshangroups.ganeshdarshan.Categories.HomeGanesha.Adapter.HomeGaneshaSlidePagerAdapter;
import com.darshangroups.ganeshdarshan.Data.GaneshaData;
import com.darshangroups.ganeshdarshan.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Vector;

public class HomeGaneshaSwipeActivity extends FragmentActivity {

    private ViewPager mPager;
    private List<Fragment> fragmentList = new Vector<>();
    private int position = 0;
    private int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_g_images);

        Intent intent = getIntent();
        String mJsonResponse = intent.getStringExtra("data");
        id = Integer.parseInt(intent.getStringExtra("nimg_id"));

        loadFragmentData(mJsonResponse);
        setViewpager();
    }

    private void loadFragmentData(String mJsonResponse) {
        List<GaneshaData> items = new Gson().fromJson(mJsonResponse, new TypeToken<List<GaneshaData>>() {}.getType());

        for (int i = 0; i < items.size(); i++) {
            GaneshaData data = items.get(i);

            Bundle bundle = new Bundle();
            bundle.putString("cimg_path", data.getcimg_path());
            bundle.putString("cshared_by", data.getcshared_by());
            bundle.putString("cplace_name", data.getcplace_name());
            bundle.putString("ctitle", data.getctitle());

            /*String cimg_path = bundle.getString("cimg_path", null);
            String cshared_by = bundle.getString("cshared_by", null);
            String cplace_name = bundle.getString("cplace_name", null);
            String ctitle = bundle.getString("ctitle", null);
*/
            HomeGaneshaImageFragment imageFragment = new HomeGaneshaImageFragment();
            imageFragment.setArguments(bundle);
            fragmentList.add(imageFragment);

            if (Integer.parseInt(data.getnimg_id()) == id) {
                position = i;
            }
        }
    }

    private void setViewpager() {
        mPager = findViewById(R.id.pager);
        PagerAdapter mPagerAdapter = new HomeGaneshaSlidePagerAdapter(getSupportFragmentManager(),fragmentList);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(position, true);
    }

}