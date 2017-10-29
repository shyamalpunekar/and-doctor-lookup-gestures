package com.epicodus.doctorlookup.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.doctorlookup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference mSearchedNameReference;

    private ValueEventListener mSearchedNameReferenceListener;

    @Bind(R.id.savedDoctorsButton) Button mSavedDoctorsButton;

    @Bind(R.id.findDoctorsButton)
    Button mFindDoctorsButton;
//    @Bind(R.id.locationEditText)
//    EditText mLocationEditText;
    @Bind(R.id.appNameTextView)
    TextView mAppNameTextView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        mSearchedNameReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_SEARCHED_NAME);

//        mSavedDoctorsButton.setOnClickListener(this);

//        mSearchedNameReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot nameSnapshot : dataSnapshot.getChildren()) {
//                    String name = nameSnapshot.getValue().toString();
//                    Log.d("Names updated", "name: " + name); //log
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //display welcome message
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
            }
        };

        //ad font
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

       // mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
       // mEditor = mSharedPreferences.edit();

        mFindDoctorsButton.setOnClickListener(this);
        mSavedDoctorsButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    @Override
        public void onClick(View v) {

            if (v == mFindDoctorsButton) {
                Intent intent = new Intent(MainActivity.this, DoctorsActivity.class);
                startActivity(intent);
            }

               // String name = mLocationEditText.getText().toString();


               // saveNameToFirebase(name);


                if (v == mSavedDoctorsButton) {
                    Intent intent = new Intent(MainActivity.this, SavedDoctorListActivity.class);
                    startActivity(intent);
                }

//                if (name.equals("") || name == null ){
//
//                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
//                    dlgAlert.setMessage("Please enter valid Name");
//                    dlgAlert.create().show();
//                    return;
//
//                }
//                else {
//                    Intent intent = new Intent(MainActivity.this, DoctorsActivity.class);
//                    intent.putExtra("name", name);
//
//                    startActivity(intent);
//                }

            }


    public void saveNameToFirebase(String name) {
        mSearchedNameReference.push().setValue(name);
    }


    //destroy app when user quits the activity
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedNameReference.removeEventListener(mSearchedNameReferenceListener);
//    }

}

