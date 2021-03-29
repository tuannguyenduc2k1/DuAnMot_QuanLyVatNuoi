package com.example.myapplication.ActivityFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.HoaDonFragment.HoaDonNhapFragment;
import com.example.myapplication.HoaDonFragment.HoaDonViewPagerAdapter;
import com.example.myapplication.HoaDonFragment.HoaDonXuatFragment;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;


public class HoaDonFragment extends Fragment {
    private View mView;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public HoaDonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_hoa_don, container, false);
        viewPager =(ViewPager) mView.findViewById(R.id.hoa_don_viewPager);
        tabLayout = mView.findViewById(R.id.tab_Layout_hoa_don);
        HoaDonViewPagerAdapter adapter = new HoaDonViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return mView;
    }
}