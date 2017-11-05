package com.epicodus.doctorlookup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.doctorlookup.R;
import com.epicodus.doctorlookup.models.Doctor;
import com.squareup.picasso.Picasso;

/**
 * Created by spunek on 10/28/17.
 */

public class FirebaseDoctorViewHolder extends RecyclerView.ViewHolder {

    public ImageView mDoctorImageView;

    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseDoctorViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        //itemView.setOnClickListener(this);
    }

    public void bindDoctor(Doctor doctor) {
        mDoctorImageView = (ImageView) mView.findViewById(R.id.doctorImageView);

        TextView titleTextView = (TextView) mView.findViewById(R.id.doctorTitleTextView);
        TextView firstNameTextView = (TextView) mView.findViewById(R.id.doctorFirstNameTextView);
        TextView lastNameTextView = (TextView) mView.findViewById(R.id.doctorLastNameTextView);

        TextView phoneTextView = (TextView) mView.findViewById(R.id.doctorPhoneTextView);
        TextView websiteTextView = (TextView) mView.findViewById(R.id.doctorWebsiteTextView);
        TextView mAcceptsPatients = (TextView) mView.findViewById(R.id.doctorAcceptsPatientsTextView);


        Picasso.with(mContext)
                .load(doctor.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mDoctorImageView);

        firstNameTextView.setText("First Name: " + doctor.getFirstName());
        lastNameTextView.setText("Last Name: " + doctor.getLastName());
        titleTextView.setText("Title: " + doctor.getTitle());

        if (doctor.isAccepts_new_patients()) {
            mAcceptsPatients.setText("Accepts new Patients: " + "Yes");
        } else {
            mAcceptsPatients.setText("Accepts new Patients: " + "No");
        }
        for (String key : doctor.getPhones().keySet()) {
            if (!phoneTextView.getText().toString().contains(key))
                phoneTextView.append(key + ": " + doctor.getPhones().get(key) + "\n");
        }
        for (String website : doctor.getWebsites()) {
            if (websiteTextView.getText().toString().contains("No Website Available")) {
                websiteTextView.setText("");
            } else if (!websiteTextView.getText().toString().contains(website)) {
                websiteTextView.append("Websites: " + website + "\n");
            }
        }

    }



//    @Override
//    public void onClick(View view) {
//        final ArrayList<Doctor> doctors = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DOCTORS);
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            String uid = user.getUid();
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Iterator<DataSnapshot> iterator = snapshot.getChildren().iterator();
//                    if (snapshot.getKey().equals(uid)) {
//                        while (iterator.hasNext())
//                            doctors.add(iterator.next().getValue(Doctor.class));
//
//                    }
//                }
//
//                int itemPosition = getLayoutPosition();
//
//                Intent intent = new Intent(mContext, DoctorDetailActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("doctors", Parcels.wrap(doctors));
//
//                mContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }


}




