package com.epicodus.doctorlookup.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.doctorlookup.ui.DoctorDetailActivity;
import com.epicodus.doctorlookup.ui.DoctorsActivity;
import com.epicodus.doctorlookup.R;
import com.epicodus.doctorlookup.models.Doctor;
import java.util.ArrayList;
import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by spuek on 10/20/17.
 */

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder> {
    private ArrayList<Doctor> mDoctors = new ArrayList<>();
    private Context mContext;

    public DoctorListAdapter(Context context, ArrayList<Doctor> Doctors) {
        mContext = context;
        mDoctors = Doctors;

    }

    @Override
    public DoctorListAdapter.DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_item, parent, false);
        DoctorViewHolder viewHolder = new DoctorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorListAdapter.DoctorViewHolder holder, int position) {
        holder.bindDoctor(mDoctors.get(position));
    }

    @Override
    public int getItemCount() {
        return mDoctors.size();
    }


    public class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        @Bind(R.id.restaurantImageView)
//        ImageView mRestaurantImageView;
        @Bind(R.id.doctorFirstNameTextView)
        TextView mFirstNameTextView;
        @Bind(R.id.doctorLastNameTextView)
        TextView mLastNameTextView;
        @Bind(R.id.doctorTitleTextView)
        TextView mTitleTextView;
        @Bind(R.id.doctorPhoneTextView)
        TextView mPhoneTextView;

        @Bind(R.id.doctorWebsiteTextView)
        TextView mWebsiteTextView;

        @Bind(R.id.doctorAcceptsPatientsTextView)
        TextView mAcceptsPatients;

        private Context mContext;

        public DoctorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }


        public void bindDoctor(Doctor doctor) {
            Log.d("click listener", "working!");
          //  Picasso.with(mContext).load(doctor.getImageUrl()).into(mDoctorImageView);
            mTitleTextView.setText("Title: " + doctor.getTitle());
            mFirstNameTextView.setText("First Name: " + doctor.getFirstName());
            mLastNameTextView.setText("Last Name: " +doctor.getLastName());
            mPhoneTextView.setText("Phones: " + doctor.getPhones().toString());
            mWebsiteTextView.setText("Websites:" + doctor.getWebsites().toString());
            if(doctor.isAccepts_new_patients()){
                mAcceptsPatients.setText("Accepts new Patients: " + "Yes");
            }
            else {
                mAcceptsPatients.setText("Accepts new Patients: " + "No");
            }
        }

        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, DoctorDetailActivity.class);

            intent.putExtra("position", String.valueOf(itemPosition));
            intent.putExtra("doctors", Parcels.wrap(mDoctors));
            mContext.startActivity(intent);

        }
    }

}
