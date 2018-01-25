package com.example.ei1057.appcliente;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Este activity podria ser en el que mostraremos a todos los turistas de una sesion en el maps,
//junto con todos los turistas registrados en una sesion
public class SessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PageAdapterSessionActivity mAdapter = new PageAdapterSessionActivity(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }
}
