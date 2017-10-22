package com.epicodus.doctorlookup.adapters;


import android.content.Context;
import android.widget.ArrayAdapter;

import com.epicodus.doctorlookup.models.Doctor;

import java.util.List;

public class MyDoctorsArrayAdapter extends ArrayAdapter{

    private Context mContext;


    private List<Doctor> mDoctors;
    private String[] mInformation;
    public MyDoctorsArrayAdapter(Context mContext, int resource, List<Doctor> mDoctors) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mDoctors = mDoctors;

    }

    @Override
    public Object getItem(int position) {
        Doctor doctor = mDoctors.get(position);
        String information = mInformation[position];
        //return String.format("%s \nServes great: %s", doctor.getFirstName(), information);
        return doctor;
    }

    @Override
    public int getCount() {
        return mDoctors.size();
    }
}
