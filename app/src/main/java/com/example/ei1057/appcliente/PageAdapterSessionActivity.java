package com.example.ei1057.appcliente;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdapterSessionActivity extends FragmentPagerAdapter{
    private final ArrayList<String> tabs = new ArrayList<>();

    public PageAdapterSessionActivity(FragmentManager fm) {
        super(fm);
        tabs.add("Tourists");
        tabs.add("Location");
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Tab1Session();
            case 1:
                return new Tab2Session();
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
