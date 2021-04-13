package com.example.myapplication.ActivityFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.AdapterLoaiDongVat;
import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.PetFragment.DongVatFragment;
import com.example.myapplication.PetFragment.LoaiDongVatFragment;
import com.example.myapplication.PetFragment.PetViewPagerAdapter;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.util.List;


public class PetFragment extends Fragment{
    private View mView;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    public PetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         mView = inflater.inflate(R.layout.fragment_pet, container, false);
         tabLayout = mView.findViewById(R.id.tab_Layout_pet);
         viewPager =(ViewPager) mView.findViewById(R.id.pet_viewPager);
         PetViewPagerAdapter adapter = new PetViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
         viewPager.setAdapter(adapter);

         tabLayout.setupWithViewPager(viewPager);


         return mView;
    }

}