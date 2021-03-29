package com.example.myapplication.HoaDonFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.PetFragment.DongVatFragment;
import com.example.myapplication.PetFragment.LoaiDongVatFragment;

public class HoaDonViewPagerAdapter extends FragmentStatePagerAdapter {
    private String TiTleTab[]={"Hóa Đơn Xuất","Hóa Đơn Nhập"};


    public HoaDonViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {super(fm,behavior); }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HoaDonNhapFragment();

            case 1:
                return new HoaDonXuatFragment();

            default:
                return new HoaDonNhapFragment();
        }


    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TiTleTab[position];
    }
}
