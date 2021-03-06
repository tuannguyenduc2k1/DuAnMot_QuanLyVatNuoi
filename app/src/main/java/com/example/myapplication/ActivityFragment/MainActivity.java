package com.example.myapplication.ActivityFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.ActivityFragment.ViewPagerAdapter;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {
    private BottomNavigationView mBottomNavigationView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPager);
        mBottomNavigationView = findViewById(R.id.btn_nav);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {//su kien vuot
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            switch (position){
                case 0 :mBottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                    break;
                case 1 :mBottomNavigationView.getMenu().findItem(R.id.action_loai_dong_vat).setChecked(true);
                    break;
                case 2 :mBottomNavigationView.getMenu().findItem(R.id.action_hoa_don).setChecked(true);
                    break;
                case 3 :mBottomNavigationView.getMenu().findItem(R.id.action_thong_ke).setChecked(true);
                    break;
                case 4 :mBottomNavigationView.getMenu().findItem(R.id.action_cai_dat).setChecked(true);
                    break;
            }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.action_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_loai_dong_vat:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_hoa_don:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.action_thong_ke:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.action_cai_dat:
                        mViewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }
}