package com.epicodus.doctorlookup.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.doctorlookup.R;
import com.epicodus.doctorlookup.adapters.DoctorListAdapter;
import com.epicodus.doctorlookup.models.Doctor;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorDetailFragment extends Fragment implements View.OnClickListener {

//    @Bind(R.id.doctorImageView)
//    ImageView mImageLabel;
    @Bind(R.id.doctorFirstNameTextView)
    TextView mNameLabel;

    @Bind(R.id.doctorLastNameTextView)
    TextView mLastName;

    @Bind(R.id.doctorTitleTextView)
    TextView mTitle;
//    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
//    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView)
    TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView)
    TextView mPhoneLabel;
    @Bind(R.id.addressTextView)
    TextView mAddressLabel;
    @Bind(R.id.saveDoctorButton)
    TextView mSaveRestaurantButton;
    @Bind(R.id.doctorAcceptsPatientsTextView)
    TextView mAcceptsPatients;

    private Doctor mDoctor;
    private RecyclerView.Adapter mAdapter;


    public static DoctorDetailFragment newInstance(Doctor doctor) {
        // Required empty public constructor
        DoctorDetailFragment doctorDetailFragment = new DoctorDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("doctor", Parcels.wrap(doctor));
        doctorDetailFragment.setArguments(args);
        return doctorDetailFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDoctor = Parcels.unwrap(getArguments().getParcelable("doctor"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_detail, container, false);
        ButterKnife.bind(this, view);
        ArrayList<Doctor> doctors = new ArrayList<>();

        doctors.add(mDoctor);
    //   Picasso.with(view.getContext()).load(mDoctor.getImageUrl()).into(mImageLabel);

        mTitle.setText("Title: " + mDoctor.getTitle());
        mNameLabel.setText("First Name: " + mDoctor.getFirstName());
        mLastName.setText("Last Name: " + mDoctor.getLastName());


        if(mDoctor.isAccepts_new_patients()){
            mAcceptsPatients.setText("Accepts new Patients: " + "Yes");
        }
        else {
            mAcceptsPatients.setText("Accepts new Patients: " + "No");
        }

        mAdapter = new DoctorListAdapter(getActivity().getApplicationContext(),doctors);

        mPhoneLabel.setText("Phones: " + mDoctor.getPhones().toString());
        mWebsiteLabel.setText("Websites: " + mDoctor.getWebsites());
        mAddressLabel.setText("Address: " + mDoctor.getPractices().get(0).getVisitAddress().toString());

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mDoctor.getPhones()));
            startActivity(phoneIntent);
        }
    }

}
