package com.exchange.client.module.homepage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.exchange.client.R;
import com.exchange.client.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页tab
 */
public class HomePageActivity extends BaseActivity {

    private static final String TAG = HomePageActivity.class.getSimpleName();
    private CategoryTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        initView();
        initListener();
        tabs = (CategoryTabStrip) findViewById(R.id.category_strip);
        pager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());   

        pager.setAdapter(adapter);

        tabs.setViewPager(pager);
    }

    protected void initView() {

    }

    protected void initListener() {

    }


    public void onDestroy() {
        super.onDestroy();
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final List<String> catalogs = new ArrayList<String>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            catalogs.add(getString(R.string.duanzi));
            catalogs.add("\u672c\u5730");
            catalogs.add(getString(R.string.shipin));
            catalogs.add(getString(R.string.category_society));
            catalogs.add(getString(R.string.category_entertainment));
            catalogs.add(getString(R.string.category_tech));
            catalogs.add(getString(R.string.category_finance));
            catalogs.add(getString(R.string.category_military));
            catalogs.add(getString(R.string.category_world));
            catalogs.add(getString(R.string.category_image_ppmm));
            catalogs.add(getString(R.string.category_health));
            catalogs.add(getString(R.string.category_government));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return catalogs.get(position);
        }

        @Override
        public int getCount() {
            return catalogs.size();
        }


        @Override
        public Fragment getItem(int position) {
            if (position==0){
                return new OneFragment();
            }else if (position==1){
                return new TwoFragment();
            }else {
                return NewsFragment.newInstance(position);
            }
        }

    }

}
