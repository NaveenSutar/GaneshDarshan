package com.darshangroups.ganeshdarshan.Categories.GaneshaPreparation.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class GaneshaPreparationSlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public GaneshaPreparationSlidePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}