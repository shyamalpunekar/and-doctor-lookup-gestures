package com.epicodus.doctorlookup;


import android.content.Context;
import android.widget.ArrayAdapter;

public class MyDoctorsArrayAdapter extends ArrayAdapter{

    private Context mContext;
    private String[] mDoctors;
    private String[] mInformation;

    public MyDoctorsArrayAdapter(Context mContext, int resource, String[] mDoctors, String[] mInformation) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mDoctors = mDoctors;
        this.mInformation = mInformation;
    }

    @Override
    public Object getItem(int position) {
        String doctor = mDoctors[position];
        String information = mInformation[position];
        return String.format("%s \nServes great: %s", doctor, information);
    }

    @Override
    public int getCount() {
        return mDoctors.length;
    }
}
