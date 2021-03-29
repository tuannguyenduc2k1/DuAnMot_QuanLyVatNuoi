package com.example.myapplication.PetFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PetViewPagerAdapter extends FragmentStatePagerAdapter {

    private String listTab[]={"Loại Động Vật","Động Vật"};
    public PetViewPagerAdapter(@NonNull FragmentManager fm,int behavior) { super(fm,behavior); }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LoaiDongVatFragment();

            case 1:
                return new DongVatFragment();

            default:
                return new LoaiDongVatFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];

    }
}
