package com.epicodus.doctorlookup.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.doctorlookup.models.Doctor;
import com.epicodus.doctorlookup.ui.DoctorDetailFragment;

import java.util.List;


/**
 * Created by spuek on 10/20/17.
 */

public class DoctorPagerAdapter extends FragmentPagerAdapter{
    private List<Doctor> mDoctors;

    public DoctorPagerAdapter(FragmentManager fm, List<Doctor> doctors) {
        super(fm);
        mDoctors = doctors;
    }

    @Override
    public Fragment getItem(int position) {
        return DoctorDetailFragment.newInstance(mDoctors.get(position));
    }

    @Override
    public int getCount() {
        return mDoctors.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDoctors.get(position).getFirstName();
    }
}
