package com.epicodus.doctorlookup.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.doctorlookup.R;
import com.epicodus.doctorlookup.adapters.FirebaseDoctorListAdapter;
import com.google.firebase.database.DatabaseReference;

import butterknife.Bind;

public class SavedDoctorListActivity extends AppCompatActivity {
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



    }

}

