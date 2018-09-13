package com.findyourfuture.amitech.findyourfuture.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.findyourfuture.amitech.findyourfuture.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentProfile extends Fragment {




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public FragmentProfile(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        NestedScrollView scrollView = (NestedScrollView) view.findViewById (R.id.nest_scrollview);
//        scrollView.setFillViewport (true);

        final int[] ICONS = new int[]{
                R.drawable.ic_calendar,
                R.drawable.ic_dashboard_black_24dp,
                R.drawable.ic_home_black_24dp,
                R.drawable.ic_locked,
                R.drawable.ic_locked,
        };

        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);

        tabs.getTabAt(0).setIcon(ICONS[0]);
        tabs.getTabAt(1).setIcon(ICONS[1]);
        tabs.getTabAt(2).setIcon(ICONS[2]);
        tabs.getTabAt(3).setIcon(ICONS[3]);
        tabs.getTabAt(4).setIcon(ICONS[4]);


        return view;
    }

    private void setupViewPager(ViewPager viewPager) {


        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new StoreFragment(), "General");
        adapter.addFragment(new StoreFragment(), "Education");
        adapter.addFragment(new StoreFragment(), "Skills");
        adapter.addFragment(new StoreFragment(), "Application");
        adapter.addFragment(new StoreFragment(), "Message");

        viewPager.setAdapter(adapter);



    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
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

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }




}
