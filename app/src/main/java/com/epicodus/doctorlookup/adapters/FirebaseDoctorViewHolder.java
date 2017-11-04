package com.epicodus.doctorlookup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.doctorlookup.R;
import com.epicodus.doctorlookup.models.Doctor;
import com.epicodus.doctorlookup.util.ItemTouchHelperViewHolder;

/**
 * Created by spunek on 10/28/17.
 */

public class FirebaseDoctorViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

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
        //ImageView restaurantImageView = (ImageView) mView.findViewById(R.id.restaurantImageView);
        TextView titleTextView = (TextView) mView.findViewById(R.id.doctorTitleTextView);
        TextView firstNameTextView = (TextView) mView.findViewById(R.id.doctorFirstNameTextView);
        TextView lastNameTextView = (TextView) mView.findViewById(R.id.doctorLastNameTextView);

        TextView phoneTextView = (TextView) mView.findViewById(R.id.doctorPhoneTextView);
        TextView websiteTextView = (TextView) mView.findViewById(R.id.doctorWebsiteTextView);
        TextView mAcceptsPatients = (TextView) mView.findViewById(R.id.doctorAcceptsPatientsTextView);
        for(String website: doctor.getWebsites()) {

            websiteTextView.setText("Websites: " + website);

        }

        firstNameTextView.setText("First Name: "+ doctor.getFirstName());
        lastNameTextView.setText("Last Name: " + doctor.getLastName());
        titleTextView.setText("Title: " + doctor.getTitle());

        if(doctor.isAccepts_new_patients()){
            mAcceptsPatients.setText("Accepts new Patients: " + "Yes");
        }
        else {
            mAcceptsPatients.setText("Accepts new Patients: " + "No");
        }

        for(String key: doctor.getPhones().keySet()) {
            phoneTextView.setText("Phone " + key + ": " + doctor.getPhones().get(key));
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
//                    if(snapshot.getKey().equals(uid)) {
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

    @Override
    public void onItemSelected() {
       // Log.d("Animation", "onItemSelected");
        //  add animations
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);

//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
//                R.animator.drag_scale_on);
//        set.setTarget(itemView);
//        set.start();
    }

    @Override
    public void onItemClear() {
//        Log.d("Animation", "onItemClear");
//        //  add animations
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
//
//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
//                R.animator.drag_scale_off);
//        set.setTarget(itemView);
//        set.start();
    }



}