package com.epicodus.doctorlookup.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.doctorlookup.Constants;
import com.epicodus.doctorlookup.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference mSearchedNameReference;

    private ValueEventListener mSearchedNameReferenceListener;

    @Bind(R.id.savedDoctorsButton) Button mSavedDoctorsButton;

    @Bind(R.id.findDoctorsButton)
    Button mFindDoctorsButton;
    @Bind(R.id.locationEditText)
    EditText mLocationEditText;
    @Bind(R.id.appNameTextView)
    TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedNameReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_NAME);

//        mSavedDoctorsButton.setOnClickListener(this);

        mSearchedNameReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot nameSnapshot : dataSnapshot.getChildren()) {
                    String name = nameSnapshot.getValue().toString();
                    Log.d("Names updated", "name: " + name); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //ad font
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

       // mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
       // mEditor = mSharedPreferences.edit();

        mFindDoctorsButton.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {

            if (v == mFindDoctorsButton) {

                String name = mLocationEditText.getText().toString();

                //
                saveNameToFirebase(name);


                if (v == mSavedDoctorsButton) {
                    Intent intent = new Intent(MainActivity.this, SavedDoctorListActivity.class);
                    startActivity(intent);
                }

                if (name.equals("") || name == null ){

                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("Please enter valid Name");
                    dlgAlert.create().show();
                    return;

                }
                else {
                    Intent intent = new Intent(MainActivity.this, DoctorsActivity.class);
                    intent.putExtra("name", name);

                    startActivity(intent);
                }

            }
        }

    public void saveNameToFirebase(String name) {
        mSearchedNameReference.push().setValue(name);
    }


    //destroy app when user quits the activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedNameReference.removeEventListener(mSearchedNameReferenceListener);
    }

}

