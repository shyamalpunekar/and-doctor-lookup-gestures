package com.epicodus.doctorlookup.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.doctorlookup.Constants;
import com.epicodus.doctorlookup.R;
import com.epicodus.doctorlookup.adapters.FirebaseDoctorListAdapter;
import com.epicodus.doctorlookup.adapters.FirebaseDoctorViewHolder;
import com.epicodus.doctorlookup.models.Doctor;
import com.epicodus.doctorlookup.util.OnStartDragListener;
import com.epicodus.doctorlookup.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedDoctorListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mDoctorReference;
    private FirebaseDoctorListAdapter mFirebaseAdapter;
    //private FirebaseRecyclerAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doctors);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mDoctorReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_DOCTORS)
                .child(uid);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {

        mFirebaseAdapter = new FirebaseDoctorListAdapter(Doctor.class,
                R.layout.doctor_list_item_drag, FirebaseDoctorViewHolder.class,
                mDoctorReference, this, this);



            mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new

            LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}

