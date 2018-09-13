package com.findyourfuture.amitech.findyourfuture;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.findyourfuture.amitech.findyourfuture.College_Detail_Fragment.about_fragment;
import com.findyourfuture.amitech.findyourfuture.College_Detail_Fragment.facility;

import java.util.ArrayList;
import java.util.List;

public class detail_activity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    AppBarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        collapsingToolbarLayout=(AppBarLayout) findViewById(R.id.app_bar);
        collapsingToolbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
                {
                    //  Collapsed
                    setTitle("College name");
                    Log.d("status","collapsed");



                }
                else
                {
//                    setTitle("");

                    Log.d("status","expanded");


                    //Expanded


                }
            }
        });


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new about_fragment(), "About");
        adapter.addFrag(new facility(), "Course");
        adapter.addFrag(new about_fragment(), "Facility");
        adapter.addFrag(new about_fragment(), "Faculty");
        adapter.addFrag(new about_fragment(), "Gallery");
        adapter.addFrag(new about_fragment(), "Reviews");
        adapter.addFrag(new about_fragment(), "Events");
        adapter.addFrag(new about_fragment(), "Placements");
        adapter.addFrag(new about_fragment(), "Contact");
//        adapter.addFrag(new TwoFragment(), "TWO");
//        adapter.addFrag(new ThreeFragment(), "THREE");
//        adapter.addFrag(new FourFragment(), "FOUR");
//        adapter.addFrag(new FiveFragment(), "FIVE");
//        adapter.addFrag(new SixFragment(), "SIX");
//        adapter.addFrag(new SevenFragment(), "SEVEN");
//        adapter.addFrag(new EightFragment(), "EIGHT");
//        adapter.addFrag(new NineFragment(), "NINE");
//        adapter.addFrag(new TenFragment(), "TEN");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
