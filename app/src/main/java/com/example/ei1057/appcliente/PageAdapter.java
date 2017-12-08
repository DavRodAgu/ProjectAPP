package com.example.ei1057.appcliente;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


class PageAdapter extends FragmentPagerAdapter {

    private final ArrayList<String> tabs = new ArrayList<>();

    public PageAdapter(FragmentManager fm) {
        super(fm);
        tabs.add("Signal");
        tabs.add("Location");
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Tab1();
            case 1:
                return new Tab2();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
