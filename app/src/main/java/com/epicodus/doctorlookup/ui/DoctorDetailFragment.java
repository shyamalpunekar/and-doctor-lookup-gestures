package com.epicodus.doctorlookup.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.doctorlookup.R;
import com.epicodus.doctorlookup.models.Doctor;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorDetailFragment extends Fragment {

//    @Bind(R.id.doctorImageView)
//    ImageView mImageLabel;
    @Bind(R.id.doctorNameTextView) TextView mNameLabel;
//    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveDoctorButton)
    TextView mSaveRestaurantButton;

    private Doctor mDoctor;


    public DoctorDetailFragment newInstance(Doctor doctor) {
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
        return inflater.inflate(R.layout.fragment_doctor_detail, container, false);
    }

}
